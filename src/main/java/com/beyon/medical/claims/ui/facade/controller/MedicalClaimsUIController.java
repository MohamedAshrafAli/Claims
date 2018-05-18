package com.beyon.medical.claims.ui.facade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.ui.facade.service.MedicalClaimsUIServiceFacade;

@RestController
@RequestMapping("/medical/claims/ui")
@CrossOrigin(origins = "*")
public class MedicalClaimsUIController{
	
	@Autowired
	private MedicalClaimsUIServiceFacade uiServiceFacade;
	
	@GetMapping("/getMedicalCardNumbers")
	public List<String> getMedicalCardNumbers(@RequestParam("compId") String compId,@RequestParam("cardNumber") String cardNumber) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getMemberCardNumberList(compId,cardNumber);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	@GetMapping("/getMemberNumberList")
	public List<String> getMemberNumberList(@RequestParam("compId") String compId,@RequestParam("memberNumber") String memberNumber) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getMemberNumberList(compId,memberNumber);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	
	@GetMapping("/getPolicyNumberList")
	public List<String> getPolicyNumberList(@RequestParam("compId") String compId,@RequestParam("policyNumber") String policyNumber) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getPolicyNumberList(compId,policyNumber);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	@GetMapping("/getEncounterTypes")
	public List<String> getEncounterTypes(@RequestParam("compId") String compId) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getEncounterTypes(compId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	@GetMapping("/getRequestTypes")
	public List<String> getRequestTypes(@RequestParam("compId") String compId) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getRequestTypes(compId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	@GetMapping("/getReportByTypes")
	public List<String> getReportByTypes(@RequestParam("compId") String compId) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getReportByTypes(compId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	@GetMapping("/getPaymentTypes")
	public List<String> getPaymentTypes(@RequestParam("compId") String compId) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getPaymentTypes(compId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
	@GetMapping("/getDocumentTypes")
	public List<String> getDocumentTypes(@RequestParam("compId") String compId) throws MedicalClaimsException {
		List<String> cardNumbers = null;
		try {
			cardNumbers = uiServiceFacade.getDocumentTypes(compId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return cardNumbers;
	}
	
}