(function() {
    'use strict';

    angular
        .module('claims')
        .service('UIDefinationService', UIDefinationService);
    
        UIDefinationService.$inject = ['$http', 'urlRoutePrefix'];

        function UIDefinationService($resource, urlRoutePrefix) {
            var resourceUrl = urlRoutePrefix + '/api/medical/claims/uid';
            return $resource(resourceUrl, {}, {
                'getEncounterTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getEncounterTypes'
                }
            })    
        }
})()