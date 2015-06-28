angular.module('resource.invoice', ['ngResource'])
	.factory('Invoice', function ($resource) {
	return $resource('http://localhost:8080/Firma/api/partneri/13/fakture/:invoiceId',null, {
        //'update': { method:'PUT' }
		 'create': {method: "POST"}, 'update': {method: "PUT"}
    });
	/*
	$scope.dispositionsResource = $resource(dispositionUrl+":id", { id: "@id" },
       { 'create': {method: "POST"}, 'update': {method: "PUT"}
       });
	*/
})