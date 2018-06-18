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

import com.beyon.medical.claims.constants.MedicalClaimTypes;
import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.factory.ServiceLocatorFactory;
import com.beyon.medical.claims.general.dto.AssignmentDTO;
import com.beyon.medical.claims.general.dto.ProcessingDTO;
import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;
import com.beyon.medical.claims.general.dto.RegistrationDTO;
import com.beyon.medical.claims.registration.reimbursement.service.ReimbursementClaimRegistrationService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/medical/claims/reimbursement")
@CrossOrigin(origins = "*")
public class ReimbursementController{
	
	@Autowired
	private ReimbursementClaimRegistrationService reimbursementClaimRegistrationService;
	
	@PostMapping("/getReimbursementRegistrationDetails")
	public  @ResponseBody List<RegistrationDTO> getReimbursementRegistrationDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		List<RegistrationDTO> registrationDTOs = null;
		try {
			registrationDTOs = ServiceLocatorFactory.getClaimRegistrationService(MedicalClaimTypes.REIMBURSEMENT).getRegistrationDetails(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return registrationDTOs;
	}
	
	@GetMapping("/getReimbursementRegistrationDetails/{id}")
	public RegistrationDTO getReimbursementRegistrationDetailsById(@PathVariable String id) throws MedicalClaimsException {
		RegistrationDTO registrationDTO = null;
		try {
			registrationDTO = ServiceLocatorFactory.getClaimRegistrationService(MedicalClaimTypes.REIMBURSEMENT).getRegistrationDetailsById( id);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return registrationDTO;
	}
	
	@PostMapping("/getReimbursementRegistrationDetailsForPolicyAndMemberNo")
	public  @ResponseBody List<RegistrationDTO> getReimbursementRegistrationDetailsForPolicyAndMemberNo(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		List<RegistrationDTO> registrationDTOs = null;
		try {
			registrationDTOs = ServiceLocatorFactory.getClaimRegistrationService(MedicalClaimTypes.REIMBURSEMENT).getRegistrationDetailsForPolicyAndMemberNo(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return registrationDTOs;
	}
	
	@PostMapping("/saveRegistrationDetails/{compId}")
	public  @ResponseBody RegistrationDTO saveRegistrationDetails(@PathVariable String compId, @RequestBody RegistrationDTO registrationDTO) throws MedicalClaimsException {
		RegistrationDTO _registrationDTO = null;
		try {
			_registrationDTO = ServiceLocatorFactory.getClaimRegistrationService(MedicalClaimTypes.REIMBURSEMENT).saveRegistrationDetails(compId,registrationDTO);
			if(_registrationDTO.getRegistrationFileDTOs() != null && !_registrationDTO.getRegistrationFileDTOs().isEmpty()) {
				reimbursementClaimRegistrationService.uploadAndSaveDocuments(compId, _registrationDTO);
			}
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _registrationDTO;
	}	
	

	
	@GetMapping("/getReimbursementRegistrationDocument")
	public  @ResponseBody byte[] getReimbursementRegistrationDocument(@RequestParam("pathName") String pathName) throws MedicalClaimsException {
		byte[] docByte = null;
		try {
			docByte = reimbursementClaimRegistrationService.getDocumentDetails(pathName);
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
			response = reimbursementClaimRegistrationService.deleteRegistrationDocument(id, docType, docName, path);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return response;
	}
	
	@PostMapping("/getReimbursementAssignmentDetails")
	public  @ResponseBody List<AssignmentDTO> getReimbursementAssignmentDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		List<AssignmentDTO> assignmentDTOs = null;
		try {
			assignmentDTOs = ServiceLocatorFactory.getClaimAssignmentService(MedicalClaimTypes.REIMBURSEMENT).getReimbursementAssignmentDetails(inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return assignmentDTOs;
	}
	
	@PostMapping("/saveAssignmentDetails/{compId}")
	public  @ResponseBody List<AssignmentDTO> saveAssignmentDetails(@PathVariable String compId, @RequestBody List<AssignmentDTO> assignmentDTOs) throws MedicalClaimsException {
		List<AssignmentDTO> _assignmentDTOs = null;
		try {
			_assignmentDTOs = ServiceLocatorFactory.getClaimAssignmentService(MedicalClaimTypes.REIMBURSEMENT).saveAssignmentDetails(compId,assignmentDTOs);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _assignmentDTOs;
	}	
	
	@PostMapping("/saveProcessingDetails/{compId}")
	public  @ResponseBody ProcessingDTO saveProcessingDetails(@PathVariable String compId, @RequestBody ProcessingDTO reimbursementProcessingDTO) throws MedicalClaimsException {
		ProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = ServiceLocatorFactory.getClaimProcessingService(MedicalClaimTypes.REIMBURSEMENT).saveProcessingDetails(compId,reimbursementProcessingDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _reimbursementProcessingDTO;
	}
	
	@PostMapping("/updateProcessingDetails/{compId}")
	public  @ResponseBody ProcessingDTO updateProcessingDetails(@PathVariable String compId, @RequestBody ProcessingDTO reimbursementProcessingDTO) throws MedicalClaimsException {
		ProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = ServiceLocatorFactory.getClaimProcessingService(MedicalClaimTypes.REIMBURSEMENT).saveProcessingDetails(compId,reimbursementProcessingDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _reimbursementProcessingDTO;
	}
	
	@PostMapping("/getReimbursementProcessingDetails")
	public @ResponseBody ProcessingDTO getReimbursementProcessingDetails(@RequestBody ObjectNode inputMap) throws MedicalClaimsException {
		ProcessingDTO reimbursementProcessingDTO = null;
		try {
			reimbursementProcessingDTO = ServiceLocatorFactory.getClaimProcessingService(MedicalClaimTypes.REIMBURSEMENT).getProcessingDetails( inputMap);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return reimbursementProcessingDTO;
	}
	
	@PostMapping("/getReimbursementProcessingDetailsForAssignment")
	public @ResponseBody ProcessingDTO getReimbursementProcessingDetailsForAssignment(@RequestBody AssignmentDTO reimbursementAssignmentDTO) throws MedicalClaimsException {
		ProcessingDTO reimbursementProcessingDTO = null;
		try {
			reimbursementProcessingDTO = ServiceLocatorFactory.getClaimProcessingService(MedicalClaimTypes.REIMBURSEMENT).getProcessingDetailsForAssignment( reimbursementAssignmentDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return reimbursementProcessingDTO;
	}
	
	@GetMapping("/getReimbursementProcessingServiceDetails")
	public List<ProcessingServiceDTO> getReimbursementProcessingServiceDetails(@RequestParam("registrationId") Long registrationId) throws MedicalClaimsException {
		List<ProcessingServiceDTO> reimbursementProcessingServiceDTOs = null;
		try {
			reimbursementProcessingServiceDTOs = ServiceLocatorFactory.getClaimProcessingService(MedicalClaimTypes.REIMBURSEMENT).getProcessingServiceDetails(registrationId);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return reimbursementProcessingServiceDTOs;
	}

	
	@PostMapping("/getReimbursementInitProcessingDetails")
	public @ResponseBody ProcessingDTO getReimbursementInitProcessingDetails(@RequestBody AssignmentDTO reimbursementAssignmentDTO) throws MedicalClaimsException {
		ProcessingDTO reimbursementProcessingDTO = null;
		try {
			reimbursementProcessingDTO = ServiceLocatorFactory.getClaimProcessingService(MedicalClaimTypes.REIMBURSEMENT).getInitProcessingDetails(reimbursementAssignmentDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return reimbursementProcessingDTO;
	}
	
	@PostMapping("/approveProcessingServiceLineItem/{compId}")
	public  @ResponseBody ProcessingDTO approveProcessingServiceLineItem(@PathVariable String compId, @RequestBody ProcessingDTO reimbursementProcessingDTO) throws MedicalClaimsException {
		ProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = ServiceLocatorFactory.getClaimProcessingService(MedicalClaimTypes.REIMBURSEMENT).approveServiceLineItem(compId,reimbursementProcessingDTO);
		} catch (Exception ex) {
			throw new MedicalClaimsException(ex.getMessage());
		} 
		return _reimbursementProcessingDTO;
	}
	
}