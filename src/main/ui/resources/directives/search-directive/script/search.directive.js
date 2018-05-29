(function () {
    'use strict';
    angular
        .module('claims')
        .directive('mySearchBox', function () {
            return {
                restrict: 'E',
                link: function (scope, element, attrs) {
                    element.css({ 'width': '100%' });
                },
                templateUrl: 'resources/directives/search-directive/view/search.directive.html',
                scope: {
                    searchText: '=',
                    isSearching: '=',
                    searchInfo: '=',
                    dropDown: '=',
                    searchAuto: '=',
                    callback: '&',
                    btnsWidth : '@',
                    moduleName : '@'
                },
                controller: function ($scope, $q, AutocompleteService, $timeout) {

                    $scope.autoSearch = $scope.searchAuto;
                    $scope.paymentWay = "";
                    $scope.search = {};

                    $scope.clearSearch = function () {
                        $scope.search = {};
                        $scope.callback({ 'data': null });
                    };

                    $scope.doSearch = function () {
                        var searchText = $scope.search;
                        $scope.callback({ 'data': searchText });
                    };

                    $scope.getAutoCompleteList = function (searchText, field, info) {
                        if(!searchText || searchText.length < 3) {
                            $scope[field] = [];
                            return[];
                        }
                        var searchParams = constructSearchparams(field, searchText);
                        return AutocompleteService[info.methodName](searchParams).$promise.then(function(resp) {
                            return resp.rowData;
                        })
                    }

                    function constructSearchparams(field, searchText) {
                        var searchObj = {}
                        if($scope.moduleName == 'registration') {
                            searchObj["compId"] = "0021",
                            searchObj["policyNumber"] = field == 'policyNumber' ? searchText+"%" : "%",
                            searchObj["memberNumber"] = "%",
                            searchObj["memberName"] = field == 'memberName' ? searchText+"%" : "%",
                            searchObj["voucherNumber"] = field == 'voucherNumber' ? searchText+"%" : "%",
                            searchObj["cardNumber"] = field == 'cardNumber' ? searchText+"%" : "%",
                            searchObj["emiratesId"] = field == 'emiratesId' ? searchText+"%" : "%"
                        }

                        if($scope.moduleName == 'registration-general') {
                          searchObj["compId"] = "0021",
                          searchObj["policyNumber"] = field == 'policyNumber' ? searchText+"%" : ($scope.search.policyNumber ? $scope.search.policyNumber.ILM_NO  : "%"),
                          searchObj["memberNumber"] = field == 'memberNumber' ? searchText+"%" : ($scope.search.memberNumber ? $scope.search.memberNumber.ULME_MEMBER_ID : "%"),
                          searchObj["voucherNumber"] = "%",
                          searchObj["cardNumber"] = "%",
                          searchObj["emiratesId"] = "%"
                        }
                        return searchObj;
                    }

                    $scope.querySearch = function (query, label) {
                        return query ? $scope.autoSearch.filter(createFilterFor(query, label)) : $scope.autoSearch;
                    }

                    function createFilterFor(query, label) {
                        var lowercaseQuery = angular.lowercase(query);
                        return function filterFn(state) {
                            return (((angular.lowercase(state[label]).indexOf(lowercaseQuery) != 0) && angular.lowercase(state[label]).indexOf(lowercaseQuery) != -1) || (angular.lowercase(state[label]).indexOf(lowercaseQuery) === 0));
                        };
                    }
                },
                replace: true
            };
        })
})();


