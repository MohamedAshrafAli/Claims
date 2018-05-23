<!DOCTYPE html>
<html ng-app="claims">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>BeyontecMedicalClaims</title>
        <%@taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core"%>

        <script type="text/javascript">
            var contextPath = '<%=request.getContextPath()%>';
        </script>

        <script type="application/javascript">
            var clientIPFound = "";

            function getIP(json) {
                clientIPFound = json.ip;
            }
        </script>

        <!-- Style Sheets -->
        <link rel="stylesheet" href="<s:url value="resources/static/bower_components/bootstrap/dist/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="<s:url value="resources/static/bower_components/Font-Awesome-4.7.0/css/font-awesome.min.css"/>">
        <link rel="stylesheet" href="<s:url value="resources/static/bower_components/angular-ui-carousel/dist/ui-carousel.css"/>">
        <link rel="stylesheet" href="<s:url value="resources/static/bower_components/angular-ui-grid/ui-grid.min.css"/>">
        <link rel="stylesheet" href="<s:url value="resources/static/bower_components/angular-material/angular-material.min.css"/>">
        <link rel="stylesheet" href="<s:url value="resources/static/bower_components/ng-notify/dist/ng-notify.min.css"/>">
        <link rel="stylesheet" href="<s:url value="resources/static/css/open-iconic.css"/>">
        <link rel="stylesheet" href="<s:url value="resources/static/css/style.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="resources/static/css/page.css"/>">
        <link rel="stylesheet" type="text/css" href="<s:url value="resources/static/css/jPushMenu.css"/>">
        

        <!-- External Libraies -->
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="<s:url value="resources/static/bower_components/jquery/dist/jquery.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/bootstrap/dist/js/bootstrap.bundle.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular/angular.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-ui-router/release/angular-ui-router.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/ng-file-upload/ng-file-upload.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-thumbnails/dist/angular-thumbnails.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/pdfjs-dist/build/pdf.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-ui-carousel/dist/ui-carousel.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-ui-grid/ui-grid.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-animate/angular-animate.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-aria/angular-aria.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-messages/angular-messages.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-material/angular-material.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/ng-notify/dist/ng-notify.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-translate/angular-translate.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-translate-loader-partial/angular-translate-loader-partial.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/underscore/underscore-min.js"/>"></script>
        <script src="<s:url value="resources/static/lib/ui-bootstrap-tpls-3.0.3.min.js"/>"></script>
        <script src="<s:url value="resources/static/bower_components/angular-resource/angular-resource.min.js"/>"></script>

        <script src="<s:url value="resources/app.js"/>"></script>
        <!-- Components -->
        <script src="<s:url value="resources/component/listView-component/listview.component.js"/>"></script>
        <script src="<s:url value="resources/component/listView-component/listview.component.service.js"/>"></script>
        <script src="<s:url value="resources/component/userAssignment-component/userAssignment.component.js"/>"></script>
        <!-- Directives -->
        <script src="<s:url value="resources/directives/grid-directive/script/grid-wrapper.directive.js"/>"></script>
        <script src="<s:url value="resources/util/pagination/pagination.directive.js"/>"></script>
        <script src="<s:url value="resources/util/dropdown-autocomplete.service.js"/>"></script>
        <script src="<s:url value="resources/directives/claimsListView-directive/script/claimslistview.directive.js"/>"></script>
        <script src="<s:url value="resources/directives/claimsListView-directive/script/claimslistview.directive.service.js"/>"></script>
        <script src="<s:url value="resources/directives/finalization-directive/script/finalizationlistview.directive.js"/>"></script>
        <script src="<s:url value="resources/directives/listView-directive/script/listview.directive.js"/>"></script>
        <script src="<s:url value="resources/directives/search-directive/script/search.directive.js"/>"></script>
        <script src="<s:url value="resources/directives/batch-directive/script/batch.directive.js"/>"></script>
        <!-- EClaims -->
        <script src="<s:url value="resources/eclaim/user-assignment/script/eclaim-user-assignment.controller.js"/>"></script>
        <script src="<s:url value="resources/eclaim/user-assignment/script/eclaim-user-assignment.service.js"/>"></script>
        <script src="<s:url value="resources/eclaim/processing/script/eclaim-processing.controller.js"/>"></script>
        <script src="<s:url value="resources/eclaim/processing/script/eclaim-processing.service.js"/>"></script>
        <script src="<s:url value="resources/eclaim/finalization/script/eclaim-finalization.controller.js"/>"></script>
        <script src="<s:url value="resources/eclaim/finalization/script/eclaim-finalization.service.js"/>"></script>
        <script src="<s:url value="resources/eclaim/batch/script/eclaim-batch.controller.js"/>"></script>
        <script src="<s:url value="resources/eclaim/batch/script/eclaim-batch.service.js"/>"></script>
        <script src="<s:url value="resources/eclaim/remittance/script/eclaim-remittance.controller.js"/>"></script>
        <script src="<s:url value="resources/eclaim/remittance/script/eclaim-remittance.service.js"/>"></script>

        <!-- Reimbursement Claims -->
        <script src="<s:url value="resources/reimbursement-claims/registration/script/reimbursement-registration-general.controller.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/registration/script/reimbursement-registration.controller.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/registration/script/reimbursement-registration.service.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/user-assignment/script/reimbursement-user-assignment.controller.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/user-assignment/script/reimbursement-user-assignment.service.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/processing/script/reimbursement-processing.controller.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/processing/script/reimbursement-processing.service.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/finalization/script/reimbursement-finalization.controller.js"/>"></script>
        <script src="<s:url value="resources/reimbursement-claims/finalization/script/reimbursement-finalization.service.js"/>"></script>
    
        <!-- PBM -->
        <script src="<s:url value="resources/pbm/user-assignment/script/pbm-user-assignment.controller.js"/>"></script>
        <script src="<s:url value="resources/pbm/user-assignment/script/pbm-user-assignment.service.js"/>"></script>
        <script src="<s:url value="resources/pbm/batch/script/pbm-batch.controller.js"/>"></script>
        <script src="<s:url value="resources/pbm/batch/script/pbm-batch.service.js"/>"></script>
        <script src="<s:url value="resources/pbm/remittance/script/pbm-remittance.controller.js"/>"></script>
        <script src="<s:url value="resources/pbm/remittance/script/pbm-remittance.service.js"/>"></script>
        <script src="<s:url value="resources/pbm/processing/script/pbm-processing.controller.js"/>"></script>
        <script src="<s:url value="resources/pbm/processing/script/pbm-processing.service.js"/>"></script>

        <!-- Provider Paper -->
        <script src="<s:url value="resources/provider-paper/batch/script/provider-paper-batch.controller.js"/>"></script>
        <script src="<s:url value="resources/provider-paper/batch/script/provider-paper-batch.service.js"/>"></script>
        <script src="<s:url value="resources/provider-paper/user-assignment/script/providerPaper-user-assignment.controller.js"/>"></script>
        <script src="<s:url value="resources/provider-paper/user-assignment/script/providerPaper-user-assignment.service.js"/>"></script>
        <script src="<s:url value="resources/provider-paper/processing/script/provider-paper-processing.controller.js"/>"></script>
        <script src="<s:url value="resources/provider-paper/processing/script/provider-paper-processing.service.js"/>"></script>
        <script src="<s:url value="resources/provider-paper/finalization/script/provider-paper-finalization.controller.js"/>"></script>
        <script src="<s:url value="resources/provider-paper/finalization/script/provider-paper-finalization.service.js"/>"></script>
    
        <!-- Pre Authorization -->
        <script src="<s:url value="resources/pre-authorization/user-assignment/script/preAuthorization-user-assignment.controller.js"/>"></script>
        <script src="<s:url value="resources/pre-authorization/user-assignment/script/preAuthorization-user-assignment.service.js"/>"></script>
        <script src="<s:url value="resources/pre-authorization/remittance/script/pre-authorization-remittance.controller.js"/>"></script>
        <script src="<s:url value="resources/pre-authorization/remittance/script/pre-authorization-remittance.service.js"/>"></script>
        <script src="<s:url value="resources/pre-authorization/processing/script/pre-authorization-processing.controller.js"/>"></script>
        <script src="<s:url value="resources/pre-authorization/processing/script/pre-authorization-processing.service.js"/>"></script>
    
    </head>
    <body >
        <header class="clearfix">
            <div class="">
                <span class="float-left" ng-click="navbar = !navbar"><i class="fa fa-bars toggle-menu menu-left"></i></span>
                <span ><img class="headLeft float-left" src="<s:url value="resources/static/img/Beyontec_logo1.png"/>" alt=""/></span>
                <span>&nbsp;</span>
                <span ><img class="headRight float-right" src="<s:url value="resources/static/img/Beyontec_Identity_CMYK_Y60p-Horizontal-BG-Alpha.png"/>" alt=""/></span>
                <span class="headRight float-right" ng-controller="LanguageController">
                    <select ng-options="key as value for (key, value) in languages" ng-model="$parent.selectedLanguage" ng-change="changelanguage()"></select>
                </span>
            </div>
        </header>
        <nav id="navbar" class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" ng-class="{'menu-open' : navbar}">
            <uib-accordion close-others="true">
                <div uib-accordion-group class="panel-default">
                    <uib-accordion-heading> Transaction </uib-accordion-heading>
                    <h3>Underwriting</h3>
                    <h3>Reinsurance</h3>
                    <h3>Claims</h3>
                    <h4>TPA Claims</h4>
                    <h4 >Export Claims</h4>
                </div>
                <div uib-accordion-group class="panel-default">
                    <uib-accordion-heading> Processing </uib-accordion-heading>
                    <span>
                        <h3>Renewal Query Screen</h3>
                        <h3>RTA Resubmission</h3>
                    </span>
                </div>
                <div uib-accordion-group class="panel-default">
                    <uib-accordion-heading> Reports and Queries </uib-accordion-heading>
                    <span>
                        <h3>Report</h3>
                    </span>
                </div>
                <div uib-accordion-group class="panel-default">
                    <uib-accordion-heading> Admin </uib-accordion-heading>
                    <span>
                        <h3>TPA Claims Import Mapping</h3>
                    </span>
                </div>
                <div uib-accordion-group class="panel-default" is-open="true">
                    <uib-accordion-heading> Medical Claims </uib-accordion-heading>
                    <uib-accordion close-others="true">
                        <div uib-accordion-group class="panel-default child-panel">
                            <uib-accordion-heading> <h3>E-Claims</h3> </uib-accordion-heading>
                            <h4 ui-sref="eclaim-batch">Batch</h4>
                            <h4 ui-sref="eclaims-user-assignment">User Assignment</h4>
                            <!-- <h4>Finalization</h4> -->
                            <h4 ui-sref="eClaims-remittance">Remittance</h4>
                        </div>
                        <div uib-accordion-group class="panel-default child-panel">
                            <uib-accordion-heading> <h3>Reimbursement Claims</h3> </uib-accordion-heading>
                            <h4 ui-sref="reimbursement-registration">Registration</h4>
                            <h4 ui-sref="reimbursement-user-assignment">User Assignment</h4>
                            <h4 ui-sref="reimbursement-finalization">Finalization</h4>
                        </div>
                        <div uib-accordion-group class="panel-default child-panel">
                            <uib-accordion-heading> <h3>PBM Claims</h3> </uib-accordion-heading>
                            <h4 ui-sref="pbm-batch">Batch</h4>
                            <h4 ui-sref="pbm-user-assignment">User Assignment</h4>
                            <h4 ui-sref="pbm-remittance">Remittance</h4>
                        </div>
                        <div uib-accordion-group class="panel-default child-panel">
                            <uib-accordion-heading> <h3>Provider Paper Claims</h3> </uib-accordion-heading>
                            <h4 ui-sref="provider-paper-batch">Batch</h4>
                            <h4 ui-sref="provider-paper-user-assignment">User Assignment</h4>
                            <h4 ui-sref="provider-paper-finalization"> Finalization</h4>
                        </div>
                        <div uib-accordion-group class="panel-default child-panel">
                            <uib-accordion-heading> <h3>Pre-Authorization</h3> </uib-accordion-heading>
                            <h4 ui-sref="pre-authorization-registration">Registration</h4>
                            <h4 ui-sref="pre-authorization-user-assignment">User Assignment</h4>
                            <h4 ui-sref="pre-authorization-remittance">Remittance</h4>
                        </div>
                    <uib-accordion>
                </div>
            </uib-accordion>
        </nav>
        <ui-view></ui-view>
    </body>
</html>