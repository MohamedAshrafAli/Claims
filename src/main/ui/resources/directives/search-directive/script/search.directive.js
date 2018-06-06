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
                        if(!searchText || searchText.length < 3) return [];
                        var searchParams = constructSearchparams(field, searchText);
                        return AutocompleteService[info.methodName](searchParams).$promise.then(function(resp) {
                            return resp.rowData;
                        })
                    }

                    function constructSearchparams(field, searchText) {
                        var searchObj = {}
                        searchObj["compId"] = "0021";
                        if($scope.moduleName == 'reimb-registration') {
                            $scope.search.memberNumber =  $scope.search.memberName;
                            searchObj["policyNumber"] = field == 'policyNumber' ? searchText+"%" : ($scope.search.policyNumber ? $scope.search.policyNumber.CLF_ULM_NO  : "%"),
                            searchObj["memberNumber"] = $scope.search.memberName ? $scope.search.memberName.CLMR_MEMBER_ID : "%",
                            searchObj["memberName"] = field == 'memberName' ? searchText+"%" : $scope.search.memberNumber ? $scope.search.memberNumber.CLMR_MEMBER_NAME : "%",
                            searchObj["voucherNumber"] = field == 'voucherNumber' ? searchText+"%" : ($scope.search.voucherNumber ? $scope.search.voucherNumber.CLMF_VOUCH_NO : "%"),
                            searchObj["cardNumber"] = field == 'cardNumber' ? searchText+"%" : ($scope.search.cardNumber ? $scope.search.cardNumber.CLMR_TPA_CARD : "%"),
                            searchObj["emiratesId"] = field == 'emiratesId' ? searchText+"%" : ($scope.search.emiratesId ? $scope.search.emiratesId.CLMR_UID_ID : "%")
                        }

                        if($scope.moduleName == 'reimb-registration-general') {
                          searchObj["policyNumber"] = field == 'policyNumber' ? searchText+"%" : ($scope.search.policyNumber ? $scope.search.policyNumber.ILM_NO  : "%"),
                          searchObj["memberNumber"] = field == 'memberNumber' ? searchText+"%" : ($scope.search.memberNumber ? $scope.search.memberNumber.ULME_MEMBER_ID : "%"),
                          searchObj["voucherNumber"] =field == 'voucherNumber' ? searchText+"%" : ($scope.search.voucherNumber ? $scope.search.voucherNumber.CLMF_VOUCH_NO : "%"),
                          searchObj["cardNumber"] = "%",
                          searchObj["emiratesId"] = "%",
                          searchObj["memberName"] = "%"
                        }
                        if($scope.moduleName == 'reimb-user-assignment') {
                            searchObj["policyNumber"] = "%",
                            searchObj["memberNumber"] = field == 'memberNumber' ? searchText+"%" : ($scope.search.memberNumber ? $scope.search.memberNumber.ULME_MEMBER_ID : "%"),
                            searchObj["voucherNumber"] = "%",
                            searchObj["cardNumber"] = "%",
                            searchObj["emiratesId"] = "%",
                            searchObj["memberName"] = "%"
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


