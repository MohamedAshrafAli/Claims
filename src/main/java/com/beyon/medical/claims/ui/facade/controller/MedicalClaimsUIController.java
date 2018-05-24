package com.beyon.medical.claims.ui.facade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.*;

import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.ui.facade.service.MedicalClaimsUIServiceFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/medical/claims/ui")
@CrossOrigin(origins = "*")
public class MedicalClaimsUIController{
	
	@Autowired
	private MedicalClaimsUIServiceFacade uiServiceFacade;
	
	@GetMapping("/getMedicalCardNumberList")
	public  @ResponseBody ObjectNode getMedicalCardNumberList(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getMemberCardNumberList(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	@PostMapping("/getMemberNumberList")
	public  @ResponseBody ObjectNode getMemberNumberList(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode memberNumbers = null;
		try {
			memberNumbers = uiServiceFacade.getMemberNumberList(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return memberNumbers;
	}
	
	
	@PostMapping("/getPolicyNumberList")
	public  @ResponseBody ObjectNode getPolicyNumberList(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode policyNumbers = null;
		try {
			policyNumbers = uiServiceFacade.getPolicyNumberList(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return policyNumbers;
	}
	
	@PostMapping("/getVoucherNumbers")
	public  @ResponseBody ObjectNode getVoucherNumbers(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode voucherNumbers = null;
		try {
			voucherNumbers = uiServiceFacade.getVoucherNumberList(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return voucherNumbers;
	}
	
	@PostMapping("/getMemberNameList")
	public  @ResponseBody ObjectNode getMemberNameList(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode memberNames = null;
		try {
			memberNames = uiServiceFacade.getMemberNameList(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return memberNames;
	}
	
	
	@PostMapping("/getClaimsPolicyNumberList")
	public  @ResponseBody ObjectNode getClaimsPolicyNumberList(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode policyNumbers = null;
		try {
			policyNumbers = uiServiceFacade.getClaimsPolicyNumberList(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return policyNumbers;
	}
	
	@PostMapping("/getEmiratesId")
	public  @ResponseBody ObjectNode getEmiratesId(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode emiratesIds = null;
		try {
			emiratesIds = uiServiceFacade.getEmiratesId(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return emiratesIds;
	}
	
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
}