package com.beyon.medical.claims.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.reimbursement.dao.ReimbursementClaimsDAOImpl;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ReimbursementClaimsServiceImpl implements ReimbursementClaimsService {

	private static final String CLASS_NAME = ReimbursementClaimsServiceImpl.class.getCanonicalName();


	@Autowired
	private ReimbursementClaimsDAOImpl reimbursementClaimsDAO;

	@Override
	public List<ReimbursementRegistrationDTO> getReimbursementRegistrationDetails(ObjectNode paramMap) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS;
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
					}
				}
				strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
			}
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}

}