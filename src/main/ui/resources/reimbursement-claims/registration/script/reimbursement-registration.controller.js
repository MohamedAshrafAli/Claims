(function() {
    'use strict';

    angular
        .module('claims')
        .controller('ReimbursementRegistrationController', ReimbursementRegistrationController)

    ReimbursementRegistrationController.$inject = ['$scope', '$rootScope', 'ReimbursementRegistrationService', 'ngNotify', '$filter', '$state', 'ListViewService', 'ReimbursementRegistrationFactory'];

    function ReimbursementRegistrationController($scope, $rootScope, ReimbursementRegistrationService, ngNotify, $filter, $state, ListViewService, ReimbursementRegistrationFactory) {

        $scope.reverse = true;
        var autoCompleteMapping = {
            cardNumber : 'CLMR_TPA_CARD',
            memberNumber : 'CLMR_MEMBER_ID',
            emiratesId : 'CLMR_UID_ID',
            policyNumber : 'CLF_ULM_NO',
            voucherNumber : 'CLMF_VOUCH_NO'
        }
        
        $scope.filterValues = function(searchValue) {
            if (searchValue) {
                var searchparam = ReimbursementRegistrationFactory.constructSearchObj(autoCompleteMapping, searchValue);
                searchparam.compId = "0021"                
                getRegisteredClaimsList(searchparam);
            } else {
                getRegisteredClaimsList({compId:"0021"});
            }
            
        }

        function getRegisteredClaimsList(searchParams) {
            ReimbursementRegistrationService.getReimbursementRegistrationDetails(searchParams, function(resp) {
                $scope.registeredClaims = resp;
            })
        }
      
        function init() {
            getRegisteredClaimsList({compId:"0021"});
            $scope.registrationHeaders = ListViewService.getRegistrationListViewHeader();
            $scope.fieldsObject =  ReimbursementRegistrationFactory.getSearchFields();
        }

        init();
        
    }
})()