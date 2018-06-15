(function() {
    'use strict';
    angular
        .module('claims')
        .controller('ReimbursementUserAssignmentController', ReimbursementUserAssignmentController)

    ReimbursementUserAssignmentController.$inject = ['$scope', '$rootScope', 'ReimbursementUserAssignmentService', '$filter', '$state', 'ngNotify', 'ListViewService', 'ClaimsListViewService', 'ReimbursementUserAssignmentFactory', 'AutocompleteService', 'ReimbursementRegistrationFactory', 'SpinnerService', 'companyId','UIDefinationService', '$q'];

    function ReimbursementUserAssignmentController($scope, $rootScope, ReimbursementUserAssignmentService, $filter, $state, ngNotify, ListViewService, ClaimsListViewService, ReimbursementUserAssignmentFactory, AutocompleteService, ReimbursementRegistrationFactory, SpinnerService, companyId, UIDefinationService, $q) {
        $scope.model = "reimbursementUserAssignment";
        $scope.selectall = false;
        $scope.selectedUserToAssign;
        $scope.claimsToAssign = [];
        $scope.claimList = [];
        $scope.rerenderView = false;
        $scope.filteredClaims = [];
        $scope.searchBy = {};

        var autoCompleteMapping = {
            memberNumber : 'ULME_MEMBER_ID'
        }
        $scope.filterValues = function(searchValue) {
            if (searchValue) {
                var searchparam = ReimbursementRegistrationFactory.constructSearchObj(autoCompleteMapping, searchValue);
                searchparam.compId = companyId;
                searchparam.reqReceivedFrom = searchparam.reqReceivedFrom ? $filter('date')(new Date(searchparam.reqReceivedFrom), 'yyyy-MM-dd') : undefined;
                searchparam.reqReceivedTo = searchparam.reqReceivedTo ? $filter('date')(new Date(searchparam.reqReceivedTo), 'yyyy-MM-dd') : undefined;
                getClaimList(searchparam);
            } else {
                getClaimList({compId : companyId});
            }
        }
        var statusParam = {"compId" : companyId, "modType" : "03"};
        UIDefinationService.getStatusTypes(statusParam, function(resp) {
            $scope.statusMap = ReimbursementRegistrationFactory.constructUidMap(resp.rowData, "value", "id");
            getClaimListRecordsByStatus();
        });

        $scope.assignedToSelectedUser = function() {
            if ($scope.selectedUserToAssign != null && $scope.claimsToAssign != null && $scope.claimsToAssign.length > 0) {
                if ($scope.selectedUserToAssign.assigned < 15) {
                    var userDto = {
                        userId : $scope.selectedUserToAssign.UserId,
                        userGroupId : $scope.selectedUserToAssign.UserGroupId
                    };
                    angular.forEach($scope.claimsToAssign, function(value, key) {
                        userDto.slId = value.id;
                        value.assignedUserDetailsDTO = userDto;
                        value.status = $scope.statusMap['Assigned'];
                    }) 
                    SpinnerService.start();
                    ReimbursementUserAssignmentService.saveAssignmentDetails($scope.claimsToAssign, function(resp) {
                        angular.forEach($scope.claimsToAssign, function(claim, claimIndex) {
                            for(var key in $scope.claimList) {
                                var actualClaim = $scope.claimList[key];
                                if (claim['id'] == actualClaim['id']) {
                                    $scope.claimList[key] = resp[claimIndex];
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
            //getClaimList({compId: companyId});
            $scope.userAssignmentHeader = ListViewService.getUserAssignmentListViewHeader();
            $scope.result = $scope.users;
            $scope.tabsToDisplay = ClaimsListViewService.getReimbursementTabsToDisplay();
            $scope.fieldsObject =  ReimbursementUserAssignmentFactory.getSearchFields();
        }

        function getClaimList(searchParams) {
            searchParams.Status = $scope.status;
            SpinnerService.start();
            ReimbursementUserAssignmentService.getReimbursementAssignmentDetails(searchParams, function(resp) {
                SpinnerService.stop();
                $scope.recordTotal = resp.length;
                $scope.claimList = resp;
                $scope.rerenderView = !$scope.rerenderView;
            }, onError)
        }        

        function onError() {
            SpinnerService.stop();
        }

        $scope.getCurrentTabRecord = function(status) {    
            $scope.status = status;
            getClaimList({compId : companyId});
        }

        function getClaimListRecordsByStatus() {
            SpinnerService.start();
            var promises = {};
            promises['newRequest'] = ReimbursementUserAssignmentService.getReimbursementAssignmentDetails({compId : companyId, Status : ""}).$promise;
            promises['Assigned'] = ReimbursementUserAssignmentService.getReimbursementAssignmentDetails({compId : companyId, Status : $scope.statusMap['Assigned']}).$promise;
            promises['Approved'] = ReimbursementUserAssignmentService.getReimbursementAssignmentDetails({compId : companyId, Status : $scope.statusMap['Approved']}).$promise;
            $q.all(promises).then((records) => {
                SpinnerService.stop();
                var claimRecords = {};
                claimRecords['newRequest'] = records['newRequest'];
                claimRecords['Assigned'] = records['Assigned'];
                claimRecords['Approved'] = records['Approved'];
                $scope.recordTotal = claimRecords['newRequest'].length;
                $scope.claimList = claimRecords['newRequest'];
                $scope.rerenderView = !$scope.rerenderView;
                console.log("claimRecords :: ", claimRecords);
            }).catch((err) => {
                SpinnerService.stop();
                alert("Error");
            })
        }
        
        init();
    }    
})();