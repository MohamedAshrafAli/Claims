<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width" />
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



        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.8/angular.min.js"></script>
        

        <!-- For ng-table in view -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ng-table/1.0.0/ng-table.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ng-table/1.0.0/ng-table.js"></script>

        <!-- For reading xls files in controller -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.1/xlsx.full.min.js"></script>
        <script src="http://oss.sheetjs.com/js-xlsx/jszip.js"></script>

        <!-- For Datepicker -->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">



        <script src="<s:url value=" /resources/static/js/app.js "/>" type="text/javascript"></script>



        <!--  ****************** TPA - CLAIMS *************************************-->


        <script src="<s:url value=" /resources/static/js/TPA/claim_mapping/controller/beyonMedicalController_Tpa_ClaimMapping.js "/>" type="text/javascript"></script>
        <script src="<s:url value=" /resources/static/js/TPA/policy_mapping/controller/beyonMedicalController_Tpa_PolicyMapping.js "/>" type="text/javascript"></script>

        <!-- 
    <script src="<s:url value="/resources/static/js/TPA/claim_mapping/service/onloadFetchIndetailsClaimsOrPolicies.js"/>" type="text/javascript"></script> 
    <script src="<s:url value="/resources/static/js/TPA/claim_mapping/service/uploadFileToaPath.js"/>" type="text/javascript"></script>    
    <script src="<s:url value="/resources/static/js/TPA/policy_mapping/service/policyMappingHttpFac.js"/>" type="text/javascript"></script> 
    <script src="<s:url value="/resources/static/js/TPA/policy_mapping/service/uploadFileToaPath_PolMapp.js"/>" type="text/javascript"></script> 
    -->

        <script src="<s:url value=" /resources/static/js/TPA/genricFac_PostGetCalls.js "/>" type="text/javascript"></script>


        <script src="<s:url value=" /resources/static/js/TPA/claim_upload/controller/beyonMedicalController_Tpa_Claims.js "/>" type="text/javascript"></script>
        <script src="<s:url value=" /resources/static/js/TPA/claim_upload/controller/beyonMedicalController_Reports.js "/>" type="text/javascript"></script>
        <script src="<s:url value=" /resources/static/js/TPA/policy_export/beyonMedicalController_Tpa_Policy_Export.js "/>" type="text/javascript"></script>

        <script type="text/javascript" src="<s:url value=" /resources/static/js/createquote/controller/ClaimInfoCntrl.js "/>"></script>
        <script type="text/javascript" src="<s:url value=" /resources/static/js/regulatory_Intg/controller/beyonMedicalController_Regulatory_Intg.js "/>"></script>




        <script src="https://cdnjs.cloudflare.com/ajax/libs/ngprogress/1.1.3/ngProgress.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ngprogress/1.1.3/ngprogress.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/nprogress/0.2.0/nprogress.css" rel="stylesheet" type="text/css" />


        <script src="<s:url value=" https://cdnjs.cloudflare.com/ajax/libs/alasql/0.4.2/alasql.min.js "/>"></script>
        <script src="<s:url value=" /resources/static/js/TPA/claim_upload/angular-fcsa-number.js "/>" type="text/javascript"></script>







        <link rel="stylesheet" href="<s:url value=" /resources/static/css/beyonMedicalTPA.css "/>">



        <!-- 
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular-sanitize.js"></script>
     -->

        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.6/angular-animate.js"></script>
        <script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.2.4.js"></script>
        <link data-require="bootstrap-css@*" data-semver="3.3.6" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angucomplete-alt/3.0.0/angucomplete-alt.min.js"></script>
        <script type="application/javascript" src="https://api.ipify.org?format=jsonp&callback=getIP"></script>

        <link rel="stylesheet" href="<s:url value=" /resources/static/css/style.css "/>">



</head>

<body style="background-color: #e9ecf3;">

    <div class="container-fluid">

        <div class="row">

            <div class="col-sm-5"><img src="resources/static/images/Beyontec_logo1.png" /></div>
            <div class="col-sm-4"> <img src="resources/static/images/headericon.png" /></div>
            <div class="col-sm-3"><img src="resources/static/images/Beyontec_Identity_CMYK_Y60p-Horizontal-BG-Alpha.png" /></div>

        </div>

        <div class="row">
            <div class="topbar">
                <div class="col-sm-8"><a href="workbasket.html" class="label">Home</a></div>
                <div class="col-sm-1">
                    <div class="label">Mohammad Ali</div>
                </div>
                <div class="col-sm-1">
                    <div class="label">|</div>
                </div>
                <div class="col-sm-1">
                    <div class="label" onClick="location.href='login.html';">Logout</div>
                </div>
            </div>
        </div>
        <br>
    </div>



    <ui-view style="margin-left:1.46%;width:97.08%">

    </ui-view>

</body>


</html>