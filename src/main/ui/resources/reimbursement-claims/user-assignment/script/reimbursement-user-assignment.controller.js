(function() {
    'use strict';
    angular
        .module('claims')
        .controller('ReimbursementUserAssignmentController', ReimbursementUserAssignmentController)

    ReimbursementUserAssignmentController.$inject = ['$scope', '$rootScope', 'ReimbursementUserAssignmentService', '$filter', '$state', '$stateParams', 'ngNotify', 'ListViewService', 'ClaimsListViewService', 'ReimbursementUserAssignmentFactory', 'AutocompleteService', 'ReimbursementRegistrationFactory'];

    function ReimbursementUserAssignmentController($scope, $rootScope, ReimbursementUserAssignmentService, $filter, $state, $stateParams, ngNotify, ListViewService, ClaimsListViewService, ReimbursementUserAssignmentFactory, AutocompleteService, ReimbursementRegistrationFactory) {
        $scope.model = "reimbursementUserAssignment";
        $scope.selectall = false;
        $scope.selectedUserToAssign;
        $scope.claimsToAssign = [];
        $scope.claimList = [];
        $scope.rerenderView = false;
        $scope.filteredClaims = [];
        $scope.searchBy = {};

        var autoCompleteMapping = {
            memberNumber : 'ULME_MEMBER_ID',
        }
        $scope.filterValues = function(searchValue) {
            if (searchValue) {
                var searchparam = ReimbursementRegistrationFactory.constructSearchObj(autoCompleteMapping, searchValue);
                searchparam.compId = "0021";
                searchparam.reqReceivedFrom = searchparam.reqReceivedFrom ? $filter('date')(new Date(searchparam.reqReceivedFrom), 'yyyy-MM-dd') : null;
                searchparam.reqReceivedTo = searchparam.reqReceivedTo ? $filter('date')(new Date(searchparam.reqReceivedTo), 'yyyy-MM-dd') : null;
                getClaimList(searchparam);
            } else {
                getClaimList({compId : "0021"});
            }
        }

        $scope.assignedToSelectedUser = function() {
            if ($scope.selectedUserToAssign != null && $scope.claimsToAssign != null && $scope.claimsToAssign.length > 0) {
                if ($scope.selectedUserToAssign.assigned < 15) {
                    ReimbursementUserAssignmentService.saveAssignmentDetails($scope.claimsToAssign, function(resp) {
                        console.log("resp ::", resp);
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
                    })
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
            $state.go('reimbursement-processing');
        }        

        function init() {
            getClaimList({compId: "0021"});
            getUsersList();
            $scope.userAssignmentHeader = ListViewService.getUserAssignmentListViewHeader();
            $scope.result = $scope.users;
            $scope.tabsToDisplay = ClaimsListViewService.getReimbursementTabsToDisplay();
            $scope.fieldsObject =  ReimbursementUserAssignmentFactory.getSearchFields();
        }

        function getClaimList(searchParams) {
            ReimbursementUserAssignmentService.getReimbursementAssignmentDetails(searchParams, function(resp) {
                $scope.recordTotal = resp.length;
                $scope.claimList = resp;
                $scope.rerenderView = !$scope.rerenderView;
            })
        }

        function getUsersList() {
            var params = {
                compId : "0021",
                userName : "%"
            }
            AutocompleteService.getUserList(params, function(resp) {
                $scope.users = resp.rowData;
                $scope.userssearch = $scope.users;
            })
        }
        
        init();
    }    
})();