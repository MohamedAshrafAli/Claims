(function() {
    'use strict';
    angular
        .module('claims')
            .controller('ProviderFinalizationController', ProviderFinalizationController)

    ProviderFinalizationController.$inject = ['$scope', '$rootScope', 'ProviderFinalizationService', '$filter', 'ngNotify'];

    function ProviderFinalizationController($scope, $rootScope, ProviderFinalizationService, $filter, ngNotify) {

        function init() {
            $scope.data = ProviderFinalizationService.getFinailzationRecords();
            $scope.finalizationRecords = ProviderFinalizationService.getFinailzationRecords();
            $scope.result = $scope.data;
            $scope.recordTotal = $scope.data.length;
            $scope.dropDownValue = ProviderFinalizationService.getSearchDropDownValues();
            $scope.fieldsObject = ProviderFinalizationService.getSearchFields(); 
        }
        init();
    }
})();