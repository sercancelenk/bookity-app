'use strict';

angular.module('app').controller('loginController', function loginController($scope, $location) {
    var url = "" + $location.$$absUrl;
    console.log("******** " + url);
    $scope.displayLoginError = (url.indexOf("error") >= 0);
    console.log("******** " + $scope.displayLoginError);
});


