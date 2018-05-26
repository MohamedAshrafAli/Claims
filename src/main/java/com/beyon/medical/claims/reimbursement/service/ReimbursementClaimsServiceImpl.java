package com.beyon.medical.claims.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DETAILS;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.reimbursement.dao.ReimbursementClaimsDAOImpl;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.ui.facade.service.GeneralServiceFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ReimbursementClaimsServiceImpl implements ReimbursementClaimsService {

	@Autowired
	private ReimbursementClaimsDAOImpl reimbursementClaimsDAO;
	
	@Autowired
	private GeneralServiceFacade generalServiceFacade;

	@Override
	public List<ReimbursementRegistrationDTO> getReimbursementRegistrationDetails(ObjectNode paramMap) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}
	
	public ReimbursementRegistrationDTO getReimbursementRegistrationDetailsById(String compId,String id) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS;
			Map<String,Object> inputMap = new HashMap<>();
			inputMap.put("compId", compId);
			inputMap.put("id", id);
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails.get(0);
	}
	
	private String getConstructedQuerywithCriterion(String strQuery,Map<String,Object> inputMap) {
		if(strQuery.contains("<CRITERIA>")) {
			Iterator<String> mapIter = inputMap.keySet().iterator();
			StringBuilder builder = new StringBuilder();
			while (mapIter.hasNext()) {
				String key = (String) mapIter.next();
				if(key.equalsIgnoreCase("policyNumber")) {
					builder.append(inputMap.get(REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA));
				} else if(key.equalsIgnoreCase("memberNumber")) {
					builder.append(inputMap.get(REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA));
				} else if(key.equalsIgnoreCase("voucherNumber")) {
					builder.append(inputMap.get(REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA));
				} else if(key.equalsIgnoreCase("id")) {
					builder.append(inputMap.get(REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID_CRITERIA));
				} 
			}
			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
		}
		return strQuery;
	}
	
	@Override
	public List<ReimbursementRegistrationDTO> getRegistrationDetailsForPolicyAndMemberNo(ObjectNode paramMap) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationListData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}
	
	@Override
	public ReimbursementRegistrationDTO saveRegistrationDetails(String compId,ReimbursementRegistrationDTO registrationDTO) throws DAOException {
		ReimbursementRegistrationDTO _registrationDTO = null;
		try {
			_registrationDTO =  reimbursementClaimsDAO.insertReimbursementRegistrationDetails(compId, registrationDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _registrationDTO;
	}

}