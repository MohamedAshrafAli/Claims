(function() {
    'use strict';

    angular
        .module('claims')
        .controller('PBMBatchController', PBMBatchController)

    PBMBatchController.$inject = ['$scope', '$rootScope', 'PBMBatchService', 'ngNotify', '$filter', '$state',];

    function PBMBatchController($scope, $rootScope, PBMBatchService, ngNotify, $filter, $state, ) {
        $scope.searchFields = PBMBatchService.getSearchFields();
        $scope.batchRecords = PBMBatchService.getBatchRecords();
        $scope.moduleName = 'PBM';
        $scope.fieldsInfo = PBMBatchService.getRecordsFieldsInfo();

    

        }        
    })();