(function() {
    'use strict';

    angular
        .module('claims')
        .controller('RegistrationController', RegistrationController)

    RegistrationController.$inject = ['$scope', '$rootScope', 'RegistrationService', 'ngNotify', '$filter', '$state'];

    function RegistrationController($scope, $rootScope, RegistrationService, ngNotify, $filter, $state) {
        $scope.registeredClaims = RegistrationService.getClaimRegistrationList();


        $scope.claims= $scope.registeredClaims;

        $scope.searchBtn = function() {
            if (($scope.voucherNumber == "" || $scope.voucherNumber == null) && ($scope.searchMemNum == "" || $scope.searchMemNum == null) && ($scope.ibanNum == "" || $scope.ibanNum == null)&&($scope.searchPolicy == "" || $scope.searchPolicy == null)&&($scope.memberName == "" || $scope.memberName == null)) {
                $scope.claims = $scope.registeredClaims;
            } else {
                var memNo = $scope.searchMemNum ? $scope.searchMemNum.memberNumber : undefined;
                var policyNo = $scope.searchPolicy ? $scope.searchPolicy.policyNumber : undefined;
                $scope.claims = $filter('filter')($scope.registeredClaims, { voucherNumber: $scope.voucherNumber, ibanNum: $scope.ibanNum, memberNumber : memNo, policyNumber : policyNo,memberName: $scope.memberName});
            }
        }

        $scope.reverse = true;
        $scope.sortBy = function(propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };

        $scope.editClaim = function(claim)  {
            RegistrationService.setClaim(claim);
            $state.go('claim-registration-edit');
        }

        $scope.querySearch = function(query, name) {
            return query ? $scope.claims.filter(createFilterFor(query, name)) : $scope.claims;
        }
      
        function createFilterFor(query, name) {
            var lowercaseQuery = angular.lowercase(query);
            return function filterFn(state) {
                return (((angular.lowercase(state[name]).indexOf(lowercaseQuery) != 0) && angular.lowercase(state[name]).indexOf(lowercaseQuery) != -1) || (angular.lowercase(state[name]).indexOf(lowercaseQuery) === 0));
            };
        }
    }
})()