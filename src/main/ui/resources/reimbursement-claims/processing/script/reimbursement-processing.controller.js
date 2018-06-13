(function() {
    'use strict';

    angular
        .module('claims')
            .controller('ReimbursmentProcessingController', ReimbursmentProcessingController);

        ReimbursmentProcessingController.$inject = ['$scope', '$rootScope', 'ReimbursementProcessingService', 'ngNotify', '$timeout', 'AutocompleteService','UIDefinationService', '$filter', 'reimbursementClaimInfo', 'ReimbursementRegistrationFactory', 'ReimbursementProcessingFactory', 'SpinnerService', 'companyId', '$q', 'dateFormat'];

        function ReimbursmentProcessingController($scope, $rootScope, ReimbursementProcessingService, ngNotify, $timeout, AutocompleteService, UIDefinationService, $filter, reimbursementClaimInfo, ReimbursementRegistrationFactory, ReimbursementProcessingFactory, SpinnerService, companyId, $q, dateFormat) {
            $scope.dateFormat = dateFormat;
            SpinnerService.stop();
            $scope.selectedClaim = reimbursementClaimInfo;
            $scope.selectedClaim.requestNumber = 1;
            $scope.selectedClaim.age = calculateAge();
            $scope.search = {};
            $scope.treatmentCodes = [];
            $scope.rejectionCode = [];
            $scope.createNew = true;
            $scope.isBaseCurrency = false;
            $scope.diagnosis = {};
            var currencyFieldsMap = {
                "requestedAmountBC" : "requestedAmount",
                "policyDeductibleAmountBC" : "policyDeductibleAmount",
                "manualDeductionAmountBC" : "manualDeductionAmount",
                "penaltyAmountBC" : "penaltyAmount",
                "suggestedAmoutBC" : "suggestedAmout",
                "approvedAmountBC" : "approvedAmount",
                "rejectedAmountBC" : "rejectedAmount"
            }
            $scope.accordionToggle = {
                isProviderDetailOpen : true,
                isClaimDetailOpen : false,
                isPolicyDetailOpen : false,
                isMemberDetailOpen : false
            }
            var statusParam = {'compId' : companyId, 'modType' : "03"}
            var uiDefPromises = {};
            uiDefPromises['claimTypes'] = UIDefinationService.getClaimType({'compId' : companyId}).$promise;
            uiDefPromises['claimConditions'] = UIDefinationService.getClaimCondition({'compId' : companyId}).$promise;
            uiDefPromises['encounterType'] = UIDefinationService.getEncounterTypes({'compId' : companyId}).$promise;
            uiDefPromises['statusReason'] = UIDefinationService.getClaimsStatusReason({'compId' : companyId}).$promise;
            uiDefPromises['statusTypes'] = UIDefinationService.getStatusTypes(statusParam).$promise;
            // uiDefPromises['estTypes'] = UIDefinationService.getClaimantESTType({'compId' : companyId}).$promise;
            // uiDefPromises['lossTypes'] = UIDefinationService.getClaimLossType({'compId' : companyId}).$promise;
            $q.all(uiDefPromises).then((uidTypes) => {
                $scope.claimTypes = uidTypes['claimTypes'].rowData;
                $scope.claimConditions = uidTypes['claimConditions'].rowData;
                $scope.encounterTypeMap = ReimbursementRegistrationFactory.constructUidMap(uidTypes['encounterType'].rowData, "id", "value");
                $scope.statusReasonMap = ReimbursementRegistrationFactory.constructUidMap(uidTypes['statusReason'].rowData, "value", "id");
                $scope.statusMap = ReimbursementRegistrationFactory.constructUidMap(uidTypes['statusTypes'].rowData, "value", "id");
            })
            AutocompleteService.getUniversalCurrencyDetails({'compId' : companyId}, function(resp) {
                $scope.curencyList = resp.rowData;
                $scope.exchangeRateMap = ReimbursementRegistrationFactory.constructUidMap(resp.rowData, "ExchangeCurrency", "ExchangeRate");
            });
            AutocompleteService.geteventCountry(function(resp) {
                $scope.eventCountries = resp;
            })
            function init() {
                $scope.isInlineEdit = false;
                $scope.moduleName = 'reimbursement';
                $scope.currencyType = '1';
                $scope.claim = ReimbursementProcessingFactory.createNewReimbursmentObject();
                // $scope.claimReqList = ReimbursementProcessingFactory.getClaimsRequest();
                initGrid();
            }

            $scope.createNewClaim = function() {
                $scope.localValidation = false;
                $scope.createNew = true;
                $scope.claim = ReimbursementProcessingFactory.createNewReimbursmentObject();
            }

            $scope.deleteRecord = function(recordIndex) {
                if ($scope.claimsResult.length > 0) {
                    $scope.claimsResult.splice(recordIndex, 1);
                }
            }

            $scope.selectAll = function(checkAll) {
                angular.forEach($scope.claimsResult, function(claim, key) {
                    $scope.claimsResult[key]['onCheck'] = checkAll;
                })
            }


            function initGrid() {
                $scope.gridOptions = {
                    data : [],
                    enableSorting: false,
                    enableVerticalScrollbars : 'Never'
                }
                constructGridColumnDef();
            }

            $scope.deleteRow = function(index) {
                $scope.gridOptions.data.splice(index, 1);
                renderTotals($scope.gridOptions.data);
                ngNotify.set('Deleted Succesfully.', 'success');
            }

            $scope.approveClaim = function(claim) {
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
            
            $scope.saveRecord = function(saveType) {
                $scope.localValidation = false;
                if($scope.reimbursementForm.$invalid) {
                    $scope.localValidation = true;
                    return;
                }                
                var processingDto = mapClaimDTO();
                SpinnerService.start();
                ReimbursementProcessingService.saveProcessingDetails(processingDto, function(resp) {
                    ReimbursementProcessingFactory.setPrevProcessingClaim(angular.copy(resp));
                    ReimbursementProcessingFactory.setPrevDiagnosis(angular.copy($scope.diagnosis));
                    $scope.claim = angular.extend($scope.claim, resp.processingServiceDTOs[0]);
                    $scope.selectedClaim = angular.extend($scope.selectedClaim, resp);
                    processClaim($scope.claim);
                    SpinnerService.stop();
                    $scope.claim = ReimbursementProcessingFactory.createNewReimbursmentObject();
                    $scope.createNew = saveType == 'SaveAndNew';
                    ngNotify.set('Saved Succesfully.', 'success');
                }, onSaveError)
                
                
                function onSaveError() {
                    SpinnerService.stop();
                }
            }

            function mapClaimDTO(claimToSave) {
                var claimToSave = angular.copy($scope.selectedClaim);
                compareTableToUpdate(claimToSave);
                $scope.claim.currencyId = claimToSave.baseCurrency;
                $scope.claim.statusDate = new Date();
                claimToSave.claimStatusReason = $scope.statusReasonMap["WIP"];
                $scope.claim.internalRejectionCode = $scope.claim.rejectionCode ? $scope.claim.rejectionCode.RejectionCode : undefined;
                $scope.claim.serviceType = $scope.claim.treatmentCode.ServiceTypeId;
                $scope.claim.serviceId = $scope.claim.treatmentCode.ServiceCode;
                $scope.claim.benefitId = $scope.claim.treatmentCode.BenefitId;
                $scope.claim.subBenefitId = $scope.claim.treatmentCode.SubBenefitId;
                $scope.claim.currencyType = $scope.selectedCurrency && ($scope.selectedCurrency != claimToSave.baseCurrency) ? 'F' : 'L';
                $scope.claim.rejectedAmount = $scope.claim.claimStatus == 'A' ?  ($scope.claim.requestedAmount - $scope.claim.suggestedAmout) : 0;
                $scope.claim.approvedAmount = $scope.claim.claimStatus == 'A' ?  $scope.claim.suggestedAmout : 0;
                var exchangeRate = $scope.selectedCurrency ? $scope.exchangeRateMap[$scope.selectedCurrency] : $scope.exchangeRateMap[claimToSave.baseCurrency];
                ReimbursementProcessingFactory.constructClaimBaseCurrencyFields(currencyFieldsMap, exchangeRate, $scope.claim);
                claimToSave.processingServiceDTOs = [angular.copy($scope.claim)];
                return claimToSave;
            }            

            function compareTableToUpdate(claimToSave) {
                var prevProcessingClaim = angular.copy(ReimbursementProcessingFactory.getPrevProcessingClaim());
                var prevDiagnosis = angular.copy(ReimbursementProcessingFactory.getPrevDiagnosis());
                if(prevProcessingClaim) {
                    deleteDiagAndServiceDto(claimToSave, prevProcessingClaim);
                    claimToSave.isChanged = !angular.equals(claimToSave, prevProcessingClaim);
                }    
                if($scope.claim.dml == 'E') {
                    $scope.claim.isChanged = !angular.equals($scope.claim, $scope.prevClaim);
                }
                claimToSave.primaryDiagnosis = {
                    'diagId' : $scope.diagnosis.primaryDiag.DiagnosisCode,
                    'diagType' : 'Primary'
                }
                claimToSave.secondaryDiagnosis = {
                    'diagId' : $scope.diagnosis.secondaryDiag.DiagnosisCode,
                    'diagType' : 'Secondary'
                }
                if(prevDiagnosis) {
                    claimToSave.primaryDiagnosis.isChanged = !angular.equals($scope.diagnosis.primaryDiag, prevDiagnosis.primaryDiag);
                    claimToSave.secondaryDiagnosis.isChanged = !angular.equals($scope.diagnosis.secondaryDiag, prevDiagnosis.secondaryDiag);    
                };
            }

            function deleteDiagAndServiceDto(claimToSave, prevProcessingClaim) {
                delete claimToSave.primaryDiagnosis;
                delete claimToSave.secondaryDiagnosis;
                delete claimToSave.processingServiceDTOs;
                delete prevProcessingClaim.primaryDiagnosis;
                delete prevProcessingClaim.secondaryDiagnosis;
                delete prevProcessingClaim.processingServiceDTOs;
            }

            $scope.editClaim = function(entity) {
                $scope.claim = angular.copy(entity);
                $scope.claim['dml'] = 'E';
                $scope.createNew = true;
                $scope.prevClaim = angular.copy($scope.claim);
                // var params = {
                //     "claimNumber" : $scope.selectedClaim.claimNumber.toString(),
                //     "requestNumber" : $scope.selectedClaim.requestNumber.toString(),
                //     "id" : entity.reimbursementProcessId.toString()
                // };
                // ReimbursementProcessingService.getReimbursementProcessingDetails(params, function(resp) {
                //     console.log("resp ::", resp);
                // }, onerror)
                // function onerror(err) {
                //     console.log("error occured");
                // }
            }

            $scope.onCancel = function() {
                $scope.createNew = false;
            }

            function processClaim(claim) {
                $scope.gridOptions['data'] = $scope.gridOptions['data'].map(record =>  {
                    if (record['id'] == claim['id']) {
                        return claim;
                    }
                    return record;
                });
                if (claim['dml'] == 'N') {
                    $scope.gridOptions['data'].push(claim);
                }
            }

            $scope.$watch('gridOptions.data', function(newValue, oldValue, scope) {
                renderTotals(newValue);
            });

            $scope.submitForApproval = function(claim) {
                if($scope.gridOptions.data.length) {
                    if (validateInformationSection()) {
                        $scope.infoToggle = false;
                        ngNotify.set('Claim Approved Succesfully.', 'success');
                    }
                } else {
                    swal("", "No Records to Approve", "warning");
                }
            }

            function validateInformationSection() {
                $scope.submitted = true;
                var policyErrorArray = [];
                var memberErrorArray = [];
                var claimErrorArray = [];
                var providerErrorArray = [];

                var policyDetailsRequiredFields = ['claimCondition'];
                var memberDetailsRequiredFields = ['memberName','memberNo','category','gender'];
                var claimDetailsRequiredFields = ['country'];
                var providerDetailsRequiredFields = ['primaryDiagnosis', 'primaryDiagDisc', 'secDiagnosis', 'secDiagnosisDesc', 'providerName', 'providerCode', 
                                                    'providerLicense', 'voucherNumber', 'encounterType', 'claimType'];
                
                angular.forEach(providerDetailsRequiredFields, function(fieldName, key) {
                    if ($scope.providerDetails[fieldName] == '' || $scope.providerDetails[fieldName] == null) {
                        providerErrorArray.push(fieldName);
                    }
                });

                angular.forEach(claimDetailsRequiredFields, function(fieldName, key) {
                    if ($scope.claimDetails[fieldName] == '' || $scope.claimDetails[fieldName] == null) {
                        claimErrorArray.push(fieldName);
                    }
                });

                angular.forEach(policyDetailsRequiredFields, function(fieldName, key) {
                    if ($scope.policyDetails[fieldName] == '' || $scope.policyDetails[fieldName] == null) {
                        policyErrorArray.push(fieldName);
                    }
                });

                angular.forEach(memberDetailsRequiredFields, function(fieldName, key) {
                    if ($scope.memberDetails[fieldName] == '' || $scope.memberDetails[fieldName] == null) {
                        memberErrorArray.push(fieldName);
                    }
                });

                $scope.accordionToggle.isProviderDetailOpen = (providerErrorArray.length > 0);
                $scope.accordionToggle.isClaimDetailOpen = (claimErrorArray.length > 0);
                $scope.accordionToggle.isPolicyDetailOpen = (policyErrorArray.length > 0);
                $scope.accordionToggle.isMemberDetailOpen = (memberErrorArray.length > 0);                
                $scope.isCloseOthers = $scope.processingForm.$valid;
                if ($scope.processingForm.$invalid) {
                    swal("Please Enter all the required fields", "", "error").then(
                        function() {
                            ($scope.processingForm.$error.required[0].$$element[0]).focus();
                        }
                    );
                    $scope.infoToggle = true;
                    return false;
                }
                return $scope.processingForm.$valid;
            }

            function renderTotals(result) {
                var totalApprovedAmount = 0;
                var totalRejectedAmount = 0;
                var totalPenaltyAmount = 0;
                var totalDeductionAmount = 0;
                var currencyFieldsMap = {
                    "requestedAmountBC" : "requestedAmount",
                    "policyDeductibleAmountBC" : "policyDeductibleAmount",
                    "manualDeductionAmountBC" : "manualDeductionAmount",
                    "penaltyAmountBC" : "penaltyAmount",
                    "suggestedAmoutBC" : "suggestedAmout",
                    "approvedAmountBC" : "approvedAmount",
                    "rejectedAmount" : "rejectedAmountBC"
                }
                angular.forEach(result, function(claim, key) {
                    totalApprovedAmount += ($scope.isBaseCurrency ? claim.approvedAmountBC : claim.approvedAmount);
                    totalRejectedAmount += ($scope.isBaseCurrency ? claim.rejectedAmountBC : claim.rejectedAmount);
                    totalPenaltyAmount += ($scope.isBaseCurrency ? claim.penaltyAmountBC : claim.penaltyAmount);
                    totalDeductionAmount += ($scope.isBaseCurrency ? claim.manualDeductionAmountBC : claim.manualDeductionAmount);
                })
                $scope.totalApprovedAmount = totalApprovedAmount;
                $scope.totalRejectedAmount = totalRejectedAmount;
                $scope.totalPenaltyAmount = totalPenaltyAmount;
                $scope.totalDeductionAmount = totalDeductionAmount;
            }

            $scope.getAutoCompleteList = function (searchText, field, methodName) {
                var searchParams = constructSearchparams(field, searchText);
                return AutocompleteService[methodName](searchParams).$promise.then(function(resp) {
                    return resp.rowData;
                })
            }

            $scope.getTreatmentCodes = function(searchText, fieldName) {
                if(!searchText || searchText.length < 3) return [];
                var searchObj = constructAutoCompleteSearchparams(searchText, fieldName)
                return AutocompleteService.getTreatmentCodes(searchObj).$promise.then(function(resp) {
                    var data = resp.rowData;
                    angular.forEach(data, function(value, key) {
                        value.displayName = value.ServiceCode + ' / ' + value.SubBenefitId;
                    });
                    return data;
                })
            }

            $scope.getDiagnosisCodes = function(searchText, fieldName) {
                if(!searchText || searchText.length < 3) return [];
                var searchObj = constructAutoCompleteSearchparams(searchText, fieldName)
                return AutocompleteService.getDiagnosisCodes(searchObj).$promise.then(function(resp) {
                    var data = resp.rowData;
                    return data;
                })
            }

            $scope.getRejectionCodes = function(searchText, fieldName) {
                if(!searchText || searchText.length < 3) return [];
                var searchObj = constructAutoCompleteSearchparams(searchText, fieldName)
                return AutocompleteService.getRejectionCodes(searchObj).$promise.then(function(resp) {
                    var data = resp.rowData;
                    return data;
                })
            }

            function constructAutoCompleteSearchparams(searchText, fieldName) {
                var searchObj = {}
                searchObj["compId"] = companyId;
                searchObj["serviceFrom"] = $filter('date')(new Date($scope.claim.treatmentFromDate), 'yyyy-MM-dd');
                searchObj[fieldName] = searchText+"%"
                return searchObj;
            }            

            function constructSearchparams(field, searchText) {
                var searchObj = {}
                if($scope.moduleName == 'reimbursement') {
                    $scope.search.memberNumber =  $scope.search.memberName;
                        searchObj["compId"] = companyId,
                        searchObj["policyNumber"] = "%"
                        searchObj["memberNumber"] = "%",
                        searchObj["memberName"] = "%",
                        searchObj["voucherNumber"] = "%",
                        searchObj["cardNumber"] = "%",
                        searchObj["emiratesId"] = field == 'emiratesId' ? searchText+"%" : ($scope.search.emiratesId ? $scope.search.emiratesId.CLMR_UID_ID : "%")
                }
                return searchObj;
            }

            $scope.querySearch = function (query, label) {
                return query ? $scope.autoSearch.filter(createFilterFor(query, label)) : $scope.autoSearch;
            }

            function createFilterFor(query, label) {
                var lowercaseQuery = angular.lowercase(query);
                return function filterFn(state) {
                    return (((angular.lowercase(state[label]).indexOf(lowercaseQuery) != 0) && angular.lowercase(state[label]).indexOf(lowercaseQuery) != -1) || (angular.lowercase(state[label]).indexOf(lowercaseQuery) === 0));
                };
            }

            $scope.toggleInfo = function() {
                $scope.infoToggle = !$scope.infoToggle;
                $scope.accordionToggle.isProviderDetailOpen = $scope.accordionToggle.isProviderDetailOpen ? $scope.accordionToggle.isProviderDetailOpen : $scope.processingForm.$valid;
                $scope.isCloseOthers = true;
            }

            $scope.gridAction = function(info) {
                switch(info.action) {
                    case "new":
                        $scope.createNewClaim()
                        break;
                    case "edit":
                        $scope.editClaim(info.data);
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
                    case "convertCurrency":
                        $scope.selectedCurrency = info.selectedCurrency;
                        break;
                    case "localBaseCurrenyToggled":
                        $scope.isBaseCurrency =  info.isBaseCurreny;
                        constructGridColumnDef();
                        renderTotals($scope.gridOptions.data);
                        break;
                }
            }

            function constructGridColumnDef() {
                var path = 'resources/directives/grid-directive/view/';
                $scope.gridOptions.columnDefs = [
                    {name:'action', displayName:'', headerCellTemplate: (path + 'headerCheckboxTemplate.html'), cellTemplate: path + 'staticTemplate.html',width:40, pinnedLeft:true, enableColumnMenu: false},
                    {name:'treatmentCode.displayName', displayName:'Treatment Code/SubBenefit',width:200},
                    {name:'treatmentFromDate', displayName:'Service From', cellTemplate: path + 'dateTemplate.html',width:120},
                    {name:'treatmentToDate', displayName:'Service To', cellTemplate: path + 'dateTemplate.html',width:110},
                    {name:'noOfTreamentDays', displayName:'Days', width:90},
                    {name:($scope.isBaseCurrency ? 'requestedAmountBC' : 'requestedAmount'), displayName:'Request Amount', width:140},
                    {name:($scope.isBaseCurrency ? 'policyDeductibleAmountBC' : 'policyDeductibleAmount'), displayName:'Policy Ded Amount',width:150},
                    {name:($scope.isBaseCurrency ? 'manualDeductionAmountBC' : 'manualDeductionAmount'), displayName:'Manual Deduction', width:140},
                    {name:($scope.isBaseCurrency ? 'penaltyAmountBC' : 'penaltyAmount'), displayName:'Penalty Amount', width:145},
                    {name:($scope.isBaseCurrency ? 'suggestedAmoutBC' : 'suggestedAmout'), displayName:'Suggessted Amount', width:150},
                    {name:($scope.isBaseCurrency ? 'approvedAmountBC' : 'approvedAmount'), displayName:'Approved Amount', width:165},
                    {name:($scope.isBaseCurrency ? 'rejectedAmountBC' : 'rejectedAmount'), displayName:'Rejected Amount', width:145},
                    {name:'rejectionCode.RejectionCode', displayName:'Rejection Code',width:140},
                    {name:'rejectionCode.RejectionDesc', displayName:'Rejection Description',width:210},
                    {name:'claimStatus', displayName:'Status', width:155},
                    {name:'internalRemarks', displayName:'Internal Remarks', cellTemplate: path + 'descriptionTemplate.html', width:210},
                    {name:'externalRemarks', displayName:'External Remarks', cellTemplate: path + 'descriptionTemplate.html', width:210},
                    {name:'Settings', displayName:'Settings', cellTemplate: path + 'settingsTemplate.html',width:75, pinnedRight:true, enableColumnMenu: false}
                ];    
            }

            $scope.constructServiceTo = function() {
                if($scope.claim.noOfTreamentDays != null){
                    $scope.claim.treatmentToDate = ReimbursementProcessingFactory.addDays($scope.claim.treatmentFromDate, $scope.claim.noOfTreamentDays);
                }
            }

            $scope.calculateDays = function() {
                if($scope.claim.treatmentFromDate && $scope.claim.treatmentToDate) {
                    var timeDiff = Math.abs($scope.claim.treatmentToDate.getTime() - $scope.claim.treatmentFromDate.getTime());
                    $scope.claim.noOfTreamentDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
                }
            }

            $scope.calculateSuggestedAmount = function() {
                if($scope.claim.requestedAmount) {
                    $scope.claim.suggestedAmout = ($scope.claim.requestedAmount - ($scope.claim.policyDeductibleAmount || 0) - 
                                                  ($scope.claim.manualDeductionAmount || 0) - ($scope.claim.penaltyAmount || 0))
                }                
            }

            function calculateAge() {
                return $scope.selectedClaim.memberDOB ? (new Date().getFullYear() - $scope.selectedClaim.memberDOB[0]) : null;
            }
            init();
        }
})()