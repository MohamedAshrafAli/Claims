package com.beyon.medical.claims.reimbursement.dao;

import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_COB_DETAIL;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_PROVIDER_DETAILS;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.CLAIMANT_IS_THE_CUSTOMER;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.CLAIM_MOD_TYPE;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.CLAIM_REPORTED_BY_INSURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_FNOL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MFNOL;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MR;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_R;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_INSERT_TDS_LEVEL_D;

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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.reimbursement.dto.RegistrationFileDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.reimbursement.mapper.ReimbRegistrationMapper;

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
		//ProviderId
		String providerId = getProviderIdDetails(GENERAL_QUERIES_GET_PROVIDER_DETAILS);

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
						reimbursementRegistrationDTO.getProviderId()});

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
					ps.setString(2, fileDTO.getDocTypeDesc());
					ps.setString(3, fileDTO.getDescription());
					ps.setString(4, "Y");
					ps.setDate(5, uploadedDate);
					ps.setString(6, reimbursementRegistrationDTO.getId()+"_"+fileDTO.getDocName());
					ps.setString(7, CLAIM_MOD_TYPE);
					ps.setString(8, reimbursementRegistrationDTO.getClaimRefNo());
					ps.setString(9, null);
					ps.setString(10, reimbursementRegistrationDTO.getCreatedBy());
					ps.setDate(11, uploadedDate);
					ps.setString(12, compId);
					ps.setLong(13, 0);
					ps.setString(14, "I");
				}

				@Override
				public int getBatchSize() {
					return fileDTOs.size();
				}
			});
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing insertTDSLEVELD", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}

}
