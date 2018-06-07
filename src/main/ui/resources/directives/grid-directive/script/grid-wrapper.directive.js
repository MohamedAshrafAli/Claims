(function() {
    'use strict';

    angular
        .module('claims')
        .directive('gridWrapper', function(ReimbursementRegistrationFactory) {
            return {
                restict: 'AEC',
                templateUrl: 'resources/directives/grid-directive/view/gridwrapper.directive.html',
                scope: {
                    uiGridOptions :'=',
                    inlineEdit : '=',
                    module : '=',
                    onGridAction: '&',
                    curencyList : '=',
                    baseCurrency : '='
                },
                link: function(scope, elem, attrs, ngModel) {
                    scope.currencyType = scope.baseCurrency;
                    scope.noRecordsAvailable = scope.uiGridOptions['data'].length == 0;
                    var path = 'resources/directives/grid-directive/view/';
                    scope.gridOptions = scope.uiGridOptions;
                    scope.noRecordsAvailable = scope.gridOptions['data'].length == 0;

                    scope.gridOptions.onRegisterApi = function(gridApi) {
                        if(scope.inlineEdit) {
                            gridApi.edit.on.beginCellEdit(null, function(row, colDef, event) {
                                row.entity.editable = !row.entity.editable;
                                row.entity.editedColName = colDef.name;
                                if(scope.prevEdittedRow && scope.prevEdittedRow.uid != row.uid) {
                                    scope.prevEdittedRow.entity.editable = false;
                                }
                                scope.prevEdittedRow = row;
                                scope.prevRowobj = angular.copy(row.entity);
                            })
                        }

                        gridApi.core.on.rowsRendered(scope, function(resp) {
                            if(scope.action == 'new') {
                                scope.prevEdittedRow = resp.grid.rows[scope.gridOptions.data.length-1];
                                scope.prevRowobj = angular.copy(scope.prevEdittedRow.entity);
                            }
                            $($('.ui-grid-render-container-body').children()).addClass('ui-grid-content');
                        });
                    }

                    scope.toggleSelect = function() {
                        scope.isChecked = !scope.isChecked;
                        angular.forEach(scope.gridOptions.data, function(value, key) {
                            value.isChecked = scope.isChecked;
                        })
                    }

                    scope.convertCurrency = function() {
                        var info = {action : 'convertCurrency', selectedCurrency : scope.currencyType}
                        scope.onGridAction({info})
                    }

                    scope.gridAction = function(actionType, record, rowIndex) {
                        scope.action = actionType;
                        var info = {action : actionType, data : record, index : rowIndex};
                        scope.onGridAction({info});
                    }                    

                    $('#right-button').click(function(event) {
                        event.preventDefault();
                        $('.ui-grid-content').animate({
                        scrollLeft: "+=322px"
                        }, "slow");
                    });
                    
                    $('#left-button').click(function(event) {
                        event.preventDefault();
                        $('.ui-grid-content').animate({
                            scrollLeft: "-=322px"
                        }, "slow");
                    });

                    scope.info = {
                        showTooltip: true,
                        tipDirection: 'right'
                    };

                    scope.$watch('gridOptions.data.length', function(newValue, oldValue) {
                        if(newValue != null) scope.noRecordsAvailable = (newValue == 0);
                    })

                    scope.toggleLocalAndBaseCurrency = function() {
                        scope.isLocalCurrency = !scope.isLocalCurrency;
                        var info = {action : 'localBaseCurrenyToggled', isBaseCurreny : scope.isLocalCurrency};
                        scope.onGridAction({info});
                    }
                }
            }    
        });
})();