package com.beyon.medical.claims.processing.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.AssignmentDTO;
import com.beyon.medical.claims.general.dto.ProcessingDTO;
import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ClaimProcessingService {

	ProcessingDTO saveProcessingDetails(String compId, ProcessingDTO processingDTO)
			throws DAOException;
	
	ProcessingDTO updateProcessingDetails(String compId,
			ProcessingDTO reimbursementProcessingDTO) throws DAOException;
	
	ProcessingDTO getProcessingDetails(ObjectNode inputMap) throws DAOException;

	ProcessingDTO getInitProcessingDetails(AssignmentDTO reimbursementAssignmentDTO) throws DAOException;

	ProcessingDTO getProcessingDetailsForAssignment(AssignmentDTO assignmentDTO) throws DAOException;
	
	List<ProcessingServiceDTO> getProcessingServiceDetails(Long registrationId) throws DAOException;

	ProcessingDTO approveServiceLineItem(String compId,
			ProcessingDTO processingDTO) throws DAOException;

}