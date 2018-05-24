package com.beyon.medical.claims.ui.facade.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface MedicalClaimsUIServiceFacade{
	
	ObjectNode getMemberNumberList(ObjectNode paramMap) throws DAOException;
	ObjectNode getPolicyNumberList(ObjectNode paramMap) throws DAOException;
	ObjectNode getVoucherNumberList(ObjectNode paramMap) throws DAOException;
	ObjectNode getUIDefinitionList(String definition,ObjectNode paramMap) throws DAOException;
	ObjectNode getMemberCardNumberList(ObjectNode paramMap) throws DAOException;
	ObjectNode getMemberNameList(ObjectNode paramMap) throws DAOException;
	ObjectNode getClaimsPolicyNumberList(ObjectNode paramMap) throws DAOException;
	ObjectNode getEmiratesId(ObjectNode paramMap) throws DAOException;

	
}