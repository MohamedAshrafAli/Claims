package com.beyon.medical.claims.reimbursement.dao;

import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_COB_DETAIL;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_USER_DIVISION;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.CLAIMANT_IS_THE_CUSTOMER;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.CLAIM_MOD_TYPE;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.*;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DELETE_TDS_LEVEL_D;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_FNOL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MFNOL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MR;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_R;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_TDS_LEVEL_D;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MFNOL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
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
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.reimbursement.dto.RegistrationFileDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementAssignmentDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.reimbursement.mapper.ReimbAssignmentMapper;
import com.beyon.medical.claims.reimbursement.mapper.ReimbRegistrationMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Repository("reimbursementClaimsDAOImpl")
@Scope(value="prototype")
public class ReimbursementClaimsDAOImpl extends BaseClaimsDAOImpl {

	private final String CLASS_NAME = ReimbursementClaimsDAOImpl.class.getCanonicalName();

	public List<ReimbursementRegistrationDTO> getRegistrationDetailsById(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ReimbursementRegistrationDTO>() {
			@Override
			public ReimbursementRegistrationDTO mapRow(ResultSet row, int count) throws SQLException {
				return ReimbRegistrationMapper.getReimbursementRegistrationDTOById(row);
			}
		});
	}
	
	public List<RegistrationFileDTO> getRegistrationFileDetailsById(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<RegistrationFileDTO>() {
			@Override
			public RegistrationFileDTO mapRow(ResultSet row, int count) throws SQLException {
				return ReimbRegistrationMapper.getReimbursementRegistrationFileDTO(row);
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
	
	public List<ReimbursementAssignmentDTO> getAssignmentListViewData(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ReimbursementAssignmentDTO>() {
			@Override
			public ReimbursementAssignmentDTO mapRow(ResultSet row, int count) throws SQLException {
				return ReimbAssignmentMapper.getViewReimbursementAssignmentDTO(row);
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
			writeLog(CLASS_NAME, "Exception occured while executing getDataList", ERROR, ex);
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
			writeLog(CLASS_NAME, "Exception occured while executing getClaimsRefNo", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return simpleJdbcCallResult;
	}
	
	public boolean deleteRegistrationDocument(String id, String docType, String docName, String path) throws DAOException {
        boolean isDeleted = false;
        try {
        NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
        MapSqlParameterSource parameters = new MapSqlParameterSource();
	    parameters.addValue("DocTypeId", docType);
	    parameters.addValue("id", id);
	    parameters.addValue("DocName", docName);
	    parameters.addValue("DocPath", path);

	    int affectedRows = namedParameterJdbcTemplate.update(REIMBURSEMENT_QUERIES_DELETE_TDS_LEVEL_D, parameters);
	    
        if(affectedRows>0) {
        	isDeleted = true;
        }
        }catch(Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing deleteRegistrationDocument", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
        return isDeleted;
    }

	public ReimbursementRegistrationDTO updateReimbursementRegistrationDetails(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		isSaved = updateCTDSLEVELMFNOL(compId, reimbursementRegistrationDTO, jdbcTemplate);
		return reimbursementRegistrationDTO;
	}
	
	private boolean updateCTDSLEVELMFNOL(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MFNOL,
				new Object[] { 
						reimbursementRegistrationDTO.getReqType(), 
						reimbursementRegistrationDTO.getEncType(),
						reimbursementRegistrationDTO.getVoucherNumber(), 
						reimbursementRegistrationDTO.getRequestAmt(), 
						reimbursementRegistrationDTO.getRequestAmtBC(),
						reimbursementRegistrationDTO.getPaymentRefNum(), 
						reimbursementRegistrationDTO.getPrevRequest(),
						reimbursementRegistrationDTO.getPaymentWay(),
						reimbursementRegistrationDTO.getId()});

		return true;
	}
	
	public ReimbursementRegistrationDTO insertReimbursementRegistrationDetails(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		//AutoGenerated Sequence Id
		Long dtlsSgsId = getSequenceNo(REIMBURSEMENT_QUERIES_INSERT_SEQUENCE_NAME);
		reimbursementRegistrationDTO.setId(dtlsSgsId);
		//Claim reference Number Procedure
		Map<String, Object> refNoMap = getClaimsRefNo("FN", dtlsSgsId, reimbursementRegistrationDTO.getProductId());
		String claimRefNo = (String)refNoMap.get("P_DOC_NO");
		reimbursementRegistrationDTO.setClaimRefNo(claimRefNo);
		//Classofbusiness for policynumber
		String cobId = getClassOfBusinessForPolicy(GENERAL_QUERIES_GET_COB_DETAIL, reimbursementRegistrationDTO.getPolicyNumber(), compId);
		reimbursementRegistrationDTO.setCobId(cobId);
		
		String userDivId = getUserDivisionForCompany(GENERAL_QUERIES_GET_USER_DIVISION, reimbursementRegistrationDTO.getCreatedBy(), compId);
		reimbursementRegistrationDTO.setUserDivision(userDivId);		
		
		//TODO:Use the boolean correctly.
		insertCTDSLEVELFNOL(compId, reimbursementRegistrationDTO,jdbcTemplate);
		insertCTDSLEVELMFNOL(compId, reimbursementRegistrationDTO, jdbcTemplate);
		insertCTDSLEVELR(compId, reimbursementRegistrationDTO, jdbcTemplate);
		insertCTDSLEVELMR(compId, reimbursementRegistrationDTO, jdbcTemplate);
		return reimbursementRegistrationDTO;
	}

	private boolean insertCTDSLEVELFNOL(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
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
				new Object[] { 	reimbursementRegistrationDTO.getId(),
						compId, 
						reimbursementRegistrationDTO.getPolicyNumber(), 
						reimbursementRegistrationDTO.getCustomerId(),
						reimbursementRegistrationDTO.getClaimRefNo(), 
						reqReceivedDate, 
						serviceFromDate,
						reimbursementRegistrationDTO.getDescription(),
						CLAIMANT_IS_THE_CUSTOMER, 
						CLAIM_REPORTED_BY_INSURED, 
						reimbursementRegistrationDTO.getStatus(),
						reimbursementRegistrationDTO.getProductId(),
						reimbursementRegistrationDTO.getCobId(), 
						policyFromDate, 
						policyToDate,
						reimbursementRegistrationDTO.getCreatedBy(), 
						createdDate,
						reimbursementRegistrationDTO.getReportedBy(),
						reimbursementRegistrationDTO.getReportedByDesc(), 
						CLAIM_REPORTED_BY_INSURED, 
						"",
						reimbursementRegistrationDTO.getDivisionId(),
						reimbursementRegistrationDTO.getDeptId(),
						reimbursementRegistrationDTO.getInsuredId(),
						reimbursementRegistrationDTO.getSourceType(),
						reimbursementRegistrationDTO.getUserDivision(),CLAIM_MOD_TYPE});

		return true;
	}

	private boolean insertCTDSLEVELMFNOL(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MFNOL,
				new Object[] { reimbursementRegistrationDTO.getId(), 
						reimbursementRegistrationDTO.getProviderId(), 
						reimbursementRegistrationDTO.getReqType(), 
						reimbursementRegistrationDTO.getEncType(),
						reimbursementRegistrationDTO.getVoucherNumber(), 
						reimbursementRegistrationDTO.getRequestAmt(), 
						reimbursementRegistrationDTO.getRequestAmtBC(),
						reimbursementRegistrationDTO.getBaseCurrency(), 
						reimbursementRegistrationDTO.getPaymentRefNum(), 
						reimbursementRegistrationDTO.getPrevRequest(),
						reimbursementRegistrationDTO.getProviderId(),
						reimbursementRegistrationDTO.getPaymentWay()});

		return true;
	}

	private boolean insertCTDSLEVELR(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date riskFromDate = null;
		java.sql.Date riskToDate = null;
		if (reimbursementRegistrationDTO.getRiskFromDate() != null) {
			riskFromDate = java.sql.Date.valueOf(reimbursementRegistrationDTO.getRiskFromDate());
		}
		if (reimbursementRegistrationDTO.getRiskToDate() != null) {
			riskToDate = java.sql.Date.valueOf(reimbursementRegistrationDTO.getRiskToDate());
		}

		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_R,
				new Object[] { reimbursementRegistrationDTO.getId(), 
						reimbursementRegistrationDTO.getRiskId(), 
						reimbursementRegistrationDTO.getRiskType(), 
						reimbursementRegistrationDTO.getPolicyNumber(),
						reimbursementRegistrationDTO.getRiskCOB(), 
						riskFromDate,
						riskToDate,
						reimbursementRegistrationDTO.getRiskAmndVerId(),
						reimbursementRegistrationDTO.getRiskFlex1(), 
						reimbursementRegistrationDTO.getRiskFlex2(), 
						reimbursementRegistrationDTO.getRiskFlex3(),
						reimbursementRegistrationDTO.getRiskFlex4(),
						reimbursementRegistrationDTO.getRiskFlex5(), 
						reimbursementRegistrationDTO.getRiskFlex6(), 
						reimbursementRegistrationDTO.getRiskFlex7(),
						reimbursementRegistrationDTO.getRiskFlex8(), 
						reimbursementRegistrationDTO.getRiskFlex9(), 
						reimbursementRegistrationDTO.getRiskFlex10(), 
						reimbursementRegistrationDTO.getRiskCurrentExchangeRate(),
						reimbursementRegistrationDTO.getRiskCurrencyId(), 
						reimbursementRegistrationDTO.getRiskFlexDesc1(), 
						reimbursementRegistrationDTO.getRiskFlexDesc2(),
						reimbursementRegistrationDTO.getRiskFlexDesc3(),
						reimbursementRegistrationDTO.getRiskFlexDesc4(), 
						reimbursementRegistrationDTO.getRiskFlexDesc5(), 
						reimbursementRegistrationDTO.getRiskFlexDesc6(),
						reimbursementRegistrationDTO.getRiskFlexDesc7(), 
						reimbursementRegistrationDTO.getRiskFlexDesc8(), 
						reimbursementRegistrationDTO.getRiskFlexDesc9(), 
						reimbursementRegistrationDTO.getRiskFlexDesc10(), 
						reimbursementRegistrationDTO.getSourceType(), 
						compId});

		return true;
	}

	private boolean insertCTDSLEVELMR(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date memberDOB = null;
		java.sql.Date cardReceivedDate = null;
		if (reimbursementRegistrationDTO.getMemberDOB() != null) {
			memberDOB = java.sql.Date.valueOf(reimbursementRegistrationDTO.getMemberDOB());
		}
		if (reimbursementRegistrationDTO.getCardReceivedDate() != null) {
			cardReceivedDate = java.sql.Date.valueOf(reimbursementRegistrationDTO.getCardReceivedDate());
		}
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MR,
				new Object[] { reimbursementRegistrationDTO.getId(), 
						reimbursementRegistrationDTO.getRiskId(), 
						reimbursementRegistrationDTO.getMemberNumber(), 
						reimbursementRegistrationDTO.getMemberName(),
						reimbursementRegistrationDTO.getMemberType(), 
						reimbursementRegistrationDTO.getMemberCategory(),
						memberDOB, 
						reimbursementRegistrationDTO.getIsDependent(), 
						reimbursementRegistrationDTO.getParentMemberId(),
						reimbursementRegistrationDTO.getRelationWithPrimary(),
						reimbursementRegistrationDTO.getNationalId(), 
						reimbursementRegistrationDTO.getUidId(), 
						reimbursementRegistrationDTO.getPassportNumber(),
						reimbursementRegistrationDTO.getCardNumber(), 
						cardReceivedDate, 
						reimbursementRegistrationDTO.getMobileNum1(), 
						reimbursementRegistrationDTO.getEmail1(),
						reimbursementRegistrationDTO.getMobileNum2(), 
						reimbursementRegistrationDTO.getEmail2()
		});

		return true;
	}


	public void insertTDSLEVELD(String compId,ReimbursementRegistrationDTO reimbursementRegistrationDTO) throws DAOException {
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			List<RegistrationFileDTO> fileDTOs = reimbursementRegistrationDTO.getRegistrationFileDTOs();
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_INSERT_TDS_LEVEL_D, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					RegistrationFileDTO fileDTO = fileDTOs.get(i);
					java.sql.Date uploadedDate = new java.sql.Date(new Date().getTime());
					ps.setLong(1, reimbursementRegistrationDTO.getId());
					ps.setString(2, fileDTO.getDocTypeId());
					ps.setString(3, fileDTO.getDescription());
					ps.setString(4, "Y");
					ps.setDate(5, uploadedDate);
					ps.setString(6, fileDTO.getDocName());
					ps.setString(7, CLAIM_MOD_TYPE);
					ps.setString(8, reimbursementRegistrationDTO.getClaimRefNo());
					ps.setString(9, null);
					ps.setString(10, reimbursementRegistrationDTO.getCreatedBy());
					ps.setDate(11, uploadedDate);
					ps.setString(12, compId);
					ps.setLong(13, 0);
					ps.setString(14, "I");
					ps.setString(15, fileDTO.getDocTypeDesc());
					ps.setString(16, fileDTO.getDocPath());
					ps.setString(17, fileDTO.getDocContentType());
				}
				@Override
				public int getBatchSize() {
					return fileDTOs.size();
				}
			});
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing insertTDSLEVELD", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}
	
	private boolean insertCTDSLEVELC(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_C, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementAssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
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
					ps.setString(7, reimbursementAssignmentDTO.getPolicyNumber());
					ps.setString(8, reimbursementAssignmentDTO.getStatus());
					ps.setString(9, reimbursementAssignmentDTO.getCustomerId());
					ps.setString(10, reimbursementAssignmentDTO.getProductId());
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
			writeLog(CLASS_NAME, "Exception occured while executing insertTDSLEVELD", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}
	
	private boolean insertCTDSLEVELCP(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_CP, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementAssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
					Long clcpId = getSequenceNo(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_CP_SEQUENCE_NAME);
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
					ps.setString(2, reimbursementAssignmentDTO.getFirstName());
					ps.setString(3, reimbursementAssignmentDTO.getMiddleName());
					ps.setString(4, reimbursementAssignmentDTO.getLastName());
					ps.setString(5, reimbursementAssignmentDTO.getAddress1());
					ps.setString(6, reimbursementAssignmentDTO.getAddress2());
					ps.setString(7, reimbursementAssignmentDTO.getAddress3());
					ps.setString(8, reimbursementAssignmentDTO.getAddress4());
					ps.setString(9, reimbursementAssignmentDTO.getPincode());
					ps.setString(10, reimbursementAssignmentDTO.getState());
					ps.setString(11, reimbursementAssignmentDTO.getCity());
					ps.setString(12, reimbursementAssignmentDTO.getCountry());
					ps.setString(13, reimbursementAssignmentDTO.getPrimaryPhoneNo());
					ps.setString(14, reimbursementAssignmentDTO.getMobileNum1());
					ps.setString(15, reimbursementAssignmentDTO.getAssignmentId()+"");
					ps.setString(16,null);
					ps.setString(17, reimbursementAssignmentDTO.getEmail1());
					ps.setString(18, reimbursementAssignmentDTO.getMemberName());
					ps.setString(19, null);
					ps.setString(20, reimbursementAssignmentDTO.getGender());
					ps.setDate(21, createdDate);
					ps.setString(22, "I");
					ps.setString(23, reimbursementAssignmentDTO.getCreatedBy());
					ps.setString(24, reimbursementAssignmentDTO.getRelationWithPrimary());
					ps.setString(25, reimbursementAssignmentDTO.getNationalId());


				}
				@Override
				public int getBatchSize() {
					return reimbursementAssignmentDTOs.size();
				}
			});
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing insertTDSLEVELD", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}
	
	public List<ReimbursementAssignmentDTO> insertReimbursementAssignmentDetails(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		
		insertCTDSLEVELC(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		insertCTDSLEVELCP(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		return reimbursementAssignmentDTOs;
	}

}
