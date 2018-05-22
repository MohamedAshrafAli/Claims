(function() {
    'use strict';    
    angular
        .module('claims')
        .service('UserAssignmentService', UserAssignmentService)

    UserAssignmentService.$inject = [];

    function UserAssignmentService() {

        this.getClaimsForUserAssignment = function() {
            return [{
                'batchId':'1231232',
                'claimNo': '456',
                'memberNo': '9562467936',
                'providerName':'ProviderNameOne',
                'providerCode':'4922867',
                'submissionType':'NA',
                'grossSubmitted':'NA',
                'patientShare':'NA',
                'netSubmitted':'NA',
                'approvedAmount': '1,00,550',
                'rejectedAmount':'1,00,345',
                'status': 'New Request',
                'paymentReference':'Nill',
                'payDate': '08 Jan 2018',
                'paymentWay':'Check',
                'remittance':'23 jan 2018',
                'assignedTo':'John',
                'assignedDate':'08 Jan 2018',
                'assignedBy':'Mohamed',
                'approvedBy':'Osama Bin',
                'approvedDate':'23 jun 2018',
                'receivedDate':"9 Dec 2018",
                'currencyCode': 'AED',
                "id" : new Date().getTime() + 1
            },{
                'batchId':'4567',
                'claimNo': '456',
                'memberNo': '9562467936',
                'providerName':'ProviderNameOne',
                'providerCode':'8978',
                'submissionType':'NA',
                'grossSubmitted':'NA',
                'patientShare':'NA',
                'netSubmitted':'NA',
                'approvedAmount': '1,00,550',
                'rejectedAmount':'1,22,100',
                'status': 'New Request',
                'paymentReference':'Nill',
                'payDate': '08 Jan 2018',
                'paymentWay':'Check',
                'remittance':'23 jan 2018',
                'assignedTo':'John',
                'assignedDate':'08 Jan 2018',
                'assignedBy':'Mohamed',
                'approvedBy':'Osama Bin',
                'approvedDate':'23 jun 2018',
                'receivedDate':"9 Dec 2018",
                'currencyCode': 'AED',
                "id" : new Date().getTime() + 1
            }];
        }

        this.getUsers = function() {
            return [{
                'userId': '1234',
                'name': 'Jessica',
                'assigned': 5,
                'pending': 2,
                'claimNo': '2345790',
                'memberNo': '98',
                'voucherNo': '45628',
                'encounterType': 'Inpatient',
                'requestRecievedOn': '23 jan 2018',
                'prevRequest': 'NA',
                'payMode': 'Cheque',
                'payDate': '08 Jan 2018',
                'payRefNo': '13212',
                'requestedAmount': ',23,515',
                'approvedAmount': '1,00,500',
                'currencyCode': 'AED',
                'status': 'Assigned',
                "id" : new Date().getTime() + 1
            }];
        }

        this.getSearchDropDownValues = function() {
            return [
                { text : 'Registered' },
                { text : 'Assigned' },
                { text : 'Pending for Approval' },
                { text : 'Approved' },
                { text : 'Rejected' },
                { text : 'Auto Failed' },
                { text : 'Waiting for Payment' },
                { text : 'Paid' } 
            ];
        }

        this.getSearchDropDownValuesRegulator = function() {
            return [
                { text : 'HAAD' },
                { text : 'DHA' }
            ];
        }


        this.getSearchFields = function() {
            return [
               
                { label: 'Provider', type: 'text', name: 'provider', width: '60%' },
                { label: 'Select Batch', type: 'text', name: 'selectBatch', width: '60%' },
                { label: 'Batch Id', type: 'text', name: 'batchId', width: '60%' },
                { label: 'Batch File Name', type: 'text', name: 'batchFileName', width: '70%' },
                { label: 'Received Date From', type: 'date', name: 'receivedDateFrom', width: '100%' },
                { label: 'Received Date To', type: 'date', name: 'receivedDateTo', width: '90%' },
                // { label: 'Cheque Period From', type: 'date', name: 'chequePeriodFrom', width: '80%' },
                // { label: 'Cheque Period To', type: 'date', name: 'chequePeriodTo', width: '80%' },
                // { label: 'Transaction Date from', type: 'date', name: 'transactionDatefrom', width: '80%' },
                // { label: 'Transaction Date To', type: 'date', name: 'transactionDateTo', width: '80%' },
                // { label: 'Status', type: 'dropDown', name: 'status', dropDownValue: this.getSearchDropDownValues(), width: '80%' },
                // { label: 'Regulator', type: 'dropDown', name: 'regulator', dropDownValue: this.getSearchDropDownValuesRegulator(), width: '80%' },
                // { label: 'User Id', type: 'text', name: 'userId', width: '80%' },
            ];
        }
    }
})();
