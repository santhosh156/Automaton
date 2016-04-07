var app = angular.module('myApp', []);
app.controller('soapController', function($scope, $location) {
	alert("Hello");
	soapStep step = new soapStep($scope.endpoint, $scope.xmlRequest);
	step.print();
});

