(function() {
    'use strict';

    angular
        .module('claims')
        .service('ReimbursementProcessingFactory', ReimbursementProcessingFactory)
    
    ReimbursementProcessingFactory.$inject = [];
    function ReimbursementProcessingFactory() {
        this.createNewReimbursmentObject = function() {
            return {
                "treatmentCodeOrSubBenefit" : "",
                "treatmentFromDate" : new Date(),
                "treatmentToDate" : "",
                "noOfTreamentDays" : "",
                "requestedAmount" : "",
                "manualDeduction" : "",
                "rejectionCode" : "",
                "policyDeductibleAmount" : "",
                "penaltyAmount" : "",
                "suggestedAmout" : 1000,
                "approvedAmount" : 2000,
                "rejectedAmount" : 3000,
                "claimStatus" : "",
                "internalRemarks" : "",
                "externalRemarks" : "",
                "isChecked" : false,
                "id" : new Date().getTime(),
                "dml": "N"
            };
        }       
        this.getClaimsRequest = function() {
            return [
                {'reqNum': 987896, 'claimStatus':'rejected', 'statusReason':'Waiting for Finalization', 'rejectedReason':'Waiting for Finalization', 'status' : 'Approved', 'approvedAmount' : 201220},
                {'reqNum': 187896, 'claimStatus':'rejected', 'statusReason':'Waiting for Approval', 'rejectedReason':'Waiting for Finalization', 'status' : 'Rejected', 'rejectedAmount' : 301320},
                {'reqNum': 287896, 'claimStatus':'rejected', 'statusReason':'Waiting for Finalization', 'rejectedReason':'Waiting for Finalization', 'status' : 'Approved', 'approvedAmount' : 40140},
                {'reqNum': 387896, 'claimStatus':'rejected', 'statusReason':'Waiting for Approval', 'rejectedReason':'Waiting for Finalization', 'status' : 'Rejected', 'rejectedAmount' : 50520},
                {'reqNum': 487896, 'claimStatus':'rejected', 'statusReason':'Waiting for Approval', 'rejectedReason':'Waiting for Finalization', 'status' : 'Approved', 'approvedAmount' : 606620},
                {'reqNum': 587896, 'claimStatus':'rejected', 'statusReason':'Waiting for Approval', 'rejectedReason':'Waiting for Finalization', 'status' : 'Approved', 'approvedAmount' : 77777},
                {'reqNum': 687896, 'claimStatus':'rejected', 'statusReason':'Waiting for Approval', 'rejectedReason':'Waiting for Finalization', 'status' : 'Rejected', 'rejectedAmount' : 88888}
            ];
        }

        this.constructClaimBaseCurrencyFields = function(fieldMap, exchangeRate, data) {
            angular.forEach(fieldMap, function(value, key) {
                if(data[value] != null && !angular.isUndefined(data[value])) {
                    data[key] = data[value] * exchangeRate;
                }                
            });
            return data;
        }
    }
})();