(function() {
    'use strict';
    angular
        .module('claims')
        .controller('EclaimRemittanceController', EclaimRemittanceController)

        EclaimRemittanceController.$inject = ['$scope', '$rootScope','ngNotify', '$filter', '$state', 'EclaimRemittanceService', 'ListViewService'];

    function EclaimRemittanceController($scope, $rootScope, ngNotify, $filter, $state, EclaimRemittanceService, ListViewService) {
        

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
            $scope.fieldsObject =  EclaimRemittanceService.getSearchFields();
            $scope.registeredClaims = EclaimRemittanceService.getClaimRemittanceList();
            $scope.claims = $scope.registeredClaims;
        }

        init();
    }
})();