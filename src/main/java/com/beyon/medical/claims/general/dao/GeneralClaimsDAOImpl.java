package com.beyon.medical.claims.general.dao;

import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_UID_DEFINITION_TYPES;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.exception.DAOException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Repository("generalClaimsDAOImpl")
@Scope(value="prototype")
public class GeneralClaimsDAOImpl extends BaseClaimsDAOImpl {

	private final String CLASS_NAME = GeneralClaimsDAOImpl.class.getCanonicalName();


	public ObjectNode getSearchDataList(String strQuery,Map<String, Object> inputMap,Map<Integer,String> outputMap) throws DAOException {
		ObjectNode objectNode = FoundationUtils.createObjectNode();
		try {
			if(strQuery.contains("<RESULTSET>")) {
				Iterator<Integer> mapIter = outputMap.keySet().iterator();
				StringBuilder builder = new StringBuilder();
				while (mapIter.hasNext()) {
					Integer key = (Integer) mapIter.next();
					builder.append(outputMap.get(key));
					if(mapIter.hasNext()) 
						builder.append(",");
				}
				strQuery = strQuery.replaceAll("<RESULTSET>", builder.toString());
			}
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			ArrayNode jsonArray = FoundationUtils.createArrayNode();
			namedParameterJdbcTemplate.query(strQuery, inputMap , new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					ObjectNode objectNode = FoundationUtils.createObjectNode();
					outputMap.entrySet().forEach(entry -> {
						try {
							objectNode.put(entry.getValue(), rs.getString(entry.getKey()));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}); 
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
	
	
	public ObjectNode getUserList(String strQuery,ObjectNode inputNode) throws DAOException {
		ObjectNode objectNode = FoundationUtils.createObjectNode();
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(inputNode, Map.class);
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			ArrayNode jsonArray = FoundationUtils.createArrayNode();
			namedParameterJdbcTemplate.query(strQuery, inputMap , new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					ObjectNode objectNode = FoundationUtils.createObjectNode();
					objectNode.put("UserId", rs.getString("UserId"));
					objectNode.put("UserName", rs.getString("UserName"));
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
