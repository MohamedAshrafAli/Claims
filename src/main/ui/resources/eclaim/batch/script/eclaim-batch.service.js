(function() {
    'use strict';

    angular
        .module('claims')
        .service('EclaimBatchService', EclaimBatchService)

    EclaimBatchService.$inject = [];

    function EclaimBatchService() {
        this.getSearchFields = function() {
            return [
                { label: 'Provider', type: 'autoSearch', name: 'provider'},
                { label: 'Batch Id', type: 'text', name: 'batchId'},
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

        this.getRecordsFieldsInfo = function() {
            return [
                [
                    {'colGroup' : 
                            [   {'subGroup' : [{label : 'Received Date: ', name: ''}, {label : 'Requet Type: ', name: ''}]},
                                {label : 'Batch ID:', name: ''},
                                {label : 'XML File Name: ', name: ''}
                            ]
                    },
                    {'colGroup' : 
                            [   {label : 'Provider Code: ', name: ''},
                                {label : 'Provider License: ', name: ''},
                                {label : 'Provider Name: ', name: ''}
                            ]
                    },
                    {'colGroup' : 
                            [   {label : 'Registered Date: ', name: ''},
                                {label : 'Remittance Upload Date: ', name: ''},
                                {label : 'Rejection Code: ', name: ''}
                            ]
                    }
                ],
                [
                    {label : 'Claims', name: ''},
                    {label : 'Processed', name: ''},
                    {label : 'UnProcessed', name: ''},
                    {label : 'Batch Paid Amount', name: ''},
                    {label : 'Rejected Amount', name: ''},
                    {label : 'Payment Reference', name: ''},
                    {label : 'Payment Date', name: ''},
                    {label : 'Payment Way', name: ''},
                    {label : 'Rejection Descritpion', name: ''}
                ]                
            ];
        }
    }
})()