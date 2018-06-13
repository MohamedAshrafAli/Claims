(function() {
    'use strict';

    angular
        .module('claims')
        .directive('batchDirective', function($uibModal, companyId, AutocompleteService, ListViewService) {
            return {
                restict: 'AEC',
                templateUrl: 'resources/directives/batch-directive/view/batch.directive.html',
                scope: {
                    moduleName: '=',
                    fieldsInfo: '=',
                    records: '='
                },
                link: function(scope, elem, attrs, ngModel) {
                    scope.selectedRecords = []
                    scope.users = [];
                    scope.selectAllBatches = function() {
                        scope.selectAll = !scope.selectAll;
                        scope.selectedRecords = [];
                        angular.forEach(scope.records, function(value ,key) {
                            value.isChecked = scope.selectAll;
                            if(scope.selectAll)scope.selectedRecords.push(value);
                        })
                    }

                    scope.rejectBatches = function() {
                        console.log('Reject');
                    }

                    scope.assignBatches = function() {
                        console.log('Assign');
                        var modalInstance =  $uibModal.open({
                            templateUrl: 'resources/directives/batch-directive/view/batch-assign-modal.html',
                            backdrop: 'static',
                            size: 'lg',
                            keyboard :false,
                            controller : function($scope) {
                                $scope.userAssignmentHeader = ListViewService.getUserAssignmentListViewHeader();
                                var params = {compId : companyId, userName : "%"};
                                AutocompleteService.getUserList(params, function(resp) {
                                    $scope.users = resp.rowData;
                                })
                                $scope.selectedUserToAssign = [];
                                scope.getAutoCompleteList = function(userSearchText) {
                                    if(userSearchText.length < 3) {
                                        return [];
                                    }
                                    var params = {
                                        compId : companyId,
                                        userName : userSearchText + "%"
                                    }
                                    return AutocompleteService.getUserList(params).$promise.then(function(resp) {
                                        return resp.rowData;
                                    });
                                }
                            }
                        })
                    }

                    scope.selectBatchRecords = function(value) {
                        if(value.isChecked) {
                            scope.selectedRecords.push(value);
                        } else {
                            var index = scope.selectedRecords.indexOf(value);
                            if(index > -1) {
                                scope.selectedRecords.splice(scope.selectedRecords.indexOf(value), 1)    
                            }
                        }
                    }

                }
            }    
        });
})();