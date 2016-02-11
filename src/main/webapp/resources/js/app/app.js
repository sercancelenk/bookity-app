'use strict';
var app = angular.module('app',[]);

app.directive(
    'bsDropdown2',
    function($compile) {
        return {
            restrict : 'E',
            scope : {
                items : '=dropdownData',
                doSelect : '&selectVal',
                selectedItem : '=preselectedItem',
                callBack : '&',
                emptyVal : '=emptyVal',
                css:'=css'
            },
            link : function(scope, element, attrs) {
                var html = '';
                var ddClass = "btn button-label btn-warning btn-xs";

                if(attrs.css === null && attrs.css=== undefined && attrs.css !== ""){
                    ddClass = attrs.css;
                }
                switch (attrs.menuType) {
                    case "button":
                        html += '<div class="btn-group"><button class="btn btn-info btn-small dropdown-toggle mm" style="border-top-left-radius:5px !important;border-bottom-left-radius:5px !important;">'
                            + '<small>' + attrs.emptyval + '</small>'
                            + '</button><button class="btn btn-info btn-small dropdown-toggle" style="border-top-right-radius:5px !important;border-bottom-right-radius:5px !important;" data-toggle="dropdown"><span class="caret"></span></button>';
                        break;
                    case "dropdown":
                        html += '<div class="dropdown">'
                            + '<button class="btn  bg-cyan btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">' + attrs.emptyval + '<span class="caret"></span></button>'
                            + '';
                        break;
                    default:
                        html += '<div class="dropdown"><a class="dropdown-toggle" role="button" data-toggle="dropdown"  href="javascript:;">Dropdown<b class="caret"></b></a>';
                        break;
                }
                html += '<ul class="dropdown-menu" style="overflow-y: auto; overflow-x: auto; max-height: 250px;"><li ng-repeat="item in items"><a tabindex="-1" data-ng-click="selectVal(item)"><span>{{item}}</span></a></li></ul></div>';
                element.append($compile(html)(scope));

                for (var i = 0; i < scope.items.length; i++) {
                    if (scope.items[i] === scope.selectedItem) {
                        scope.bSelectedItem = scope.items[i];
                        break;
                    }
                }

                scope.selectVal = function(item) {
                    switch (attrs.menuType) {
                        case "button":
                            $('button.mm', element).html(
                                '<small>' + item + '</small>');
                            break;
                        default:
                            $('a.dropdown-toggle', element).html(
                                '<b class="caret"></b> '
                                + item);
                            break;
                    }
                    scope.doSelect({
                        selectedVal : item
                    });
                    scope.callBack(item);
                };
                if (scope.bSelectedItem != null) {
                    scope.selectVal(scope.bSelectedItem);
                }else {
                    //var b = {
                    //    id : -1,
                    //    name : attrs.emptyval
                    //};
                    //scope.selectVal(b);
                }
            }
        };
    });

angular.module('app').directive('ngEnter', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if (event.which === 13) {
                scope.$apply(function() {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});

app.directive('numbersonly', function() {
    return {
        require : 'ngModel',
        link : function(scope, element, attrs, modelCtrl) {
            modelCtrl.$parsers.push(function(inputValue) {
                // this next if is necessary for when using ng-required on your
                // input.
                // In such cases, when a letter is typed first, this parser will
                // be called
                // again, and the 2nd time, the value will be undefined
                if (inputValue == undefined)
                    return '';
                var transformedInput = inputValue.replace(/[^0-9]/g, '');
                if (transformedInput != inputValue) {
                    modelCtrl.$setViewValue(transformedInput);
                    modelCtrl.$render();
                }

                return transformedInput;
            });
        }
    };
});