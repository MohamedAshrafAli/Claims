package com.beyon.medical.claims.assignment.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_BY_STATUS;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_DETAILS;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_EMPTY_CRITERIA;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_EMPTY_CRITERIA_ORDER_BY;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_DETAILS;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_MEMBER_DETAILS;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_BETWEEN_DETAILS;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_FROM_DETAILS;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_TO_DETAILS;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_STATUS_CRITERIA;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_CTDS_ASSIGNMENT_STATUS_CRITERIA_ORDER_BY;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.assignment.dao.ClaimAssignmentDAOImpl;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.AssignmentDTO;
import com.beyon.medical.claims.mapper.AssignmentMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service(value="reimbursementClaimAssignmentServiceImpl")
public class ReimbursementClaimAssignmentServiceImpl implements ReimbursementClaimAssignmentService {

	@Autowired
	private ClaimAssignmentDAOImpl claimAssignmentDAOImpl;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<AssignmentDTO> getReimbursementAssignmentDetails(ObjectNode paramMap) throws DAOException {
		List<AssignmentDTO> reimbursementAssignmentDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = QUERIES_CTDS_ASSIGNMENT_DETAILS;
			strQuery = getConstructedQuerywithCriterionForAssignment(strQuery, inputMap);
			reimbursementAssignmentDetails = claimAssignmentDAOImpl.getAssignmentListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementAssignmentDetails;
	}

	private String getConstructedQuerywithCriterionForAssignment(String strQuery, Map<String, Object> inputMap) {
		StringBuilder builder = new StringBuilder("");
		String orderByCriteria;
		if(StringUtils.isBlank((String) inputMap.get("Status"))) {
			builder.append(QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_EMPTY_CRITERIA + " ");
			orderByCriteria =  QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_EMPTY_CRITERIA_ORDER_BY;
		} else {
			strQuery = QUERIES_CTDS_ASSIGNMENT_BY_STATUS;
			builder.append(" "+QUERIES_CTDS_ASSIGNMENT_STATUS_CRITERIA + " ");
			orderByCriteria = QUERIES_CTDS_ASSIGNMENT_STATUS_CRITERIA_ORDER_BY;
		}
		if (strQuery.contains("<CRITERIA>")) {
			Iterator<String> mapIter = inputMap.keySet().iterator();
			while (mapIter.hasNext()) {
				String key = (String) mapIter.next();
				if (key.equalsIgnoreCase("claimNumber") && StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_DETAILS + " ");
				} else if (key.equalsIgnoreCase("memberNumber") && StringUtils.isNotBlank((String) inputMap.get(key))) {
					builder.append(QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA + " ");
				}
			}
			if (StringUtils.isNotBlank((String) inputMap.get("reqReceivedFrom"))
					&& StringUtils.isNotBlank((String) inputMap.get("reqReceivedTo"))) {
				builder.append(QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_BETWEEN_DETAILS + " ");
			} else if (StringUtils.isNotBlank((String) inputMap.get("reqReceivedFrom"))) {
				builder.append(QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_FROM_DETAILS + " ");
			} else if (StringUtils.isNotBlank((String) inputMap.get("reqReceivedTo"))) {
				builder.append(QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_TO_DETAILS + " ");
			}
			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
			strQuery += (" "+ orderByCriteria);
		}
		return strQuery;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public ObjectNode getMemberDetailsForPolicyAndMemberNo(ObjectNode paramMap) throws DAOException {
		ObjectNode outputMap = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = QUERIES_CTDS_ASSIGNMENT_MEMBER_DETAILS;
			outputMap = claimAssignmentDAOImpl.getAssignmentnListData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return outputMap;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public List<AssignmentDTO> saveAssignmentDetails(String compId,
			List<AssignmentDTO> assignmentDTOs) throws DAOException {
		List<AssignmentDTO> _assignmentDTOs = null;
		try {
			for (AssignmentDTO reimbursementAssignmentDTO : assignmentDTOs) {
				Map<String, Object> refNoMap = claimAssignmentDAOImpl.getClaimNo("C", compId, reimbursementAssignmentDTO.getPolicyDetailsDTO().getProductId());
				String claimRefNo = (String) refNoMap.get("P_DOC_NO");
				reimbursementAssignmentDTO.setClaimNumber(claimRefNo);
				ObjectNode objectNode = FoundationUtils.createObjectNode();
				objectNode.put("policyNumber", reimbursementAssignmentDTO.getPolicyDetailsDTO().getPolicyNumber());
				objectNode.put("compId", compId);
				objectNode.put("memberNumber", reimbursementAssignmentDTO.getMemberDetailsDTO().getMemberNumber());
				ObjectNode outputMap = getMemberDetailsForPolicyAndMemberNo(objectNode);
				AssignmentMapper.getAssignmentDTOWithMemberDetails(outputMap, reimbursementAssignmentDTO);

			}
			_assignmentDTOs = claimAssignmentDAOImpl.insertReimbursementAssignmentDetails(compId, assignmentDTOs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _assignmentDTOs;
	}
}