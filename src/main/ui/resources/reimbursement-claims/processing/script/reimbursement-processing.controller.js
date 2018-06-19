(function() {
    'use strict';

    angular
        .module('claims')
            .controller('ReimbursmentProcessingController', ReimbursmentProcessingController);

        ReimbursmentProcessingController.$inject = ['$scope', '$rootScope', 'ReimbursementProcessingService', 'ngNotify', '$timeout', 'AutocompleteService','UIDefinationService', '$filter', 'ReimbursementRegistrationFactory', 'ReimbursementProcessingFactory', 'SpinnerService', 'companyId', '$q', '$window', 'dateFormat', 'ClaimsListViewService', '$stateParams'];

        function ReimbursmentProcessingController($scope, $rootScope, ReimbursementProcessingService, ngNotify, $timeout, AutocompleteService, UIDefinationService, $filter, ReimbursementRegistrationFactory, ReimbursementProcessingFactory, SpinnerService, companyId, $q, $window, dateFormat, ClaimsListViewService, $stateParams) {
            $scope.dateFormat = dateFormat;
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
            }).catch((err) => {
                alert("Error");
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
                $scope.claim = ReimbursementProcessingFactory.createNewReimbursmentObject();
                $scope.selectedClaim.requestNumber = $scope.selectedClaim.requestNumber ? $scope.selectedClaim.requestNumber : 1;
                $scope.selectedClaim.age = calculateAge();
                $scope.search = {};
                $scope.treatmentCodes = [];
                $scope.rejectionCode = [];
                if($scope.isEditMode) {
                    ReimbursementProcessingFactory.setPrevProcessingClaim(angular.copy($scope.selectedClaim));
                    var diagObj = constructDiagToCompare($scope.selectedClaim.primaryDiagnosis.diagId, $scope.selectedClaim.secondaryDiagnosis.diagId);
                    ReimbursementProcessingFactory.setPrevDiagnosis(angular.copy(diagObj));
                    getDiagnosisCode('primaryDiag', 'primaryDiagnosis');
                    getDiagnosisCode('secondaryDiag', 'secondaryDiagnosis');
                }
                $scope.isBaseCurrency = false;                
                $scope.isInit = true;
            }

            function getDiagnosisCode(field, dtoField) {
                var searchObj = constructAutoCompleteSearchparams($scope.selectedClaim[dtoField].diagId, 'diagnosisCode')
                AutocompleteService.getDiagnosisCodes(searchObj, function(resp) {
                    $scope.diagnosis[field] = resp.rowData[0];
                })                    
            }            

            var data = ClaimsListViewService.getClaim();
            var registrationId = data.id;
            SpinnerService.start();
            if($stateParams.tabStatus == 'ASN') {
                ReimbursementProcessingService.getReimbursementInitProcessingDetails(data, function(initResp) {
                    SpinnerService.stop();
                    $scope.selectedClaim = initResp;
                    $scope.selectedClaim.created = true;
                    $scope.createNew = true;
                    init();
                }, onerror)
            } else {
                $scope.isloadingData = true;
                ReimbursementProcessingService.getReimbursementProcessingDetailsForAssignment(data, function(resp) {
                    SpinnerService.stop();
                    $scope.selectedClaim = resp;
                    $scope.selectedClaim.created = false;
                    $scope.createNew = false;
                    $scope.isEditMode = true;
                    init();
                    getGridData();
                }, onerror)
            }

            function getGridData() {
                $scope.gridOptions['data'] = [];
                ReimbursementProcessingService.getReimbursementProcessingServiceDetails({'registrationId' : registrationId}, function(resp) {
                    $scope.isloadingData = false;
                    $scope.gridOptions['data'] = resp;
                }, onerror)
            }

            function onerror() {
                console.log("Error");
                SpinnerService.stop();
            }

            $scope.createNewClaim = function() {
                $scope.localValidation = false;
                $scope.createNew = true;
                $scope.claim = ReimbursementProcessingFactory.createNewReimbursmentObject();
                $scope.claim.treatmentFromDate = angular.copy($scope.selectedClaim.serviceFmDate);
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
                    var claimToApprove = angular.copy(ReimbursementProcessingFactory.getPrevProcessingClaim());
                    var currentClaim = angular.copy(claim);
                    currentClaim.claimStatus = $scope.statusMap['Approved'];
                    currentClaim.approvedAmount = currentClaim.suggestedAmout;
                    currentClaim.approvedAmountBC = currentClaim.suggestedAmoutBC;
                    claimToApprove.processingServiceDTOs = [currentClaim];
                    SpinnerService.start();
                    ReimbursementProcessingService.approveProcessingServiceLineItem(claimToApprove, function(resp) {
                        SpinnerService.stop();
                        ngNotify.set('Claim Approved Succesfully.', 'success');
                        var claim = angular.extend(currentClaim, resp.processingServiceDTOs[0]);
                        claim.dml="E";
                        processClaim(claim);
                    }, onerror)
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
                if($scope.claim.treatmentCode == null || $scope.diagnosis.primaryDiag == null || $scope.diagnosis.secondaryDiag == null){
                    $scope.validInputValidation = true;
                }
                $scope.localValidation = false;
                if($scope.reimbursementForm.$invalid) {
                    $scope.localValidation = true;
                    if($scope.infoForm.$invalid){
                        $scope.infoToggle = true;
                        $scope.infoForm.$error.required[0].$$element[0].focus();
                    }
                    return;
                }    
                $scope.dateValidation = false;     
                var processingDto = mapClaimDTO();
                console.log("processingDto :: ",processingDto);
                SpinnerService.start();
                processingDto.serviceAdded = $scope.claim.dml == 'N' ? true : false;
                ReimbursementProcessingService.saveProcessingDetails(processingDto, function(resp) {
                    $scope.selectedClaim.created = false;
                    var diagObj = constructDiagToCompare($scope.diagnosis.primaryDiag.DiagnosisCode, $scope.diagnosis.secondaryDiag.DiagnosisCode);
                    ReimbursementProcessingFactory.setPrevProcessingClaim(angular.copy(resp));
                    ReimbursementProcessingFactory.setPrevDiagnosis(angular.copy(diagObj));
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
                var currentClaim = angular.copy($scope.claim);
                compareTableToUpdate(claimToSave, currentClaim);
                $scope.claim.currencyId = claimToSave.baseCurrency;
                $scope.claim.statusDate = new Date();
                claimToSave.claimStatusReason = $scope.statusReasonMap["WIP"];
                currentClaim.internalRejectionCode = $scope.claim.rejectionCode ? $scope.claim.rejectionCode.RejectionCode : undefined;
                currentClaim.serviceType = $scope.claim.treatmentCode.ServiceTypeId;
                currentClaim.serviceId = $scope.claim.treatmentCode.ServiceCode;
                currentClaim.benefitId = $scope.claim.treatmentCode.BenefitId;
                currentClaim.subBenefitId = $scope.claim.treatmentCode.SubBenefitId;
                currentClaim.currencyType = $scope.selectedCurrency && ($scope.selectedCurrency != claimToSave.baseCurrency) ? 'F' : 'L';
                currentClaim.rejectedAmount = $scope.claim.claimStatus == 'A' ?  ($scope.claim.requestedAmount - $scope.claim.suggestedAmout) : 0;
                currentClaim.approvedAmount = $scope.claim.claimStatus == 'A' ?  $scope.claim.suggestedAmout : 0;
                var exchangeRate = $scope.selectedCurrency ? $scope.exchangeRateMap[$scope.selectedCurrency] : $scope.exchangeRateMap[claimToSave.baseCurrency];
                ReimbursementProcessingFactory.constructClaimBaseCurrencyFields(currencyFieldsMap, exchangeRate, $scope.claim);
                claimToSave.processingServiceDTOs = [angular.copy(currentClaim)];
                return claimToSave;
            }            

            function compareTableToUpdate(claimToSave, currentClaim) {
                var prevProcessingClaim = angular.copy(ReimbursementProcessingFactory.getPrevProcessingClaim());
                var prevDiagnosis = angular.copy(ReimbursementProcessingFactory.getPrevDiagnosis());
                if(prevProcessingClaim) {
                    debugger;
                    deleteDiagAndServiceDto(claimToSave, prevProcessingClaim);
                    claimToSave.changed = !angular.equals(claimToSave, prevProcessingClaim);
                }    
                if($scope.claim.dml == 'E') {
                    var prevClaim = angular.copy($scope.prevClaim);
                    deleteServiceAndRejectionCode(currentClaim, prevClaim);
                    delete $scope.claim.treatmentCode.ServiceName;
                    delete $scope.prevClaim.treatmentCode.ServiceName;
                    var treatmentCodeChanged = !angular.equals($scope.claim.treatmentCode, $scope.prevClaim.treatmentCode);
                    var rejectionCodeChanged = $scope.claim.rejectionCode ? !angular.equals($scope.claim.rejectionCode.RejectionCode, $scope.prevClaim.rejectionCode.RejectionCode) : false;
                    currentClaim.changed = (treatmentCodeChanged || rejectionCodeChanged) ? true : !angular.equals(currentClaim, prevClaim);
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
                    claimToSave.primaryDiagnosis.changed = !angular.equals($scope.diagnosis.primaryDiag.DiagnosisCode, prevDiagnosis.primaryDiag);
                    claimToSave.secondaryDiagnosis.changed = !angular.equals($scope.diagnosis.secondaryDiag.DiagnosisCode, prevDiagnosis.secondaryDiag);    
                };
            }

            function deleteServiceAndRejectionCode(currentClaim, prevClaim) {
                delete currentClaim.treatmentCode;
                delete currentClaim.RejectionCode;
                delete prevClaim.treatmentCode;
                delete prevClaim.RejectionCode;
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
                entity.treatmentCode = {
                    'displayName' : entity.treatmentCodeDisplayName,
                    'ServiceCode' : entity.serviceId,
                    'BenefitId' : entity.benefitId,
                    'SubBenefitId' : entity.subBenefitId,
                    'ServiceTypeId' : entity.serviceType
                };
                $scope.claim = angular.copy(entity);
                $scope.claim['dml'] = 'E';
                $scope.createNew = true;
                $scope.prevClaim = angular.copy($scope.claim);
            }

            $scope.onCancel = function() {
                $scope.createNew = false;
            }

            function processClaim(claim) {
                if (claim['dml'] == 'N') {
                    $scope.gridOptions['data'].push(claim);
                } else {
                    $scope.gridOptions['data'] = $scope.gridOptions['data'].map(record =>  {
                        if (record['reimbursementProcessId'] == claim['reimbursementProcessId']) {
                            return claim;
                        }
                        return record;
                    });
                }
            }

            $scope.$watch('gridOptions.data', function(newValue, oldValue, scope) {
                renderTotals(newValue);
            });

            $scope.submitForApproval = function(claim) {
                if($scope.gridOptions.data.length) {
                    //if (validateInformationSection()) {
                        var claimToApprove = angular.copy(ReimbursementProcessingFactory.getPrevProcessingClaim());
                        var datas = angular.copy($scope.gridOptions.data);
                        var processingServiceDTOs = datas.filter((item) => {
                            if(item.isChecked) {
                                item.approvedAmount = item.suggestedAmout;
                                item.approvedAmountBC = item.suggestedAmoutBC;
                                item.claimStatus = $scope.statusMap['Approved'];
                                return item;
                            }
                        })
                        console.log('processingServiceDTOs ::', processingServiceDTOs);
                        $scope.infoToggle = false;
                        ngNotify.set('Claim Approved Succesfully.', 'success');
                    //}
                } else {
                    swal("", "No Records to Approve", "warning");
                }
            }

            function validateInformationSection() {
                $scope.submitted = true;
                var providerErrorArray = [];
                var providerDetailsRequiredFields = ['primaryDiagnosis', 'primaryDiagDisc', 'secDiagnosis', 'secDiagnosisDesc', 'providerName', 'providerCode', 
                                                    'providerLicense', 'voucherNumber', 'encounterType', 'claimType'];
                
                angular.forEach(providerDetailsRequiredFields, function(fieldName, key) {
                    if ($scope.providerDetails[fieldName] == '' || $scope.providerDetails[fieldName] == null) {
                        providerErrorArray.push(fieldName);
                    }
                });

                $scope.accordionToggle.isProviderDetailOpen = (providerErrorArray.length > 0);
                $scope.isCloseOthers = $scope.processingForm.$valid;
                if ($scope.processingForm.$invalid) {
                    swal("Please Enter all the required fields", "", "error").then(
                        function() {
                            ($scope.processingForm.$error.required[0]).focus();
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

            $scope.getAutoCompleteList = function (searchText, fieldName, methodName) {
                var searchParams = constructAutoCompleteSearchparams(searchText, fieldName);
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
                $scope.accordionToggle.isProviderDetailOpen = $scope.accordionToggle.isProviderDetailOpen ? $scope.accordionToggle.isProviderDetailOpen : $scope.infoForm.$valid;
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
                    {name:'treatmentCodeDisplayName', displayName:'Treatment Code/SubBenefit',width:200},
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
                if($scope.claim.treatmentFromDate && $scope.claim.treatmentToDate && ($scope.claim.treatmentFromDate < $scope.claim.treatmentToDate)) {
                    var timeDiff = Math.abs($scope.claim.treatmentToDate.getTime() - $scope.claim.treatmentFromDate.getTime());
                    $scope.claim.noOfTreamentDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
                }else{
                    $scope.claim.noOfTreamentDays = 0;
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

            function constructDiagToCompare(primaryDiagId, secondaryDiagId) {
                var diagnosis = {
                    'primaryDiag' : primaryDiagId,
                    'secondaryDiag' : secondaryDiagId
                }
                return diagnosis;
            }
            initGrid();
        }
})()