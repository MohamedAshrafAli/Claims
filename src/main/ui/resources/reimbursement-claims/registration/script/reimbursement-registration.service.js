(function() {
    'use strict';

    angular
        .module('claims')
        .service('ReimbursementRegistrationService', ReimbursementRegistrationService)
    
    ReimbursementRegistrationService.$inject = ['$resource', 'urlRoutePrefix', 'ReimbursementRegistrationFactory'];
    function ReimbursementRegistrationService($resource, urlRoutePrefix, ReimbursementRegistrationFactory) {
        var resourceUrl = urlRoutePrefix + '/api/medical/claims/reimbursement';
        var compId = "0021";
        return $resource(resourceUrl, {}, {
            'getReimbursementRegistrationDetails' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementRegistrationDetails',
                isArray : true
            },
            'getReimbursementRegistrationDetailsForPolicyAndMemberNo' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementRegistrationDetailsForPolicyAndMemberNo',
                isArray : true
            },
            'saveRegistrationDetails' : {
                method: 'POST',
                url: resourceUrl + '/saveRegistrationDetails/'+ compId,
            }
        })
    }
})();