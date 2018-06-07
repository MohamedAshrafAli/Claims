package com.beyon.medical.claims.reimbursement.controller;

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
import com.beyon.medical.claims.reimbursement.dto.ReimbursementAssignmentDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingDTO;
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
	
	@PostMapping("/getReimbursementAssignmentDetails")
	public  @ResponseBody List<ReimbursementAssignmentDTO> getReimbursementAssignmentDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		List<ReimbursementAssignmentDTO> assignmentDTOs = null;
		try {
			assignmentDTOs = reimbursementClaimsService.getReimbursementAssignmentDetails(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return assignmentDTOs;
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
	
	@PostMapping("/saveAssignmentDetails/{compId}")
	public  @ResponseBody List<ReimbursementAssignmentDTO> saveAssignmentDetails(@PathVariable String compId, @RequestBody List<ReimbursementAssignmentDTO> assignmentDTOs) throws MedicalClaimsException {
		List<ReimbursementAssignmentDTO> _assignmentDTOs = null;
		try {
			_assignmentDTOs = reimbursementClaimsService.saveAssignmentDetails(compId,assignmentDTOs);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _assignmentDTOs;
	}	
	
	
	@GetMapping("/getReimbursementRegistrationDocument")
	public  @ResponseBody byte[] getReimbursementRegistrationDocument(@RequestParam("pathName") String pathName) throws MedicalClaimsException {
		byte[] docByte = null;
		try {
			docByte = reimbursementClaimsService.getDocumentDetails(pathName);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return docByte;
	}
	
	@PostMapping("/deleteRegistrationFile")
	public  boolean deleteRegistrationFile(@RequestParam("sgsid") String id,
																	   @RequestParam("docType") String docType,
																	   @RequestParam("docName") String docName,
																	   @RequestParam("path") String path ) throws MedicalClaimsException {
		boolean response = false;
		try {
			response = reimbursementClaimsService.deleteRegistrationDocument(id, docType, docName, path);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return response;
	}
	
	@PostMapping("/saveProcessingDetails/{compId}")
	public  @ResponseBody ReimbursementProcessingDTO saveProcessingDetails(@PathVariable String compId, @RequestBody ReimbursementProcessingDTO reimbursementProcessingDTO) throws MedicalClaimsException {
		ReimbursementProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = reimbursementClaimsService.saveProcessingDetails(compId,reimbursementProcessingDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _reimbursementProcessingDTO;
	}
	
	@PostMapping("/updateProcessingDetails/{compId}")
	public  @ResponseBody ReimbursementProcessingDTO updateProcessingDetails(@PathVariable String compId, @RequestBody ReimbursementProcessingDTO reimbursementProcessingDTO) throws MedicalClaimsException {
		ReimbursementProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = reimbursementClaimsService.saveProcessingDetails(compId,reimbursementProcessingDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _reimbursementProcessingDTO;
	}
	
	@PostMapping("/getReimbursementProcessingDetails")
	public @ResponseBody ReimbursementProcessingDTO getReimbursementProcessingDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ReimbursementProcessingDTO reimbursementProcessingDTO = null;
		try {
			reimbursementProcessingDTO = reimbursementClaimsService.getReimbursementProcessingDetailsById( inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return reimbursementProcessingDTO;
	}
	
	@PostMapping("/getReimbursementInitProcessingDetails")
	public @ResponseBody ReimbursementProcessingDTO getReimbursementInitProcessingDetails(@RequestBody ReimbursementAssignmentDTO reimbursementAssignmentDTO) throws MedicalClaimsException {
		ReimbursementProcessingDTO reimbursementProcessingDTO = null;
		try {
			reimbursementProcessingDTO = reimbursementClaimsService.getReimbursementInitProcessingDetails(reimbursementAssignmentDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return reimbursementProcessingDTO;
	}
	
	
}