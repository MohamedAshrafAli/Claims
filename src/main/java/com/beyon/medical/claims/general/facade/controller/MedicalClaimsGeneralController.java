package com.beyon.medical.claims.general.facade.controller;

import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_UID_CLAIM_CONDITION;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.general.facade.service.GeneralServiceFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("api/medical/claims/ui")
@CrossOrigin(origins = "*")
public class MedicalClaimsGeneralController{
	
	@Autowired
	private GeneralServiceFacade uiServiceFacade;
	
	@PostMapping("/getMedicalCardNumberList")
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
	
	@PostMapping("/getClaimNumbers")
	public  @ResponseBody ObjectNode getClaimNumbers(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
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
	
	@PostMapping("/getCurrencyDetailsForPolicyNo")
	public @ResponseBody ObjectNode getCurrencyDetailsForPolicyNo(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode currencyDetails = null;
		try {
			currencyDetails = uiServiceFacade.getCurrencyDetails(inputMap,false);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return currencyDetails;
	}
	
	@PostMapping("/getUserList")
	public @ResponseBody ObjectNode getUserList(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode userList = null;
		try {
			userList = uiServiceFacade.getUserList(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return userList;
	}
	
	@GetMapping("/getUserDivisionForCompany")
	public String getUserDivisionForCompany(@RequestParam String userId,@RequestParam String compId) throws MedicalClaimsException {
		String userDivision = null;
		try {
			userDivision = uiServiceFacade.getUserDivisionForCompany(userId,compId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return userDivision;
	}
	
	@PostMapping("/getServiceCodeDetails")
	public @ResponseBody ObjectNode getServiceCodeDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode userList = null;
		try {
			userList = uiServiceFacade.getServiceCodeDetails(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return userList;
	}
	
	@PostMapping("/getDiagnosisCodeDetails")
	public @ResponseBody ObjectNode getDiagnosisCodeDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode userList = null;
		try {
			userList = uiServiceFacade.getDiagnosisCodeDetails(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return userList;
	}
	
	@PostMapping("/getRejectionCodeDetails")
	public @ResponseBody ObjectNode getRejectionCodeDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode userList = null;
		try {
			userList = uiServiceFacade.getRejectionCodeDetails(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return userList;
	}
	
	@PostMapping("/getUniversalCurrencyDetails")
	public @ResponseBody ObjectNode getUniversalCurrencyDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode currencyDetails = null;
		try {
			currencyDetails = uiServiceFacade.getCurrencyDetails(inputMap,true);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return currencyDetails;
	}
	
	@GetMapping("/getCountryIds/{compId}")
	public List<String> getCountryIds(@PathVariable String compId) throws MedicalClaimsException {
		List<String> countryIds = null;
		try {
			countryIds = uiServiceFacade.getCountryIds(compId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return countryIds;
	}
	
	@GetMapping("/getStatusCountByUser")
	public   @ResponseBody ObjectNode getStatusCountByUser(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ObjectNode statusCountByUser = null;
		try {
			statusCountByUser = uiServiceFacade.getStatusCountByUser(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return statusCountByUser;
	}
	
}