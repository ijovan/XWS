angular.module('resource.invoiceItem', ['ngResource'])
	.factory('InvoiceItem', function ($resource) {
	return $resource('http://localhost:8080/Firma/api/partneri/invoiceItem/:invoiceItemId');
})