package com.beyon.medical.claims.reimbursement.dao;

import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.*;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_USER_DIVISION;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.*;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.CLAIM_MOD_TYPE;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.CLAIM_REPORTED_BY_INSURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DELETE_TDS_LEVEL_D;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_C;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_CP;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_CP_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_FNOL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MC;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MDIAG;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MFNOL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MR;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MSRVC;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MSRVC_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_R;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_SL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_SL_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_TDS_LEVEL_D;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MFNOL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_MSRVC;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MSRVC;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DELETE_TDS_LEVEL_D;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MC;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MDIAG;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DETAILS_CTDS_LEVEL_MSRVC_VERSION;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_SL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_SL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_C;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_CP;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
import com.beyon.medical.claims.general.dto.DiagnosisDTO;
import com.beyon.medical.claims.reimbursement.dto.RegistrationFileDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementAssignmentDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementEstimateDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingServiceDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.reimbursement.mapper.ReimbAssignmentMapper;
import com.beyon.medical.claims.reimbursement.mapper.ReimbProcessingMapper;
import com.beyon.medical.claims.reimbursement.mapper.ReimbRegistrationMapper;
import com.beyon.medical.claims.reimbursement.mapper.ReimbursementEstimationMapper;
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
	
	private boolean updateCTDSLEVELC(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_C, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementAssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
					java.sql.Date claimIntimationDate = toSQLDate(reimbursementAssignmentDTO.getReqReceivedDate());
					java.sql.Date claimLossDate = toSQLDate(reimbursementAssignmentDTO.getServiceFmDate());			
					
					ps.setString(1, reimbursementAssignmentDTO.getClaimNumber());
					ps.setString(2, reimbursementAssignmentDTO.getClaimRefNo());
					ps.setDate(3, claimIntimationDate);
					ps.setDate(4, claimLossDate);
					ps.setString(5, reimbursementAssignmentDTO.getDescription());
					ps.setString(6, reimbursementAssignmentDTO.getPolicyNumber());
					ps.setString(7, reimbursementAssignmentDTO.getStatus());
					ps.setString(8, reimbursementAssignmentDTO.getCustomerId());
					ps.setString(9, reimbursementAssignmentDTO.getProductId());										
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
	
	private boolean updateCTDSLEVELCP(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_CP, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementAssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);					
					java.sql.Date claimIntimationDate = toSQLDate(reimbursementAssignmentDTO.getReqReceivedDate());
					java.sql.Date claimLossDate = toSQLDate(reimbursementAssignmentDTO.getServiceFmDate());					
					
					ps.setString(1, reimbursementAssignmentDTO.getFirstName());
					ps.setString(2, reimbursementAssignmentDTO.getMiddleName());
					ps.setString(3, reimbursementAssignmentDTO.getLastName());
					ps.setString(4, reimbursementAssignmentDTO.getAddress1());
					ps.setString(5, reimbursementAssignmentDTO.getAddress2());
					ps.setString(6, reimbursementAssignmentDTO.getAddress3());
					ps.setString(7, reimbursementAssignmentDTO.getAddress4());
					ps.setString(8, reimbursementAssignmentDTO.getPincode());
					ps.setString(9, reimbursementAssignmentDTO.getState());
					ps.setString(10, reimbursementAssignmentDTO.getCity());
					ps.setString(11, reimbursementAssignmentDTO.getCountry());
					ps.setString(12, reimbursementAssignmentDTO.getPrimaryPhoneNo());
					ps.setString(13, reimbursementAssignmentDTO.getMobileNum1());
					ps.setString(14, reimbursementAssignmentDTO.getAssignmentId()+"");
					ps.setString(15,null);
					ps.setString(16, reimbursementAssignmentDTO.getEmail1());
					ps.setString(17, reimbursementAssignmentDTO.getMemberName());
					ps.setString(18, null);
					ps.setString(19, reimbursementAssignmentDTO.getGender());					
					ps.setString(20, "I");					
					ps.setString(21, reimbursementAssignmentDTO.getRelationWithPrimary());
					ps.setString(22, reimbursementAssignmentDTO.getNationalId());
					
					ps.setLong(23, reimbursementAssignmentDTO.getId());


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
	
	private List<ReimbursementAssignmentDTO> insertCTDSLEVELSL(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		List<ReimbursementAssignmentDTO> results = new ArrayList<>();
		try {
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_SL, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementAssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);
					Long slId = getSequenceNo(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_SL_SEQUENCE_NAME);
					if(reimbursementAssignmentDTO.getAssignedUserDetailsDTO() != null)
						reimbursementAssignmentDTO.getAssignedUserDetailsDTO().setSlId(slId);					
					java.sql.Date allocationDate = new java.sql.Date(new Date().getTime());
					java.sql.Date dueDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
					
					ps.setLong(1, reimbursementAssignmentDTO.getId());
					ps.setString(2, reimbursementAssignmentDTO.getRiskId());
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
			writeLog(CLASS_NAME, "Exception occured while executing insertCTDSLEVELSL", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return results;
	}
	
	private List<ReimbursementAssignmentDTO> updateCTDSLEVELSL(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {
		List<ReimbursementAssignmentDTO> results = new ArrayList<>();
		try {
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_SL, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementAssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);										
					java.sql.Date allocationDate = new java.sql.Date(new Date().getTime());
					java.sql.Date dueDate = new java.sql.Date(new Date().getTime());		
					
					ps.setString(1, reimbursementAssignmentDTO.getRiskId());
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
			writeLog(CLASS_NAME, "Exception occured while executing insertCTDSLEVELSL", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return results;
	}
	
	private boolean insertCHDSLEVELSL(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs,JdbcTemplate jdbcTemplate) throws DAOException {		
		try {
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_SL, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementAssignmentDTO reimbursementAssignmentDTO = reimbursementAssignmentDTOs.get(i);					
					java.sql.Date allocationDate = new java.sql.Date(new Date().getTime());
					java.sql.Date dueDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());					
					ps.setLong(1, reimbursementAssignmentDTO.getId());
					ps.setString(2, reimbursementAssignmentDTO.getRiskId());
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
			writeLog(CLASS_NAME, "Exception occured while executing insertCHDSLEVELSL", ERROR, e);
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
		List<ReimbursementAssignmentDTO> results = insertCTDSLEVELSL(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		//insertCHDSLEVELSL(compId, results, jdbcTemplate);
		updateCTDSLEVELFNOL(results.get(0).getId(), results.get(0).getClaimNumber(), jdbcTemplate);

		return reimbursementAssignmentDTOs;
	}
	
	public List<ReimbursementAssignmentDTO> updateReimbursementAssignmentDetails(String compId,List<ReimbursementAssignmentDTO> reimbursementAssignmentDTOs) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		updateCTDSLEVELC(compId, reimbursementAssignmentDTOs, jdbcTemplate);
//		updateCTDSLEVELCP(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		List<ReimbursementAssignmentDTO> results = updateCTDSLEVELSL(compId, reimbursementAssignmentDTOs, jdbcTemplate);
		//insertCHDSLEVELSL(compId, results, jdbcTemplate);
		return reimbursementAssignmentDTOs;
	}

	private ReimbursementProcessingDTO insertCTDSLEVELMSRVC(String compId,ReimbursementProcessingDTO reimbursementProcessingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		List<ReimbursementProcessingServiceDTO> reimbursementProcessingServiceResultDTOs  = new ArrayList<>();
		try {
			List<ReimbursementProcessingServiceDTO> reimbursementProcessingServiceDTOs  = reimbursementProcessingDTO.getProcessingServiceDTOs();			
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MSRVC, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementProcessingServiceDTO processingServiceDTO = reimbursementProcessingServiceDTOs.get(i);
					Long clcpId = getSequenceNo(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MSRVC_SEQUENCE_NAME);
					processingServiceDTO.setReimbursementProcessId(clcpId);
					java.sql.Date treatmentFromDate = toSQLDate(processingServiceDTO.getTreatmentFromDate());
					java.sql.Date treatmentToDate = toSQLDate(processingServiceDTO.getTreatmentToDate());
					java.sql.Date statusDate = toSQLDate(processingServiceDTO.getStatusDate());
					java.sql.Date approvedDate = toSQLDate(processingServiceDTO.getApprovedDate());
					java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
					
					ps.setLong(1, processingServiceDTO.getReimbursementProcessId());
					ps.setLong(2, reimbursementProcessingDTO.getId());
					ps.setLong(3, 0);
					ps.setDate(4, treatmentFromDate);
					ps.setDate(5, treatmentToDate);
					ps.setLong(6, processingServiceDTO.getNoOfTreamentDays());
					ps.setString(7, processingServiceDTO.getServiceType());
					ps.setString(8, processingServiceDTO.getServiceId());
					ps.setString(9, processingServiceDTO.getBenefitId());
					ps.setString(10, processingServiceDTO.getSubBenefitId());
					ps.setString(11, processingServiceDTO.getCurrencyId());
					ps.setBigDecimal(12, processingServiceDTO.getRequestedAmount());
					ps.setBigDecimal(13, processingServiceDTO.getRequestedAmountBC());
					ps.setBigDecimal(14, null);
					ps.setBigDecimal(15, null);
					ps.setBigDecimal(16, processingServiceDTO.getManualDeductionAmount());
					ps.setBigDecimal(17, processingServiceDTO.getManualDeductionAmountBC());
					ps.setBigDecimal(18, processingServiceDTO.getPenaltyAmount());
					ps.setBigDecimal(19, processingServiceDTO.getPenaltyAmountBC());
					ps.setBigDecimal(20, processingServiceDTO.getSuggestedAmout());
					ps.setBigDecimal(21, processingServiceDTO.getSuggestedAmoutBC());
					ps.setBigDecimal(22, processingServiceDTO.getApprovedAmount());
					ps.setBigDecimal(23, processingServiceDTO.getApprovedAmountBC());
					ps.setString(24, processingServiceDTO.getInternalRejectionCode());
					ps.setBigDecimal(25, processingServiceDTO.getRejectedAmount());
					ps.setBigDecimal(26, processingServiceDTO.getRejectedAmountBC());
					ps.setString(27, processingServiceDTO.getClaimStatus());
					ps.setDate(28, statusDate);
					ps.setString(29, processingServiceDTO.getInternalRemarks());
					ps.setString(30, processingServiceDTO.getExternalRemarks());
					ps.setString(31, processingServiceDTO.getLossType());
					ps.setString(32, processingServiceDTO.getEstType());
					ps.setString(33, processingServiceDTO.getBenefitId());
					ps.setString(34, processingServiceDTO.getSubBenefitId());
					ps.setString(35, reimbursementProcessingDTO.getPrimaryDiagnosis().getDiagId());
					ps.setString(36, processingServiceDTO.getCreatedBy());
					ps.setDate(37, createdDate);
					ps.setString(38, processingServiceDTO.getUpdatedBy());
					ps.setDate(39, updatedDate);
					ps.setString(40, processingServiceDTO.getApprovedBy());
					ps.setDate(41, approvedDate);
					ps.setString(42, processingServiceDTO.getCurrencyType());
					reimbursementProcessingServiceResultDTOs.add(processingServiceDTO);
				}
				@Override
				public int getBatchSize() {
					return reimbursementProcessingServiceDTOs.size();
				}
			});
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing insertCTDSLEVELMSRVC", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		reimbursementProcessingDTO.setProcessingServiceDTOs(reimbursementProcessingServiceResultDTOs);
		return reimbursementProcessingDTO;
	}
	
	private boolean insertCHDSLEVELMSRVC(String compId,ReimbursementProcessingDTO reimbursementProcessingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			List<ReimbursementProcessingServiceDTO> reimbursementProcessingServiceDTOs  = reimbursementProcessingDTO.getProcessingServiceDTOs();
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_MSRVC, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementProcessingServiceDTO processingServiceDTO = reimbursementProcessingServiceDTOs.get(i);					
					java.sql.Date treatmentFromDate = toSQLDate(processingServiceDTO.getTreatmentFromDate());
					java.sql.Date treatmentToDate = toSQLDate(processingServiceDTO.getTreatmentToDate());
					java.sql.Date statusDate = toSQLDate(processingServiceDTO.getStatusDate());
					java.sql.Date approvedDate = toSQLDate(processingServiceDTO.getApprovedDate());
					java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
					
					ps.setLong(1, processingServiceDTO.getReimbursementProcessId());
					ps.setLong(2, reimbursementProcessingDTO.getId());
					ps.setLong(3, 0);
					ps.setDate(4, treatmentFromDate);
					ps.setDate(5, treatmentToDate);
					ps.setLong(6, processingServiceDTO.getNoOfTreamentDays());
					ps.setString(7, processingServiceDTO.getServiceType());
					ps.setString(8, processingServiceDTO.getServiceId());
					ps.setString(9, processingServiceDTO.getBenefitId());
					ps.setString(10, processingServiceDTO.getSubBenefitId());
					ps.setString(11, processingServiceDTO.getCurrencyId());
					ps.setBigDecimal(12, processingServiceDTO.getRequestedAmount());
					ps.setBigDecimal(13, processingServiceDTO.getRequestedAmountBC());
					ps.setBigDecimal(14, null);
					ps.setBigDecimal(15, null);
					ps.setBigDecimal(16, processingServiceDTO.getManualDeductionAmount());
					ps.setBigDecimal(17, processingServiceDTO.getManualDeductionAmountBC());
					ps.setBigDecimal(18, processingServiceDTO.getPenaltyAmount());
					ps.setBigDecimal(19, processingServiceDTO.getPenaltyAmountBC());
					ps.setBigDecimal(20, processingServiceDTO.getSuggestedAmout());
					ps.setBigDecimal(21, processingServiceDTO.getSuggestedAmoutBC());
					ps.setBigDecimal(22, processingServiceDTO.getApprovedAmount());
					ps.setBigDecimal(23, processingServiceDTO.getApprovedAmountBC());
					ps.setString(24, processingServiceDTO.getInternalRejectionCode());
					ps.setBigDecimal(25, processingServiceDTO.getRejectedAmount());
					ps.setBigDecimal(26, processingServiceDTO.getRejectedAmountBC());
					ps.setString(27, processingServiceDTO.getClaimStatus());
					ps.setDate(28, statusDate);
					ps.setString(29, processingServiceDTO.getInternalRemarks());
					ps.setString(30, processingServiceDTO.getExternalRemarks());
					ps.setString(31, processingServiceDTO.getLossType());
					ps.setString(32, processingServiceDTO.getEstType());
					ps.setString(33, "");
					ps.setString(34, "");
					ps.setString(35, "");
					ps.setString(36, processingServiceDTO.getCreatedBy());
					ps.setDate(37, createdDate);
					ps.setString(38, processingServiceDTO.getUpdatedBy());
					ps.setDate(39, updatedDate);
					ps.setString(40, processingServiceDTO.getApprovedBy());
					ps.setDate(41, approvedDate);
					ps.setString(42, processingServiceDTO.getCurrencyType());
				}
				@Override
				public int getBatchSize() {
					return reimbursementProcessingServiceDTOs.size();
				}
			});
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing insertCTDSLEVELMSRVC", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}	

	private boolean insertCTDSLEVELMC(String compId,ReimbursementProcessingDTO reimbursementProcessingDTO,JdbcTemplate jdbcTemplate) throws DAOException {

		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MC,
				new Object[] { reimbursementProcessingDTO.getId(), 
						reimbursementProcessingDTO.getClaimNumber(), 
						reimbursementProcessingDTO.getRequestNumber(), 
						reimbursementProcessingDTO.getClaimType(),
						reimbursementProcessingDTO.getEventCountry(), 
						reimbursementProcessingDTO.getClaimCondition(),
						reimbursementProcessingDTO.getClaimStatusReason()
		});

		return true;
	}	

	private boolean insertCTDSLEVELMDIAG(String compId,Long sgsId,DiagnosisDTO diagnosisDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MDIAG,
				new Object[] { sgsId, 
						diagnosisDTO.getDiagId(), 
						diagnosisDTO.getDiagType(), 
						diagnosisDTO.getCreatedBy(),
						createdDate, 
						diagnosisDTO.getUpdatedBy(),
						updatedDate
		});
		return true;
	}
	
	private ReimbursementProcessingDTO updateCTDSLEVELMSRVC(String compId, ReimbursementProcessingDTO reimbursementProcessingDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		List<ReimbursementProcessingServiceDTO> reimbursementProcessingServiceResultDTOs  = new ArrayList<>();
		try {
			List<ReimbursementProcessingServiceDTO> reimbursementProcessingServiceDTOs  = reimbursementProcessingDTO.getProcessingServiceDTOs();			
			jdbcTemplate.batchUpdate(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MSRVC, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ReimbursementProcessingServiceDTO processingServiceDTO = reimbursementProcessingServiceDTOs.get(i);
					Long version = getLatestVersion(processingServiceDTO.getReimbursementProcessId(), jdbcTemplate);
					processingServiceDTO.setClaimsSequenceNo(version+1);
					java.sql.Date treatmentFromDate = toSQLDate(processingServiceDTO.getTreatmentFromDate());
					java.sql.Date treatmentToDate = toSQLDate(processingServiceDTO.getTreatmentToDate());
					java.sql.Date statusDate = toSQLDate(processingServiceDTO.getStatusDate());
					java.sql.Date approvedDate = toSQLDate(processingServiceDTO.getApprovedDate());
					java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());					
					
					ps.setLong(1, reimbursementProcessingDTO.getId());					
					ps.setLong(2, processingServiceDTO.getClaimsSequenceNo());
					ps.setDate(3, treatmentFromDate);
					ps.setDate(4, treatmentToDate);
					ps.setLong(5, processingServiceDTO.getNoOfTreamentDays());
					ps.setString(6, processingServiceDTO.getServiceType());
					ps.setString(7, processingServiceDTO.getServiceId());
					ps.setString(8, processingServiceDTO.getBenefitId());
					ps.setString(9, processingServiceDTO.getSubBenefitId());
					ps.setString(10, processingServiceDTO.getCurrencyId());
					ps.setBigDecimal(11, processingServiceDTO.getRequestedAmount());
					ps.setBigDecimal(12, processingServiceDTO.getRequestedAmountBC());
					ps.setBigDecimal(13, null);
					ps.setBigDecimal(14, null);
					ps.setBigDecimal(15, processingServiceDTO.getManualDeductionAmount());
					ps.setBigDecimal(16, processingServiceDTO.getManualDeductionAmountBC());
					ps.setBigDecimal(17, processingServiceDTO.getPenaltyAmount());
					ps.setBigDecimal(18, processingServiceDTO.getPenaltyAmountBC());
					ps.setBigDecimal(19, processingServiceDTO.getSuggestedAmout());
					ps.setBigDecimal(20, processingServiceDTO.getSuggestedAmoutBC());
					ps.setBigDecimal(21, processingServiceDTO.getApprovedAmount());
					ps.setBigDecimal(22, processingServiceDTO.getApprovedAmountBC());
					ps.setString(23, processingServiceDTO.getInternalRejectionCode());
					ps.setBigDecimal(24, processingServiceDTO.getRejectedAmount());
					ps.setBigDecimal(25, processingServiceDTO.getRejectedAmountBC());
					ps.setString(26, processingServiceDTO.getClaimStatus());
					ps.setDate(27, statusDate);
					ps.setString(28, processingServiceDTO.getInternalRemarks());
					ps.setString(39, processingServiceDTO.getExternalRemarks());
					ps.setString(30, processingServiceDTO.getLossType());
					ps.setString(31, processingServiceDTO.getEstType());
					ps.setString(32, "");
					ps.setString(33, "");
					ps.setString(34, "");
					ps.setString(35, processingServiceDTO.getCreatedBy());					
					ps.setString(36, processingServiceDTO.getUpdatedBy());
					ps.setDate(37, updatedDate);
					ps.setString(38, processingServiceDTO.getApprovedBy());
					ps.setDate(39, approvedDate);
					ps.setString(40, processingServiceDTO.getCurrencyType());
					ps.setLong(41, processingServiceDTO.getReimbursementProcessId());
					reimbursementProcessingServiceResultDTOs.add(processingServiceDTO);
				}
				@Override
				public int getBatchSize() {
					return reimbursementProcessingServiceDTOs.size();
				}
			});
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing insertCTDSLEVELMSRVC", ERROR, e);
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		reimbursementProcessingDTO.setProcessingServiceDTOs(reimbursementProcessingServiceResultDTOs);
		return reimbursementProcessingDTO;
	}
	
	private boolean updateCTDSLEVELMC(String compId,ReimbursementProcessingDTO reimbursementProcessingDTO,JdbcTemplate jdbcTemplate) throws DAOException {

		jdbcTemplate.update(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MC,
				new Object[] {  
						reimbursementProcessingDTO.getClaimNumber(), 
						reimbursementProcessingDTO.getRequestNumber(), 
						reimbursementProcessingDTO.getClaimType(),
						reimbursementProcessingDTO.getEventCountry(), 
						reimbursementProcessingDTO.getClaimCondition(),
						reimbursementProcessingDTO.getClaimStatusReason(),
						reimbursementProcessingDTO.getId()
		});

		return true;
	}
	
	
	private boolean updateCTDSLEVELFNOL(Long sgsId,String claimNumber,JdbcTemplate jdbcTemplate) throws DAOException {

		jdbcTemplate.update(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_FNOL_FOR_CLAIM_NUMBER,
				new Object[] {  
						claimNumber,
						sgsId
						
		});

		return true;
	}
	
	private boolean updateCTDSLEVELMDIAG(String compId,Long sgsId,DiagnosisDTO diagnosisDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());		
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MDIAG,
				new Object[] {  
						diagnosisDTO.getDiagId(), 
						diagnosisDTO.getDiagType(),						
						diagnosisDTO.getUpdatedBy(),
						updatedDate,
						sgsId
		});
		return true;
	}
	
	private boolean insertCTDSLEVELE(String compId,ReimbursementProcessingDTO reimbursementProcessingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		java.sql.Date approvedDate = toSQLDate(reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedDate());

		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_E,
				new Object[] { reimbursementProcessingDTO.getServiceGroupId(), 
						reimbursementProcessingDTO.getId(), 
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getClaimsSequenceNo(), 
						reimbursementProcessingDTO.getRiskId(),
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getBenefitId(), 
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getSubBenefitId(),
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getSubBenefitId(),						
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getEstType(), 
						createdDate, 
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedAmount(), 
						reimbursementProcessingDTO.getCustomerId(),
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getCreatedBy(), 
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedAmount(),
						null,
						"P",
						reimbursementProcessingDTO.getRiskCurrentExchangeRate(),
						reimbursementProcessingDTO.getRiskCurrencyId(),
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedAmountBC(),
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedAmountBC(),
						"1",
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedBy(),
						approvedDate,
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getUpdatedBy(),
						createdDate,
						compId						
		});

		return true;
	}
	
	private boolean insertCHDSLEVELE(String compId, ReimbursementProcessingDTO reimbursementProcessingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		java.sql.Date approvedDate = toSQLDate(reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedDate());
		ReimbursementProcessingServiceDTO reimbursementProcessingServiceDTO = reimbursementProcessingDTO.getProcessingServiceDTOs().get(0);
		
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_E,
				new Object[] { reimbursementProcessingDTO.getServiceGroupId(), 
						reimbursementProcessingDTO.getId(), 
						reimbursementProcessingServiceDTO.getClaimsSequenceNo(), 
						reimbursementProcessingDTO.getRiskId(),
						reimbursementProcessingServiceDTO.getBenefitId(), 
						reimbursementProcessingServiceDTO.getSubBenefitId(),
						reimbursementProcessingServiceDTO.getSubBenefitId(),						
						reimbursementProcessingServiceDTO.getEstType(), 
						createdDate,
						reimbursementProcessingServiceDTO.getApprovedAmount(),
						reimbursementProcessingServiceDTO.getApprovedAmount(), 
						reimbursementProcessingDTO.getCustomerId(),
						reimbursementProcessingServiceDTO.getCreatedBy(), 
						reimbursementProcessingServiceDTO.getApprovedAmount(),
						null,
						"P",
						reimbursementProcessingServiceDTO.getApprovedAmount(),
						reimbursementProcessingDTO.getRiskCurrencyId(),
						reimbursementProcessingDTO.getRiskCurrentExchangeRate(),
						reimbursementProcessingServiceDTO.getApprovedAmountBC(),
						reimbursementProcessingServiceDTO.getApprovedAmountBC(),
						reimbursementProcessingServiceDTO.getApprovedBy(),
						approvedDate,
						createdDate,
						reimbursementProcessingServiceDTO.getUpdatedBy(),
						"1",
						compId						
		});

		return true;
	}
	
	private boolean insertCHDSLEVELE(String compId, ReimbursementEstimateDTO reimbursementEstimateDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());		
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_E,
				new Object[] {  
						reimbursementEstimateDTO.getId(),
						reimbursementEstimateDTO.getClaimsId(),
						reimbursementEstimateDTO.getVersion(),
						reimbursementEstimateDTO.getRiskId(),
						reimbursementEstimateDTO.getBenefitId(), 
						reimbursementEstimateDTO.getSubBenefitId(),
						reimbursementEstimateDTO.getLossDescription(),						
						reimbursementEstimateDTO.getEstimateType(), 
						toSQLDate(reimbursementEstimateDTO.getEstimateDate()),
						reimbursementEstimateDTO.getRevisedEstimatedAmt(),
						reimbursementEstimateDTO.getOldEstimatedAmt(),
						null,
						reimbursementEstimateDTO.getCreatedBy(), 
						reimbursementEstimateDTO.getReservedOutstandingAmt(),
						null,
						"P",
						reimbursementEstimateDTO.getReservedOutstandingAmtBC(),
						null,
						reimbursementEstimateDTO.getExchangeRate(),
						reimbursementEstimateDTO.getRevisedEstimatedAmtBC(),
						reimbursementEstimateDTO.getOldEstimatedAmtBC(),
						reimbursementEstimateDTO.getApprovedBy(),
						toSQLDate(reimbursementEstimateDTO.getApprovedDate()),
						toSQLDate(reimbursementEstimateDTO.getUpdatedDate()),
						reimbursementEstimateDTO.getUpdatedBy(),												
						"1",						
						compId						
		});

		return true;
	}
	
	private boolean updateCTDSLEVELE(String compId, ReimbursementEstimateDTO reimbursementEstimateDTO,
			ReimbursementProcessingDTO reimbursementProcessingDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		java.sql.Date approvedDate = toSQLDate(reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getApprovedDate());
		ReimbursementProcessingServiceDTO reimbursementProcessingServiceDTO = reimbursementProcessingDTO.getProcessingServiceDTOs().get(0);
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_E,
				new Object[] {
						reimbursementEstimateDTO.getReservedOutstandingAmt(),
						reimbursementEstimateDTO.getReservedOutstandingAmtBC(),
						reimbursementEstimateDTO.getEstimatedAmt(),
						reimbursementEstimateDTO.getEstimatedAmtBC(),
						reimbursementProcessingServiceDTO.getApprovedBy(),
						toSQLDate(reimbursementProcessingServiceDTO.getApprovedDate()),
						toSQLDate(reimbursementProcessingServiceDTO.getUpdatedDate()),
						reimbursementProcessingServiceDTO.getUpdatedBy(),
						reimbursementEstimateDTO.getVersion()+1,
						reimbursementProcessingServiceDTO.getClaimsRegistrationId(),
						reimbursementProcessingDTO.getRiskId(),
						reimbursementProcessingServiceDTO.getBenefitId(),
						reimbursementProcessingServiceDTO.getSubBenefitId()
		});

		return true;
	}
	
	public ReimbursementProcessingDTO insertReimbursementProcessingDetails(String compId,ReimbursementProcessingDTO processingDTO) throws DAOException {
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		ReimbursementProcessingDTO reimbursementProcessingDTO = insertCTDSLEVELMSRVC(compId, processingDTO, jdbcTemplate);
		insertCHDSLEVELMSRVC(compId, reimbursementProcessingDTO, jdbcTemplate);
		insertCTDSLEVELMC(compId, processingDTO, jdbcTemplate);
		insertCTDSLEVELMDIAG(compId, processingDTO.getId(), processingDTO.getPrimaryDiagnosis(), jdbcTemplate);
		insertCTDSLEVELMDIAG(compId, processingDTO.getId(), processingDTO.getSecondaryDiagnosis(), jdbcTemplate);
		return processingDTO;
	}
	
	public ReimbursementProcessingDTO updateReimbursementProcessingDetails(String compId,ReimbursementProcessingDTO processingDTO) throws DAOException {
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		ReimbursementProcessingDTO result = updateCTDSLEVELMSRVC(compId, processingDTO, jdbcTemplate);
		insertCHDSLEVELMSRVC(compId, result, jdbcTemplate);
		updateCTDSLEVELMC(compId, processingDTO, jdbcTemplate);
		updateCTDSLEVELMDIAG(compId, processingDTO.getId(), processingDTO.getPrimaryDiagnosis(), jdbcTemplate);
		updateCTDSLEVELMDIAG(compId, processingDTO.getId(), processingDTO.getSecondaryDiagnosis(), jdbcTemplate);
		return processingDTO;
	}
	
	public List<ReimbursementProcessingDTO> getProcessingDetails(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ReimbursementProcessingDTO>() {
			@Override
			public ReimbursementProcessingDTO mapRow(ResultSet row, int count) throws SQLException {
				return ReimbProcessingMapper.getReimbursementProcessingDTO(row);
			}
		});
	}
	
	private Long getLatestVersion(Long serviceId, JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.queryForObject(REIMBURSEMENT_QUERIES_DETAILS_CTDS_LEVEL_MSRVC_VERSION, 
				new Object[] {serviceId}, Long.class);
	}
	
	private java.sql.Date toSQLDate(LocalDate date) {
		return Optional.ofNullable(date).map(java.sql.Date::valueOf).orElse(null);
	}
	
	private boolean approveCTDSLEVELMSRVC(String compId, ReimbursementProcessingServiceDTO reimbursementProcessingServiceDTO, JdbcTemplate jdbcTemplate) {		
		java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
		Long version = getLatestVersion(reimbursementProcessingServiceDTO.getReimbursementProcessId(), jdbcTemplate);
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_APPROVE_CTDS_LEVEL_MSRVC,
				new Object[] {  
						reimbursementProcessingServiceDTO.getClaimStatus(),
						toSQLDate(reimbursementProcessingServiceDTO.getStatusDate()),
						reimbursementProcessingServiceDTO.getInternalRemarks(),
						reimbursementProcessingServiceDTO.getExternalRemarks(),
						reimbursementProcessingServiceDTO.getUpdatedBy(),
						toSQLDate(reimbursementProcessingServiceDTO.getUpdatedDate()),
						reimbursementProcessingServiceDTO.getApprovedBy(),
						toSQLDate(reimbursementProcessingServiceDTO.getApprovedDate()),
						version+1,
						reimbursementProcessingServiceDTO.getApprovedAmount(),
						reimbursementProcessingServiceDTO.getApprovedAmountBC(),
						reimbursementProcessingServiceDTO.getReimbursementProcessId()
						
		});
		return true;		
	}
	
	private boolean approveCTDSLEVELC(String compId, ReimbursementProcessingServiceDTO reimbursementProcessingServiceDTO, JdbcTemplate jdbcTemplate) {		
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_APPROVE_CTDS_LEVEL_C,
				new Object[] {  
						reimbursementProcessingServiceDTO.getClaimStatus(),						
						reimbursementProcessingServiceDTO.getReimbursementProcessId()
						
		});
		return true;		
	}
	
	private boolean approveCTDSLEVELMC(String compId, ReimbursementProcessingDTO reimbursementProcessingDTO, JdbcTemplate jdbcTemplate) {		
		jdbcTemplate.update(REIMBURSEMENT_QUERIES_APPROVE_CTDS_LEVEL_MC,
				new Object[] {  
						reimbursementProcessingDTO.getClaimStatusReason(),						
						reimbursementProcessingDTO.getProcessingServiceDTOs().get(0).getReimbursementProcessId()
						
		});
		return true;		
	}
	
	public ReimbursementProcessingDTO approveServiceLineItem(String compId,ReimbursementProcessingDTO reimbursementProcessingDTO) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		ReimbursementProcessingServiceDTO reimbursementProcessingServiceDTO = reimbursementProcessingDTO.getProcessingServiceDTOs().get(0);
		ReimbursementEstimateDTO reimbursementEstimateDTO = jdbcTemplate.query(REIMBURSEMENT_QUERIES_CHECK_EXISTENCE_CTDS_LEVEL_E, 
				new Object[] {
						reimbursementProcessingServiceDTO.getClaimsRegistrationId(),
						reimbursementProcessingDTO.getRiskId(),
						reimbursementProcessingServiceDTO.getBenefitId(),
						reimbursementProcessingServiceDTO.getSubBenefitId()
				}, new ResultSetExtractor<ReimbursementEstimateDTO>() {
					@Override
					public ReimbursementEstimateDTO extractData(ResultSet row) throws SQLException {
						if (row.next())
						return ReimbursementEstimationMapper.getReimbursementEstimateDTO(row);						
						else return null;
					}
				});
		if (reimbursementEstimateDTO == null) {
			//AutoGenerated Sequence Id
			Long dtlsSgsId = getSequenceNo(REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_E_SEQUENCE_NAME);
			reimbursementProcessingDTO.setServiceGroupId(dtlsSgsId);
			insertCTDSLEVELE(compId, reimbursementProcessingDTO, jdbcTemplate);
			insertCHDSLEVELE(compId, reimbursementProcessingDTO, jdbcTemplate);
			approveCTDSLEVELMSRVC(compId, reimbursementProcessingServiceDTO, jdbcTemplate);
			approveCTDSLEVELC(compId, reimbursementProcessingServiceDTO, jdbcTemplate);
			approveCTDSLEVELMC(compId, reimbursementProcessingDTO, jdbcTemplate);
			return reimbursementProcessingDTO;
		}
		
		setEstimatedAmt(reimbursementEstimateDTO, reimbursementProcessingDTO);
		//setOutstandingAmt(reimbursementEstimateDTO, reimbursementProcessingDTO);
		updateCTDSLEVELE(compId, reimbursementEstimateDTO, reimbursementProcessingDTO, jdbcTemplate);
		insertCHDSLEVELE(compId, reimbursementEstimateDTO, jdbcTemplate);
		approveCTDSLEVELMSRVC(compId, reimbursementProcessingServiceDTO, jdbcTemplate);
		approveCTDSLEVELC(compId, reimbursementProcessingServiceDTO, jdbcTemplate);
		approveCTDSLEVELMC(compId, reimbursementProcessingDTO, jdbcTemplate);
		return reimbursementProcessingDTO;		
	}
	
	private void setEstimatedAmt(ReimbursementEstimateDTO reimbursementEstimateDTO, ReimbursementProcessingDTO reimbursementProcessingDTO) {
		ReimbursementProcessingServiceDTO reimbursementProcessingServiceDTO = reimbursementProcessingDTO.getProcessingServiceDTOs().get(0);
		BigDecimal oldEstimatedAmt = reimbursementEstimateDTO.getRevisedEstimatedAmt();
		BigDecimal revisedEstimatedAmt = new BigDecimal(0);
		if (oldEstimatedAmt != null) {
			revisedEstimatedAmt = oldEstimatedAmt.add(reimbursementProcessingServiceDTO.getApprovedAmount());
		} else {
			revisedEstimatedAmt = reimbursementProcessingServiceDTO.getApprovedAmount();
		}
		
		BigDecimal oldEstimatedAmtBC = reimbursementEstimateDTO.getRevisedEstimatedAmtBC();
		BigDecimal revisedEstimatedAmtBC = new BigDecimal(0);
		if (oldEstimatedAmtBC != null) {
			revisedEstimatedAmtBC = oldEstimatedAmtBC.add(reimbursementProcessingServiceDTO.getApprovedAmountBC());
		} else {
			revisedEstimatedAmtBC = reimbursementProcessingServiceDTO.getApprovedAmountBC();
		}
		reimbursementEstimateDTO.setOldEstimatedAmt(oldEstimatedAmt);
		reimbursementEstimateDTO.setOldEstimatedAmtBC(oldEstimatedAmtBC);
		reimbursementEstimateDTO.setRevisedEstimatedAmt(revisedEstimatedAmt);
		reimbursementEstimateDTO.setRevisedEstimatedAmtBC(revisedEstimatedAmtBC);
		reimbursementEstimateDTO.setEstimatedAmt(revisedEstimatedAmt);
		reimbursementEstimateDTO.setEstimatedAmtBC(revisedEstimatedAmtBC);
		reimbursementEstimateDTO.setVersion(reimbursementEstimateDTO.getVersion()+1);
		BigDecimal existingOutstandingAmt = reimbursementEstimateDTO.getReservedOutstandingAmt();		
		if (existingOutstandingAmt != null) {
			reimbursementEstimateDTO.setReservedOutstandingAmt(existingOutstandingAmt.subtract(reimbursementEstimateDTO.getRevisedEstimatedAmt()));
		}
		
		BigDecimal existingOutstandingAmtBC = reimbursementEstimateDTO.getReservedOutstandingAmtBC();		
		if (existingOutstandingAmtBC != null) {
			reimbursementEstimateDTO.setReservedOutstandingAmtBC(existingOutstandingAmtBC.subtract(reimbursementEstimateDTO.getRevisedEstimatedAmtBC()));
		}
	}
}
