(function() {
    'use strict';

    angular
        .module('claims')
        .service('ListViewService', ListViewService);
    
        ListViewService.$inject = [];
        var requestData = {};

        function ListViewService() {

            this.getRegistrationListViewHeader = function() {
                return [
                    {'label': 'Claim Ref Number', 'fieldName' : 'claimRefNo', 'redirectable' : true},
                    {'label': 'Card Number', 'fieldName' : 'cardNumber', 'redirectable' : false},
                    {'label': 'Member Name', 'fieldName' : 'memberName', 'redirectable' : false},
                    {'label': 'Emirates Id', 'fieldName' : 'uidId', 'redirectable' : false},
                    {'label': 'Voucher Number', 'fieldName' : 'voucherNumber', 'redirectable' : false},
                    {'label': 'Policy Number', 'fieldName' : 'policyNumber', 'redirectable' : false},
                    {'label': 'Mobile Number', 'fieldName' : 'mobileNum1', 'redirectable' : false},
                    {'label': 'Service From Date', 'fieldName' : 'serviceFmDate', 'redirectable' : false, type:"date"}
                ];
            }

            this.getRemittanceListViewHeader = function() {
                return [
                    {'label': 'Batch Id', 'fieldName' : 'batchId', 'redirectable' : false},
                    {'label': 'Batch File Name', 'fieldName' : 'batchFileName', 'redirectable' : false},
                    {'label': 'Received Date ', 'fieldName' : 'receivedDate', 'redirectable' : false,type:"date"},
                    {'label': 'Provider Code', 'fieldName' : 'providerCode', 'redirectable' : false},
                    {'label': 'Provider License ', 'fieldName' : 'providerLicense', 'redirectable' : false},
                    {'label': 'Provider Name', 'fieldName' : 'providerName', 'redirectable' : false},
                    {'label': 'Remittance Upload Date', 'fieldName' : 'remittanceUploadDate', 'redirectable' : false,type:"date"},
                ];
            }

            this.getPreAuthorizationRegistrationListViewHeader = function() {
                return [
                    {'label': 'Member Number', 'fieldName' : 'memberNumber', 'redirectable' : false},
                    {'label': 'Member Name', 'fieldName' : 'memberName', 'redirectable' : false},
                    {'label': 'Policy Number', 'fieldName' : 'policyNumber', 'redirectable' : false},
                    {'label': 'Provider Name', 'fieldName' : 'providerName', 'redirectable' : false},
                    {'label': 'Claim Type', 'fieldName' : 'claimType', 'redirectable' : false},
                    {'label': 'Fax Number', 'fieldName' : 'faxNumber', 'redirectable' : false},
                    {'label': 'Request Received Date', 'fieldName' : 'reqReceivedDate', 'redirectable' : false, type:"date"}
                ];
            }

            this.setRequestData = function(request) {
                this.requestData = request;
            }
    
            this.getRequestData = function() {
                return this.requestData;
            }

            this.getUserAssignmentListViewHeader = function() {
                return [
                    {'label': 'User Id', 'fieldName' : 'UserId'},
                    {'label': 'Name', 'fieldName' : 'UserName'},
                    {'label': 'Assigned', 'fieldName' : 'assigned', 'class' : 'text-center'},
                    {'label': 'Pending', 'fieldName' : 'pending', 'class' : 'text-center'},
                    {'label': '', 'fieldName' : 'isSelected', 'isCheckBox' : true}
                ];
            }
        }

})();