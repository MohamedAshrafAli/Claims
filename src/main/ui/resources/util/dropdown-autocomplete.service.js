(function() {
    'use strict';

    angular
        .module('claims')
        .service('DropdownAutocompleteService', DropdownAutocompleteService);
    
        DropdownAutocompleteService.$inject = ['$resource', 'urlRoutePrefix'];

        function DropdownAutocompleteService($resource, urlRoutePrefix) {
            var resourceUrl = '/medical/claims/ui';

            return $resource(resourceUrl, {}, {
                'getEncounterTypes' : {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getEncounterTypes'
                },
                'getRequestTypes' : {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getRequestTypes'
                },
                'getReportByTypes' : {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getReportByTypes'
                },
                'getSourceTypes' : {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getSourceTypes'
                },
                'getPaymentTypes' : {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getPaymentTypes'
                },
                'getDocumentTypes' : {
                    method: 'POST',
                    url: urlRoutePrefix + resourceUrl + '/getDocumentTypes'
                }

            })
        }

})()

