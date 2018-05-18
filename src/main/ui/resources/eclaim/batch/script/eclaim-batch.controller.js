(function() {
    'use strict';

    angular
        .module('claims')
        .controller('EclaimBatchController', EclaimBatchController)

    EclaimBatchController.$inject = ['$scope', '$rootScope', 'EclaimBatchService', 'ngNotify', '$filter', '$state', 'ListViewService'];

    function EclaimBatchController($scope, $rootScope, EclaimBatchService, ngNotify, $filter, $state, ListViewService) {
        $scope.moduleName = 'eclaim';
        $scope.fieldsInfo = EclaimBatchService.getRecordsFieldsInfo();
        $scope.searchFields = EclaimBatchService.getSearchFields();
    }
})()