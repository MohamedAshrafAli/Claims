(function() {
    'use strict';

    angular
        .module('claims')
        .service('ReimbursementProcessingFactory', ReimbursementProcessingFactory)
    
    ReimbursementProcessingFactory.$inject = [];
    function ReimbursementProcessingFactory() {
        this.processingClaim = null;
        this.prevDiagnosis = null;
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
                "suggestedAmout" : 0,
                "approvedAmount" : 0,
                "rejectedAmount" : 0,
                "claimStatus" : "WIP",
                "internalRemarks" : "",
                "externalRemarks" : "",
                "isChecked" : false,
                "id" : new Date().getTime(),
                "dml": "N"
            };
        }
        this.constructClaimBaseCurrencyFields = function(fieldMap, exchangeRate, data) {
            angular.forEach(fieldMap, function(value, key) {
                if(data[value] != null && !angular.isUndefined(data[value])) {
                    data[key] = data[value] * exchangeRate;
                }                
            });
            return data;
        }
        this.addDays = function(date, days) {
            var addedDate = angular.copy(new Date(date));
            addedDate.setDate(date.getDate() + days);
            return addedDate;
        }
        this.setPrevProcessingClaim = function(claimInfo) {
            this.processingClaim = claimInfo;
        }
        this.getPrevProcessingClaim = function(claimInfo) {
            return this.processingClaim;
        }
        this.setPrevDiagnosis = function(diagInfo) {
            this.prevDiagnosis = diagInfo;
        }
        this.getPrevDiagnosis = function() {
            return this.prevDiagnosis;
        }
    }
})();