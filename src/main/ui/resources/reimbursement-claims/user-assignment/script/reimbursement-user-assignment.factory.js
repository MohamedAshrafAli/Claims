(function() {
    'use strict';

    angular
        .module('claims')
        .factory('ReimbursementUserAssignmentFactory', ReimbursementUserAssignmentFactory)
    
    ReimbursementUserAssignmentFactory.$inject = [];
    function ReimbursementUserAssignmentFactory() {
        this.getSearchFields = function() {
            return [
                // { label: 'Member Number', type: 'text', name: 'memberNo', width:'100%'},
                { label: 'Member Number', type: 'autoSearch', name: 'memberNumber', autoCompleteClass:'autoSearch', iconClass:'searchAutoIcon', width:'135%', class: 'searchCol-relative-pos', autoCompleteInfo : {methodName: 'getMemberNumberList', displayName: 'ULME_MEMBER_ID'}},
                { label: 'Claim Number', type: 'text', name: 'claimNumber', width:'90%'},
                { label: 'Approved By', type: 'autoSearch', name: 'name', width: '135%', iconClass:'searchAutoIcon', class: 'searchCol-relative-pos', autoCompleteClass:'autoSearch'},
                { label: 'Request Recevied From', type: 'date', name: 'reqReceivedFrom', width: '165%'},
                { label: 'Request Recevied To', type: 'date', name: 'reqReceivedTo', width: '160%'},
                { label: 'Assigned User', type: 'autoSearch', name: 'name', width: '135%', iconClass:'searchAutoIcon', class: 'searchCol-relative-pos', autoCompleteClass:'autoSearch'}
            ];
        }

        return this;
    }
})();    

