(function() {
    'use strict';

    angular
        .module('claims')
        .service('ReimbursementRegistrationService', ReimbursementRegistrationService)
    
    ReimbursementRegistrationService.$inject = ['$resource', 'urlRoutePrefix', 'companyId'];
    function ReimbursementRegistrationService($resource, urlRoutePrefix, companyId) {
        var resourceUrl = urlRoutePrefix + '/api/medical/claims/reimbursement';
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
                url: resourceUrl + '/saveRegistrationDetails/'+ companyId
            },
            'getReimbursementRegistrationDetailsById' : {
                method: 'GET',
                url: resourceUrl + '/getReimbursementRegistrationDetails/:id'
            },
            'getReimbursementRegistrationDocument' : {
                method: 'GET',
                url: resourceUrl + '/getReimbursementRegistrationDocument?pathName=:pathName',
                responseType: 'arraybuffer',
                transformResponse: function(data) { 
                    return { 
                        data : data 
                    }
                }
            },
            'deleteRegistrationFile' : {
                method: 'POST',
                url: resourceUrl + '/deleteRegistrationFile',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                params: {
                    sgsid : '',
                    docType : '',
                    docName : '',
                    path : ''
                }
            }
        })
    }
})();