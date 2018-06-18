package com.beyon.medical.claims.assignment.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.AssignmentDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ClaimAssignmentService {

	List<AssignmentDTO> getReimbursementAssignmentDetails(ObjectNode inputMap) throws DAOException;
	
	List<AssignmentDTO> saveAssignmentDetails(String compId, List<AssignmentDTO> assignmentDTOs) throws DAOException;
	
	ObjectNode getMemberDetailsForPolicyAndMemberNo(ObjectNode paramMap) throws DAOException ;

}