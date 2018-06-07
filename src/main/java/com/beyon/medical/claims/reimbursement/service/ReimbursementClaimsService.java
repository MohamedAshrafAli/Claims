package com.beyon.medical.claims.reimbursement.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementAssignmentDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ReimbursementClaimsService {

	List<ReimbursementRegistrationDTO> getReimbursementRegistrationDetails(ObjectNode inputMap) throws DAOException;
	
	ReimbursementRegistrationDTO getReimbursementRegistrationDetailsById(String id) throws DAOException;
	
	List<ReimbursementRegistrationDTO> getRegistrationDetailsForPolicyAndMemberNo(ObjectNode inputMap) throws DAOException;

	ReimbursementRegistrationDTO saveRegistrationDetails(String compId,ReimbursementRegistrationDTO registrationDTO) throws DAOException;
	
	void uploadAndSaveDocuments(String compId,ReimbursementRegistrationDTO registrationDTO) throws DAOException;

	byte[] getDocumentDetails(String path) throws MedicalClaimsException;
	
	boolean deleteRegistrationDocument(String id, String docType, String docName, String path ) throws DAOException;

	List<ReimbursementAssignmentDTO> getReimbursementAssignmentDetails(ObjectNode inputMap) throws DAOException;
	
	public ObjectNode getMemberDetailsForPolicyAndMemberNo(ObjectNode paramMap) throws DAOException ;

	List<ReimbursementAssignmentDTO> saveAssignmentDetails(String compId, List<ReimbursementAssignmentDTO> assignmentDTOs)
			throws DAOException;
	
	ReimbursementProcessingDTO saveProcessingDetails(String compId, ReimbursementProcessingDTO processingDTO)
			throws DAOException;
	
	ReimbursementProcessingDTO updateProcessingDetails(String compId,
			ReimbursementProcessingDTO reimbursementProcessingDTO) throws DAOException;
	
	ReimbursementProcessingDTO getReimbursementProcessingDetailsById(ObjectNode inputMap) throws DAOException;

	ReimbursementProcessingDTO getReimbursementInitProcessingDetails(ReimbursementAssignmentDTO reimbursementAssignmentDTO) throws DAOException;

	
}