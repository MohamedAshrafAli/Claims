package com.beyon.medical.claims.ui.facade.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_CARD_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_EMIRATES_IDS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NAMES;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_POLICY_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_VOUCHER_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_LISTS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_IDS_COLUMN_MEMBER_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_IDS_COLUMN_POLICY_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_MEMBER_NUMBERS;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.GeneralClaimsDAOImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MedicalClaimsUIServiceFacadeImpl implements MedicalClaimsUIServiceFacade {
	
	@Autowired
	private GeneralClaimsDAOImpl generalClaimsDao;

	private final ClaimConstants claimConstants = ClaimConstants.getInstance();
 

	@Override
	public ObjectNode getMemberNumberList(ObjectNode paramMap) throws DAOException {
		ObjectNode memberNumberList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_IDS_COLUMN_MEMBER_NUMBERS);
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			memberNumberList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_MEMBER_NUMBERS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return memberNumberList;
	}

	@Override
	public ObjectNode getPolicyNumberList(ObjectNode paramMap) throws DAOException {
		ObjectNode memberNumberList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_IDS_COLUMN_POLICY_NUMBERS);
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			memberNumberList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_MEMBER_NUMBERS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return memberNumberList;
	}
	@Override
	public ObjectNode getVoucherNumberList(ObjectNode paramMap) throws DAOException {
		ObjectNode voucherNumberList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_CTDS_COLUMN_VOUCHER_NUMBERS);
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			voucherNumberList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_CTDS_LISTS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return voucherNumberList;
	}
	
	@Override
	public ObjectNode getMemberNameList(ObjectNode paramMap) throws DAOException {
		ObjectNode memberNameList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NUMBERS);
			outputMap.put(2, GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NAMES);
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			memberNameList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_CTDS_LISTS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return memberNameList;
	}
	
	@Override
	public ObjectNode getMemberCardNumberList(ObjectNode paramMap) throws DAOException {
		ObjectNode cardNumbers = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_CTDS_COLUMN_CARD_NUMBERS);
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			cardNumbers =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_CTDS_LISTS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return cardNumbers;
	}
	
	
	@Override
	public ObjectNode getClaimsPolicyNumberList(ObjectNode paramMap) throws DAOException {
		ObjectNode claimPolicyNumbers = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_CTDS_COLUMN_POLICY_NUMBERS);
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			claimPolicyNumbers =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_CTDS_LISTS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return claimPolicyNumbers;
	}
	
	@Override
	public ObjectNode getEmiratesId(ObjectNode paramMap) throws DAOException {
		ObjectNode emiratesId = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_CTDS_COLUMN_EMIRATES_IDS);
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			emiratesId =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_CTDS_LISTS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return emiratesId;
	}
	
	@Override
	public ObjectNode getUIDefinitionList(String definition,ObjectNode paramMap) throws DAOException {
		return generalClaimsDao.getUIDataListForDefinition(definition,paramMap);
	}


	
}