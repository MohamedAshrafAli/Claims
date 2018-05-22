(function() {
    'use strict';    
    angular
        .module('claims')
        .service('ProviderFinalizationService', ProviderFinalizationService)

        ProviderFinalizationService.$inject = [];

    function ProviderFinalizationService() {

        this.getFinailzationRecords = function() {
            return [{
                batch: "#111111",
                requestType:"Initial Request",
                batchFileName: "671-652d-d3453-MF17152-A002-CLAIM",
                receivedDate: '30 jan 2018',
                PaidToProviderCode: "4241",
                PaidToProviderName: "Al Noor Hospital",
                PaidTo: "Al Noor Hospital",
                Processed: "500000",
                unProcessed: "511290",
                rejectedAmount: "1,80,500",
                batchPaidAmount: "1,33,500",
                paymentWay: "Cheque",
                totalClaims: "923,515",
                paymentDate: "25 Mar 2018",
                paymentReference: "698536",
                status: "Approved",
               
            },
            {
                batch: "#222222",
                requestType:"Initial Request",
                batchFileName: "881-652d-d3453-MF17152-A002-CLAIM",
                receivedDate: '30 jan 2018',
                PaidToProviderCode: "4811",
                PaidToProviderName: "Apollo Hospital",
                PaidTo: "Apollo Hospital",
                Processed: "600000",
                unProcessed: "568890",
                rejectedAmount: "1,50,100",
                batchPaidAmount: "1,00,300",
                paymentWay: "iban",
                totalClaims: "923,515",
                paymentDate: "25 Mar 2018",
                paymentReference: "869541",
                status: "Approved",
               
            },
            {
                batch: "#333333",
                requestType:"Initial Request",
                batchFileName: "343-652d-d3453-MF17152-A002-CLAIM",
                receivedDate: '30 jan 2018',
                PaidToProviderCode: "5641",
                PaidToProviderName: "KMC Hospital",
                PaidTo: "KMC Hospital",
                Processed: "800000",
                unProcessed: "897890",
                rejectedAmount: "1,90,530",
                batchPaidAmount: "1,40,280",
                paymentWay: "Cheque",
                totalClaims: "888,515",
                paymentDate: "25 Mar 2018",
                paymentReference: "934536",
                status: "Approved",
               
            },
            {
                batch: "#444444",
                requestType:"Initial Request",
                batchFileName: "113-652d-d3453-MF17152-A002-CLAIM",
                receivedDate: '30 jan 2018',
                PaidToProviderCode: "6941",
                PaidToProviderName: "Apollo Hospital",
                PaidTo: "Apollo Hospital",
                Processed: "400000",
                unProcessed: "568468",
                rejectedAmount: "1,70,300",
                batchPaidAmount: "1,00,900",
                paymentWay: "iban",
                totalClaims: "923,692",
                paymentDate: "25 Mar 2018",
                paymentReference: "698683",
                status: "Approved",
               
            },
            {
                batch: "#555555",
                requestType:"Initial Request",
                batchFileName: "786-652d-d3453-MF17152-A002-CLAIM",
                receivedDate: '30 jan 2018',
                PaidToProviderCode: "4881",
                PaidToProviderName: "KMC Hospital",
                PaidTo: "KMC Hospital",
                Processed: "100000",
                unProcessed: "865475",
                rejectedAmount: "1,88,120",
                batchPaidAmount: "1,20,500",
                paymentWay: "Cheque",
                totalClaims: "698,615",
                paymentDate: "25 Mar 2018",
                paymentReference: "862497",
                status: "Approved",
               
            },
            {
                batch: "#666666",
                requestType:"Initial Request",
                batchFileName: "671-652d-d3453-MF17152-A002-CLAIM",
                receivedDate: '30 jan 2018',
                PaidToProviderCode: "7241",
                PaidToProviderName: "Al Noor Hospital",
                PaidTo: "Al Noor Hospital",
                Processed: "900000",
                unProcessed: "575890",
                rejectedAmount: "1,80,500",
                batchPaidAmount: "1,00,500",
                paymentWay: "iban",
                totalClaims: "953,511",
                paymentDate: "25 Mar 2018",
                paymentReference: "768536",
                status: "Approved",
               
            }
        ];
        }

        this.getSearchDropDownValues = function() {
            return [
                { text : 'Cheque' },
                { text : 'Iban' } 
            ];
        }

        this.getSearchFields = function() {
            return [
                { label : 'Provider', type :'text', name: 'PaidToProviderCode'},
                { label : 'Select Batch', type: 'text', name: 'PaidTo'},
                { label : 'Batch ID ', type: 'text', name: 'batch'},
                { label : 'Batch File Name ', type: 'text', name: 'batchFileName'}
            ];
        }
    }
})();
