'use strict';

angular.module('invoice', [
	'resource.invoice',
	'ui.bootstrap',
	'invoiceItem',
	'resource.invoiceItem'])

.controller('invoiceCtrl', function (Invoice, $scope, $routeParams, $modal, $log, $location, InvoiceItem) {
	//ako pozivamo edit postojece fakture
	if($routeParams.invoiceId!='new'){
		//preuzimanje parametra iz URL
		var invoiceId = $routeParams.invoiceId;
		
		//preuzimanje fakure sa servera. Posto smo u Invoice factory rutu definisali kao '...invoice/:invoiceId' invoiceId ce se proslediti kao parametar rute na server 
		Invoice.get({'invoiceId':invoiceId}).$promise.then(function (data) {
			/*
			console.log(data);
			console.log(data["ns2.Faktura"]["ns2.Zaglavlje"]);
			var zaglavlje = data["ns2.Faktura"]["ns2.Zaglavlje"];
			$scope.invoice={};
			$scope.invoice.suplierName = zaglavlje["ns2.Naziv_dobavljaca"];
			$scope.invoice.suplierAddress = zaglavlje["ns2.Adresa_dobavljaca"];
			$scope.invoice.supplierPib = zaglavlje["ns2.PIB_dobavljaca"];
			$scope.invoice.buyerName = zaglavlje["ns2.Naziv_kupca"];
			$scope.invoice.buyerAddress = zaglavlje["ns2.Adresa_kupca"];
			$scope.invoice.buyerPib = zaglavlje["ns2.PIB_kupca"];
			$scope.invoice.acountNumber= zaglavlje["ns2.Broj_racuna"];
			$scope.invoice.date= zaglavlje["ns2.Datum_racuna"];
			$scope.invoice.totalGoodsValue= zaglavlje["ns2.Vrednost_robe"];
			$scope.invoice.totalServiceValue= zaglavlje["ns2.Vrednost_usluga"];
			$scope.invoice.totalValue= zaglavlje["ns2.Ukupno_roba_i_usluga"];
			$scope.invoice.totalRabate= zaglavlje["ns2.Ukupan_rabat"];
			$scope.invoice.totalTax= zaglavlje["ns2.Ukupan_porez"];
			$scope.invoice.currency= zaglavlje["ns2.Oznaka_valute"];
			$scope.invoice.totalAmount= zaglavlje["ns2.Iznos_za_uplatu"];
			$scope.invoice.paymentAccount = zaglavlje["ns2.Uplata_na_racun"];
			$scope.invoice.currencyDate = zaglavlje["ns2.Datum_valute"];
			*/
			var zaglavlje = data.zaglavlje;
			$scope.invoice={};
			$scope.invoice.id = zaglavlje.idporuke;
			$scope.invoice.suplierName = zaglavlje.nazivDobavljaca;
			$scope.invoice.suplierAddress = zaglavlje.adresaDobavljaca;
			$scope.invoice.supplierPib = zaglavlje.pibdobavljaca;
			$scope.invoice.buyerName = zaglavlje.nazivKupca;
			$scope.invoice.buyerAddress = zaglavlje.adresaKupca;
			$scope.invoice.buyerPib = zaglavlje.pibkupca;
			$scope.invoice.acountNumber= zaglavlje.BrojRacuna;
			$scope.invoice.date= zaglavlje.datumRacuna;
			$scope.invoice.totalGoodsValue= zaglavlje.vrednostRobe;
			$scope.invoice.totalServiceValue= zaglavlje.vrednostUsluga;
			$scope.invoice.totalValue= zaglavlje.ukupnoRobaIUsluga;
			$scope.invoice.totalRabate= zaglavlje.ukupanRabat;
			$scope.invoice.totalTax= zaglavlje.ukupanPorez;
			$scope.invoice.currency= zaglavlje.oznakaValute;
			$scope.invoice.totalAmount= zaglavlje.iznosZaUplatu;
			$scope.invoice.paymentAccount = zaglavlje.uplataNaRacun;
			$scope.invoice.currencyDate = zaglavlje.datumValute;
			$scope.invoice.invoiceItems = [];
			var stavke = data.stavkaFakture;
			console.log(stavke);
			stavke.forEach(function(entry) {
				var item = {};
				console.log(entry);
				item.rbr = entry.redniBroj;
				item.goodsName = entry.nazivRobeIliUsluge;
				item.quantity = entry.kolicina;
				item.measureUnit = entry.jedinicaMere;
				item.pricePerUnit = entry.jedinicnaCena;
				item.amount = entry.vrednost;
				item.rabatePercentage = entry.procenatRabata;
				item.rabateAmount = entry.iznosRabata;
				item.minusRabat = entry.umanjenoZaRabat;
				item.totalTax = entry.ukupanPorez;
				item.valid = entry.valid;
				if (item.valid)
					$scope.invoice.invoiceItems.push(item);
			});
			
		});
	}
	//ako kreiramo novu fakutru
	else{
		$scope.invoice = new Invoice();
		$scope.invoice.invoiceItems = [];
	}
	//funkcija koja otvara datepicker
	$scope.openDatepicker = function($event, opened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[opened] = true;
	};

	//modalni dijalog za stavku fakutre
	$scope.openModal = function (invoiceItem, size) {

		var modalInstance = $modal.open({
			templateUrl: 'views/invoice-item.html',
			controller: 'invoiceItemCtrl',
			size: size,
			resolve: {
				invoiceItem: function () {
					return invoiceItem;
				}
			}
		});
		modalInstance.result.then(function (data) {
			var invoiceItem = data.invoiceItem;
			//ako stavka fakture nema id i ako je akcija 'save' znaci da je nova i dodaje se u listu. ako ima, svakako se manja u listi
			if(!invoiceItem.id && data.action==='save'){
				$scope.invoice.invoiceItems.push(invoiceItem);				
			}
			//ako stavka treba da se obrise izbaci se iz niza
			if(data.action==='delete'){
				var index = $scope.invoice.invoiceItems.indexOf(invoiceItem);
				$scope.invoice.invoiceItems.splice(index, 1);
				//ako je stavka imala i id, treba da se obrise i na serveru (da li je to dobro?)
				if(invoiceItem.id){
					InvoiceItem.delete({invoiceItemId:invoiceItem.id});
				}
			}
		}, function () {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
	$scope.comparators = [{
		id: '=',
		label: '='
	  }, {
		id: '<',
		label: '<'
	  }, 
	  {
		id: '>',
		label: '>'
	  }];
	  


	$scope.comparatorQuantitySelect = $scope.comparators[0];
	$scope.comparatorMinusRabatSelect = $scope.comparators[0];
		
	$scope.compare = function(item, prop, val, comparator){
		if(val==null || val.trim()=='')
			return true;
		//Unary plus za konverziju u broj
			if (comparator=='=')
				return +item[prop] == +val;
			if (comparator=="<")
				return +item[prop] < +val;
			if (comparator==">")
				return +item[prop] > +val;
    };

	//cuvanje izmena
	$scope.save = function () {
		
		
		console.log($scope.invoice.date);
		
		if($scope.invoice.id){
			//zbog cega redirekcija ide na callback?
			$scope.invoice.$update({invoiceId:$scope.invoice.id},function () {
				$location.path('/invoiceList');
			});
		}
		else{
			$scope.invoice.$save(function () {
				$location.path('/invoiceList');
			});
		}
		$log.info("save");
		
	}
	
	$scope.showItems = function (invoice) {
 		$location.path('/showItems/'+invoice.id);
 	}
	
	$scope.showInvoice = function (invoice) {
 		if(invoice){
 			$location.path('/showInvoice/'+invoice.id);
 		}
 	}
	
	$scope.sortBy = 'rbr';
	$scope.sortElementId = null;
	
	$scope.toggleSortInvoices = function(prop, element) 
	{
		var plusVersion= '+'+prop;
		if ($scope.sortBy!=plusVersion)
		{
			if($scope.sortElementId!=null)
				document.getElementById($scope.sortElementId).className = "fa fa-fw fa-sort";
			$scope.sortBy = plusVersion;
			document.getElementById(element).className = "fa fa-fw fa-sort-asc";
		}
		else
		{
			if($scope.sortElementId!=null)
				document.getElementById($scope.sortElementId).className = "fa fa-fw fa-sort";
			$scope.sortBy = '-'+prop;
			document.getElementById(element).className = "fa fa-fw fa-sort-desc";
		}
		$scope.sortElementId = element;
	}

	$scope.delete = function () {
		if($scope.invoice.id){
			$scope.invoice.$delete({invoiceId:$scope.invoice.id}, function () {
				$location.path('invoiceList');
			});
		}
	}	
});
