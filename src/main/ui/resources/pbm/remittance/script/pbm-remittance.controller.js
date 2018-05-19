(function() {
    'use strict';
    angular
        .module('claims')
        .controller('PBMRemittanceController', PBMRemittanceController)

        PBMRemittanceController.$inject = ['$scope', '$rootScope','ngNotify', '$filter', '$state', 'PBMRemittanceService', 'ListViewService'];

    function PBMRemittanceController($scope, $rootScope, ngNotify, $filter, $state, PBMRemittanceService, ListViewService) {
        

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
            $scope.fieldsObject =  PBMRemittanceService.getSearchFields();
            $scope.registeredClaims = PBMRemittanceService.getClaimRemittanceList();
            $scope.claims = $scope.registeredClaims;
        }

        init();
    }
})();