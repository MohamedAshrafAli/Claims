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
                        var searchParams = {};
                        if($scope.moduleName == 'registration') {
                            searchParams["compId"] = "0021",
                            searchParams["policyNumber"] = field == 'policyNumber' ? searchText+"%" : "%",
                            searchParams["memberNumber"] = "%",
                            searchParams["memberName"] = field == 'memberName' ? searchText+"%" : "%",
                            searchParams["voucherNumber"] = field == 'voucherNumber' ? searchText+"%" : "%",
                            searchParams["cardNumber"] = field == 'cardNumber' ? searchText+"%" : "%",
                            searchParams["emiratesId"] = field == 'emiratesId' ? searchText+"%" : "%"
                        }
                        return AutocompleteService[info.methodName](searchParams).$promise.then(function(resp) {
                            return resp.rowData;
                        })
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


