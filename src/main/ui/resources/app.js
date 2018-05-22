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
        'pascalprecht.translate'
    ])
    .config(stateConfig)
    .config(translationConfig)
    .run(appConfig)

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
                    claim : function(ReimbursementRegistrationService) {
                        return ReimbursementRegistrationService.createRegDetailObj();
                    },
                    isNew : function() {
                        return true;
                    }
                }
            })

            .state('reimbursement-registration-edit', {
                url: "/reimbursement-registration-general",
                templateUrl: "resources/reimbursement-claims/registration/view/reimbursement-registration-general.html",
                controller: 'ReimbursementRegistrationGeneralController',
                resolve : {
                    claim : function(ListViewService) {
                        return ListViewService.getRequestData();
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
                    }]
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

            .state('pre-authorization-remittance', {
                url: "/pre-authorization-remittance",
                templateUrl:"resources/pre-authorization/remittance/view/pre-authorization-remittance.html",
                controller: 'PreAuthorizationRemittanceController'
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

            
        $urlRouterProvider.otherwise("/");    
    }
    
    function translationConfig($translateProvider) {
        $translateProvider.preferredLanguage('en-US');
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
            if(event.keyCode != 9 && event.keyCode != 13 && event.keyCode != 37 && event.keyCode != 27
                && event.keyCode != 38 && event.keyCode != 39 && event.keyCode != 40) {
                event.target.value = "";
            }
        }
        
        $rootScope.navigateTo = function(stateName) {
            $state.go(stateName);
        }
    }

})();

(function() {
    'use strict';

    angular
        .module('claims')
        .controller('LanguageController', LanguageController)

    LanguageController.$inject = ['$translate', '$scope', '$rootScope'] ;

    function LanguageController($translate, $scope, $rootScope) {
        $scope.languages = {
            'en-US' : 'English',
            'ar-AR' : 'Arabic'
        }
        $rootScope.selectedLanguage = 'en-US';
        $scope.changelanguage = function() {
            $translate.use($scope.selectedLanguage).then(function() {
                $translate.refresh();
            });
        }
    }
})();
