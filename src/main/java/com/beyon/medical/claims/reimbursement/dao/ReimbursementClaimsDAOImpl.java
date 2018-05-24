package com.beyon.medical.claims.reimbursement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.*;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.BaseClaimsDAOImpl;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Repository("reimbursementClaimsDAOImpl")
@Scope(value="prototype")
public class ReimbursementClaimsDAOImpl extends BaseClaimsDAOImpl {

	private final String CLASS_NAME = ReimbursementClaimsDAOImpl.class.getCanonicalName();

	public List<?> getReimbursementRegistrationDetails(String id,String compId) {
        JdbcTemplate template = DAOFactory.getJdbcTemplate("gm");
        List<Object> tpaMap = template.query(REIMBURSEMENT_QUERIES_CTDS_DETAILS, new Object[] {id, compId }, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet row, int count) throws SQLException {
            	return null;
            }

        });
        return tpaMap;
    }
	
	public ObjectNode getRegistrationListViewData(String strQuery,Map<String, Object> inputMap,Map<Integer,String> outputMap) throws DAOException {
		ObjectNode objectNode = FoundationUtils.createObjectNode();
		try {
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


}
