package com.beyon.medical.claims.general.dao;

import static com.beyon.framework.util.AppLogger.DEBUG;
import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.exception.DAOException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Repository("generalClaimsDAOImpl")
@Scope(value="prototype")
public class GeneralClaimsDAOImpl {

	private final String CLASS_NAME = GeneralClaimsDAOImpl.class.getCanonicalName();

	public List<String> getMedicalCardNumbers(String compId,String cardNumber) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_CARD_NUMBERS, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}

	public ObjectNode getSearchDataList(String strQuery,Map<String, Object> inputMap,Map<Integer,String> outputMap) throws DAOException {
		ObjectNode objectNode = FoundationUtils.createObjectNode();
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			ArrayNode jsonArray = FoundationUtils.createArrayNode();
			namedParameterJdbcTemplate.query(strQuery, inputMap , new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					ObjectNode objectNode = FoundationUtils.createObjectNode();
					objectNode.put(outputMap.get(1), rs.getString(1));
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

	public ObjectNode getUIDataListForDefinition(String definiton,ObjectNode paramMap) throws DAOException {
		ObjectNode objectNode = null;
		try {
			paramMap.put("uidType", definiton);
			objectNode = getUIDefinitionDataList(GENERAL_QUERIES_GET_UID_DEFINITION_TYPES, paramMap);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return objectNode;
	}

	private Long getSequenceNo(String sequenceName) {
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

	public ObjectNode getUIDefinitionDataList(String strQuery,ObjectNode inputNode) throws DAOException {
		ObjectNode objectNode = FoundationUtils.createObjectNode();
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(inputNode, Map.class);
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			ArrayNode jsonArray = FoundationUtils.createArrayNode();
			namedParameterJdbcTemplate.query(strQuery, inputMap , new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					ObjectNode objectNode = FoundationUtils.createObjectNode();
					objectNode.put("id", rs.getString(1));
					objectNode.put("value", rs.getString(2));
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

}
