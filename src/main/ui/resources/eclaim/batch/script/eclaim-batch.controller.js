(function() {
    'use strict';

    angular
        .module('claims')
        .controller('EclaimBatchController', EclaimBatchController)

    EclaimBatchController.$inject = ['$scope', '$rootScope', 'EclaimBatchService', 'ngNotify', '$filter', '$state', 'ListViewService'];

    function EclaimBatchController($scope, $rootScope, EclaimBatchService, ngNotify, $filter, $state, ListViewService) {
        $scope.searchFields = EclaimBatchService.getSearchFields();
    }
})()