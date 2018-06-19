(function() {
    'use strict';    
    angular
        .module('claims')
        .service('ReimbursementProcessingService', ReimbursementProcessingService)

    ReimbursementProcessingService.$inject = ['$resource', 'urlRoutePrefix', 'companyId'];

    function ReimbursementProcessingService($resource, urlRoutePrefix, companyId) {
        var resourceUrl = urlRoutePrefix + '/api/medical/claims/reimbursement';
        return $resource(resourceUrl, {}, {
            'getReimbursementInitProcessingDetails' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementInitProcessingDetails',
            },
            'saveProcessingDetails' : {
                method: 'POST',
                url: resourceUrl + '/saveProcessingDetails/'+ companyId,
                isArray : false
            },
            'getReimbursementProcessingDetails' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementProcessingDetails',
                isArray : false,
                transformResponse: function(data) {
                    return {
                        data : data
                    }
                }
            },
            'approveProcessingServiceLineItem' : {
                method: 'POST',
                url: resourceUrl + '/approveProcessingServiceLineItem/'+ companyId,
                isArray : false
            },
            'getReimbursementProcessingDetailsForAssignment' : {
                method: 'POST',
                url: resourceUrl + '/getReimbursementProcessingDetailsForAssignment',
                isArray : false
            },
            'getReimbursementProcessingServiceDetails' : {
                method : 'GET',
                url: resourceUrl + '/getReimbursementProcessingServiceDetails',
                isArray : true
            }
        })
    }
})();