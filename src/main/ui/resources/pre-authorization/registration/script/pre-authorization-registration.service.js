(function() {
    'use strict';

    angular
        .module('claims')
        .service('PreAuthorizationRegistrationService', PreAuthorizationRegistrationService)
    
    PreAuthorizationRegistrationService.$inject = [];
    function PreAuthorizationRegistrationService() {
        this.claimObj = {};
        this.createRegDetailObj = function() {
            return {
                "memberName" : null,
                "mobileNum1" : null,
                "email" : null,
                'mobileNum2' : null,
                "encType" : null,
                "reqType" : null,
                "reqReceivedDate" : null,
                "prevRequest" : null,
                "source" : "post",
                "claimType" : 'Elective'
            };
        }

        this.registerClaim = function(data) {
            console.log("data :: ", data);
        }

        this.getClaimRegistrationList = function(params) {
            return [
                {
                    "memberName" : 'Naseeha',
                    "mobileNum1" : 9894838851,
                    "email" : 'naseeha1@gmail.com',
                    'mobileNum2' : 9894838851,
                    "reqType" : 'resubmission',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "9566340416",
                    "policyNumber": "0792418901",
                    "claimType" : "Emergency",
                    "providerCode" : 'Prov1',
                    "providerName" : 'Provider-1',
                    "rejectionReason" : 'Rejected',
                    'faxNumber' : '+44 161 999 8888'
                },
                {
                    "memberName" : 'sherif',
                    "mobileNum1" : 9874563214,
                    "email" : 'sherif65@gmail.com',
                    'mobileNum2' : 98444438851,
                    "reqType" : 'newRequest',
                    "encType" : 'inPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "3457678876",
                    "policyNumber": "0884422909",
                    "claimType" : "Elective",
                    "providerCode" : 'Prov2',
                    "providerName" : 'Provider-2',
                    "rejectionReason" : 'Rejected',
                    'faxNumber' : '+1 212 999 8888'
                },
                
                {
                    "memberName" : 'Shakhil',
                    "mobileNum1" : 7864388510,
                    "email" : 'shakhil@gmail.com',
                    'mobileNum2' : 98444438851,
                    "reqType" : 'resubmission',
                    "encType" : 'inPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "9324140416",
                    "policyNumber": "0909886612",
                    "claimType" : "Elective",
                    "providerCode" : 'Prov3',
                    "providerName" : 'Provider-3',
                    'faxNumber' : '+1 212 999 7777'
                },
                {
                    "memberName" : 'Rayaan',
                    "mobileNum1" : 8958438851,
                    "email" : 'rayaan65@gmail.com',
                    'mobileNum2' : 9844738851,
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "4196754416",
                    "policyNumber": "0304532091",
                    "claimType" : "Emergency",
                    "providerCode" : 'Prov4',
                    "providerName" : 'Provider-4',
                    'faxNumber' : '+44 161 999 5555'
                },
                {
                    "memberName" : 'Rizwan',
                    "mobileNum1" : 9844388565,
                    "email" : 'rizwan65@gmail.com',
                    'mobileNum2' : 98444438851,
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "9181140413",
                    "policyNumber": "0903322091",
                    "claimType" : "Elective",
                    "providerCode" : 'Prov5',
                    "providerName" : 'Provider-5',
                    "rejectionReason" : 'Rejected',
                    'faxNumber' : '+1 212 999 6666'
                },
                {
                    "memberName" : 'Mahateer',
                    "mobileNum1" : 6844438851,
                    "email" : 'mahateer65@gmail.com',
                    'mobileNum2' : 98444438851,
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "3366312416",
                    "policyNumber": "0904532091",
                    "claimType" : "Emergency",
                    "providerCode" : 'Prov6',
                    "providerName" : 'Provider-6',
                    'faxNumber' : '+44 161 999 6666'
                },
                {
                    "memberName" : 'Mahira',
                    "mobileNum1" : 7844438851,
                    "email" : 'mahira65@gmail.com',
                    'mobileNum2' : 98444438851,
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "7509340916",
                    "policyNumber": "0904221091",
                    "claimType" : "Elective",
                    "providerCode" : 'Prov7',
                    "providerName" : 'Provider-7',
                    'faxNumber' : '+44 161 999 3333'
                },
                {
                    "memberName" : 'Sumaiya',
                    "mobileNum1" : 8844438851,
                    "email" : 'sumaiya65@gmail.com',
                    'mobileNum2' : 9844438851,
                    "reqType" : 'newRequest',
                    "encType" : 'outPatient',
                    "reqReceivedDate" : new Date(),
                    "prevRequest" : 123,
                    "source" : "post",
                    "memberNumber": "8566340416",
                    "policyNumber": "0904588291",
                    "claimType" : "Elective",
                    "providerCode" : 'Prov8',
                    "providerName" : 'Provider-8',
                    'faxNumber' : '+1 212 999 1111'
                }

            ]
        }

        this.getUsers = function() {
            return [{
                "memberName" : 'Mahira',
                "memberNumber" : "98444438851",
                "email" : 'mahira65@gmail.com',
                'mobileNum2' : 98444438851,
                "reqType" : 'newRequest',
                "encType" : 'outPatient',
                "reqReceivedDate" : new Date(),
                "prevRequest" : 123,
                "source" : "post",
                "memberNumber": "7509340916",
                "policyNumber": "2342344",
                "claimType" : "Elective",
                "providerCode" : 'Prov1',
                "providerName" : 'Provider-1'
            },{
                "memberName" : 'Sumaiya',
                "memberNumber" : "98444438855",
                "email" : 'sumaiya65@gmail.com',
                'mobileNum2' : 98444438851,
                "reqType" : 'newRequest',
                "encType" : 'outPatient',
                "reqReceivedDate" : new Date(),
                "prevRequest" : 123,
                "source" : "post",
                "memberNumber": "8566340416",
                "policyNumber": "7897878",
                "claimType" : "Elective",
                "providerCode" : 'Prov2',
                "providerName" : 'Provider-2'
            }];
        }
        
        this.searchClaims = function(params) {
            return this.getClaimRegistrationList(params);
        }

        this.setClaim = function(claim) {
            this.claimObj = claim;
        }

        this.getClaim = function(claim) {
            return this.claimObj;
        }

        this.getDocumentTypes = function() {
            return [
                {id:'reimbursementForm', label:'Reimbursement Form', isRequired:false},
                {id:'medicalBills', label:'Medical Bills', isRequired:true},
                {id:'medicalBillsSummary', label:'Medical Bills Summary', isRequired:false},
                {id:'prescription', label:'Prescription', isRequired:true},
                {id:'otherDocuments', label:'Other Documents', isRequired:false}
            ]
        }

        this.getSearchFields = function() {
            return [
                { label: 'Member Number', type: 'autoSearch', name: 'memberNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos'},
                { label: 'Policy Number', type: 'autoSearch', name: 'policyNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'90%', class: 'searchCol-relative-pos'}
            ];
        }
    }
})();