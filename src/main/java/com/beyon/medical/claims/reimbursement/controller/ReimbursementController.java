package com.beyon.medical.claims.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.reimbursement.service.ReimbursementClaimsService;
import com.beyon.medical.claims.ui.facade.service.MedicalClaimsUIServiceFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/medical/claims/reimbursement")
@CrossOrigin(origins = "*")
public class ReimbursementController{
	
	@Autowired
	private ReimbursementClaimsService reimbursementClaimsService;
	
	@PostMapping("/getReimbursementRegistrationDetails")
	public  @ResponseBody List<ReimbursementRegistrationDTO> getReimbursementRegistrationDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		List<ReimbursementRegistrationDTO> registrationDTOs = null;
		try {
			registrationDTOs = reimbursementClaimsService.getReimbursementRegistrationDetails(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return registrationDTOs;
	}
	
	@GetMapping("/getReimbursementRegistrationDetails/{compId}/{id}")
	public ReimbursementRegistrationDTO getReimbursementRegistrationDetailsById(@PathVariable String compId,@PathVariable String id) throws MedicalClaimsException {
		ReimbursementRegistrationDTO registrationDTO = null;
		try {
			registrationDTO = reimbursementClaimsService.getReimbursementRegistrationDetailsById(compId, id);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return registrationDTO;
	}
}