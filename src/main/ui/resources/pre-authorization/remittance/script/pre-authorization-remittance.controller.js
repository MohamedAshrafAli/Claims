(function() {
    'use strict';
    angular
        .module('claims')
        .controller('PreAuthorizationRemittanceController', PreAuthorizationRemittanceController)

        PreAuthorizationRemittanceController.$inject = ['$scope', '$rootScope','ngNotify', '$filter', '$state', 'PreAuthorizationRemittanceService', 'ListViewService'];

    function PreAuthorizationRemittanceController($scope, $rootScope, ngNotify, $filter, $state, PreAuthorizationRemittanceService, ListViewService) {
     
        

        $scope.filterValues = function(searchValue) {
            if (searchValue) {
                $scope.searchBy = {
                    batchId : searchValue['batchId'] ? searchValue['batchId'] : '',
                    batchFileName : searchValue['batchFileName'] ? searchValue['batchFileName'] : '',
                    providerCode : searchValue['providerCode'] ? searchValue['providerCode'] : '',
                    providerName : searchValue['providerName'] ? searchValue['providerName'] : '',
               
                };
            } else {
                $scope.searchBy = {};
            }
        }

        function init() {
            $scope.remittanceHeaders = ListViewService.getRemittanceListViewHeader();
            $scope.fieldsObject =  PreAuthorizationRemittanceService.getSearchFields();
            $scope.registeredClaims = PreAuthorizationRemittanceService.getClaimRemittanceList();
            $scope.claims = $scope.registeredClaims;
        }

        init();
    }
})();