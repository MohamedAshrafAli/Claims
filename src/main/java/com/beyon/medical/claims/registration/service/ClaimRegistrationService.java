package com.beyon.medical.claims.registration.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.RegistrationDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
public interface ClaimRegistrationService {

	List<RegistrationDTO> getRegistrationDetails(ObjectNode inputMap) throws DAOException;
	
	RegistrationDTO getRegistrationDetailsById(String id) throws DAOException;
	
	List<RegistrationDTO> getRegistrationDetailsForPolicyAndMemberNo(ObjectNode inputMap) throws DAOException;

	RegistrationDTO saveRegistrationDetails(String compId,RegistrationDTO registrationDTO) throws DAOException;

}