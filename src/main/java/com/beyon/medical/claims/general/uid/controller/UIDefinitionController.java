package com.beyon.medical.claims.general.uid.controller;

import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.*;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_UID_ENCOUNTER_TYPE;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_UID_PAYMENT_TYPE;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_UID_REPORT_BY_TYPE;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_UID_REQUEST_TYPE;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_UID_SOURCE_TYPE;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_UID_STATUS_TYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.general.facade.service.GeneralServiceFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/medical/claims/uid")
@CrossOrigin(origins = "*")
public class UIDefinitionController{
	
	@Autowired
	private GeneralServiceFacade uiServiceFacade;
	
	@PostMapping("/getEncounterTypes")
	public @ResponseBody ObjectNode getEncounterTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode encounterTypes = null;
		try {
			encounterTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_ENCOUNTER_TYPE, inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return encounterTypes;
	}
	
	@PostMapping("/getRequestTypes")
	public @ResponseBody ObjectNode getRequestTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode requestTypes = null;
		try {
			requestTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_REQUEST_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return requestTypes;
	}
	
	@PostMapping("/getReportByTypes")
	public @ResponseBody ObjectNode getReportByTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode reportByTypes = null;
		try {
			reportByTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_REPORT_BY_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return reportByTypes;		
	}
	
	@PostMapping("/getPaymentTypes")
	public @ResponseBody ObjectNode getPaymentTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode paymentTypes = null;
		try {
			paymentTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_PAYMENT_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return paymentTypes;
	}
	
	@PostMapping("/getDocumentTypes")
	public @ResponseBody ObjectNode getDocumentTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode documentTypes = null;
		try {
			documentTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_DOCUMENT_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return documentTypes;
	}
	
	@PostMapping("/getSourceTypes")
	public @ResponseBody ObjectNode getSourceTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode sourceTypes = null;
		try {
			sourceTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_SOURCE_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return sourceTypes;
	}
	
	@PostMapping("/getStatusTypes")
	public @ResponseBody ObjectNode getStatusTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode statusTypes = null;
		try {
			statusTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_STATUS_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return statusTypes;
	}
	
	@PostMapping("/getClaimsStatusReason")
	public @ResponseBody ObjectNode getClaimsStatusReason(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode statusTypes = null;
		try {
			statusTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_CLAIMS_STATUS_REASON_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return statusTypes;
	}
	
	@PostMapping("/getClaimLossType")
	public @ResponseBody ObjectNode getClaimLossType(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode claimLossTypes = null;
		try {
			claimLossTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_LOSS_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return claimLossTypes;
	}
	
	@PostMapping("/getClaimantESTType")
	public @ResponseBody ObjectNode getClaimantESTType(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode claimantESTTypes = null;
		try {
			claimantESTTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_EST_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return claimantESTTypes;
	}
	
	@PostMapping("/getDiagnosisType")
	public @ResponseBody ObjectNode getDiagnosisType(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode diagnosisTypes = null;
		try {
			diagnosisTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_DIAG_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return diagnosisTypes;
	}
	
	@PostMapping("/getClaimType")
	public @ResponseBody ObjectNode getClaimType(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode claimTypes = null;
		try {
			claimTypes = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_CLAIM_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return claimTypes;
	}
	
	@PostMapping("/getClaimCondition")
	public @ResponseBody ObjectNode getClaimCondition(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode claimConditions = null;
		try {
			claimConditions = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_CLAIM_CONDITION ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return claimConditions;
	}
	
	@PostMapping("/getClaimCurrencyType")
	public @ResponseBody ObjectNode getClaimCurrencyType(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode claimConditions = null;
		try {
			claimConditions = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_CLAIM_CURRENCY_TYPE ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return claimConditions;
	}
	
	@PostMapping("/getJobStatusTypes")
	public @ResponseBody ObjectNode getJobStatusTypes(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode claimConditions = null;
		try {
			claimConditions = uiServiceFacade.getUIDefinitionList(GENERAL_QUERIES_UID_JOB_STATUS ,inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return claimConditions;
	}
	
	
}