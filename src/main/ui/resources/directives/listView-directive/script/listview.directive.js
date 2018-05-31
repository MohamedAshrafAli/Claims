(function() {
    'use strict';

    angular
        .module("claims")
            .directive("listView", function($rootScope, $filter, ListViewService) {
                return {
                    restrict: 'E',
                    templateUrl: 'resources/directives/listView-directive/view/listview.directive.html',
                    scope: {
                        headers: '<',
                        records: '=',
                        redirectTo: '@',
                        searchBy: '=',
                        stateParam: '@'
                    },
                    link: function(scope, element, attrs) {
                        
                        scope.filteredRecords = [];
                        scope.reverse = true;

                        scope.sortBy = function(fieldName) {
                            scope.reverse = (scope.fieldName === fieldName) ? !scope.reverse : false;
                            scope.fieldName = fieldName;
                        }
                
                        scope.navigateTo = function(requestData) {
                            ListViewService.setRequestData(requestData);
                            if(scope.stateParam) {
                                var param = {}
                                param[scope.stateParam] = requestData[scope.stateParam];
                                $rootScope.navigateTo(scope.redirectTo, param);
                            } else {
                                $rootScope.navigateTo(scope.redirectTo, null);
                            }                            
                        }
                
                        scope.getSelectedData = function(selectedRecord) {
                            scope.selectedUser = selectedRecord;
                        }
                    
                        scope.$watch('searchBy', (newValue, oldValue, scope) => {
                            if (newValue != null) {
                                scope.records = $filter('filter')(scope.allRecords, newValue);
                            }
                        });

                        function init() {
                            scope.allRecords = angular.copy(scope.records);
                        }

                        init();

                    }
                }
            }
           
        )
})()