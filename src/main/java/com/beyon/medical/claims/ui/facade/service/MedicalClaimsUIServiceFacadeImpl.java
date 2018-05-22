package com.beyon.medical.claims.ui.facade.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.*;

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
public class MedicalClaimsUIServiceFacadeImpl implements MedicalClaimsUIServiceFacade {
	
	@Autowired
	private GeneralClaimsDAOImpl generalClaimsDao;

	private final ClaimConstants claimConstants = ClaimConstants.getInstance();

	@Override
	public List<String> getMemberCardNumberList(String compId,String cardNumber) throws DAOException {
		List<String> memberCardNumers = generalClaimsDao.getMedicalCardNumbers(compId, cardNumber);
		return memberCardNumers;
	}  

	@Override
	public ObjectNode getMemberNumberList(ObjectNode paramMap) throws DAOException {
		ObjectNode memberNumberList = null;
		try {
			Map<Integer, String> outputMap = new HashMap<>();
			outputMap.put(1, "ULME_MEMBER_ID");
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
			outputMap.put(1, "ILM_NO");
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			memberNumberList =  generalClaimsDao.getSearchDataList(GENERAL_QUERIES_GET_MEMBER_NUMBERS, inputMap, outputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return memberNumberList;
	}

	@Override
	public ObjectNode getUIDefinitionList(String definition,ObjectNode paramMap) throws DAOException {
		return generalClaimsDao.getUIDataListForDefinition(definition,paramMap);
	}


	
}