package com.beyon.medical.claims.factory;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.assignment.service.ClaimAssignmentService;
import com.beyon.medical.claims.constants.MedicalClaimTypes;
import com.beyon.medical.claims.finalization.service.ClaimFinalizationService;
import com.beyon.medical.claims.processing.service.ClaimProcessingService;
import com.beyon.medical.claims.registration.service.ClaimRegistrationService;

public class ServiceLocatorFactory {
	
	private ServiceLocatorFactory() {
		
	}

    public static ClaimRegistrationService getClaimRegistrationService(MedicalClaimTypes claimTypes) {
    	switch (claimTypes) {
		case ECLAIMS:
//			return (ClaimRegistrationService) FoundationUtils.getObject("TPAMapManagerImpl");
		case PBM:
//			return (TPAMapManager) FoundationUtils.getObject("TPAMapManagerImpl");
		case PREAUTHORIZATION:
//			return (TPAMapManager) FoundationUtils.getObject("TPAMapManagerImpl");
		case PROVIDERPAPER:
//			return (TPAMapManager) FoundationUtils.getObject("TPAMapManagerImpl");
		case REIMBURSEMENT:
			return (ClaimRegistrationService) FoundationUtils.getObject("reimbursementClaimRegistrationServiceImpl");
		default :
			return null;
		}
        
    }
    
    
    public static ClaimFinalizationService getClaimFinalizationService(MedicalClaimTypes claimTypes) {
    	switch (claimTypes) {
		case ECLAIMS:
//			return (ClaimRegistrationService) FoundationUtils.getObject("ReimbursementClaimRegistrationServiceImpl");
		case PBM:
//			return (ClaimRegistrationService) FoundationUtils.getObject("ReimbursementClaimRegistrationServiceImpl");
		case PREAUTHORIZATION:
//			return (ClaimRegistrationService) FoundationUtils.getObject("ReimbursementClaimRegistrationServiceImpl");
		case PROVIDERPAPER:
//			return (ClaimRegistrationService) FoundationUtils.getObject("ReimbursementClaimRegistrationServiceImpl");
		case REIMBURSEMENT:
			return (ClaimFinalizationService) FoundationUtils.getObject("reimbursementClaimFinalizationServiceImpl");
		default :
			return null;
		}
        
    }
    
    public static ClaimAssignmentService getClaimAssignmentService(MedicalClaimTypes claimTypes) {
    	switch (claimTypes) {
		case ECLAIMS:
//			return (ClaimAssignmentService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case PBM:
//			return (ClaimAssignmentService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case PREAUTHORIZATION:
//			return (ClaimAssignmentService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case PROVIDERPAPER:
//			return (ClaimAssignmentService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case REIMBURSEMENT:
			return (ClaimAssignmentService) FoundationUtils.getObject("reimbursementClaimAssignmentServiceImpl");
		default :
			return null;
		}
        
    }
    
    public static ClaimProcessingService getClaimProcessingService(MedicalClaimTypes claimTypes) {
    	switch (claimTypes) {
		case ECLAIMS:
//			return (ClaimProcessingService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case PBM:
//			return (ClaimProcessingService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case PREAUTHORIZATION:
//			return (ClaimProcessingService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case PROVIDERPAPER:
//			return (ClaimProcessingService) FoundationUtils.getObject("ReimbursementClaimAssignmentServiceImpl");
		case REIMBURSEMENT:
			return (ClaimProcessingService) FoundationUtils.getObject("reimbursementClaimProcessingServiceImpl");
		default :
			return null;
		}
        
    }

}
