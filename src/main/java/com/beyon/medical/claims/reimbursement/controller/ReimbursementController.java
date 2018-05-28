package com.beyon.medical.claims.reimbursement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/medical/claims/reimbursement")
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
	
	@GetMapping("/getReimbursementRegistrationDetails/{id}")
	public ReimbursementRegistrationDTO getReimbursementRegistrationDetailsById(@PathVariable String id) throws MedicalClaimsException {
		ReimbursementRegistrationDTO registrationDTO = null;
		try {
			registrationDTO = reimbursementClaimsService.getReimbursementRegistrationDetailsById( id);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return registrationDTO;
	}
	
	@PostMapping("/getReimbursementRegistrationDetailsForPolicyAndMemberNo")
	public  @ResponseBody List<ReimbursementRegistrationDTO> getReimbursementRegistrationDetailsForPolicyAndMemberNo(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		List<ReimbursementRegistrationDTO> registrationDTOs = null;
		try {
			registrationDTOs = reimbursementClaimsService.getRegistrationDetailsForPolicyAndMemberNo(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return registrationDTOs;
	}
	
	@PostMapping("/saveRegistrationDetails/{compId}")
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public  @ResponseBody ReimbursementRegistrationDTO saveRegistrationDetails(@PathVariable String compId, @RequestBody ReimbursementRegistrationDTO registrationDTO) throws MedicalClaimsException {
		ReimbursementRegistrationDTO _registrationDTO = null;
		try {
			_registrationDTO = reimbursementClaimsService.saveRegistrationDetails(compId,registrationDTO);
			if(_registrationDTO.getRegistrationFileDTOs() != null && !_registrationDTO.getRegistrationFileDTOs().isEmpty()) {
				reimbursementClaimsService.uploadAndSaveDocuments(compId, _registrationDTO);
			}
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _registrationDTO;
	}
	
}