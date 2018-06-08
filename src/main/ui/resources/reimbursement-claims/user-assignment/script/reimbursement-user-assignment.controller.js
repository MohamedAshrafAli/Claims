(function() {
    'use strict';
    angular
        .module('claims')
        .controller('ReimbursementUserAssignmentController', ReimbursementUserAssignmentController)

    ReimbursementUserAssignmentController.$inject = ['$scope', '$rootScope', 'ReimbursementUserAssignmentService', '$filter', '$state', '$stateParams', 'ngNotify', 'ListViewService', 'ClaimsListViewService', 'ReimbursementUserAssignmentFactory', 'AutocompleteService', 'ReimbursementRegistrationFactory', 'SpinnerService'];

    function ReimbursementUserAssignmentController($scope, $rootScope, ReimbursementUserAssignmentService, $filter, $state, $stateParams, ngNotify, ListViewService, ClaimsListViewService, ReimbursementUserAssignmentFactory, AutocompleteService, ReimbursementRegistrationFactory, SpinnerService) {
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
                searchparam.reqReceivedFrom = searchparam.reqReceivedFrom ? $filter('date')(new Date(searchparam.reqReceivedFrom), 'yyyy-MM-dd') : undefined;
                searchparam.reqReceivedTo = searchparam.reqReceivedTo ? $filter('date')(new Date(searchparam.reqReceivedTo), 'yyyy-MM-dd') : undefined;
                getClaimList(searchparam);
            } else {
                getClaimList({compId : "0021"});
            }
        }

        $scope.assignedToSelectedUser = function() {
            if ($scope.selectedUserToAssign != null && $scope.claimsToAssign != null && $scope.claimsToAssign.length > 0) {
                if (true) {
                    var userDto = {
                        userId : $scope.selectedUserToAssign.UserId,
                        userGroupId : $scope.selectedUserToAssign.UserGroupId
                    };
                    angular.forEach($scope.claimsToAssign, function(value, key) {
                        userDto.slId = value.id;
                        value.assignedUserDetailsDTO = userDto
                    }) 
                    SpinnerService.start();
                    ReimbursementUserAssignmentService.saveAssignmentDetails($scope.claimsToAssign, function(resp) {
                        angular.forEach($scope.claimsToAssign, function(claim, claimIndex) {
                            for(var key in $scope.claimList) {
                                var actualClaim = $scope.claimList[key];
                                if (claim['id'] == actualClaim['id']) {
                                    $scope.claimList[key] = resp[claimIndex];
                                    $scope.claimList[key]['status'] = 'Assigned';
                                    $scope.claimList[key]['selected'] = false;
                                    break;
                                }
                            }
                        })
                        $scope.rerenderView = !$scope.rerenderView;
                        $scope.selectedUserToAssign.assigned += ($scope.claimsToAssign.length);
                        $scope.claimsToAssign = [];
                        SpinnerService.stop();
                        ngNotify.set('Request Assigned Succesfully.', 'success');
                    }, onError)
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
            SpinnerService.start();
            ReimbursementUserAssignmentService.getReimbursementAssignmentDetails(searchParams, function(resp) {
                SpinnerService.stop();
                $scope.recordTotal = resp.length;
                $scope.claimList = resp;
                $scope.rerenderView = !$scope.rerenderView;
            }, onError)
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

        function onError() {
            SpinnerService.stop();
        }
        
        init();
    }    
})();