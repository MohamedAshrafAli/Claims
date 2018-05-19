(function() {
    'use strict';
    angular
        .module('claims')
        .controller('ProviderPaperUserAssignmentController', ProviderPaperUserAssignmentController)

    ProviderPaperUserAssignmentController.$inject = ['$scope', '$rootScope', 'ProviderPaperUserAssignmentService', '$filter', '$state', '$stateParams', 'ngNotify', 'ListViewService', 'ClaimsListViewService'];
    function ProviderPaperUserAssignmentController($scope, $rootScope, ProviderPaperUserAssignmentService, $filter, $state, $stateParams, ngNotify, ListViewService, ClaimsListViewService) {
        $scope.model = "ProviderPaperUserAssignment";
        $scope.selectedClaim = $stateParams.param;
        $scope.selectall = false;
        $scope.selectedUserToAssign;
        $scope.claimsToAssign = [];
        $scope.claimList = [];
        $scope.rerenderView = false;
        $scope.filteredClaims = [];
        $scope.searchBy = {};

        $scope.querySearch = function(query) {
            return query ? $scope.users.filter(createFilterFor(query)) : $scope.users;
        }
        
        $scope.assignedToSelectedUser = function() {
            if ($scope.selectedUserToAssign != null && $scope.claimsToAssign != null && $scope.claimsToAssign.length > 0) {
                if ($scope.selectedUserToAssign.assigned < 15) {
                    angular.forEach($scope.claimsToAssign, function(claim, claimIndex) {
                        for(var key in $scope.claimList) {
                            var actualClaim = $scope.claimList[key];
                            if (claim['id'] == actualClaim['id']) {
                                $scope.claimList[key]['status'] = 'Assigned';
                                $scope.claimList[key]['selected'] = false;
                                break;
                            }
                        }
                    })
                    $scope.rerenderView = !$scope.rerenderView;
                    $scope.selectedUserToAssign.assigned += ($scope.claimsToAssign.length);
                    $scope.claimsToAssign = [];
                    ngNotify.set('Request Assigned Succesfully.', 'success');                
                } else {
                    swal("", "User has Request Assigned more than 15", "warning");
                }
            } else if ($scope.claimsToAssign.length == 0 && $scope.selectedUserToAssign == null) {
                swal("", "Please select claims and user to assign.", "warning");
            } else if ($scope.claimsToAssign.length == 0) {
                swal("", "Please select claims to assign.", "warning");
            } else if ($scope.selectedUserToAssign == null) {
                swal("", "Please select user to assign.", "warning");
            }
        }
       
        $scope.navigateTo = function() {
            $state.go($scope.selectedClaim == 'eclaims' ? 'eclaim' : 'reimbursement-processing');
        }

        $scope.filterValues = function(searchValue) {
            if (searchValue) {
                $scope.searchBy = {
                    providerCode :searchValue.provider,
                    batchId: searchValue.batchId
                };
            } else {
                $scope.searchBy = {};
            }
        }

        function init() {
            $scope.claim = ProviderPaperUserAssignmentService.getClaimsForUserAssignment();
            $scope.users = $scope.userNamesObject = ProviderPaperUserAssignmentService.getUsers();
            $scope.claimList = $scope.claim;
            $scope.userAssignmentHeader = ListViewService.getUserAssignmentListViewHeader();
            $scope.userssearch = $scope.users;
            $scope.recordTotal = $scope.claim.length;
            $scope.result = $scope.users;
            $scope.tabsToDisplay = ClaimsListViewService.getTabsToDisplay();
            $scope.fieldsObject =  ProviderPaperUserAssignmentService.getSearchFields();
        }

        init();
    }    
})();