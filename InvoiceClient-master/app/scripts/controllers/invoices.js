'use strict';

 angular.module('invoices', ['resource.invoice',
 	'angular-md5'])

 .controller('invoicesListCtrl', function (Invoice, $scope, $location, md5, $log) {
 	//postavljanje niza invoices u kodu
 	$scope.invoices = [
 	{
 		"id": 1,
 		"deleted": false,
 		"version": 0,
 		"suplierName": "Majstor za pivo",
 		"suplierAddress": "Zorza Klemansoa 18",
 		"supplierPib": "1232233",
 		"buyerName": "Home Brew INC",
 		"buyerAddress": "Skojevska 12",
 		"acountNumber": 1226458,
 		"date": "2015-11-11",
 		"totalGoodsValue": 900.0,
 		"totalServiceValue": 0.0,
 		"totalValue": 5000.0,
 		"totalRabate": 0.0,
 		"totalTax": 20.0,
 		"currency": "rsd",
 		"totalAmount": 5000.0,
 		"currencyDate": "2015-11-11",
 		"invoiceItems": [
 		{
 			"id": 1,
 			"deleted": false,
 			"version": 0,
 			"orderNumber": 1,
 			"goodsName": "Hmelj",
 			"quantity": 3.0,
 			"measureUnit": "kilogram",
 			"pricePerUnit": 1000.0,
 			"amount": 3.0,
 			"rabatePercentage": 0.0,
 			"rabateAmount": 0.0,
 			"minusRabat": 0.0,
 			"totalTax": 20.0
 		},
 		{
 			"id": 2,
 			"deleted": false,
 			"version": 0,
 			"orderNumber": 2,
 			"goodsName": "Jecam",
 			"quantity": 2.0,
 			"measureUnit": "kilogram",
 			"pricePerUnit": 1000.0,
 			"amount": 2.0,
 			"rabatePercentage": 0.0,
 			"rabateAmount": 0.0,
 			"minusRabat": 0.0,
 			"totalTax": 20.0
 		}
 		]
 	}
	,
	{
 		"id": 1,
 		"deleted": false,
 		"version": 0,
 		"suplierName": "Majstor za kese",
 		"suplierAddress": "Zorza Klemansoa 18",
 		"supplierPib": "1232233",
 		"buyerName": "Home Brew INC",
 		"buyerAddress": "Skojevska 12",
 		"acountNumber": 1226458,
 		"date": "2015-11-01",
 		"totalGoodsValue": 1000.0,
 		"totalServiceValue": 0.0,
 		"totalValue": 1000.0,
 		"totalRabate": 0.0,
 		"totalTax": 20.0,
 		"currency": "rsd",
 		"totalAmount": 5000.0,
 		"currencyDate": "2015-10-11",
 		"invoiceItems": [
 		
 		]
 	}
	,
	{
 		"id": 1,
 		"deleted": false,
 		"version": 0,
 		"suplierName": "Majstor za Sve",
 		"suplierAddress": "Zorza Klemansoa 18",
 		"supplierPib": "1232233",
 		"buyerName": "Home Brew INC",
 		"buyerAddress": "Skojevska 12",
 		"acountNumber": 1226458,
 		"date": "2013-11-11",
 		"totalGoodsValue": 7000.0,
 		"totalServiceValue": 0.0,
 		"totalValue": 7000.0,
 		"totalRabate": 0.0,
 		"totalTax": 20.0,
 		"currency": "rsd",
 		"totalAmount": 5000.0,
 		"currencyDate": "2016-11-11",
 		"invoiceItems": [
 		
 		]
 	}
	
 	];
 	//preuzimanje niza faktura sa servera
	
 	Invoice.query().$promise.then(function (data) {
 		$scope.invoices = data;
		console.log($scope.invoices);
		data.forEach(function(entry) {
			var invoice = {};
			var zaglavlje = entry.zaglavlje;
			invoice.id = entry.id;
			invoice.suplierName = zaglavlje.nazivDobavljaca;
			invoice.suplierAddress = zaglavlje.adresaDobavljaca;
			invoice.supplierPib = zaglavlje.pibdobavljaca;
			invoice.buyerName = zaglavlje.nazivKupca;
			invoice.buyerAddress = zaglavlje.adresaKupca;
			invoice.buyerPib = zaglavlje.pibkupca;
			invoice.acountNumber= zaglavlje.BrojRacuna;
			invoice.date= zaglavlje.datumRacuna;
			invoice.totalGoodsValue= zaglavlje.vrednostRobe;
			invoice.totalServiceValue= zaglavlje.vrednostUsluga;
			invoice.totalValue= zaglavlje.ukupnoRobaIUsluga;
			invoice.totalRabate= zaglavlje.ukupanRabat;
			invoice.totalTax= zaglavlje.ukupanPorez;
			invoice.currency= zaglavlje.oznakaValute;
			invoice.totalAmount= zaglavlje.iznosZaUplatu;
			invoice.paymentAccount = zaglavlje.uplataNaRacun;
			invoice.currencyDate = zaglavlje.datumValute;
			invoice.invoiceItems = [];
			$scope.invoices.push(invoice);
		});
 	}, function (error) {
 		console.log(error);
 	});

	///$scope.invoices = Invoice.query();
	$log.info($scope.invoices.length);//0
	//kada smo kliknuli na red u tabeli prelazimo na stranicu za editovanje fakture sa zadatim id-om
 	$scope.insertOrEditInvoice = function (invoice) {
 		if(invoice){
 			$location.path('/invoice/'+invoice.id);
 		}
 		else{
			$location.path('/invoice/new');
 		}
 	}
	
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
	  
	$scope.datecomparators = [{
		id: '=',
		label: 'isti dan'
	  }, {
		id: '<',
		label: 'pre'
	  }, 
	  {
		id: '>',
		label: 'posle'
	  }];  

	$scope.comparatorCurrencyDateSelect = $scope.datecomparators[0];
	$scope.comparatorDateSelect = $scope.datecomparators[0];
	$scope.comparatorAmountSelect = $scope.comparators[0];
	
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
	
	$scope.datecompare = function(item, prop, val, comparator){
		//var dateReg = /^\d{2}([./-])\d{2}\1\d{4}$/;
		if(val==null)
			return true;
		//if ((String(val)).match(dateReg))
		//{
			//var valdate = new Date(val);
			var itemdate = new Date(item[prop]);
			itemdate.setHours(0,0,0,0);
			val.setHours(0,0,0,0);
		//Unary plus za konverziju u milisekunde
			if (comparator=='=')
				return +itemdate == +val;
			if (comparator=="<")
				return itemdate < val;
			if (comparator==">")
				return itemdate > val;
		//}
		//else
			//return true;
    };
	
	$scope.sortBy = null;
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
	
	$scope.showInvoice = function (invoice) {
 		if(invoice){
 			$location.path('/showInvoice/'+invoice.id);
 		}
 	}
	
	//funkcija koja otvara datepicker
	$scope.openDatepicker = function($event, opened) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope[opened] = true;
	};
	
	$scope.showItems = function (invoice) {
 		if(invoice){
 			$location.path('/showItems/'+invoice.id);
 		}
 	}
	
 });
