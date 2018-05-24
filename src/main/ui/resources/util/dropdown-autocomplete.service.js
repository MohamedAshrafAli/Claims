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
                }
            })
        }
})()