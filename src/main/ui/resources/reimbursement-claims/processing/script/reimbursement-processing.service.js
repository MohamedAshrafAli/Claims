(function() {
    'use strict';    
    angular
        .module('claims')
        .service('ReimbursementProcessingService', ReimbursementProcessingService)

    ReimbursementProcessingService.$inject = ['$resource', 'urlRoutePrefix'];

    function ReimbursementProcessingService($resource, urlRoutePrefix) {
        var resourceUrl = urlRoutePrefix + '/api/medical/claims/reimbursement';
        var compId = "0021";
        return $resource(resourceUrl, {}, {
            'getReimbursementInitProcessingDetails' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementInitProcessingDetails',
            },
            'saveProcessingDetails' : {
                method: 'POST',
                url: resourceUrl + '/saveProcessingDetails/'+ compId,
                isArray : false
            }
        })
    }
})();