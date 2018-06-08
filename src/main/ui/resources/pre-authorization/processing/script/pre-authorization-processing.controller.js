(function() {
    'use strict';

    angular
        .module('claims')
        .controller('PreAuthorizationProcessingController', PreAuthorizationProcessingController)

    PreAuthorizationProcessingController.$inject = ['$scope', '$rootScope', 'PreAuthorizationProcessingService', 'ngNotify'];

    function PreAuthorizationProcessingController($scope, $rootScope, PreAuthorizationProcessingService, ngNotify) {
        $scope.claimReqList = PreAuthorizationProcessingService.getClaimsRequest();
        $scope.claim = $scope.claimReqList[0];
        $scope.isToggled = true;
        $scope.isInlineEdit = true;
        $scope.moduleName = 'eclaim';
        var path = 'resources/directives/grid-directive/view/';
        $scope.gridOptions = {
            data : PreAuthorizationProcessingService.getEclaimList(true),
            columnDefs: [
                {name:'action', displayName:'', headerCellTemplate:path+'headerCheckboxTemplate.html', cellTemplate:path+'staticTemplate.html',width:40, pinnedLeft:true, enableCellEdit:false, enableColumnMenu: false},
                {name:'serviceType', displayName:'Service Type', cellTemplate:path+'textTemplate.html',width:120},
                {name:'serviceCode', displayName:'Service code', cellTemplate:path+'textTemplate.html',width:120},
                {name:'serviceFrmDate', displayName:'Service From', cellTemplate:path+'dateTemplate.html',width:130},
                {name:'serviceToDate', displayName:'Service To', cellTemplate:path+'dateTemplate.html',width:130},
                {name:'days', displayName:'Days', cellTemplate:path+'numberTemplate.html',width:80},
                {name:'quantity', displayName:'QTY', cellTemplate:path+'numberTemplate.html',width:80},
                {name:'treatmentCode', displayName:'Treatment Code', cellTemplate:path+'textTemplate.html',width:140},
                {name:'dhaPrice', displayName:'Regulator Price', cellTemplate:path+'numberTemplate.html',width:145},
                {name:'agreedPrice', displayName:'Agreed Price', cellTemplate:path+'numberTemplate.html',width:125},
                {name:'reqAmount', displayName:'Requested Amount', cellTemplate:path+'numberTemplate.html',width:165},
                {name:'dedAmount', displayName:'Ded Amount', cellTemplate:path+'numberTemplate.html',width:125},
                {name:'suggestedAmount', displayName:'Suggested Amount', cellTemplate:path+'numberTemplate.html',width:125},
                {name:'approvedAmt', displayName:'Approved Amount', cellTemplate:path+'numberTemplate.html',width:160, enableCellEdit:false},
                {name:'rejectedAmt', displayName:'Rejected Amount', cellTemplate:path+'numberTemplate.html',width:160, enableCellEdit:false},
                {name:'rejectedCode', displayName:'Rejected Code', cellTemplate:path+'textTemplate.html',width:130},
                {name:'internalRemarks', displayName:'Internal Remarks', cellTemplate:path+'textTemplate.html',width:162},
                {name:'externalRemarks', displayName:'External Remarks', cellTemplate:path+'textTemplate.html',width:162},
                {name:'serviceDesc', displayName:'Service Description', cellTemplate:path+'textTemplate.html',width:175},
                {name:'serviceToDate', displayName:'Service To', cellTemplate:path+'dateTemplate.html',width:130},
                {name:'clinicianCode', displayName:'Clinician Code', cellTemplate:path+'textTemplate.html',width:120},
                {name:'clinicianName', displayName:'Clinician Name', cellTemplate:path+'textTemplate.html',width:120},
                {name:'clinicianSpeciality', displayName:'Clinician Speciality', cellTemplate:path+'textTemplate.html',width:140},
                {name:'rejDesc', displayName:'Rejection Description', cellTemplate:path+'textTemplate.html',width:175},
                {name:'settings', displayName:'Settings', cellTemplate:path+'settingsTemplate.html',width:75, pinnedRight:true, enableCellEdit:false},          
            ],
            enableSorting: false
        }
        
        $scope.addRow = function() {
            var newClaim = PreAuthorizationProcessingService.newClaim();
            newClaim.serviceType = ($scope.gridOptions.data.length +1 ).toString();
            newClaim.editable = true;
            newClaim.editedColName = 'serviceType';
            $scope.gridOptions.data.push(newClaim);
        }

        $scope.deleteRow = function(index) {
            $scope.gridOptions.data.splice(index, 1);
            renderTotals($scope.gridOptions.data);
            ngNotify.set('Claim Deleted Succesfully.', 'success');
        }

        $scope.toggleJson = function(item) {
            $scope.claim = item;
            $scope.isToggled = !$scope.isToggled;
            $scope.gridOptions.data = PreAuthorizationProcessingService.getEclaimList($scope.isToggled);
        }

        $scope.approveClaim = function() {
            if($scope.gridOptions.data.length) {
                ngNotify.set('Claim Approved Succesfully.', 'success');    
            } else {
                swal("", "No Records to Approve", "warning");
            }            
        }

        $scope.rejectClaim = function() {   
            if($scope.gridOptions.data.length) {
                ngNotify.set('Claim Rejected.', 'error');
            } else {
                swal("", "No Records to Reject", "warning");
            }
        }

        $scope.$watch('gridOptions.data', function(newValue, oldValue, scope) {
            renderTotals(newValue);
        });

        function renderTotals(result) {
            var totalApprovedAmount = 0;
            var totalRejectedAmount = 0;
            var totalDeductionAmount = 0;
            var totalPenaltyAmount = 0;
            angular.forEach(result, function(claim, key) {
                totalApprovedAmount += claim.approvedAmt;
                totalRejectedAmount += claim.rejectedAmt;
                totalDeductionAmount += claim.dedAmount;
                totalPenaltyAmount += claim.shortfallAmount;
            })
            $scope.totalApprovedAmount = totalApprovedAmount;
            $scope.totalRejectedAmount = totalRejectedAmount;
            $scope.totalDeductionAmount = totalDeductionAmount;
            $scope.totalPenaltyAmount = totalPenaltyAmount;
        }
        
        $scope.gridAction = function(info) {
            switch(info.action) {
                case "new":
                    $scope.addRow()
                    break;
                case "edit":
                    console.log('Eclaim InlineEdit');
                    break;
                case "delete":
                    $scope.deleteRow(info.index);    
                    break;
                case "approve":
                    $scope.approveClaim(info.data);
                    break;
                case "reject":
                    $scope.rejectClaim(info.data);
                    break;
            }
        }
    }
})();