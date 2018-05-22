package com.beyon.medical.claims.ui.facade.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface MedicalClaimsUIServiceFacade{
	
	List<String> getMemberCardNumberList(String compId,String cardNumber) throws DAOException;
	ObjectNode getMemberNumberList(ObjectNode paramMap) throws DAOException;
	ObjectNode getPolicyNumberList(ObjectNode paramMap) throws DAOException;
	ObjectNode getUIDefinitionList(String definition,ObjectNode paramMap) throws DAOException;


}