package com.beyon.medical.claims.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.*;
import static com.beyon.medical.claims.constants.ClaimConstants.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.general.dto.DiagnosisDTO;
import com.beyon.medical.claims.reimbursement.dao.ReimbursementClaimsDAOImpl;
import com.beyon.medical.claims.reimbursement.dto.RegistrationFileDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementAssignmentDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingServiceDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.reimbursement.mapper.ReimbAssignmentMapper;
import com.beyon.medical.claims.ui.facade.service.GeneralServiceFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ReimbursementClaimsServiceImpl implements ReimbursementClaimsService {

	@Autowired
	private ReimbursementClaimsDAOImpl reimbursementClaimsDAO;

	@Autowired
	private GeneralServiceFacade generalServiceFacade;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<ReimbursementRegistrationDTO> getReimbursementRegistrationDetails(ObjectNode paramMap)
			throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails = reimbursementClaimsDAO.getRegistrationListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<ReimbursementAssignmentDTO> getReimbursementAssignmentDetails(ObjectNode paramMap) throws DAOException {
		List<ReimbursementAssignmentDTO> reimbursementAssignmentDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_DETAILS;
			strQuery = getConstructedQuerywithCriterionForAssignment(strQuery, inputMap);
			reimbursementAssignmentDetails = reimbursementClaimsDAO.getAssignmentListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementAssignmentDetails;
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public ReimbursementRegistrationDTO getReimbursementRegistrationDetailsById(String id) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		ReimbursementRegistrationDTO registrationDTO = null;
		try {
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID;
			Map<String, Object> inputMap = new HashMap<>();
			inputMap.put("id", id);
			reimbursementRegDetails = reimbursementClaimsDAO.getRegistrationDetailsById(strQuery, inputMap);
			if (reimbursementRegDetails != null && !reimbursementRegDetails.isEmpty()) {
				registrationDTO = reimbursementRegDetails.get(0);
				List<RegistrationFileDTO> fileDTOs = reimbursementClaimsDAO
						.getRegistrationFileDetailsById(REIMBURSEMENT_QUERIES_DETAILS_TDS_LEVEL_D, inputMap);
				registrationDTO.setRegistrationFileDTOs(fileDTOs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return registrationDTO;
	}

	private String getConstructedQuerywithCriterion(String strQuery, Map<String, Object> inputMap) {
		if (strQuery.contains("<CRITERIA>")) {
			Iterator<String> mapIter = inputMap.keySet().iterator();
			StringBuilder builder = new StringBuilder();
			while (mapIter.hasNext()) {
				String key = (String) mapIter.next();
				if (key.equalsIgnoreCase("policyNumber")) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("memberNumber")) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("voucherNumber")) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA + " ");
				}
			}
			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
		}
		return strQuery;
	}

	private String getConstructedQuerywithCriterionForAssignment(String strQuery, Map<String, Object> inputMap) {
		if (strQuery.contains("<CRITERIA>")) {
			Iterator<String> mapIter = inputMap.keySet().iterator();
			StringBuilder builder = new StringBuilder("");
			while (mapIter.hasNext()) {
				String key = (String) mapIter.next();
				if (key.equalsIgnoreCase("claimNumber") && StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_DETAILS + " ");
				} else if (key.equalsIgnoreCase("memberNumber") && StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA + " ");
				}
			}
			if (StringUtils.isNotBlank((String) inputMap.get("reqReceivedFrom"))
					&& StringUtils.isNotBlank((String) inputMap.get("reqReceivedTo"))) {
				builder.append(REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_BETWEEN_DETAILS + " ");
			} else if (StringUtils.isNotBlank((String) inputMap.get("reqReceivedFrom"))) {
				builder.append(REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_FROM_DETAILS + " ");
			} else if (StringUtils.isNotBlank((String) inputMap.get("reqReceivedTo"))) {
				builder.append(REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_TO_DETAILS + " ");
			}

			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
		}
		return strQuery;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<ReimbursementRegistrationDTO> getRegistrationDetailsForPolicyAndMemberNo(ObjectNode paramMap)
			throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails = reimbursementClaimsDAO.getRegistrationListData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public ReimbursementRegistrationDTO saveRegistrationDetails(String compId,
			ReimbursementRegistrationDTO registrationDTO) throws DAOException {
		ReimbursementRegistrationDTO _registrationDTO = null;
		try {
			if (registrationDTO.getId() == null || registrationDTO.getId() == 0L) {
				_registrationDTO = reimbursementClaimsDAO.insertReimbursementRegistrationDetails(compId,
						registrationDTO);
			} else {
				_registrationDTO = reimbursementClaimsDAO.updateReimbursementRegistrationDetails(compId,
						registrationDTO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _registrationDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public List<ReimbursementAssignmentDTO> saveAssignmentDetails(String compId,
			List<ReimbursementAssignmentDTO> assignmentDTOs) throws DAOException {
		List<ReimbursementAssignmentDTO> _assignmentDTOs = null;
		try {
			for (ReimbursementAssignmentDTO reimbursementAssignmentDTO : assignmentDTOs) {
				Map<String, Object> refNoMap = reimbursementClaimsDAO.getClaimsRefNo("C",
						reimbursementAssignmentDTO.getId(), reimbursementAssignmentDTO.getProductId());
				String claimRefNo = (String) refNoMap.get("P_DOC_NO");
				reimbursementAssignmentDTO.setClaimNumber(claimRefNo);
				ObjectNode objectNode = FoundationUtils.createObjectNode();
				objectNode.put("policyNumber", reimbursementAssignmentDTO.getPolicyNumber());
				objectNode.put("compId", compId);
				objectNode.put("memberNumber", reimbursementAssignmentDTO.getMemberNumber());
				ObjectNode outputMap = getMemberDetailsForPolicyAndMemberNo(objectNode);
				ReimbAssignmentMapper.getReimbursmentDTOWithMemberDetails(outputMap, reimbursementAssignmentDTO);

			}
			_assignmentDTOs = reimbursementClaimsDAO.insertReimbursementAssignmentDetails(compId, assignmentDTOs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _assignmentDTOs;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public boolean deleteRegistrationDocument(String id, String docType, String docName, String path)
			throws DAOException {
		boolean isDeleted = false;
		try {
			isDeleted = reimbursementClaimsDAO.deleteRegistrationDocument(id, docType, docName, path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return isDeleted;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public void uploadAndSaveDocuments(String compId, ReimbursementRegistrationDTO registrationDTO)
			throws DAOException {
		try {
			for (RegistrationFileDTO registrationFileDTO : registrationDTO.getRegistrationFileDTOs()) {
				transferFilesToFileServer(registrationDTO, registrationFileDTO);
			}
			reimbursementClaimsDAO.insertTDSLEVELD(compId, registrationDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public byte[] getDocumentDetails(String path) throws MedicalClaimsException {
		byte[] docBytes = null;
		try {
			docBytes = Files.readAllBytes(
					new File(CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER + File.separator + path).toPath());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MedicalClaimsException(e);
		}
		return docBytes;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public ObjectNode getMemberDetailsForPolicyAndMemberNo(ObjectNode paramMap) throws DAOException {
		ObjectNode outputMap = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_MEMBER_DETAILS;
			outputMap = reimbursementClaimsDAO.getAssignmentnListData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return outputMap;
	}

	private void transferFilesToFileServer(ReimbursementRegistrationDTO registrationDTO,
			RegistrationFileDTO registrationFileDTO) throws DAOException {
		File uploadDir = null;
		try {
			String base64 = registrationFileDTO.getBase64String().split(",")[1];
			byte[] filearray = Base64.getDecoder().decode(base64.getBytes("UTF-8"));
			String filename = registrationFileDTO.getDocName();
			String fileServer = ClaimConstants.CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER + File.separator;

			String path = registrationDTO.getClaimRefNo() + File.separator + registrationFileDTO.getDocTypeDesc();
			registrationFileDTO.setDocPath(path);
			path = fileServer + path;
			uploadDir = new File(path);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			try (FileOutputStream fos = new FileOutputStream(path + File.separator + filename)) {
				fos.write(filearray);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public ReimbursementProcessingDTO saveProcessingDetails(String compId,
			ReimbursementProcessingDTO reimbursementProcessingDTO) throws DAOException {
		ReimbursementProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = reimbursementClaimsDAO.insertReimbursementProcessingDetails(compId,
					reimbursementProcessingDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _reimbursementProcessingDTO;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public ReimbursementProcessingDTO updateProcessingDetails(String compId,
			ReimbursementProcessingDTO reimbursementProcessingDTO) throws DAOException {
		ReimbursementProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = reimbursementClaimsDAO.updateReimbursementProcessingDetails(compId,
					reimbursementProcessingDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _reimbursementProcessingDTO;
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public ReimbursementProcessingDTO getReimbursementProcessingDetailsById(ObjectNode paramMap) throws DAOException {
		List<ReimbursementProcessingDTO> processingDTOs = null;
		List<ReimbursementProcessingServiceDTO> processingServiceDTOs = new ArrayList<>();
		ReimbursementProcessingDTO reimbursementProcessingDTO = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_PROCESSING_DETAILS;
			strQuery = getConstructedQuerywithCriterionForAssignment(strQuery, inputMap);

			processingDTOs = reimbursementClaimsDAO.getProcessingDetails(strQuery, inputMap);
			if (processingDTOs != null && !processingDTOs.isEmpty()) {
				reimbursementProcessingDTO = processingDTOs.get(0);
				processingDTOs.forEach(processingDTO -> processingServiceDTOs.addAll(processingDTO.getProcessingServiceDTOs()));
				reimbursementProcessingDTO.setProcessingServiceDTOs(processingServiceDTOs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementProcessingDTO;
	}
	
	private String getConstructedQuerywithCriterionForProcessing(String strQuery, Map<String, Object> inputMap) {
		if (strQuery.contains("<CRITERIA>")) {
			Iterator<String> mapIter = inputMap.keySet().iterator();
			StringBuilder builder = new StringBuilder("");
			while (mapIter.hasNext()) {
				String key = (String) mapIter.next();
				if (key.equalsIgnoreCase("claimNumber") && StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(REIMBURSEMENT_QUERIES_PROCESSING_DETAILS_CLAIMNUMBER_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("requestNumber") && StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(REIMBURSEMENT_QUERIES_PROCESSING_DETAILS_REQUESTNUMBER_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("id") && StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(REIMBURSEMENT_QUERIES_PROCESSING_DETAILS_ID_CRITERIA + " ");
				}
			}
			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
		}
		return strQuery;
	}
	
	public 	ReimbursementProcessingDTO getReimbursementInitProcessingDetails(ReimbursementAssignmentDTO reimbursementAssignmentDTO) throws DAOException {
		ReimbursementProcessingDTO reimbursementProcessingDTO =  new ReimbursementProcessingDTO();
		try {
			BeanUtils.copyProperties(reimbursementAssignmentDTO, reimbursementProcessingDTO, "assignmentId","primaryDiagnosis", 
					"secondaryDiagnosis","processingServiceDTOs", "eventCountry","claimCondition",
					"requestNumber","claimType","claimStatusReason");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementProcessingDTO;
	}

}