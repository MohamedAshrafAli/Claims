package com.beyon.medical.claims.registration.reimbursement.service;

import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.general.dto.RegistrationDTO;
import com.beyon.medical.claims.registration.service.ClaimRegistrationService;
public interface ReimbursementClaimRegistrationService extends ClaimRegistrationService {

	void uploadAndSaveDocuments(String compId,RegistrationDTO registrationDTO) throws DAOException;

	byte[] getDocumentDetails(String path) throws MedicalClaimsException;
	
	boolean deleteRegistrationDocument(String id, String docType, String docName, String path ) throws DAOException;
	
}