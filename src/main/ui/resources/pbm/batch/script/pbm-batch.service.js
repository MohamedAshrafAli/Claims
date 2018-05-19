(function() {
    'use strict';

    angular
        .module('claims')
        .service('PBMBatchService', PBMBatchService)

    PBMBatchService.$inject = [];

    function PBMBatchService() {
        
        this.getSearchFields = function() {

            return [
                { label: 'Provider', type: 'autoSearch', name: 'provider'},
                { label: 'Batch Id', type: 'text', name: 'batchId'},
                { label: 'Select Batch', type: 'text', name: 'selectbatch'},
                { label: 'Batch File Name', type: 'text', name: 'fileName'},
                { label: 'Request Type', type: 'dropDown', name: 'voucherNumber'},
                { label: 'Received Date From', type: 'date', name: 'receivedFrmDate'},
                { label: 'Received Date To', type: 'date', name: 'receivedToDate'},
                // { label: 'Cheque Period From', type: 'date', name: 'chequeFrmPeriod'},
                // { label: 'Cheque Period To', type: 'date', name: 'chequeToPeriod'},
                // { label: 'Transaction Date From', type: 'date', name: 'transactionFrmDate'},
                // { label: 'Transaction Date To', type: 'date', name: 'transactionToDate'}
            ];
        }
        this.getBatchRecords = function() {
            return [
                {
                    receivedDate: '12/5/2017',
                    requestType: 'Initial Request',
                    providerCode: 7524,
                    registeredDate: '12/04/2018',
                    batchId: 10208970,
                    providerName: 'Al Noor Hospital',
                    remittanceUploadDate: '12/04/2018',
                    xmlFileName: '9091281-652d-d3453-MF17152-A002-CLAIM',
                    rejectionCode: 'REJ-001',
                    claims: 500,
                    processed: 500400,
                    unProcessed: 542048,
                    batchPaidAmount: 698536,
                    rejectedAmount: 589674,
                    paymentReference:'Reference',
                    paymentDate:'22/10/2018',
                    paymentWay:'Online',
                    providerLicense:'MF17152'
                },
                { 
                    receivedDate: '12/5/2017',
                    requestType: 'Initial Request',
                    providerCode: '4856',
                    registeredDate: '12/04/2018',
                    batchId: 10782970,
                    providerName: 'KMC Hospital',
                    remittanceUploadDate: '12/04/2018',
                    xmlFileName: '98963281-652d-d3453-MF17152-A002-CLAIM',
                    rejectionCode: 'REJ-008',
                    claims: 550,
                    processed: 500400,
                    unProcessed: 542048,
                    batchPaidAmount: 698536,
                    rejectedAmount: 589674,
                    paymentReference:'Reference',
                    paymentDate:'22/10/2018',
                    paymentWay:'Online',
                    providerLicense:'MF17152'},
                {
                    receivedDate: '12/5/2017',
                    requestType: 'Initial Request',
                    providerCode: '8962',
                    registeredDate: '12/04/2018',
                    batchId: 102089881,
                    providerName: 'Apollo Hospital',
                    remittanceUploadDate: '12/04/2018',
                    xmlFileName: '9485281-652d-d3453-MF17152-A002-CLAIM',
                    rejectionCode: 'REJ-004',
                    claims: 530,
                    processed: 500400,
                    unProcessed: 542048,
                    batchPaidAmount: 698536,
                    rejectedAmount: 589674,
                    paymentReference:'Reference',
                    paymentDate:'22/10/2018',
                    paymentWay:'Online',
                    providerLicense:'MF17152'
                },
                {
                    receivedDate: '12/5/2017',
                    requestType: 'Initial Request',
                    providerCode: '9637',
                    registeredDate: '12/04/2018',
                    batchId: 10201040,
                    providerName: 'Al Noor Hospital',
                    remittanceUploadDate: '12/04/2018',
                    xmlFileName: '1191281-652d-d3453-MF17152-A002-CLAIM',
                    rejectionCode: 'REJ-003',
                    claims: 500,
                    processed: 500400,
                    unProcessed: 542048,
                    batchPaidAmount: 698536,
                    rejectedAmount: 589674,
                    paymentReference:'Reference',
                    paymentDate:'22/10/2018',
                    paymentWay:'Online',
                    providerLicense:'MF17152'
                },
               
            ]
        }
        this.getRecordsFieldsInfo = function() {
            return [
                [
                    {'colGroup' : 
                            [   {'subGroup' : [{label : 'Received Date: ', name: 'receivedDate', labelClass:"boldText"}, {label : 'Requet Type: ', name: 'requestType', labelClass:"boldText"}]},
                                {label : 'Batch ID:', name: 'batchId', labelClass : "batchText", valueClass : "batchText"},
                                {label : 'XML File Name: ', name: 'xmlFileName'}
                            ]
                    },
                    {'colGroup' : 
                            [   {label : 'Provider Code: ', name: 'providerCode', labelClass:"boldText"},
                                {label : 'Provider License: ', name: 'providerLicense', labelClass:"boldText"},
                                {label : 'Provider Name: ', name: 'providerName', labelClass:"boldText"}
                            ]
                    },
                    {'colGroup' : 
                            [   {label : 'Registered Date: ', name: 'registeredDate', labelClass:"boldText"},
                                {label : 'Remittance Upload Date: ', name: 'remittanceUploadDate', labelClass:"boldText"},
                                {label : 'Rejection Code: ', name: 'rejectionCode', labelClass:"boldText"}
                            ]
                    }
                ],
                [
                    {label : 'Total Claims', name: 'claims', valueClass:"boldText"},
                    {label : 'Processed', name: 'processed', valueClass:"boldText"},
                    {label : 'UnProcessed', name: 'unProcessed', valueClass:"boldText"},
                    {label : 'Batch Paid Amount', name: 'batchPaidAmount', valueClass:"boldText"},
                    {label : 'Rejected Amount', name: 'rejectedAmount', valueClass:"boldText"},
                    {label : 'Payment Reference', name: 'paymentReference',valueClass:"textColor"},
                    {label : 'Payment Date', name: 'paymentDate', valueClass:"textColor"},
                    {label : 'Payment Way', name: 'paymentWay', valueClass:"textColor"},
                    {label : 'Rejection Descritpion', name: ''}
                ]                
            ];
        }
        
    }
})();