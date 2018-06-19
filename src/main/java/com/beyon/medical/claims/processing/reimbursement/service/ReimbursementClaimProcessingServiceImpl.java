package com.beyon.medical.claims.processing.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_PROCESSING_DETAILS;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_PROCESSING_DETAILS_CLAIMNUMBER_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_PROCESSING_DETAILS_FOR_ASSIGNMENT;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_PROCESSING_DETAILS_ID_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_PROCESSING_DETAILS_ORDER_BY;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_PROCESSING_DETAILS_REQUESTNUMBER_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_PROCESSING_SERVICE_DETAILS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.AssignmentDTO;
import com.beyon.medical.claims.general.dto.ProcessingDTO;
import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;
import com.beyon.medical.claims.processing.dao.ClaimProcessingDAOImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service(value="reimbursementClaimProcessingServiceImpl")
public class ReimbursementClaimProcessingServiceImpl implements ReimbursementClaimProcessingService {

	@Autowired
	private ClaimProcessingDAOImpl claimProcessingDAOImpl;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public ProcessingDTO saveProcessingDetails(String compId, ProcessingDTO reimbursementProcessingDTO)
			throws DAOException {
		ProcessingDTO _reimbursementProcessingDTO = null;
		try {
			if(reimbursementProcessingDTO.isCreated()) {
				_reimbursementProcessingDTO = claimProcessingDAOImpl.insertProcessingDetails(compId,
					reimbursementProcessingDTO);
			} else {
				_reimbursementProcessingDTO = claimProcessingDAOImpl.updateProcessingDetails(compId,
					reimbursementProcessingDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _reimbursementProcessingDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public ProcessingDTO updateProcessingDetails(String compId, ProcessingDTO reimbursementProcessingDTO)
			throws DAOException {
		ProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = claimProcessingDAOImpl.updateProcessingDetails(compId,
					reimbursementProcessingDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _reimbursementProcessingDTO;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public ProcessingDTO getProcessingDetails(ObjectNode paramMap) throws DAOException {
		List<ProcessingDTO> processingDTOs = null;
		List<ProcessingServiceDTO> processingServiceDTOs = new ArrayList<>();
		ProcessingDTO reimbursementProcessingDTO = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = QUERIES_PROCESSING_DETAILS;
			strQuery = getConstructedQuerywithCriterionForProcessing(strQuery, inputMap);

			processingDTOs = claimProcessingDAOImpl.getProcessingDetails(strQuery, inputMap);
			if (processingDTOs != null && !processingDTOs.isEmpty()) {
				reimbursementProcessingDTO = processingDTOs.get(0);
				Map<Long, List<ProcessingDTO>> groupById = 
						processingDTOs.stream().collect(Collectors.groupingBy(a->a.getProcessingServiceDTOs().get(0).getReimbursementProcessId()));
				for (Entry<Long, List<ProcessingDTO>> entry : groupById.entrySet()) {
					processingServiceDTOs.addAll(entry.getValue().get(0).getProcessingServiceDTOs());
				}
				Map<String, List<ProcessingDTO>> groupByDiagType = 
						processingDTOs.stream().collect(Collectors.groupingBy(ProcessingDTO::getDiagType));
				for (Entry<String, List<ProcessingDTO>> entry : groupByDiagType.entrySet()) {
					if(entry.getKey().equalsIgnoreCase("Primary")) {
						reimbursementProcessingDTO.setPrimaryDiagnosis(entry.getValue().get(0).getPrimaryDiagnosis());
					} else if(entry.getKey().equalsIgnoreCase("Secondary")) {
						reimbursementProcessingDTO.setSecondaryDiagnosis(entry.getValue().get(0).getSecondaryDiagnosis());

					}
				}

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
					builder.append(QUERIES_PROCESSING_DETAILS_CLAIMNUMBER_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("requestNumber")
						&& StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(QUERIES_PROCESSING_DETAILS_REQUESTNUMBER_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("id") & inputMap.get(key) != null) {
					builder.append(QUERIES_PROCESSING_DETAILS_ID_CRITERIA + " ");
				}
			}
			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
			strQuery += (" " + QUERIES_PROCESSING_DETAILS_ORDER_BY);
		}
		return strQuery;

	}

	@Override
	public ProcessingDTO getInitProcessingDetails(AssignmentDTO reimbursementAssignmentDTO) throws DAOException {
		ProcessingDTO reimbursementProcessingDTO = new ProcessingDTO();
		try {
			BeanUtils.copyProperties(reimbursementAssignmentDTO, reimbursementProcessingDTO, "assignmentId",
					"primaryDiagnosis", "secondaryDiagnosis", "processingServiceDTOs", "eventCountry", "claimCondition",
					"requestNumber", "claimType", "claimStatusReason", "assignedUserDetailsDTO");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementProcessingDTO;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public ProcessingDTO approveServiceLineItem(String compId,
			ProcessingDTO reimbursementProcessingDTO) throws DAOException {
		ProcessingDTO _reimbursementProcessingDTO = null;
		try {
			_reimbursementProcessingDTO = claimProcessingDAOImpl.approveServiceLineItem(compId,
					reimbursementProcessingDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _reimbursementProcessingDTO;
	}
	
	@Override
	public 	ProcessingDTO getProcessingDetailsForAssignment(AssignmentDTO reimbursementAssignmentDTO) throws DAOException {
		ProcessingDTO reimbursementProcessingDTO =  new ProcessingDTO();
		List<ProcessingDTO> processingDTOs = null;
		ProcessingDTO _reimbursementProcessingDTO = null;
		try {
			BeanUtils.copyProperties(reimbursementAssignmentDTO, reimbursementProcessingDTO, "assignmentId","primaryDiagnosis", 
					"secondaryDiagnosis","processingServiceDTOs", "eventCountry","claimCondition",
					"requestNumber","claimType","claimStatusReason","assignedUserDetailsDTO");
			Map<String, Object> inputMap = new HashMap<>();
			inputMap.put("id", reimbursementAssignmentDTO.getId());

			String strQuery = QUERIES_PROCESSING_DETAILS_FOR_ASSIGNMENT;
			processingDTOs = claimProcessingDAOImpl.getProcessingDetailsForAssignment(strQuery, inputMap);
			if (processingDTOs != null && !processingDTOs.isEmpty()) {
				_reimbursementProcessingDTO = processingDTOs.get(0);
				Map<String, List<ProcessingDTO>> groupByDiagType = 
						processingDTOs.stream().collect(Collectors.groupingBy(ProcessingDTO::getDiagType));
				for (Entry<String, List<ProcessingDTO>> entry : groupByDiagType.entrySet()) {
					if(entry.getKey().equalsIgnoreCase("Primary")) {
						reimbursementProcessingDTO.setPrimaryDiagnosis(entry.getValue().get(0).getPrimaryDiagnosis());
					} else if(entry.getKey().equalsIgnoreCase("Secondary")) {
						reimbursementProcessingDTO.setSecondaryDiagnosis(entry.getValue().get(0).getSecondaryDiagnosis());
					}
				}
				reimbursementProcessingDTO.setClaimNumber(_reimbursementProcessingDTO.getClaimNumber());
				reimbursementProcessingDTO.setRequestNumber(_reimbursementProcessingDTO.getRequestNumber());		
				reimbursementProcessingDTO.setClaimType(_reimbursementProcessingDTO.getClaimType());
				reimbursementProcessingDTO.setEventCountry(_reimbursementProcessingDTO.getEventCountry());
				reimbursementProcessingDTO.setClaimCondition(_reimbursementProcessingDTO.getClaimCondition());
				reimbursementProcessingDTO.setClaimStatusReason(_reimbursementProcessingDTO.getClaimStatusReason());		
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementProcessingDTO;
	}
	
	
	@Override
	public 	List<ProcessingServiceDTO> getProcessingServiceDetails(Long registrationId) throws DAOException {
		List<ProcessingServiceDTO> processingServiceDTOs = null;
		try {
			String strQuery = QUERIES_PROCESSING_SERVICE_DETAILS;
			Map<String, Object> inputMap = new HashMap<>();
			inputMap.put("id", registrationId);
			processingServiceDTOs = claimProcessingDAOImpl.getProcessingServiceDetails(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return processingServiceDTOs;
	}


}