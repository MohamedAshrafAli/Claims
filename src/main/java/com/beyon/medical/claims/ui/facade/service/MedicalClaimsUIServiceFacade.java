package com.beyon.medical.claims.ui.facade.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;

public interface MedicalClaimsUIServiceFacade{
	
	List<String> getMemberCardNumberList(String compId,String cardNumber) throws DAOException;
	List<String> getMemberNumberList(String compId,String memberNumber) throws DAOException;
	List<String> getPolicyNumberList(String compId,String policy) throws DAOException;
	List<String> getEncounterTypes(String compId) throws DAOException;
	List<String> getRequestTypes(String compId) throws DAOException;
	List<String> getReportByTypes(String compId) throws DAOException;
	List<String> getPaymentTypes(String compId) throws DAOException;
	List<String> getDocumentTypes(String compId) throws DAOException;


}