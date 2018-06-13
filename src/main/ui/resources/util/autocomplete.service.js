(function() {
    'use strict';

    angular
        .module('claims')
        .service('AutocompleteService', AutocompleteService);
    
        AutocompleteService.$inject = ['$resource', 'urlRoutePrefix', 'companyId'];

        function AutocompleteService($resource, urlRoutePrefix, companyId) {
            var resourceUrl = urlRoutePrefix + '/api/medical/claims/ui';

            return $resource(resourceUrl, {}, {
                'getMemberNumberList': {
                    method: 'POST',
                    url: resourceUrl + '/getMemberNumberList'
                },
                'getPolicyNumberList': {
                    method: 'POST',
                    url: resourceUrl + '/getPolicyNumberList'
                },
                'getMedicalCardNumberList': {
                    method: 'POST',
                    url: resourceUrl + '/getMedicalCardNumberList'
                },
                'getMemberNameList': {
                    method: 'POST',
                    url: resourceUrl + '/getMemberNameList'
                },
                'getClaimsPolicyNumberList': {
                    method: 'POST',
                    url: resourceUrl + '/getClaimsPolicyNumberList'
                },
                'getEmiratesId': {
                    method: 'POST',
                    url: resourceUrl + '/getEmiratesId'
                },
                'getVoucherNumbers': {
                    method: 'POST',
                    url: resourceUrl + '/getVoucherNumbers'
                },
                'getCurrencyDetailsForPolicyNo': {
                    method: 'POST',
                    url: resourceUrl + '/getCurrencyDetailsForPolicyNo'
                },
                'getUserList': {
                    method: 'POST',
                    url: resourceUrl + '/getUserList'
                },
                'getTreatmentCodes': {
                    method: 'POST',
                    url: resourceUrl + '/getServiceCodeDetails'
                },
                'getRejectionCodes': {
                    method: 'POST',
                    url: resourceUrl + '/getRejectionCodeDetails'
                },
                'getDiagnosisCodes': {
                    method: 'POST',
                    url: resourceUrl + '/getDiagnosisCodeDetails'
                },
                //Reimbursement-Processing (providerCode)
                'getProviderCodes': {
                    method: 'POST',
                    url: resourceUrl + '/getEmiratesId'
                },                
                'getUniversalCurrencyDetails': {
                    method: 'POST',
                    url: resourceUrl + '/getUniversalCurrencyDetails'
                },
                'geteventCountry': {
                    method: 'GET',
                    url: resourceUrl +'/getCountryIds/'+ companyId,
                    isArray : true
                },
                'getStatusCountByUser': {
                    method: 'POST',
                    url: resourceUrl +'/getStatusCountByUser'
                }
            })
        }
})()