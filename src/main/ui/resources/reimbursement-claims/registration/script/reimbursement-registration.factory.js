(function() {
    'use strict';

    angular
        .module('claims')
        .factory('ReimbursementRegistrationFactory', ReimbursementRegistrationFactory)
    
    ReimbursementRegistrationFactory.$inject = [];
    function ReimbursementRegistrationFactory() {

        this.claimObj = {};
        this.createRegDetailObj = function() {
            return {
                "memberName" : null,
                "mobileNum1" : null,
                "email1" : null,
                'mobileNum2' : null,
                "email2" : null,
                "serviceFmDate" : null,
                "requestAmt" : null,
                "encType" : null,
                "reqType" : null,
                "reqReceivedDate" : null,
                "prevRequest" : null,
                "source" : "post",
                "voucherNumber" : null,
                "ibanNum" : null,
                "emiratesId":null,
                "paymentWay" : 'iban'
            };
        }

        this.registerClaim = function(data) {
            console.log("data :: ", data);
        }

        this.getUsers = function() {
            return [{
                "memberName" : 'Mahira',
                "memberNumber" : "98444438851",
                "email1" : 'mahira65@gmail.com',
                'mobileNum2' : 98444438851,
                "email2" : 'mahira32@gmail.com',
                "serviceFmDate" : new Date(),
                "requestAmt" : 55555,
                "reqType" : 'newRequest',
                "encType" : 'outPatient',
                "reqReceivedDate" : new Date(),
                "prevRequest" : 123,
                "source" : "post",
                "voucherNumber" : 36978,
                "ibanNum" : "1909465ABSCSD",
                "emiratesId":89064,
                "memberNumber": "7509340916",
                "policyNumber": "2342344",
                "paymentWay" : "cheque"
            },{
                "memberName" : 'Sumaiya',
                "memberNumber" : "98444438855",
                "email1" : 'sumaiya65@gmail.com',
                'mobileNum2' : 98444438851,
                "email2" : 'sumaiya32@gmail.com',
                "serviceFmDate" : new Date(),
                "requestAmt" : 55555,
                "reqType" : 'newRequest',
                "encType" : 'outPatient',
                "reqReceivedDate" : new Date(),
                "prevRequest" : 123,
                "source" : "post",
                "voucherNumber" : '78915',
                "ibanNum" : "1856465ABSCSD",
                "emiratesId":78451,
                "memberNumber": "8566340416",
                "policyNumber": "7897878",
                "paymentWay" : "cheque"
            }];
        }
        
        this.searchClaims = function(params) {
            return this.getClaimRegistrationList(params);
        }

        this.convertDate = function(data) {
            console.log("Data ::" ,data);
            return new Date(data);
        }

        this.setClaim = function(claim) {
            this.claimObj = claim;
        }

        this.getClaim = function(claim) {
            return this.claimObj;
        }

        this.getDocumentTypes = function() {
            return [
                {id:'iban', label:'Iban', isRequired:true},
                {id:'reimbursementForm', label:'Reimbursement Form', isRequired:false},
                {id:'medicalBills', label:'Medical Bills', isRequired:true},
                {id:'medicalBillsSummary', label:'Medical Bills Summary', isRequired:false},
                {id:'prescription', label:'Prescription', isRequired:true},
                {id:'otherDocuments', label:'Other Documents', isRequired:false}
            ]
        }

        this.getSearchFields = function() {
            return [
                { label: 'Card Number', type: 'autoSearch', name: 'cardNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos', autoCompleteInfo : {methodName: 'getMedicalCardNumberList', displayName: 'CLMR_TPA_CARD'}},
                { label: 'Member Name', type: 'autoSearch', name: 'memberName', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos',  autoCompleteInfo : {methodName: 'getMemberNameList', displayName: 'CLMR_MEMBER_NAME'}},
                { label: 'Emirates  Id', type: 'autoSearch', name: 'emiratesId', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos',  autoCompleteInfo : {methodName: 'getEmiratesId', displayName: 'CLMR_UID_ID'}},
                { label: 'Voucher  Number', type: 'text', name: 'voucherNumber', width:'90%'},
                { label: 'Policy Number', type: 'autoSearch', name: 'policyNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos',  autoCompleteInfo : {methodName: 'getClaimsPolicyNumberList', displayName: 'CLF_ULM_NO'}}
            ];
        }

        return this;
    }
})();