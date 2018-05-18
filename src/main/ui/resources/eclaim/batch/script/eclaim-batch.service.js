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
    }
})()