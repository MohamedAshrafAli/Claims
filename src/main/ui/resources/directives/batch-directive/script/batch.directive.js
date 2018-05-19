(function() {
    'use strict';

    angular
        .module('claims')
        .directive('batchDirective', function() {
            return {
                restict: 'AEC',
                templateUrl: 'resources/directives/batch-directive/view/batch.directive.html',
                scope: {
                    moduleName: '=',
                    fieldsInfo: '=',
                    records: '='
                },
                link: function(scope, elem, attrs, ngModel) {
                    console.log(scope.fieldsInfo);
                }
            }    
        });
})();