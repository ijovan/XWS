angular.module('resource.invoice', ['ngResource'])
	.factory('Invoice', function ($resource) {
	return $resource('http://localhost:8080/Firma/api/partneri/13/fakture/:invoiceId',null, {
        'update': { method:'PUT' }
    });
})