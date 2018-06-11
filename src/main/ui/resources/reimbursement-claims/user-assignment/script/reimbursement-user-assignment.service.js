(function() {
    'use strict';    
    angular
        .module('claims')
        .service('ReimbursementUserAssignmentService', ReimbursementUserAssignmentService)

    ReimbursementUserAssignmentService.$inject = ['$resource', 'urlRoutePrefix', 'companyId'];

    function ReimbursementUserAssignmentService($resource, urlRoutePrefix, companyId) {
        var resourceUrl = urlRoutePrefix + '/api/medical/claims/reimbursement';
        return $resource(resourceUrl, {}, {
            'getReimbursementAssignmentDetails' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementAssignmentDetails',
                isArray : true
            },
            'saveAssignmentDetails' : {
                method: 'POST',
                url: resourceUrl + '/saveAssignmentDetails/'+ companyId,
                isArray : true
            }            
        })            
    }
})();
