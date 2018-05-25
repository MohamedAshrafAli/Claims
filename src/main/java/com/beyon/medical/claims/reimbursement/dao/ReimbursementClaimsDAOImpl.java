package com.beyon.medical.claims.reimbursement.dao;

import static com.beyon.framework.util.AppLogger.DEBUG;
import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.*;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.queries.constants.GeneralQueriesConstants;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.reimbursement.mapper.ReimbRegistrationMapper;

@Repository("reimbursementClaimsDAOImpl")
@Scope(value="prototype")
public class ReimbursementClaimsDAOImpl extends BaseClaimsDAOImpl {

	private final String CLASS_NAME = ReimbursementClaimsDAOImpl.class.getCanonicalName();

	public List<?> getReimbursementRegistrationDetails(String id,String compId) {
		JdbcTemplate template = DAOFactory.getJdbcTemplate("gm");
		return template.query(REIMBURSEMENT_QUERIES_CTDS_DETAILS, new Object[] {id, compId }, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet row, int count) throws SQLException {
				return null;
			}

		});
	}

	public List<ReimbursementRegistrationDTO> getRegistrationListViewData(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ReimbursementRegistrationDTO>() {
			@Override
			public ReimbursementRegistrationDTO mapRow(ResultSet row, int count) throws SQLException {
				return ReimbRegistrationMapper.getViewReimbursementRegistrationDTO(row);
			}
		});
	}

	public List<ReimbursementRegistrationDTO> getRegistrationListData(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ReimbursementRegistrationDTO>() {
			@Override
			public ReimbursementRegistrationDTO mapRow(ResultSet row, int count) throws SQLException {
				return ReimbRegistrationMapper.getReimbursementRegistrationDTO(row);
			}
		});
	}
	
	public Map<String, Object> getClaimsRefNo(String docType,Long sequenceNumber,String productId) throws DAOException {
		Map<String, Object> simpleJdbcCallResult = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_DOC_TYPE", docType);
			inParamMap.put("P_CLF_SGS_ID", sequenceNumber);
			inParamMap.put("P_PROD_ID", productId);
			String strProcName = "CLK_GENERAL.UWP_GENERATE_DOC_NO";
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName(strProcName);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			simpleJdbcCallResult = simpleJdbcCall.execute(in);
		}catch(Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getClaimsRefNo", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return simpleJdbcCallResult;
	}
	
	private boolean insertCTDSLEVELFNOL(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO) throws DAOException {
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		boolean isSaved = false;
		Long dtlsSgsId = getSequenceNo(REIMBURSEMENT_QUERIES_INSERT_SEQUENCE_NAME);
		
		Map<String, Object> refNoMap = getClaimsRefNo("FN", dtlsSgsId, reimbursementRegistrationDTO.getProductId());
		
		String cobId = getClassOfBusinessForPolicy(GENERAL_QUERIES_GET_COB_DETAIL, reimbursementRegistrationDTO.getPolicyNumber(), compId);

		String claimRefNo = (String)refNoMap.get("P_DOC_NO");
		
		java.sql.Date reqReceivedDate = null;
		java.sql.Date serviceFromDate = null;
		java.sql.Date policyFromDate = null;
		java.sql.Date policyToDate = null;
		java.sql.Date createdDate = null;
		if (reimbursementRegistrationDTO.getReqReceivedDate() != null) {
			reqReceivedDate = java.sql.Date.valueOf(reimbursementRegistrationDTO.getReqReceivedDate());
		}
		if (reimbursementRegistrationDTO.getServiceFmDate() != null) {
			serviceFromDate = java.sql.Date.valueOf(reimbursementRegistrationDTO.getServiceFmDate());
		}
		if (reimbursementRegistrationDTO.getPolicyFromDate() != null) {
			policyFromDate = java.sql.Date.valueOf(reimbursementRegistrationDTO.getPolicyFromDate());
		}
		if (reimbursementRegistrationDTO.getPolicyToDate() != null) {
			policyToDate = java.sql.Date.valueOf(reimbursementRegistrationDTO.getPolicyToDate());
		}
		createdDate = new java.sql.Date(new Date().getTime());

		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_FNOL,
				new Object[] { dtlsSgsId, compId, reimbursementRegistrationDTO.getPolicyNumber(), reimbursementRegistrationDTO.getCustomerId(),
						claimRefNo, reqReceivedDate, serviceFromDate,reimbursementRegistrationDTO.getDescription(),
						CLAIMANT_IS_THE_CUSTOMER, CLAIM_REPORTED_BY_INSURED, "CC",reimbursementRegistrationDTO.getProductId(),
						cobId, policyFromDate, policyToDate,"", createdDate,reimbursementRegistrationDTO.getReportedBy(),
						reimbursementRegistrationDTO.getReportedByDesc(), CLAIM_REPORTED_BY_INSURED, "",reimbursementRegistrationDTO.getDivisionId(),
						reimbursementRegistrationDTO.getDeptId(),reimbursementRegistrationDTO.getInsuredId(),reimbursementRegistrationDTO.getSourceType(),
						reimbursementRegistrationDTO.getUserDivision(),CLAIM_MOD_TYPE});

		isSaved = true;
		writeLog(CLASS_NAME, "Exit method saveBatchDetails", DEBUG);
		return isSaved;
	}

}
