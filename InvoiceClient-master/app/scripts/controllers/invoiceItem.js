'use strict';

angular.module('invoiceItem', [])

.controller('invoiceItemCtrl', function ($scope, $modalInstance, invoiceItem) {
	if(invoiceItem){
		$scope.invoiceItem = invoiceItem;
	}
	else{
		$scope.invoiceItem = {};	
	}
	$scope.ok = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'save'});
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};

	$scope.delete = function () {
		$modalInstance.close({'invoiceItem':$scope.invoiceItem,
								'action':'delete'});
	};
	$scope.compare = function(item, prop, val, comparator){
		console.log("u compare u invoiice item");
		return function(item){
			if (comparator=="=")
				return item[prop] == val;
			if (comparator=="<")
				return item[prop] < val;
			if (comparator==">")
				return item[prop] > val;
		}
    };
	
});
