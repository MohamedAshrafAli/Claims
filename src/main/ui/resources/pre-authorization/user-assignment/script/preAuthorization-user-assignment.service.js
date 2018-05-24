(function() {
    'use strict';    
    angular
        .module('claims')
        .service('preAuthorizationAssignmentService', preAuthorizationAssignmentService)

    preAuthorizationAssignmentService.$inject = [];

    function preAuthorizationAssignmentService() {

        this.getClaimsForUserAssignment = function() {
            return [{
                //Request Received Date	
                'Source': '456',
                'documentLink':'Medical Bills',
                'memberName': 'Ashraf',
                'memberType':'Normal',
                'claimType':'Claim Type',
                'providerCode':'596222',
                'providerName':'Provider Name',
                'registrationDate':'23 jan 2018',
                'encounterType':'Inpatient',
                'requestType':'NA',
                'previousRequest':'NA',
                'rejectionReason':'NA',
                'assignedTo':'Jack',
                'assignedDate':'08 Jan 2018',
                'assignedBy':'Osama Bin',
                'approvedAmount':'1,00,500',
                'rejectedAmount':'2,11,00',
                'approvedBy':'Arifa',
                'status': 'New Request',
                'currencyCode': 'AED',
                'approvedDate':'13 Dec 2019',
                "id" : new Date().getTime() + 1
            },{
                //Request Received Date	
                'Source': '456',
                'documentLink':'Medical Bills',
                'memberName': 'Ashraf',
                'memberType':'Normal',
                'claimType':'Claim Type',
                'providerCode':'176222',
                'providerName':'Provider Name',
                'registrationDate':'23 jan 2018',
                'encounterType':'Inpatient',
                'requestType':'NA',
                'previousRequest':'NA',
                'rejectionReason':'NA',
                'assignedTo':'Jack',
                'assignedDate':'08 Jan 2018',
                'assignedBy':'Osama Bin',
                'approvedAmount':'2,11,500',
                'rejectedAmount':'2,11,00',
                'approvedBy':'Arifa',
                'status': 'New Request',
                'currencyCode': 'AED',
                'approvedDate':'13 Dec 2019',
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

        this.getSearchFields = function() {
            return [
                { label: 'Provider', type: 'text', name: 'provider', width:'50%'},
                { label: 'XML File', type: 'text', name: 'xmlFile', width:'50%'},
                { label: 'Batch Id', type: 'text', name: 'batchId', width:'50%'},
                { label: 'Member No', type: 'text', name: 'batchFileName', width:'90%'},
                { label: 'Received Date From', type: 'date', name: 'receivedDateFrom', width:'100%'},
                { label: 'Received Date To', type: 'date', name: 'receivedDateTo', width:'100%'},
                // { label: 'Approved Date From', type: 'date', name: 'approvedFrom', width:'90%'},
                // { label: 'Approved Date  To', type: 'date', name: 'approvedTo', width:'90%'},
                // { label : 'Status', type: 'dropDown', name: 'status', dropDownValue: this.getSearchDropDownValues(),width:'90%'},
                // { label: 'User Id', type: 'text', name: 'userId', width:'90%'}
            ];
        }
    }
})();
