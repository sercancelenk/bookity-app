'use strict';

angular.module('app')
    .controller('registerController', function registerController($scope, $location, CommonService, $timeout) {
        $scope.registerformdata = {};
        $scope.test = "sercan";
        $scope.sendregister = function(){
            $timeout(function(){
                CommonService.registerAction($scope.registerformdata).then(function(response){
                    $scope.registerformdata = {};
                    $scope.message = response.data.error;
                }, function(error){});
            }, 1);
        }

    });


