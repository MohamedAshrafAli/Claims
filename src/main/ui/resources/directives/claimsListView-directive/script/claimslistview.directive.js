(function() {
    'use strict';

    angular
        .module("claims")
            .directive("claimsListView", function($rootScope, $filter, UIDefinationService, ReimbursementRegistrationFactory, ReimbursementUserAssignmentService, ClaimsListViewService, companyId, $q, SpinnerService) {
                return {
                    restrict: 'E',
                    templateUrl: 'resources/directives/claimsListView-directive/view/claimslistview.directive.html',
                    scope: {
                        tabsToDisplay: '<',
                        allClaimRecords: '=',
                        selectedClaims: '=',
                        navigateTo: '@',
                        rerenderView: '=',
                        searchBy: '=',
                        modelDir:'=',
                        onTabChange: '&'
                    },
                    link: function(scope, element, attrs) {
                        
                        scope.selectAll = false;
                        scope.sortBy = 'receivedDateDesc';
                        scope.orderByField = 'requestRecievedOn';
                        scope.reverseSort = false;
                        scope.countByStatus = {};
                        scope.filteredClaims = [];
                        var statusParam = {'compId' : companyId,'modType' : "03"}
                        var uidefPromises = {
                            "encounterTypes" : UIDefinationService.getEncounterTypes({'compId' : companyId}).$promise,
                            "paymentTypes" : UIDefinationService.getPaymentTypes({'compId' : companyId}).$promise,
                            "statusTypes" : UIDefinationService.getStatusTypes(statusParam).$promise,
                        }
                        $q.all(uidefPromises).then((uidTypes) => {
                            scope.encounterTypeMap = ReimbursementRegistrationFactory.constructUidMap(uidTypes['encounterTypes'].rowData, "id", "value");
                            scope.paymentMap = ReimbursementRegistrationFactory.constructUidMap(uidTypes['paymentTypes'].rowData, "id", "value");
                            scope.statusMap = ReimbursementRegistrationFactory.constructUidMap(uidTypes['statusTypes'].rowData, "id", "value");
                            scope.statusValueMap = ReimbursementRegistrationFactory.constructUidMap(uidTypes['statusTypes'].rowData, "value", "id");
                        }).catch((err) => {
                            alert("Error");
                        })                                             

                        function init() {
                            scope.currentTab = scope.tabsToDisplay ? scope.tabsToDisplay[0].tab : 'newRequest';
                            scope.currentTabIndex = 0;
                            setTabsCount(scope.currentTabIndex);
                            scope.claimsRecords = angular.copy($filter('filter')(scope.allClaimRecords, {status: scope.filterByStatus}));
                        }

                        function setTabsCount(statusIndex) {
                            angular.forEach(scope.tabsToDisplay, (statusRecord, index) => {
                                if (statusIndex == index) {
                                    scope.filterByStatus = statusRecord.state;
                                }
                                scope.countByStatus[statusRecord.state] = angular.copy($filter('filter')(scope.allClaimRecords, { status: statusRecord.state }).length);
                            });
                        }

                        scope.changeTab = function(tabName, tabIndex) {
                            scope.selectAll = false;
                            scope.currentTabIndex = tabIndex;
                            scope.currentTab = tabName;
                            if(scope.statusValueMap) {
                                scope.filterByStatus = scope.statusValueMap[tabName] ? scope.statusValueMap[tabName] : 'CC';
                            }                                
                            var status = tabName == 'newRequest' ? "" : scope.statusValueMap[tabName];
                            scope.onTabChange({'status' : status});
                            // switch(tabName) {
                            //     case 'Approved':
                            //         scope.filterByStatus = 'Approved';
                            //         scope.claimsRecords = angular.copy($filter('filter')(scope.allClaimRecords, {status: 'Approved'}));
                            //     break;
                            //     case 'InProgress':
                            //         scope.filterByStatus = 'InProgress';
                            //         scope.claimsRecords = angular.copy($filter('filter')(scope.allClaimRecords, {status: 'InProgress'}));
                            //     break;
                            //     case 'Rejected':
                            //         scope.filterByStatus = 'Rejected';
                            //         scope.claimsRecords = angular.copy($filter('filter')(scope.allClaimRecords, {status: 'Rejected'}));
                            //     break;
                            //     case 'Waitingforapproval':
                            //         scope.filterByStatus = 'Waitingforapproval';
                            //         scope.claimsRecords = angular.copy($filter('filter')(scope.allClaimRecords, {status: 'Waitingforapproval'}));
                            //     break;
                            //     case 'Assigned':
                            //         scope.filterByStatus = 'ASN';
                            //         getClaimList("ASN");
                            //         //scope.claimsRecords = angular.copy($filter('filter')(scope.allClaimRecords, {status: 'ASN'}));
                            //     break;
                            //     case 'newRequest':
                            //         scope.filterByStatus = 'CC';
                            //         getClaimList("");
                            //         //scope.claimsRecords = angular.copy($filter('filter')(scope.allClaimRecords, {status: 'CC'}));
                            //     break;
                            //     default:
                            //         console.log('no tabs available...');
                            // }
                        }

                        function getClaimList(status) {
                            var searchParams = {};
                            searchParams.compId = companyId;
                            searchParams.Status = status;
                            SpinnerService.start();
                            ReimbursementUserAssignmentService.getReimbursementAssignmentDetails(searchParams, function(resp) {
                                scope.claimsRecords = resp;
                                SpinnerService.stop();
                                // $scope.recordTotal = resp.length;
                                // $scope.claimList = resp;
                                // $scope.rerenderView = !$scope.rerenderView;
                            }, onError)
                        }        
                
                        function onError() {
                            SpinnerService.stop();
                        }

                        scope.selectAllClaims = function() {
                            scope.selectAll = !scope.selectAll;
                            scope.filteredClaims.forEach((claim) => {
                                if (claim['status'] == scope.filterByStatus) {
                                    claim['selected'] = scope.selectAll;
                                    if (scope.selectAll) {
                                        scope.selectedClaims.push(claim);
                                    }
                                }
                            });
                            if (!scope.selectAll) {
                                scope.selectedClaims = [];
                            }
                        }

                        scope.claimsToSelect = function(claimRecord) {
                            if (claimRecord['selected']) {
                                scope.selectedClaims.push(claimRecord);
                            } else {
                                var claimIndex = scope.selectedClaims.indexOf(claimRecord);
                                if (claimIndex != -1) {
                                    scope.selectedClaims.splice(claimIndex, 1);
                                }
                            }
                        }

                        scope.sortByValue = function() {
                            scope.reverseSort = (scope.sortBy == 'receivedDateDesc') ? false : true;
                        }

                        scope.redirectTo = function(stateName, data) {
                            ClaimsListViewService.setClaim(data);
                            $rootScope.navigateTo(stateName);
                        }

                        scope.$watch('rerenderView', (newValue, oldValue, scope) => {
                            //scope.changeTab(scope.currentTab, scope.currentTabIndex);
                            setTabsCount(scope.currentTabIndex);
                            scope.claimsRecords = scope.allClaimRecords;
                        });

                        scope.$watch('filteredClaims', (newValue, oldValue, scope) => {
                            scope.selectAll = false;
                        });

                        scope.$watch('searchBy', (newValue, oldValue, scope) => {
                            if (newValue != null) {
                                var claimsFilteredBySearch = $filter('filter')(scope.allClaimRecords, newValue);
                                scope.claimsRecords = angular.copy($filter('filter')(claimsFilteredBySearch, {status: scope.filterByStatus}));
                                scope.countByStatus[scope.filterByStatus] = scope.claimsRecords.length;
                            }
                        });

                        init();
                    }
                }
            }
        );
})()