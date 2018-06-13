(function() {
    'use strict';

    angular
        .module('claims')
        .controller('ReimbursementRegistrationController', ReimbursementRegistrationController)

    ReimbursementRegistrationController.$inject = ['$scope', '$rootScope', 'ReimbursementRegistrationService', 'ngNotify', '$filter', '$state', 'ListViewService', 'ReimbursementRegistrationFactory', 'SpinnerService', 'companyId'];

    function ReimbursementRegistrationController($scope, $rootScope, ReimbursementRegistrationService, ngNotify, $filter, $state, ListViewService, ReimbursementRegistrationFactory, SpinnerService, companyId) {

        $scope.reverse = true;
        var properties = $rootScope.searchProperties.registration;
        var autoCompleteMapping = {
            cardNumber : properties["cardNumber"],
            memberName : properties["memberNumber"],
            emiratesId : properties["emiratesId"],
            policyNumber : properties["policyNumber"],
            voucherNumber : properties["voucherNumber"]
        }
        
        $scope.filterValues = function(searchValue) {
            if (searchValue) {
                var searchparam = ReimbursementRegistrationFactory.constructSearchObj(autoCompleteMapping, searchValue);
                searchparam.memberNumber = searchparam.memberName;
                delete searchparam.memberName;
                searchparam.compId = companyId
                getRegisteredClaimsList(searchparam);
            } else {
                getRegisteredClaimsList({compId:companyId});
            }
        }

        function getRegisteredClaimsList(searchParams) {
            SpinnerService.start();
            ReimbursementRegistrationService.getReimbursementRegistrationDetails(searchParams, function(resp) {
                $scope.registeredClaims = resp;
                SpinnerService.stop();
            }, onError)
        }

        function onError() {
            SpinnerService.stop();
        }
      
        function init() {
            getRegisteredClaimsList({compId:companyId});
            $scope.registrationHeaders = ListViewService.getRegistrationListViewHeader();
            $scope.fieldsObject =  ReimbursementRegistrationFactory.getSearchFields();
        }

        init();
        
    }
})()