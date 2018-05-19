(function() {
    'use strict';

    angular
        .module('claims')
        .service('PreAuthorizationRemittanceService', PreAuthorizationRemittanceService)
    
    PreAuthorizationRemittanceService.$inject = [];
    function PreAuthorizationRemittanceService() {


        this.getClaimRemittanceList = function(params) {
            return [
                {
                    "batchId" : 1020910,
                    "batchFileName" : '8428281-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" : '12/5/2017',
                    'providerCode' : 9041,
                    "providerLicense" : 'MF17152',
                    "providerName" : 'Alnoor Hospital',
                    "remittanceUploadDate" : '1/1/2018',
                    "reqType" : 'resubmission',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : 44587,
                    "ibanNum" : "1345465ABSCSD",
                    "emiratesId":45673,
                    "memberNumber": "9566340416",
                    "policyNumber": "0792418901",
                    "paymentWay" : "iban"
                },
                {
                    "batchId" : '1305421',
                    "batchFileName" :'1128281-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" : '12/5/2017',
                    'providerCode' : 7623,
                    "providerLicense" : 'MF17152',
                    "providerName" :'Apollo Hospital',
                    "remittanceUploadDate" : '1/1/2018',
                    "reqType" : 'newRequest',
                    "encType" : 'inPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : 38221,
                    "ibanNum" : "1435675ABSCSD",
                    "emiratesId":41113,
                    "memberNumber": "3457678876",
                    "policyNumber": "0884422909",
                    "paymentWay" : "cheque"
                },
                
                {
                    "batchId" : '1458432',
                    "batchFileName" :'9742781-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" : '12/5/2017',
                    'providerCode' : 3347,
                    "providerLicense" : 'MF17152',
                    "providerName" : 'KMC Hospital',
                    "remittanceUploadDate" : '1/1/2018',
                    "reqType" : 'resubmission',
                    "encType" : 'inPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : 24859,
                    "ibanNum" : "1098763ABSCSD",
                    "emiratesId":25437,
                     "memberNumber": "9324140416",
                     "policyNumber": "0909886612",
                     "paymentWay" : "cheque"
                },
                {
                    "batchId" : '1357621',
                    "batchFileName" : '5678281-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" :  '12/5/2017',
                    'providerCode' : 4341,
                    "providerLicense" : 'MF17152',
                    "providerName" : 'Apollo Hospital',
                    "remittanceUploadDate" : '1/1/2018',
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : 39758,
                    "ibanNum" : "1742465ABSCSD",
                    "emiratesId":53672,
                    "memberNumber": "4196754416",
                    "policyNumber": "0304532091",
                    "paymentWay" : "iban"
                },
                {
                    "batchId" : '1179674',
                    "batchFileName" : '3921281-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" :  '12/5/2017',
                    'providerCode' : 9304,
                    "providerLicense" : 'MF17152',
                    "providerName" :'KMC Hospital',
                    "remittanceUploadDate" : '1/1/2018',
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : 87924,
                    "ibanNum" : "1098455ABSCSD",
                    "emiratesId":57893,
                    "memberNumber": "9181140413",
                    "policyNumber": "0903322091",
                    "paymentWay" : "cheque"
                },
                {
                    "batchId" : '1942973',
                    "batchFileName" : '8908281-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" :  '12/5/2017',
                    'providerCode' : 9341,
                    "providerLicense" : 'MF17152',
                    "providerName" :'KMC Hospital',
                    "remittanceUploadDate" : '1/1/2018',
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : 15789,
                    "ibanNum" : "1243565ABSCSD",
                    "emiratesId":48763,
                    "memberNumber": "3366312416",
                    "policyNumber": "0904532091",
                    "paymentWay" : "iban"
                },
                {
                    "batchId" : '1096342',
                    "batchFileName" :'6548281-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" : '12/5/2017',
                    'providerCode' : 1041,
                    "providerLicense" : 'MF17152',
                    "providerName" : 'Alnoor Hospital',
                    "remittanceUploadDate" : "1/1/2018",
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : 36978,
                    "ibanNum" : "1909465ABSCSD",
                    "emiratesId":89064,
                    "memberNumber": "7509340916",
                    "policyNumber": "0904221091",
                    "paymentWay" : "cheque"
                },
                {
                    "batchId" : '1213452',
                    "batchFileName" :'9091281-652d-d3453-MF17152-A002-CLAIM',
                    "receivedDate" :  '12/5/2017',
                    'providerCode' : 9041,
                    "providerLicense" : 'MF17152',
                    "providerName" : 'Alnoor Hospital',
                    "remittanceUploadDate" : '1/1/2018',
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "voucherNumber" : '78915',
                    "ibanNum" : "1856465ABSCSD",
                    "emiratesId":78451,
                    "memberNumber": "8566340416",
                    "policyNumber": "0904588291",
                    "paymentWay" : "cheque"
                }

            ]
        }
        this.searchClaims = function(params) {
            return this.getClaimRemittanceList(params);
        }
        this.getSearchFields = function() {
            return [
                { label: 'Batch Id', type: 'text', name: 'batchId'},
                { label: 'Batch File Name', type: 'text', name: 'batchFileName'},
                { label: 'Provider Name', type: 'text', name: 'providerName'},
                { label: 'Provider Code', type: 'text', name: 'providerCode'},
            ];
        }
    }
})();