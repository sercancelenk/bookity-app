(function () {
'use strict';
angular
    .module('app')
    .factory('CommonService', CommonService);

CommonService.$inject = ['$http'];
function CommonService($http) {
    var service = {};

    service.registerAction = registerAction;
    service.loadBookList = loadBookList;

    return service;

    function registerAction(data) {
        var url = "/register";
        var data = data;
        return $http.post(url, data).then(handleSuccess, handleError('Error on service!'));
    }

    function loadBookList(page){
        var url = "/sc/books";
        var config = {params: {page: page}};
        return $http.get(url, config).then(handleSuccess, handleError('Error on service!'));
    }

    // private functions

    function handleSuccess(data) {
        return data;
    }

    function handleError(error) {
        return function () {
            return {success: false, message: error};
        };
    }
}
})();

