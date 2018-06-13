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
                controller: function ($scope, $q, AutocompleteService, $timeout, companyId, dateFormat, $rootScope) {
                    var searchProperties = $rootScope.searchProperties;
                    $scope.dateFormat = dateFormat;
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
                            var data = responseData(resp.rowData, field);
                            return data;
                        })
                    }

                    function responseData(data, field) {
                        if($scope.properties && data.length == 1 && data[0][$scope.properties[field]] == null) return[];
                        return data;
                    }

                    function constructSearchparams(field, searchText) {
                        var searchObj = {}
                        searchObj["compId"] = companyId;
                        if($scope.moduleName == 'reimb-registration') {
                            $scope.properties = searchProperties.registration;
                            $scope.search.memberNumber =  $scope.search.memberName;
                            searchObj["policyNumber"] = field == 'policyNumber' ? searchText+"%" : ($scope.search.policyNumber ? $scope.search.policyNumber[$scope.properties['policyNumber']] : "%"),
                            searchObj["memberNumber"] = $scope.search.memberName ? $scope.search.memberName[$scope.properties['memberNumber']]: "%",
                            searchObj["memberName"] = field == 'memberName' ? searchText+"%" : $scope.search.memberNumber ? $scope.search.memberNumber[$scope.properties['memberName']] : "%",
                            searchObj["voucherNumber"] = field == 'voucherNumber' ? searchText+"%" : ($scope.search.voucherNumber ? $scope.search.voucherNumber[$scope.properties['voucherNumber']] : "%"),
                            searchObj["cardNumber"] = field == 'cardNumber' ? searchText+"%" : ($scope.search.cardNumber ? $scope.search.cardNumber[$scope.properties['cardNumber']] : "%"),
                            searchObj["emiratesId"] = field == 'emiratesId' ? searchText+"%" : ($scope.search.emiratesId ? $scope.search.emiratesId[$scope.properties['emiratesId']] : "%")
                        }

                        if($scope.moduleName == 'reimb-registration-general') {
                          $scope.properties = searchProperties.registrationGeneral;
                          searchObj["policyNumber"] = field == 'policyNumber' ? searchText+"%" : ($scope.search.policyNumber ? $scope.search.policyNumber[$scope.properties['policyNumber']]  : "%"),
                          searchObj["memberNumber"] = field == 'memberNumber' ? searchText+"%" : ($scope.search.memberNumber ? $scope.search.memberNumber[$scope.properties['memberNumber']] : "%"),
                          searchObj["voucherNumber"] = field == 'voucherNumber' ? searchText+"%" : ($scope.search.voucherNumber ? $scope.search.voucherNumber[$scope.properties['voucherNumber']] : "%"),
                          searchObj["cardNumber"] = "%",
                          searchObj["emiratesId"] = "%",
                          searchObj["memberName"] = "%"
                        }
                        if($scope.moduleName == 'reimb-user-assignment') {
                            $scope.properties = null;
                            searchObj["policyNumber"] = "%",
                            searchObj["memberNumber"] = field == 'memberNumber' ? searchText+"%" : ($scope.search.memberNumber ? $scope.search.memberNumber.ULME_MEMBER_ID : "%"),
                            searchObj["voucherNumber"] = "%",
                            searchObj["cardNumber"] = "%",
                            searchObj["emiratesId"] = "%",
                            searchObj["memberName"] = "%"
                        }
                        return searchObj;
                    }
                }
            };
        })
})();


