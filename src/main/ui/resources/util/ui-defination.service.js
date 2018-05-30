(function() {
    'use strict';

    angular
        .module('claims')
        .service('UIDefinationService', UIDefinationService);
    
        UIDefinationService.$inject = ['$resource', 'urlRoutePrefix'];

        function UIDefinationService($resource, urlRoutePrefix) {
            var resourceUrl = urlRoutePrefix + '/api/medical/claims/uid';
            return $resource(resourceUrl, {}, {
                'getEncounterTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getEncounterTypes'
                },
                'getRequestTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getRequestTypes'
                },
                'getReportByTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getReportByTypes'
                },
                'getSourceTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getSourceTypes'
                },
                'getPaymentTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getPaymentTypes'
                },
                'getDocumentTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getDocumentTypes'
                },
                'getStatusTypes' : {
                    method: 'POST',
                    url: resourceUrl + '/getStatusTypes'
                }
            })    
        }
})()