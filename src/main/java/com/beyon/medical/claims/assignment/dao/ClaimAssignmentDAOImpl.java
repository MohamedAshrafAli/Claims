package com.beyon.medical.claims.assignment.dao;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_INSERT_CHDS_LEVEL_SL;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_C;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_CP;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_CP_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_SL;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_SL_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_C;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_CP;
import static com.beyon.medical.claims.queries.constants.AssignmentQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_SL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.base.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.AssignmentDTO;
import com.beyon.medical.claims.mapper.AssignmentMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Repository
public class ClaimAssignmentDAOImpl extends BaseClaimsDAOImpl {


	public List<AssignmentDTO> getAssignmentListViewData(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<AssignmentDTO>() {
			@Override
			public AssignmentDTO mapRow(ResultSet row, int count) throws SQLException {
				return AssignmentMapper.getAssignmentDTO(row);
			}
		});
	}

	public ObjectNode getAssignmentnListData(String strQuery,Map<String,Object> inputMap) throws DAOException {
		ObjectNode objectNode = FoundationUtils.createObjectNode();
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			namedParameterJdbcTemplate.query(strQuery, inputMap , new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					objectNode.put("FirstName", rs.getString("FirstName"));
					objectNode.put("MiddleName", rs.getString("MiddleName"));
					objectNode.put("LastName", rs.getString("LastName"));
					objectNode.put("Address1", rs.getString("Address1"));
					objectNode.put("Address2", rs.getString("Address2"));
					objectNode.put("Address3", rs.getString("Address3"));
					objectNode.put("Address4", rs.getString("Address4"));
					objectNode.put("Pincode", rs.getString("Pincode"));
					objectNode.put("City", rs.getString("City"));
					objectNode.put("State", rs.getString("State"));
					objectNode.put("Country", rs.getString("Country"));
					objectNode.put("PhoneNumber", rs.getString("PhoneNumber"));
					objectNode.put("MobileNumber", rs.getString("MobileNumber"));
					objectNode.put("EmailId", rs.getString("EmailId"));
					objectNode.put("MemberName", rs.getString("MemberName"));
					objectNode.put("Gender", rs.getString("Gender"));
					objectNode.put("Relation", rs.getString("Relation"));
					objectNode.put("Nationality", rs.getString("Nationality"));
				}

			});
		} catch (Exception ex) {
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return objectNode;
	}


	public Map<String, Object> getClaimsRefNo(String docType,Long sequenceNumber,String productId) throws DAOException {
		Map<String, Object> simpleJdbcCallResult = null;
		try {
			//TODO:No Hardcoding move the constants to constant file
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_DOC_TYPE", docType);
			inParamMap.put("P_CLF_SGS_ID", sequenceNumber);
			inParamMap.put("P_PROD_ID", productId);
			String strProcName = "UWP_GENERATE_DOC_NO";
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName(strProcName).withCatalogName("CLK_GENERAL");
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			simpleJdbcCallResult = simpleJdbcCall.execute(in);
		}catch(Exception e) {
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return simpleJdbcCallResult;
	}

	private boolean insertCTDSLEVELC(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(QUERIES_INSERT_CTDS_LEVEL_C, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					AssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
					java.sql.Date claimIntimationDate = null;
					java.sql.Date claimLossDate = null;
					java.sql.Date createdDate = null;
					if (reimbursementAssignmentDTO.getReqReceivedDate() != null) {
						claimIntimationDate = java.sql.Date.valueOf(reimbursementAssignmentDTO.getReqReceivedDate());
					}
					if (reimbursementAssignmentDTO.getServiceFmDate() != null) {
						claimLossDate = java.sql.Date.valueOf(reimbursementAssignmentDTO.getServiceFmDate());
					}
					createdDate = new java.sql.Date(new Date().getTime());
					ps.setLong(1, reimbursementAssignmentDTO.getId());
					ps.setString(2, reimbursementAssignmentDTO.getClaimNumber());
					ps.setString(3, reimbursementAssignmentDTO.getClaimRefNo());
					ps.setDate(4, claimIntimationDate);
					ps.setDate(5, claimLossDate);
					ps.setString(6, reimbursementAssignmentDTO.getDescription());
					ps.setString(7, reimbursementAssignmentDTO.getPolicyDetailsDTO().getPolicyNumber());
					ps.setString(8, reimbursementAssignmentDTO.getStatus());
					ps.setString(9, reimbursementAssignmentDTO.getPolicyDetailsDTO().getCustomerId());
					ps.setString(10, reimbursementAssignmentDTO.getPolicyDetailsDTO().getProductId());
					ps.setString(11, reimbursementAssignmentDTO.getCreatedBy());
					ps.setDate(12, createdDate);
					ps.setString(13, compId);
					ps.setString(14, reimbursementAssignmentDTO.getDivisionId());
					ps.setString(15, reimbursementAssignmentDTO.getDeptId());

				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}
	
	private boolean updateCTDSLEVELC(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(QUERIES_UPDATE_CTDS_LEVEL_C, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					AssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
					java.sql.Date claimIntimationDate = toSQLDate(reimbursementAssignmentDTO.getReqReceivedDate());
					java.sql.Date claimLossDate = toSQLDate(reimbursementAssignmentDTO.getServiceFmDate());			
					
					ps.setString(1, reimbursementAssignmentDTO.getClaimNumber());
					ps.setString(2, reimbursementAssignmentDTO.getClaimRefNo());
					ps.setDate(3, claimIntimationDate);
					ps.setDate(4, claimLossDate);
					ps.setString(5, reimbursementAssignmentDTO.getDescription());
					ps.setString(6, reimbursementAssignmentDTO.getPolicyDetailsDTO().getPolicyNumber());
					ps.setString(7, reimbursementAssignmentDTO.getStatus());
					ps.setString(8, reimbursementAssignmentDTO.getPolicyDetailsDTO().getCustomerId());
					ps.setString(9, reimbursementAssignmentDTO.getPolicyDetailsDTO().getProductId());										
					ps.setString(10, compId);
					ps.setString(11, reimbursementAssignmentDTO.getDivisionId());
					ps.setString(12, reimbursementAssignmentDTO.getDeptId());					
					ps.setLong(13, reimbursementAssignmentDTO.getId());
				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}

	private boolean insertCTDSLEVELCP(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(QUERIES_INSERT_CTDS_LEVEL_CP, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					AssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
					Long clcpId = getSequenceNo(QUERIES_INSERT_CTDS_LEVEL_CP_SEQUENCE_NAME);
					reimbursementAssignmentDTO.setAssignmentId(clcpId);
					java.sql.Date claimIntimationDate = null;
					java.sql.Date claimLossDate = null;
					java.sql.Date createdDate = null;
					if (reimbursementAssignmentDTO.getReqReceivedDate() != null) {
						claimIntimationDate = java.sql.Date.valueOf(reimbursementAssignmentDTO.getReqReceivedDate());
					}
					if (reimbursementAssignmentDTO.getServiceFmDate() != null) {
						claimLossDate = java.sql.Date.valueOf(reimbursementAssignmentDTO.getServiceFmDate());
					}
					createdDate = new java.sql.Date(new Date().getTime());
					ps.setLong(1, reimbursementAssignmentDTO.getId());
					ps.setString(2, reimbursementAssignmentDTO.getMemberDetailsDTO().getFirstName());
					ps.setString(3, reimbursementAssignmentDTO.getMemberDetailsDTO().getMiddleName());
					ps.setString(4, reimbursementAssignmentDTO.getMemberDetailsDTO().getLastName());
					ps.setString(5, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress1());
					ps.setString(6, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress2());
					ps.setString(7, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress3());
					ps.setString(8, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress4());
					ps.setString(9, reimbursementAssignmentDTO.getMemberDetailsDTO().getPincode());
					ps.setString(10, reimbursementAssignmentDTO.getMemberDetailsDTO().getState());
					ps.setString(11, reimbursementAssignmentDTO.getMemberDetailsDTO().getCity());
					ps.setString(12, reimbursementAssignmentDTO.getMemberDetailsDTO().getCountry());
					ps.setString(13, reimbursementAssignmentDTO.getMemberDetailsDTO().getPrimaryPhoneNo());
					ps.setString(14, reimbursementAssignmentDTO.getMemberDetailsDTO().getMobileNum1());
					ps.setString(15, reimbursementAssignmentDTO.getAssignmentId()+"");
					ps.setString(16,null);
					ps.setString(17, reimbursementAssignmentDTO.getMemberDetailsDTO().getEmail1());
					ps.setString(18, reimbursementAssignmentDTO.getMemberDetailsDTO().getMemberName());
					ps.setString(19, null);
					ps.setString(20, reimbursementAssignmentDTO.getMemberDetailsDTO().getGender());
					ps.setDate(21, createdDate);
					ps.setString(22, "I");
					ps.setString(23, reimbursementAssignmentDTO.getCreatedBy());
					ps.setString(24, reimbursementAssignmentDTO.getMemberDetailsDTO().getRelationWithPrimary());
					ps.setString(25, reimbursementAssignmentDTO.getMemberDetailsDTO().getNationalId());


				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}
	
	private boolean updateCTDSLEVELCP(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(QUERIES_UPDATE_CTDS_LEVEL_CP, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					AssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);					
					java.sql.Date claimIntimationDate = toSQLDate(reimbursementAssignmentDTO.getReqReceivedDate());
					java.sql.Date claimLossDate = toSQLDate(reimbursementAssignmentDTO.getServiceFmDate());					
					
					ps.setString(1, reimbursementAssignmentDTO.getMemberDetailsDTO().getFirstName());
					ps.setString(2, reimbursementAssignmentDTO.getMemberDetailsDTO().getMiddleName());
					ps.setString(3, reimbursementAssignmentDTO.getMemberDetailsDTO().getLastName());
					ps.setString(4, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress1());
					ps.setString(5, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress2());
					ps.setString(6, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress3());
					ps.setString(7, reimbursementAssignmentDTO.getMemberDetailsDTO().getAddress4());
					ps.setString(8, reimbursementAssignmentDTO.getMemberDetailsDTO().getPincode());
					ps.setString(9, reimbursementAssignmentDTO.getMemberDetailsDTO().getState());
					ps.setString(10, reimbursementAssignmentDTO.getMemberDetailsDTO().getCity());
					ps.setString(11, reimbursementAssignmentDTO.getMemberDetailsDTO().getCountry());
					ps.setString(12, reimbursementAssignmentDTO.getMemberDetailsDTO().getPrimaryPhoneNo());
					ps.setString(13, reimbursementAssignmentDTO.getMemberDetailsDTO().getMobileNum1());
					ps.setString(14, reimbursementAssignmentDTO.getAssignmentId()+"");
					ps.setString(15,null);
					ps.setString(16, reimbursementAssignmentDTO.getMemberDetailsDTO().getEmail1());
					ps.setString(17, reimbursementAssignmentDTO.getMemberDetailsDTO().getMemberName());
					ps.setString(18, null);
					ps.setString(19, reimbursementAssignmentDTO.getMemberDetailsDTO().getGender());					
					ps.setString(20, "I");					
					ps.setString(21, reimbursementAssignmentDTO.getMemberDetailsDTO().getRelationWithPrimary());
					ps.setString(22, reimbursementAssignmentDTO.getMemberDetailsDTO().getNationalId());
					
					ps.setLong(23, reimbursementAssignmentDTO.getId());


				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}
	
	private List<AssignmentDTO> insertCTDSLEVELSL(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		List<AssignmentDTO> results = new ArrayList<>();
		try {
			jdbcTemplate.batchUpdate(QUERIES_INSERT_CTDS_LEVEL_SL, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					AssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
					Long slId = getSequenceNo(QUERIES_INSERT_CTDS_LEVEL_SL_SEQUENCE_NAME);
					if(reimbursementAssignmentDTO.getAssignedUserDetailsDTO() != null)
						reimbursementAssignmentDTO.getAssignedUserDetailsDTO().setSlId(slId);					
					java.sql.Date allocationDate = new java.sql.Date(new Date().getTime());
					java.sql.Date dueDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
					
					ps.setLong(1, reimbursementAssignmentDTO.getId());
					ps.setString(2, reimbursementAssignmentDTO.getPolicyDetailsDTO().getRiskId());
					ps.setString(3, reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getUserId());
					ps.setString(4, reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getUserGroupId());
					ps.setString(5,  "O");
					ps.setDate(6, allocationDate);
					ps.setDate(7, dueDate);
					ps.setString(8, "SFR");//TODO: remove the hardcoded value based on the UID
					ps.setString(9, null);
					ps.setString(10, null);
					ps.setString(11, null);
					ps.setString(12, null);
					ps.setString(13, "*");
					ps.setLong(14, reimbursementAssignmentDTO.getAssignmentId());
					ps.setString(15, "Primary");
					ps.setLong(16,reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getSlId());
					ps.setLong(17, 0L);
					ps.setString(18, reimbursementAssignmentDTO.getCreatedBy());
					ps.setDate(19, createdDate);
					ps.setString(20, "*");
					ps.setString(21, "I");
					results.add(reimbursementAssignmentDTO);
				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return results;
	}
	
	private List<AssignmentDTO> updateCTDSLEVELSL(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		List<AssignmentDTO> results = new ArrayList<>();
		try {
			jdbcTemplate.batchUpdate(QUERIES_UPDATE_CTDS_LEVEL_SL, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					AssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);										
					java.sql.Date allocationDate = new java.sql.Date(new Date().getTime());
					java.sql.Date dueDate = new java.sql.Date(new Date().getTime());		
					
					ps.setString(1, reimbursementAssignmentDTO.getPolicyDetailsDTO().getRiskId());
					ps.setString(2, reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getUserId());
					ps.setString(3, reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getUserGroupId());
					ps.setString(4,  "O");
					ps.setDate(5, allocationDate);
					ps.setDate(6, dueDate);
					ps.setString(7, "SFR");//TODO: remove the hardcoded value based on the UID
					ps.setString(8, null);
					ps.setString(9, null);
					ps.setString(10, null);
					ps.setString(11, null);
					ps.setString(12, "*");
					ps.setLong(13, reimbursementAssignmentDTO.getAssignmentId());
					ps.setString(14, "Primary");
					ps.setLong(15,reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getSlId());
					ps.setLong(16, 0L);					
					ps.setString(17, "*");
					ps.setString(18, "I");
					ps.setLong(19, reimbursementAssignmentDTO.getId());
					results.add(reimbursementAssignmentDTO);
				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return results;
	}
	
	private boolean insertCHDSLEVELSL(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {		
		try {
			jdbcTemplate.batchUpdate(QUERIES_INSERT_CHDS_LEVEL_SL, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					AssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);					
					java.sql.Date allocationDate = new java.sql.Date(new Date().getTime());
					java.sql.Date dueDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());					
					ps.setLong(1, reimbursementAssignmentDTO.getId());
					ps.setString(2, reimbursementAssignmentDTO.getPolicyDetailsDTO().getRiskId());
					ps.setString(3, reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getUserId());
					ps.setString(4, reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getUserGroupId());
					ps.setString(5,  "O");
					ps.setDate(6, allocationDate);
					ps.setDate(7, dueDate);
					ps.setString(8, "SFR");//TODO: remove the hardcoded value based on the UID
					ps.setString(9, null);
					ps.setString(10, null);
					ps.setString(11, null);
					ps.setString(12, null);
					ps.setString(13, "*");
					ps.setLong(14, reimbursementAssignmentDTO.getAssignmentId());
					ps.setString(15, "Primary");
					ps.setLong(16,reimbursementAssignmentDTO.getAssignedUserDetailsDTO().getSlId());
					ps.setLong(17, 0L);
					ps.setString(18, reimbursementAssignmentDTO.getCreatedBy());
					ps.setDate(19, createdDate);
					ps.setString(20, "*");
				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}

	public List<AssignmentDTO> insertReimbursementAssignmentDetails(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		insertCTDSLEVELC(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		insertCTDSLEVELCP(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		List<AssignmentDTO> results = insertCTDSLEVELSL(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		//insertCHDSLEVELSL(compId, results, jdbcTemplate);
		return reimbursementAssignmentDTOs;
	}
	
	public List<AssignmentDTO> updateReimbursementAssignmentDetails(String compId,List<AssignmentDTO> reimbursementAssignmentDTOs) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		updateCTDSLEVELC(compId, reimbursementAssignmentDTOs, jdbcTemplate);
//		updateCTDSLEVELCP(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		List<AssignmentDTO> results = updateCTDSLEVELSL(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		//insertCHDSLEVELSL(compId, results, jdbcTemplate);
		return reimbursementAssignmentDTOs;
	}

	
}
