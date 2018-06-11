package com.beyon.medical.claims.general.facade.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_ASSIGNMENT_USER_STATUS_COUNT;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_DIAGNOSIS_CODE_DETAILS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_COB_DETAIL;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_CARD_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_CLAIM_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_EMIRATES_IDS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NAMES;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_POLICY_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_COLUMN_VOUCHER_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CTDS_LISTS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_CURRENCY_DETAILS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_IDS_COLUMN_MEMBER_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_IDS_COLUMN_POLICY_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_MEMBER_NUMBERS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_UNIVERSAL_CURRENCIES;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_USER_DIVISION;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_SERVICE_CODE_DETAILS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_USER_LIST;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_REJECTION_CODE_DETAILS;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_COUNTRYIDS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.GeneralClaimsDAOImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class GeneralServiceFacadeImpl implements GeneralServiceFacade {
	
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
	public ObjectNode getCurrencyDetails(ObjectNode paramMap,boolean isUniversal) throws DAOException {
		ObjectNode baseCurrency = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			baseCurrency =  generalClaimsDao.getCurrencyDetails(isUniversal ? GENERAL_QUERIES_GET_UNIVERSAL_CURRENCIES : GENERAL_QUERIES_GET_CURRENCY_DETAILS,inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return baseCurrency;
	}
	
	@Override
	public String getUserDivisionForCompany(String userId,String compId) throws DAOException {
		String baseCurrency = null;
		try {
			baseCurrency =  generalClaimsDao.getUserDivisionForCompany(GENERAL_QUERIES_GET_USER_DIVISION,userId,compId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return baseCurrency;
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
	public ObjectNode getClaimNumberList(ObjectNode paramMap) throws DAOException {
		ObjectNode voucherNumberList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, GENERAL_QUERIES_GET_CTDS_COLUMN_CLAIM_NUMBERS);
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

	@Override
	public String getClassOfBusinessForPolicy(String policyNumber,String compId) throws DAOException {
		String baseCurrency = null;
		try {
			baseCurrency =  generalClaimsDao.getClassOfBusinessForPolicy(GENERAL_QUERIES_GET_COB_DETAIL,policyNumber,compId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return baseCurrency;
	}
	
	@Override
	public ObjectNode getUserList(ObjectNode paramMap) throws DAOException {
		ObjectNode userList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, "UserId");
			outputMap.put(2, "UserName");
			outputMap.put(3, "UserGroupId");
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			userList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_USER_LIST, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return userList;
	}
	
	@Override
	public ObjectNode getServiceCodeDetails(ObjectNode paramMap) throws DAOException {
		ObjectNode userList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, "BenefitId");
			outputMap.put(2, "ServiceCode");
			outputMap.put(3, "ServiceTypeId");
			outputMap.put(4, "ServiceName");
			outputMap.put(5, "SubBenefitId");			
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			userList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_SERVICE_CODE_DETAILS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return userList;
	}
	
	@Override
	public ObjectNode getDiagnosisCodeDetails(ObjectNode paramMap) throws DAOException {
		ObjectNode userList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, "DiagnosisCode");
			outputMap.put(2, "DiagnosisName");
			outputMap.put(3, "DiagnosisType");
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			userList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_DIAGNOSIS_CODE_DETAILS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return userList;
	}
	
	@Override
	public ObjectNode getRejectionCodeDetails(ObjectNode paramMap) throws DAOException {
		ObjectNode userList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, "RejectionCode");
			outputMap.put(2, "RejectionDesc");
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			userList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_REJECTION_CODE_DETAILS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return userList;
	}
	
	@Override
	public List<String> getCountryIds(String compId) throws DAOException {
		List<String> countryIds = null;
		try {
			countryIds =  generalClaimsDao.getCountryIds(GENERAL_QUERIES_GET_COUNTRYIDS,compId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return countryIds;
	}
	
	@Override
	public ObjectNode getStatusCountByUser(ObjectNode paramMap) throws DAOException {
		ObjectNode userList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, "Total");
			outputMap.put(2, "Status");
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			userList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_ASSIGNMENT_USER_STATUS_COUNT, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return userList;
	}
	
}