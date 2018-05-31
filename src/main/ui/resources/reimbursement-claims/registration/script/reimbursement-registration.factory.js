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
                "voucherNumber" : null,
                "ibanNum" : null,
                "emiratesId":null,
                "paymentWay" : '01',
                "status" : "CC"
            };
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

        this.getRegistrationGeneralSearchFields = function() {
            return [
                { label: 'Member Number', type: 'autoSearch', name: 'memberNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos', autoCompleteInfo : {methodName: 'getMemberNumberList', displayName: 'ULME_MEMBER_ID'}},
                { label: 'Policy Number', type: 'autoSearch', name: 'policyNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos', autoCompleteInfo : {methodName: 'getPolicyNumberList', displayName: 'ILM_NO'}},
                { label: 'Voucher Number', type: 'autoSearch', name: 'voucherNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos', autoCompleteInfo : {methodName: 'getVoucherNumbers', displayName: 'CLMF_VOUCH_NO'}},
                { label: 'Previous Request Number', type: 'text', name: 'requestNumber', iconClass:'searchAutoIcon', width:'90%'}
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

        this.constructClaim = function(claim, sourceType) {
            claim.reqReceivedDate = null;
            claim.serviceFmDate = null;
            claim.requestAmt = null;
            claim.status = "CC";
            claim.sourceType = sourceType;
            return claim;
        }        

        this.constructUidMap = function(data, itemKey, itemValue) {
            var uidMap = {};
            angular.forEach(data, function(item, key) {
                uidMap[item[itemKey]] = item[itemValue];
            })
            return uidMap
        }

        return this;
    }
})();