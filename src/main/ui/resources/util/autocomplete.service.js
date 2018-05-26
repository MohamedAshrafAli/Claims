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
                }
            })
        }
})()