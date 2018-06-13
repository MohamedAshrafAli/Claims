(function() {
    'use strict';
angular
    .module('claims', [
        'ui.router',
        'ngFileUpload',
        'angular-thumbnails',
        'ui.carousel',
        'ui.grid',
        'ui.grid.edit',
        'ui.grid.pinning',
        'ngMaterial',
        'ui.bootstrap',
        'ngNotify',
        'pascalprecht.translate',
        'ngResource'
    ])
    .config(stateConfig)
    .config(translationConfig)
    .run(appConfig)
    .constant('urlRoutePrefix',contextPath)
    .constant('companyId',"0021")
    .constant("dateFormat", "mm-dd-yyyy")

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('home', {
                url: "/",
                template: "<h4 style=\"text-align: center; padding: 150px;\" data-translate=\"home.title\">Claim Home</h4>",
                resolve : {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('home');
                        return $translate.refresh();
                    }]
                }
            })

            .state('reimbursement-registration', {
                url: "/reimbursement-registration",
                templateUrl: "resources/reimbursement-claims/registration/view/reimbursement-registration.html",
                controller: 'ReimbursementRegistrationController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('registration');
                        return $translate.refresh();
                    }]
                }
            })

            .state('reimbursement-registration-new', {
                url: "/reimbursement-registration-general",
                templateUrl: "resources/reimbursement-claims/registration/view/reimbursement-registration-general.html",
                controller: 'ReimbursementRegistrationGeneralController',
                resolve : {
                    claim : function(ReimbursementRegistrationFactory) {
                        return ReimbursementRegistrationFactory.createRegDetailObj();
                    },
                    isNew : function() {
                        return true;
                    }
                }
            })

            .state('reimbursement-registration-edit', {
                url: "/{id}/reimbursement-registration-general",
                templateUrl: "resources/reimbursement-claims/registration/view/reimbursement-registration-general.html",
                controller: 'ReimbursementRegistrationGeneralController',
                resolve : {
                    claim : function(ReimbursementRegistrationService, $stateParams, SpinnerService) {
                        SpinnerService.start();
                        return ReimbursementRegistrationService.getReimbursementRegistrationDetailsById({'id' : $stateParams.id}).$promise
                    },
                    isNew : function() {
                        return false;
                    }
                }
            })

            .state('reimbursement-finalization', {
                url: "/reimbursement-finalization",
                templateUrl: "resources/reimbursement-claims/finalization/view/reimbursement-finalization.html",
                controller: 'ReimbursementFinalizationController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('finalization');
                        return $translate.refresh();
                    }]
                }
            })

            .state('reimbursement-user-assignment', {
                url: "/reimbursement-user-assignment",
                templateUrl: "resources/reimbursement-claims/user-assignment/view/reimbursement-user-assignment.html",
                controller: 'ReimbursementUserAssignmentController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user-assignment');
                        return $translate.refresh();
                    }]
                }
            })

            .state('reimbursement-processing', {
                url: "/reimbursement-processing",
                templateUrl: "resources/reimbursement-claims/processing/view/reimbursement-processing.html",
                controller: 'ReimbursmentProcessingController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('processing');
                        return $translate.refresh();
                    }],
                    reimbursementClaimInfo : function(SpinnerService, ClaimsListViewService, ReimbursementProcessingService) {
                        SpinnerService.start();
                        var data = ClaimsListViewService.getClaim();
                        return ReimbursementProcessingService.getReimbursementInitProcessingDetails(data).$promise;
                    }
                }
            })

            .state('eclaim-batch', {
                url: "/eclaim-batch",
                templateUrl: "resources/eclaim/batch/view/eclaim-batch.html",
                controller: 'EclaimBatchController'
            })

            .state('eclaim-processing', {
                url: "/eclaim-processing",
                templateUrl: "resources/eclaim/processing/view/eclaim-processing.html",
                controller: 'EclaimProcessingController'
            })

            .state('eClaims-remittance', {
                url: "/eClaims-remittance",
                templateUrl:"resources/eclaim/remittance/view/eclaim-remittance.html",
                controller: 'EclaimRemittanceController'
            })

            .state('eclaims-user-assignment', {
                url: "/eclaims-user-assignment",
                templateUrl: "resources/eclaim/user-assignment/view/eclaim-user-assignment.html",
                controller: 'EclaimUserAssignmentController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user-assignment');
                        return $translate.refresh();
                    }]
                }
            })

            .state('pbm-batch', {
                url: "/pbm-batch",
                templateUrl: "resources/pbm/batch/view/pbm-batch.html",
                controller: 'PBMBatchController'
            })

            .state('pbm-remittance', {
                url: "/pbm-remittance",
                templateUrl: "resources/pbm/remittance/view/pbm-remittance.html",
                controller: 'PBMRemittanceController'
            })

            .state('pbm-processing', {
                url: "/pbm-processing",
                templateUrl: "resources/pbm/processing/view/pbm-processing.html",
                controller: 'PBMProcessingController'
            })

            .state('pbm-user-assignment', {
                url: "/pbm-user-assignment",
                templateUrl: "resources/pbm/user-assignment/view/pbm-user-assignment.html",
                controller: 'PBMUserAssignmentController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user-assignment');
                        return $translate.refresh();
                    }]
                }
            })

            .state('provider-paper-batch', {
                url: "/providerpaper-batch",
                templateUrl: "resources/provider-paper/batch/view/provider-paper-batch.html",
                controller: 'ProviderBatchController'
            })
            
            .state('provider-paper-user-assignment', {
                url: "/providerPaper-user-assignment",
                templateUrl: "resources/provider-paper/user-assignment/view/providerPaper-user-assignment.html",
                controller: 'ProviderPaperUserAssignmentController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user-assignment');
                        return $translate.refresh();
                    }]
                }
            })

            .state('provider-paper-finalization', {
                url: "/provider-paper-finalization",
                templateUrl: "resources/provider-paper/finalization/view/provider-paper-finalization.html",
                controller: 'ProviderFinalizationController'
            })

            .state('provider-paper-processing', {
                url: "/provider-paper-processing",
                templateUrl: "resources/provider-paper/processing/view/provider-paper-processing.html",
                controller: 'ProviderPaperProcessingController'
            })

            .state('pre-authorization-user-assignment', {
                url: "/PreAuthorization-user-assignment",
                templateUrl: "resources/pre-authorization/user-assignment/view/preAuthorization-user-assignment.html",
                controller: 'preAuthorizationAssignmentController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('user-assignment');
                        return $translate.refresh();
                    }]
                }
            })
            .state('pre-authorization-processing', {
                url: "/pre-authorization-processing",
                templateUrl:"resources/pre-authorization/processing/view/pre-authorization-processing.html",
                controller: 'PreAuthorizationProcessingController'
            })
            .state('pre-authorization-registration', {
                url: "/pre-authorization-registration",
                templateUrl:"resources/pre-authorization/registration/view/pre-authorization-registration.html",
                controller: 'PreAuthorizationRegistrationController',
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('registration');
                        return $translate.refresh();
                    }]
                }
            })
            .state('pre-authorization-remittance', {
                url: "/pre-authorization-remittance",
                templateUrl:"resources/pre-authorization/remittance/view/pre-authorization-remittance.html",
                controller: 'PreAuthorizationRemittanceController'
            })

            
        $urlRouterProvider.otherwise("/");    
    }
    
    function translationConfig($translateProvider) {
        $translateProvider.preferredLanguage('en-US');
        $translateProvider.useSanitizeValueStrategy('escape')
        $translateProvider.useLoader('$translatePartialLoader', {
            urlTemplate: 'resources/i18n/{lang}/{part}.json'
        });
    }

    function appConfig ($transitions, $rootScope, ngNotify, $state) {
        ngNotify.config({
            position: 'top',
            duration: 2000,
            button : true
        });

        $transitions.onSuccess({}, function() {
            $rootScope.navbar = false;
        });

        $rootScope.clearDatepickerKeyupValue = function(event) {
            var key = event.keyCode;
            if (key >= 65 && key <= 90) {
                var value = event.target.value;
                event.target.value = value.substring(0, value.length-1);
            }
        }

        $rootScope.navigateTo = function(stateName, param) {
            if(param != null) {
                $state.go(stateName, param);
            } else {
                $state.go(stateName);
            }            
        }
    }

})();

(function() {
    'use strict';

    angular
        .module('claims')
        .controller('AppController', AppController)

    AppController.$inject = ['$translate', '$scope', '$rootScope', '$http'] ;

    function AppController($translate, $scope, $rootScope, $http) {
        $scope.languages = {
            'en-US' : 'English',
            'ar-AR' : 'Arabic'
        }
        $scope.selectedLanguage = 'en-US';
        $scope.changelanguage = function() {
            $translate.use($scope.selectedLanguage).then(function() {
                $translate.refresh();
            });
        }

        $http.get('resources/static/properties/autocomplete-search.properties').then(function (response) {
            $rootScope.searchProperties = response.data;
        });
    }
})();

(function() {
    'use strict';
    angular
        .module('claims')
        .filter('customeDate', function () {
            return function (item) {
                var date;
                if (item) {
                    date = item[1]+'/'+item[2]+'/'+item[0];
                }
                return date
            };
        })
})();
