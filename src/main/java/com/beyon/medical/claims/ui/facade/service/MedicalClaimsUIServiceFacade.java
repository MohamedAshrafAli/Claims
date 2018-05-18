package com.beyon.medical.claims.ui.facade.service;

import java.util.List;

public interface MedicalClaimsUIServiceFacade{
	
	List<String> getMemberCardNumberList(String compId,String cardNumber);
	List<String> getMemberNumberList(String compId,String memberNumber);
	List<String> getPolicyNumberList(String compId,String policy);
	List<String> getEncounterTypes(String compId);
	List<String> getRequestTypes(String compId);
	List<String> getReportByTypes(String compId);
	List<String> getPaymentTypes(String compId);
	List<String> getDocumentTypes(String compId);


}