'use strict';
/**
 * @author: sercan
 */

angular.module('app').controller('BooksController', function BooksController($scope, $http, CommonService) {

    $scope.book_categories = ["Roman", "Teknik", "Hikaye"];
    $scope.selected_book_category = {};
    $scope.search_criterias = [{id:1, name:"Name"},{id:2, name:"Category"}, {id:3, name:"ISBN"}]
    $scope.isSearchQuery = false;


    $scope.pageToGet = -1;
    $scope.state = 'busy';
    $scope.lastAction = '';
    $scope.url = "/sc/books";
    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displayCreateBookButton = false;
    $scope.showloading = false;

    $scope.book = {}

    $scope.getBookList = function () {
        $scope.isSearchQuery = false;
        var url = $scope.url;
        $scope.lastAction = 'list';
        $scope.startDialogAjaxRequest();

        if($scope.pageToGet === -1)
            $scope.pageToGet = 0;

        var config = {params: {page: $scope.pageToGet}};
        $http.get(url, config)
            .success(function (data) {
                console.log(data);
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateBookButton = false;
            });
    }


    $scope.search = function () {
        $scope.isSearchQuery = true;
        var url = "/sc/books/search";
        $scope.lastAction = 'list';
        $scope.startDialogAjaxRequest();

        if($scope.pageToGet === -1)
            $scope.pageToGet = 0;

        var data = {cat:$scope.selected_search_criteria, page:$scope.pageToGet, term:$scope.search_string};

        $http.post(url, data)
            .success(function (response) {
                $scope.finishAjaxCallOnSuccess(response.data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateBookButton = false;
            });
    }

    $scope.populateTable = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            $scope.page = {source: data.bookList, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, totalBooks : data.totalBooks};

            var currentpage = $scope.pageToGet;

            if($scope.page.pagesCount <= $scope.page.currentPage){
                $scope.pageToGet = $scope.page.pagesCount - 1;
                $scope.page.currentPage = $scope.page.pagesCount - 1;
            }
            $scope.displayCreateBookButton = true;


        } else {
            $scope.state = 'noresult';
            $scope.displayCreateBookButton = true;
        }

        $scope.displayMessageToUser = false;

    }

    $scope.changePage = function (page) {
        $scope.pageToGet = page;
        if($scope.isSearchQuery){
            $scope.search();
        }else{
            $scope.getBookList();
        }



    };

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');

        $scope.book = {};
        $scope.errorOnSubmit = false;
        $scope.errorIllegalAccess = false;
        $scope.displayValidationError = false;
    }

    $scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {

        $scope.populateTable(data);
        $("#loadingModal").modal('hide');
        if(!isPagination){
            if(modalId){
                $scope.exit(modalId);
            }
        }

        $scope.lastAction = '';
    }

    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    }

    $scope.handleErrorInDialogs = function (status) {
        $("#loadingModal").modal('hide');
        $scope.state = $scope.previousState;

        // illegal access
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
        $scope.lastAction = '';
    }



    $scope.resetBook = function(){
        $scope.book = {};
        $scope.loadCaptcha();
    };

    $scope.createBook = function (newBookForm) {


        if (!newBookForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        //captcha is cleaning
        $scope.captchaOrg="";
        $scope.captchaAgain="";
        $scope.lastAction = 'create';

        var url = $scope.url;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

        $scope.startDialogAjaxRequest();


        $http.post(url, $.param($scope.book), config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#addBooksModal", false);
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.selectedBook = function (book) {
        var selectedBook = angular.copy(book);
        $scope.book = selectedBook;
    }

    $scope.updateBook = function (updateBookForm) {
        if (!updateBookForm.$valid) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'update';

        var url = $scope.url + "/u/" + $scope.book.id;


        $scope.startDialogAjaxRequest();

        var config = {headers: {'Content-Type': 'application/json; charset=UTF-8'}};
        $http.post(url, $scope.book, config)
            .success(function (data) {
                CommonService.loadBookList($scope.pageToGet).then(function(response){
                    $scope.finishAjaxCallOnSuccess(response.data, "#updateBooksModal", false);
                });
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };


    $scope.deleteBook = function () {
        $scope.lastAction = 'delete';

        var url = $scope.url + "/" + $scope.book.id;

        $scope.startDialogAjaxRequest();

        var params = {page: $scope.pageToGet};

        $http({
            method: 'DELETE',
            url: url,
            params: params
        }).success(function (data) {
            $scope.resetBook();
            $scope.finishAjaxCallOnSuccess(data, "#deleteBooksModal", false);
        }).error(function(data, status, headers, config) {
            $scope.handleErrorInDialogs(status);
        });
    };

    $scope.captchaOrg = "";
    $scope.captchaAgain = "";
    $scope.words = ["Ankara","Istanbul","Izmir","Bursa"];
    $scope.loadCaptcha = function(){
        var tmpNum = Math.floor((Math.random() * 3));
        $scope.captchaOrg = $scope.words[tmpNum];

    }




    $scope.getBookList();
});

