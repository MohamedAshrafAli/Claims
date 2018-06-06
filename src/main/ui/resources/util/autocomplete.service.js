(function() {
    'use strict';

    angular
        .module('claims')
        .service('AutocompleteService', AutocompleteService);
    
        AutocompleteService.$inject = ['$resource', 'urlRoutePrefix'];

        function AutocompleteService($resource, urlRoutePrefix) {
            var resourceUrl = '/api/medical/claims/ui';

            return $resource(resourceUrl, {}, {
                'getMemberNumberList': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getMemberNumberList'
                },
                'getPolicyNumberList': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getPolicyNumberList'
                },
                'getMedicalCardNumberList': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getMedicalCardNumberList'
                },
                'getMemberNameList': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getMemberNameList'
                },
                'getClaimsPolicyNumberList': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getClaimsPolicyNumberList'
                },
                'getEmiratesId': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getEmiratesId'
                },
                'getVoucherNumbers': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getVoucherNumbers'
                },
                'getCurrencyDetailsForPolicyNo': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getCurrencyDetailsForPolicyNo'
                },
                'getUserList': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getUserList'
                },
                'getTreatmentCodes': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getServiceCodeDetails'
                },
                'getRejectionCodes': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getRejectionCodeDetails'
                },
                'getDiagnosisCodes': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getDiagnosisCodeDetails'
                },
                //Reimbursement-Processing (providerCode)
                'getProviderCodes': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getEmiratesId'
                },
                //Reimbursement-Processing (Event Country)
                'geteventCountry': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getEmiratesId'
                },
                'getUniversalCurrencyDetails': {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getUniversalCurrencyDetails'
                }
            })
        }
})()