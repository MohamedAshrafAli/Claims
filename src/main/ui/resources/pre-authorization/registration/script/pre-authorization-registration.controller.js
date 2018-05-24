(function() {
    'use strict';

    angular
        .module('claims')
        .controller('PreAuthorizationRegistrationController', PreAuthorizationRegistrationController)

    PreAuthorizationRegistrationController.$inject = ['$scope', '$rootScope', 'PreAuthorizationRegistrationService', 'ngNotify', '$filter', '$state', 'ListViewService'];

    function PreAuthorizationRegistrationController($scope, $rootScope, PreAuthorizationRegistrationService, ngNotify, $filter, $state, ListViewService) {

        $scope.reverse = true;

        $scope.filterValues = function(searchValue) {
            if (searchValue) {
                $scope.searchBy = {
                    memberNumber : searchValue['memberNumber'] ? searchValue['memberNumber']['memberNumber'] : '',
                    policyNumber: searchValue['policyNumber'] ? searchValue['policyNumber']['policyNumber'] : ''
                };
            } else {
                $scope.searchBy = {};
            }
        }
      
        function init() {
            $scope.registrationHeaders = ListViewService.getPreAuthorizationRegistrationListViewHeader();
            $scope.fieldsObject =  PreAuthorizationRegistrationService.getSearchFields();
            $scope.registeredClaims = PreAuthorizationRegistrationService.getClaimRegistrationList();
            $scope.claims = $scope.registeredClaims;
        }

        init();
        
    }
})()