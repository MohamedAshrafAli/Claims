package com.beyon.medical.claims.general.dao;

import static com.beyon.framework.util.AppLogger.DEBUG;
import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.exception.DAOException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BaseClaimsDAOImpl {

	private final String CLASS_NAME = BaseClaimsDAOImpl.class.getCanonicalName();

	public Long getSequenceNo(String sequenceName) {
		writeLog(CLASS_NAME, "Entered method getSequenceNo --> sequenceName:" + sequenceName, DEBUG);
		if (sequenceName == null) {
			throw new IllegalArgumentException("The passed sequence name was null", new Throwable());
		}

		JdbcTemplate template = DAOFactory.getJdbcTemplate("gm");
		String strSeqNoQry = "SELECT " + sequenceName + ".nextval FROM DUAL";
		Long seqNo = template.queryForObject(strSeqNoQry, Long.class);
		writeLog(CLASS_NAME, "Exit method getSequenceNo  --> sequenceName:" + sequenceName + " and seqNo:" + seqNo,
				DEBUG);
		return seqNo;
	}
	public ObjectNode getCurrencyDetailsForPolicyNo(String strQuery,Map<String, Object> inputMap) throws DAOException {
		ObjectNode objectNode = FoundationUtils.createObjectNode();
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			ArrayNode jsonArray = FoundationUtils.createArrayNode();
			namedParameterJdbcTemplate.query(strQuery, inputMap , new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					ObjectNode objectNode = FoundationUtils.createObjectNode();
					objectNode.put("BaseCurrency", rs.getString("BaseCurrency"));
					objectNode.put("ExchangeCurrency", rs.getString("ExchangeCurrency"));
					objectNode.put("ExchangeRate", rs.getBigDecimal("ExchangeRate"));
					jsonArray.add(objectNode);
				}
			});
			objectNode.putArray("rowData").addAll(jsonArray);
		} catch (Exception ex) {
			writeLog(CLASS_NAME, "Exception occured while executing getDataList", ERROR, ex);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return objectNode;
	}
	
	public String getUserDivisionForCompany(String strQuery,String userName,String compId) throws DAOException {
		String userDivision = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			List<String> userDivisionList = jdbcTemplate.query(strQuery, new Object[] { compId,userName }, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rownumber) throws SQLException {
					return rs.getString("DivisionId");
				}
			});
			if(userDivisionList != null && !userDivisionList.isEmpty()) {
				userDivision = userDivisionList.get(0);
			}
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getUserDivisionForCompany", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return userDivision;
	}
	
	
	public String getProviderIdDetails(String strQuery) throws DAOException {
		String providerId = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			List<String> providerIdList = jdbcTemplate.query(strQuery, new Object[] { }, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rownumber) throws SQLException {
					return rs.getString("ProviderId");
				}
			});
			providerId = providerIdList.get(0);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getProviderIdDetails", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return providerId;
	}
	
	
	public String getClassOfBusinessForPolicy(String strQuery,String policyNumber,String compId) throws DAOException {
		String cobId = null;
		try {
			List<String> cobIds = new ArrayList<String>();
			
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			Map<String, Object> inputMap = new HashMap<>();
			inputMap.put("compId",compId);
			inputMap.put("policyNumber", policyNumber);
			namedParameterJdbcTemplate.query(strQuery, inputMap , new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					cobIds.add(rs.getString("CobId"));
				}
			});
			cobId = cobIds.get(0);
		} catch (Exception ex) {
			writeLog(CLASS_NAME, "Exception occured while executing getDataList", ERROR, ex);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return cobId;
	}


}
