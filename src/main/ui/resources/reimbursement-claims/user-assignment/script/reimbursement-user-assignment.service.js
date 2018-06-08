(function() {
    'use strict';    
    angular
        .module('claims')
        .service('ReimbursementUserAssignmentService', ReimbursementUserAssignmentService)

    ReimbursementUserAssignmentService.$inject = ['$resource', 'urlRoutePrefix'];

    function ReimbursementUserAssignmentService($resource, urlRoutePrefix) {
        var resourceUrl = urlRoutePrefix + '/api/medical/claims/reimbursement';
        var compId = "0021";
        return $resource(resourceUrl, {}, {
            'getReimbursementAssignmentDetails' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementAssignmentDetails',
                isArray : true
            },
            'saveAssignmentDetails' : {
                method: 'POST',
                url: resourceUrl + '/saveAssignmentDetails/'+ compId,
                isArray : true
            }            
        })            
    }
})();
