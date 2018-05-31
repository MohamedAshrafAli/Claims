(function() {
    'use strict';

    angular
        .module('claims')
        .controller('ReimbursementRegistrationGeneralController', ReimbursementRegistrationGeneralController)
    
    ReimbursementRegistrationGeneralController.$inject = ['$scope', '$rootScope', 'ReimbursementRegistrationService', '$state', '$uibModal', '$timeout', 'ngNotify', '$stateParams', 'claim', 'isNew', 'AutocompleteService', '$q', 'ReimbursementRegistrationFactory', 'UIDefinationService'];

    function ReimbursementRegistrationGeneralController($scope, $rootScope, ReimbursementRegistrationService, $state, $uibModal, $timeout, ngNotify, $stateParams, claim, isNew, AutocompleteService, $q, ReimbursementRegistrationFactory, UIDefinationService) {
        $scope.regDetail = claim;
        $scope.previewIndex = 0;
        $scope.isNew = isNew;
        $scope.search = {};
        $scope.fileInfos = [];
        $scope.documents = [];
        $scope.docTypes = [];
        $scope.hasMandatory = true;
        $scope.isEdit = (claim.id != null);
        UIDefinationService.getEncounterTypes({'compId' : '0021'}, function(resp) {
            $scope.encounterTypes = resp.rowData;
        });
        
        UIDefinationService.getRequestTypes({'compId' : '0021'}, function(resp) {
            $scope.requestTypes = resp.rowData;
        });

        UIDefinationService.getReportByTypes({'compId' : '0021'}, function(resp) {
            $scope.reportByTypes = resp.rowData;
            $scope.reportByMap = ReimbursementRegistrationFactory.constructUidMap(resp.rowData, "id", "value");
        });

        UIDefinationService.getPaymentTypes({'compId' : '0021'}, function(resp) {
            $scope.paymentTypes = resp.rowData;
        });

        UIDefinationService.getDocumentTypes({'compId' : '0021'}, function(resp) {
            $scope.documentTypes = resp.rowData;
            $scope.documentMap = ReimbursementRegistrationFactory.constructUidMap(resp.rowData, "id", "value");
        })
        UIDefinationService.getSourceTypes({'compId' : '0021'}, function(resp) {
            $scope.sourceTypes = resp.rowData;
            $scope.sourceMap = ReimbursementRegistrationFactory.constructUidMap(resp.rowData, "value", "id");
        });

        if($scope.regDetail.id && $scope.regDetail.registrationFileDTOs.length) {
            var promises = [];
            angular.forEach($scope.regDetail.registrationFileDTOs, function(value, key) {
                var pathName =  value.docPath+'/'+ value.docName;
                value.ext = getFileExtension(value.docContentType);
                value.id = Math.random();
                value.savedDoc = true;
                promises.push(ReimbursementRegistrationService.getReimbursementRegistrationDocument({'pathName' : pathName}).$promise);                
            })
            $q.all(promises).then((files) => {
                angular.forEach(files, function(value, key){
                    var base64String = arrayBufferToBase64(value.data);
                    $scope.regDetail.registrationFileDTOs[key].base64String = "data:" +  $scope.regDetail.registrationFileDTOs[key].docContentType + ";base64," + base64String;
                })
                $scope.toggleInfo();
                $scope.fileInfos = $scope.documents = $scope.regDetail.registrationFileDTOs;
                $scope.uploaded = true;                
            });
        }

        function arrayBufferToBase64(buffer) {
            let binary = '';
            let bytes = new Uint8Array(buffer);
            let len = bytes.byteLength;
            for (let i = 0; i < len; i++) {
                binary += String.fromCharCode(bytes[i]);
            }
            return window.btoa(binary);
        }

        $scope.setReportedBy = function(reportedType) {
            $scope.regDetail['reportedBy'] = reportedType;
            $scope.regDetail['reportedByDesc'] = $scope.reportByMap[reportedType];
        }

        $scope.setPaymentWay = function(paymentType) {
            $scope.regDetail['paymentWay'] = paymentType;
            $scope.paymentWay = paymentType;
            $scope.regDetail['paymentRefNum'] = paymentType == '01' ? $scope.regDetail['paymentRefNum'] : null;
        }

        $scope.saveRegistrationDetails = function() {
            if($scope.form.$invalid){
                $scope.localValidation = true;
                return;
            }
            $scope.localValidation = false;
            $scope.regDetail['registrationFileDTOs'] = $scope.documents;
            var params = { policyNumber: $scope.regDetail.policyNumber, compId : "0021" };
            AutocompleteService.getCurrencyDetailsForPolicyNo(params, function(response) {
                var currencyInfo = response.rowData[0];
                $scope.regDetail.baseCurrency = currencyInfo.BaseCurrency;
                $scope.regDetail.requestAmtBC = $scope.regDetail.requestAmt * currencyInfo.ExchangeRate;
                saveRegistration();
            });
        }

        function saveRegistration() {
            ReimbursementRegistrationService.saveRegistrationDetails($scope.regDetail, function(resp) {
                $state.go('reimbursement-registration', {}, {reload: true});
            });
        }

        $scope.uploadFiles = function(files, doc) {
            $scope.files = files;
            $scope.fileInfos = ($scope.fileInfos && $scope.fileInfos.length) ? $scope.fileInfos :[];
            $scope.uploadedId = $scope.hasMandatory ? Math.random() : null;
            var files = [];
            angular.forEach($scope.files, function(value, key) {
                var reader  = new FileReader();
                $timeout(function() {
                    value.progress = 20;
                },150);
                reader.onload = function(event) {
                    var file = constructFileObj(value, event, doc);
                    files.push(file);
                    $scope.fileInfos.push(file);
                    $scope.$apply(function() {
                        $timeout(function() {
                            value.progress = 100;
                        },300)
                        if(files.length == $scope.files.length) documentsUploaded();
                    });
                };
                reader.readAsDataURL(value);
            });
        }

        function constructFileObj(file, event, doc) {
            var fileObj = {};
            fileObj.id = Math.random();
            var base64String = event.target.result;//base64 String..
            fileObj.docName = file.name;
            fileObj.docContentType = file.type;
            fileObj.ext = getFileExtension(file.type);
            fileObj.uploadedDate = new Date();
            fileObj.docTypeId = $scope.hasMandatory ? doc.id :  $scope.upload.type;
            fileObj.docTypeDesc = $scope.documentMap[fileObj.docTypeId];
            fileObj.description = $scope.upload ? $scope.upload.description : '';
            fileObj.base64String = base64String;
            fileObj.isNew = true;
            if($scope.hasMandatory) fileObj.uploadedId = $scope.uploadedId;
            return fileObj;
        }

        function getFileExtension(type) {
            var ext = 'excel';
            if(type.indexOf('image/') > -1)
                ext = 'image';
            if(type.indexOf('/pdf') > -1)
                ext = 'pdf';
            if(type.indexOf('.document') > -1)
                ext = 'docx';
            return ext;    
        }

        function documentsUploaded() {
            $scope.uploaded = true;
            $scope.documents = angular.copy($scope.fileInfos);
            if(!$scope.hasMandatory) {
                $scope.noOfSlides = 5;
                $scope.showUpload = false;
            } else {
                var uploadKey = $scope.fileInfos.length-1;
                $scope.openDocumentModal(uploadKey, $scope.fileInfos[uploadKey].uploadedId);
            }
        }

        $scope.showPreview = function(index, item) {
            $scope.noOfSlides = 3;
            $scope.previewIndex = index;
            $scope.isPreview = true;
            $scope.showUpload = true;
        }
        

        $scope.searchClaims = function (data) {
            if ((data.memberNumber == null) && (data.policyNumber == null) && (data.voucherNumber == null) && (data.previousRequestNumber == null)) {
                swal("", "Please Enter any Search Inputs", "warning");
            } else {
                var modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'resources/reimbursement-claims/registration/view/claim-search-modal.html',
                    size: 'lg',
                    backdrop: 'static',
                    keyboard :false,
                    controller: function ($scope, $uibModalInstance) {
                        var searchInfo = angular.copy(data);
                        var autoCompleteMapping = {
                            memberNumber : 'ULME_MEMBER_ID',
                            policyNumber : 'ILM_NO'
                        }
                        $scope.searchObj = ReimbursementRegistrationFactory.constructSearchObj(autoCompleteMapping, searchInfo);
                        $scope.searchObj.compId = "0021"                
                        ReimbursementRegistrationService.getReimbursementRegistrationDetails($scope.searchObj, function(resp) {
                            $scope.searchedList = resp;
                        })
                        $scope.cancelModal = function() {
                            $uibModalInstance.dismiss();
                            $('.modal-backdrop').remove();
                        }
    
                        $scope.newClaim = function() {
                            $scope.registerNew = true;
                            ReimbursementRegistrationService.getReimbursementRegistrationDetailsForPolicyAndMemberNo($scope.searchObj, function(resp) {
                                $scope.searchedList = resp;
                            });
                        }
    
                        $scope.continue = function(claim) {
                            if(claim.id != null) {
                                ReimbursementRegistrationService.getReimbursementRegistrationDetailsById({'id' : claim.id}, function(resp) {
                                    $uibModalInstance.close(resp);
                                })
                            } else {
                                $uibModalInstance.close(claim);
                            }                                                   
                        }
                    }
                });
    
                modalInstance.result.then(function(result) {
                    $scope.regDetail = ReimbursementRegistrationFactory.constructClaim(result, $scope.sourceMap['Reimbursement'])
                    $('.modal-backdrop').remove();
                    $scope.setReportedBy($scope.regDetail.reportedBy);
                    $scope.setPaymentWay($scope.regDetail.paymentWay);
                }, function() {});
            }                
        }

        $scope.deleteFile = function(index, id) {
            if($scope.regDetail.id && $scope.documents[index].savedDoc) {
                ReimbursementRegistrationService.deleteRegistrationFile({
                    "sgsid": $scope.regDetail.id,
                    "docType" : $scope.documents[index].docTypeId,
                    "docName" : $scope.documents[index].docName,
                    "path" : $scope.documents[index].docPath
                }, {}, function(resp) {
                    console.log(resp)  
                })
            }
            for(var f = 0; f<$scope.fileInfos.length; f++) {
                if($scope.fileInfos[f].id != id) continue;
                $scope.fileInfos.splice(f, 1);
                break;
            }
            $scope.documents.splice(index, 1);  
        }

        $scope.openDocumentModal = function(index, uploadedId) {
            $scope.docObj = angular.copy($scope.documents[index]);
            $scope.isUpload = uploadedId != null ? true : false;
            $scope.uploadModalInstance = $uibModal.open({
                animation: true,
                backdrop: 'static',
                templateUrl: 'resources/reimbursement-claims/registration/view/upload-modal.html',
                size: 'lg',
                keyboard :false,
                scope: $scope
            });

            $scope.uploadModalInstance.result.then(
                function() {
                    if(uploadedId != null) {
                        angular.forEach($scope.documents, function(value, key) {
                            if(value.uploadedId == uploadedId) updateDocument(value);
                        })
                    } else {
                        angular.forEach($scope.fileInfos, function(value, key) {
                            if(value.id == $scope.docObj.id) updateDocument(value);
                        })
                        $scope.documents[index] = $scope.docObj;
                    }
                    if($scope.docTypes && $scope.docTypes.length)$scope.filterDocuments();
                }, function() {}
            );
        }

        function updateDocument(value) {
            value.docTypeId = $scope.docObj.docTypeId;
            value.description = $scope.docObj.description;
            value.docTypeDesc = $scope.documentMap[value.docTypeId];
        }

        $scope.cancelModal = function() {
            $scope.uploadModalInstance.dismiss();
        }

        $scope.saveDocument = function() {
            $scope.uploadModalInstance.close();            
        }

        $scope.documentsUpload = function() {
            $scope.upload = {};
            $scope.showUpload = true;
            $scope.noOfSlides = $scope.showUpload ? 3 : 5;
            $scope.isPreview = false;
        }

        $scope.toggleInfo = function() {
            $scope.isPreview = false;
            $scope.showUpload = false;
            $scope.noOfSlides = 5;
        }

        $scope.filterDocuments = function() {
            var documents = [];
            if($scope.docTypes.length) {
                $scope.docTypes.forEach(function(type) {
                    var filteredFiles =  $scope.fileInfos.filter(function(item) {
                        return (item.docTypeId == type);
                    })
                    documents = documents.concat(filteredFiles);
                })
                $scope.documents = documents;
            } else {
                $scope.documents = angular.copy($scope.fileInfos);
            }
        }

        function init() {
            $scope.regDetail.paymentWay ? $scope.setPaymentWay($scope.regDetail.paymentWay) : '';
            $scope.regDetail.source ? $scope.setDcoumentType($scope.regDetail.source) : '';
            $scope.fieldsObject =  ReimbursementRegistrationFactory.getRegistrationGeneralSearchFields();
        }

        $scope.clearDocFilter = function() {
            $scope.docTypes = [];
            $scope.documents = angular.copy($scope.fileInfos);
        }

        init();
    }
})();