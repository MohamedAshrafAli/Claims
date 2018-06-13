package com.beyon.medical.claims.general.facade.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface GeneralServiceFacade{
	
	ObjectNode getMemberNumberList(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getPolicyNumberList(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getVoucherNumberList(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getUIDefinitionList(String definition,ObjectNode paramMap) throws DAOException;
	
	ObjectNode getMemberCardNumberList(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getMemberNameList(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getClaimsPolicyNumberList(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getEmiratesId(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getCurrencyDetails(ObjectNode paramMap,boolean isUniversal) throws DAOException;
	
	String getUserDivisionForCompany(String userId,String compId) throws DAOException;

	String getClassOfBusinessForPolicy(String compId,String policyNumber) throws DAOException;
	
	ObjectNode getClaimNumberList(ObjectNode paramMap) throws DAOException;

	ObjectNode getUserList(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getServiceCodeDetails(ObjectNode paramMap) throws DAOException;

	ObjectNode getDiagnosisCodeDetails(ObjectNode paramMap) throws DAOException;
	
	ObjectNode getRejectionCodeDetails(ObjectNode paramMap) throws DAOException;
	
	List<String> getCountryIds(String compId) throws DAOException;
	
	ObjectNode getStatusCountByUser(ObjectNode paramMap) throws DAOException;

}