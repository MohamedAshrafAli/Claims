(function() {
    'use strict';

    angular
        .module('claims')
        .factory('ReimbursementRegistrationFactory', ReimbursementRegistrationFactory)
    
    ReimbursementRegistrationFactory.$inject = [];
    function ReimbursementRegistrationFactory() {

        this.claimObj = {};
        this.createRegDetailObj = function() {
            return {
                "memberName" : null,
                "mobileNum1" : null,
                "email1" : null,
                'mobileNum2' : null,
                "email2" : null,
                "serviceFmDate" : null,
                "requestAmt" : null,
                "encType" : null,
                "reqType" : null,
                "reqReceivedDate" : null,
                "prevRequest" : null,
                "source" : "post",
                "voucherNumber" : null,
                "ibanNum" : null,
                "emiratesId":null,
                "paymentWay" : 'iban'
            };
        }

        this.registerClaim = function(data) {
            console.log("data :: ", data);
        }
        
        this.searchClaims = function(params) {
            return this.getClaimRegistrationList(params);
        }

        this.setClaim = function(claim) {
            this.claimObj = claim;
        }

        this.getClaim = function(claim) {
            return this.claimObj;
        }

        this.getSearchFields = function() {
            return [
                { label: 'Card Number', type: 'autoSearch', name: 'cardNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos', autoCompleteInfo : {methodName: 'getMedicalCardNumberList', displayName: 'CLMR_TPA_CARD'}},
                { label: 'Member Name', type: 'autoSearch', name: 'memberName', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos',  autoCompleteInfo : {methodName: 'getMemberNameList', displayName: 'CLMR_MEMBER_NAME'}},
                { label: 'Emirates  Id', type: 'autoSearch', name: 'emiratesId', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos',  autoCompleteInfo : {methodName: 'getEmiratesId', displayName: 'CLMR_UID_ID'}},
                { label: 'Voucher Number', type: 'autoSearch', name: 'voucherNumber', width:'90%', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos',  autoCompleteInfo : {methodName: 'getVoucherNumbers', displayName: 'CLMF_VOUCH_NO'}},
                { label: 'Policy Number', type: 'autoSearch', name: 'policyNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos',  autoCompleteInfo : {methodName: 'getClaimsPolicyNumberList', displayName: 'CLF_ULM_NO'}}
            ];
        }
        this.constructSearchObj = function(autoCompleteMapping, searchValue) {
            var search = {};
            angular.forEach(searchValue, function(value,key){
                if(value != null) {
                    search[key] = value
                    if(autoCompleteMapping[key]){
                        search[key] = value[autoCompleteMapping[key]];
                    }
                }
            });
            return search;
        }
        return this;
    }
})();