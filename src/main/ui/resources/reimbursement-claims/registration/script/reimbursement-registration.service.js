(function() {
    'use strict';

    angular
        .module('claims')
        .service('ReimbursementRegistrationService', ReimbursementRegistrationService)
    
    ReimbursementRegistrationService.$inject = ['$resource', 'urlRoutePrefix', 'ReimbursementRegistrationFactory'];
    function ReimbursementRegistrationService($resource, urlRoutePrefix, ReimbursementRegistrationFactory) {
        var resourceUrl = '/api/medical/claims/reimbursement';

        return $resource(resourceUrl, {}, {
            'getReimbursementRegistrationDetails' : {
                method: 'POST',
                url: urlRoutePrefix + resourceUrl + '/getReimbursementRegistrationDetails',
                isArray : true
            }
        })
    }
})();