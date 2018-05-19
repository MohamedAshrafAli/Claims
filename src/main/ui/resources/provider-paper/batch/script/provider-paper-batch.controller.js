(function(){
    'use strict';
    angular
    .module('claims')
    .controller('ProviderBatchController', ProviderBatchController)

ProviderBatchController.$inject = ['$scope', '$rootScope', 'ngNotify', 'ProviderBatchService', '$filter', '$state',];

function ProviderBatchController($scope, $rootScope, ngNotify, ProviderBatchService, $filter, $state, ) {
       $scope.searchFields = ProviderBatchService.getSearchFields();
        $scope.batchRecords = ProviderBatchService.getBatchRecords();
        $scope.moduleName = 'provider';
        $scope.fieldsInfo = ProviderBatchService.getRecordsFieldsInfo();


 
}
})();