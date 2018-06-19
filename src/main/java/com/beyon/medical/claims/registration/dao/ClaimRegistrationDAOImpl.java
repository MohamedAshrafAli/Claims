package com.beyon.medical.claims.registration.dao;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_COB_DETAIL;
import static com.beyon.medical.claims.queries.constants.GeneralQueriesConstants.GENERAL_QUERIES_GET_USER_DIVISION;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.CLAIMANT_IS_THE_CUSTOMER;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.CLAIM_MOD_TYPE;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.CLAIM_REPORTED_BY_INSURED;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_DELETE_TDS_LEVEL_D;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_FNOL;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MFNOL;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MR;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_R;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_INSERT_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_INSERT_TDS_LEVEL_D;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_FNOL_FOR_CLAIM_NUMBER;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_MFNOL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.medical.claims.base.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.RegistrationDTO;
import com.beyon.medical.claims.general.dto.RegistrationFileDTO;
import com.beyon.medical.claims.mapper.RegistrationMapper;

@Repository
public class ClaimRegistrationDAOImpl extends BaseClaimsDAOImpl {

	public List<RegistrationDTO> getRegistrationDetailsById(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<RegistrationDTO>() {
			@Override
			public RegistrationDTO mapRow(ResultSet row, int count) throws SQLException {
				return RegistrationMapper.getRegistrationDTOById(row);
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
				return RegistrationMapper.getRegistrationFileDTO(row);
			}
		});
	}

	public List<RegistrationDTO> getRegistrationListViewData(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<RegistrationDTO>() {
			@Override
			public RegistrationDTO mapRow(ResultSet row, int count) throws SQLException {
				return RegistrationMapper.getViewRegistrationDTO(row);
			}
		});
	}

	public List<RegistrationDTO> getRegistrationListData(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<RegistrationDTO>() {
			@Override
			public RegistrationDTO mapRow(ResultSet row, int count) throws SQLException {
				return RegistrationMapper.getRegistrationDTO(row);
			}
		});
	}


	public Map<String, Object> getClaimRefNo(String docType,Long sequenceNumber,String productId) throws DAOException {
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

	public boolean deleteRegistrationDocument(String id, String docType, String docName, String path) throws DAOException {
		boolean isDeleted = false;
		try {
			NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("DocTypeId", docType);
			parameters.addValue("id", id);
			parameters.addValue("DocName", docName);
			parameters.addValue("DocPath", path);

			int affectedRows = namedParameterJdbcTemplate.update(QUERIES_DELETE_TDS_LEVEL_D, parameters);

			if(affectedRows>0) {
				isDeleted = true;
			}
		}catch(Exception e) {
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return isDeleted;
	}

	public RegistrationDTO updateReimbursementRegistrationDetails(String compId,RegistrationDTO registrationDTO) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		isSaved = updateCTDSLEVELMFNOL(compId, registrationDTO, jdbcTemplate);
		return registrationDTO;
	}

	private boolean updateCTDSLEVELMFNOL(String compId,RegistrationDTO registrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		jdbcTemplate.update(QUERIES_UPDATE_CTDS_LEVEL_MFNOL,
				new Object[] { 
						registrationDTO.getReqType(), 
						registrationDTO.getEncType(),
						registrationDTO.getVoucherNumber(), 
						registrationDTO.getRequestAmt(), 
						registrationDTO.getRequestAmtBC(),
						registrationDTO.getPaymentRefNum(), 
						registrationDTO.getPrevRequest(),
						registrationDTO.getPaymentWay(),
						registrationDTO.getId()});

		return true;
	}
	
	private boolean updateCTDSLEVELFNOL(Long sgsId,String claimNumber,JdbcTemplate jdbcTemplate) throws DAOException {

		jdbcTemplate.update(QUERIES_UPDATE_CTDS_LEVEL_FNOL_FOR_CLAIM_NUMBER,
				new Object[] {  
						claimNumber,
						sgsId
						
		});

		return true;
	}

	public RegistrationDTO insertReimbursementRegistrationDetails(String compId,RegistrationDTO registrationDTO) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		//AutoGenerated Sequence Id
		Long dtlsSgsId = getSequenceNo(QUERIES_INSERT_SEQUENCE_NAME);
		registrationDTO.setId(dtlsSgsId);
		//Claim reference Number Procedure
		Map<String, Object> refNoMap = getClaimRefNo("FN", dtlsSgsId, registrationDTO.getPolicyDetailsDTO().getProductId());
		String claimRefNo = (String)refNoMap.get("P_DOC_NO");
		registrationDTO.setClaimRefNo(claimRefNo);
		//Classofbusiness for policynumber
		String cobId = getClassOfBusinessForPolicy(GENERAL_QUERIES_GET_COB_DETAIL, registrationDTO.getPolicyDetailsDTO().getPolicyNumber(), compId);
		registrationDTO.getPolicyDetailsDTO().setCobId(cobId);

		String userDivId = getUserDivisionForCompany(GENERAL_QUERIES_GET_USER_DIVISION, registrationDTO.getCreatedBy(), compId);
		registrationDTO.setUserDivision(userDivId);		

		//TODO:Use the boolean correctly.
		insertCTDSLEVELFNOL(compId, registrationDTO,jdbcTemplate);
		insertCTDSLEVELMFNOL(compId, registrationDTO, jdbcTemplate);
		insertCTDSLEVELR(compId, registrationDTO, jdbcTemplate);
		insertCTDSLEVELMR(compId, registrationDTO, jdbcTemplate);
		return registrationDTO;
	}

	private boolean insertCTDSLEVELFNOL(String compId,RegistrationDTO registrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date reqReceivedDate = null;
		java.sql.Date serviceFromDate = null;
		java.sql.Date policyFromDate = null;
		java.sql.Date policyToDate = null;
		java.sql.Date createdDate = null;
		if (registrationDTO.getReqReceivedDate() != null) {
			reqReceivedDate = java.sql.Date.valueOf(registrationDTO.getReqReceivedDate());
		}
		if (registrationDTO.getServiceFmDate() != null) {
			serviceFromDate = java.sql.Date.valueOf(registrationDTO.getServiceFmDate());
		}
		if (registrationDTO.getPolicyDetailsDTO().getPolicyFromDate() != null) {
			policyFromDate = java.sql.Date.valueOf(registrationDTO.getPolicyDetailsDTO().getPolicyFromDate());
		}
		if (registrationDTO.getPolicyDetailsDTO().getPolicyToDate() != null) {
			policyToDate = java.sql.Date.valueOf(registrationDTO.getPolicyDetailsDTO().getPolicyToDate());
		}
		createdDate = new java.sql.Date(new Date().getTime());
		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_FNOL,
				new Object[] { 	registrationDTO.getId(),
						compId, 
						registrationDTO.getPolicyDetailsDTO().getPolicyNumber(), 
						registrationDTO.getPolicyDetailsDTO().getCustomerId(),
						registrationDTO.getClaimRefNo(), 
						reqReceivedDate, 
						serviceFromDate,
						registrationDTO.getDescription(),
						CLAIMANT_IS_THE_CUSTOMER, 
						CLAIM_REPORTED_BY_INSURED, 
						registrationDTO.getStatus(),
						registrationDTO.getPolicyDetailsDTO().getProductId(),
						registrationDTO.getPolicyDetailsDTO().getCobId(), 
						policyFromDate, 
						policyToDate,
						registrationDTO.getCreatedBy(), 
						createdDate,
						registrationDTO.getReportedBy(),
						registrationDTO.getReportedByDesc(), 
						CLAIM_REPORTED_BY_INSURED, 
						"",
						registrationDTO.getDivisionId(),
						registrationDTO.getDeptId(),
						registrationDTO.getPolicyDetailsDTO().getInsuredId(),
						registrationDTO.getSourceType(),
						registrationDTO.getUserDivision(),CLAIM_MOD_TYPE});

		return true;
	}

	private boolean insertCTDSLEVELMFNOL(String compId,RegistrationDTO registrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_MFNOL,
				new Object[] { registrationDTO.getId(), 
						registrationDTO.getProviderId(), 
						registrationDTO.getReqType(), 
						registrationDTO.getEncType(),
						registrationDTO.getVoucherNumber(), 
						registrationDTO.getRequestAmt(), 
						registrationDTO.getRequestAmtBC(),
						registrationDTO.getBaseCurrency(), 
						registrationDTO.getPaymentRefNum(), 
						registrationDTO.getPrevRequest(),
						registrationDTO.getProviderId(),
						registrationDTO.getPaymentWay()});

		return true;
	}

	private boolean insertCTDSLEVELR(String compId,RegistrationDTO registrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date riskFromDate = null;
		java.sql.Date riskToDate = null;
		if (registrationDTO.getPolicyDetailsDTO().getRiskFromDate() != null) {
			riskFromDate = java.sql.Date.valueOf(registrationDTO.getPolicyDetailsDTO().getRiskFromDate());
		}
		if (registrationDTO.getPolicyDetailsDTO().getRiskToDate() != null) {
			riskToDate = java.sql.Date.valueOf(registrationDTO.getPolicyDetailsDTO().getRiskToDate());
		}

		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_R,
				new Object[] { registrationDTO.getId(), 
						registrationDTO.getPolicyDetailsDTO().getRiskId(), 
						registrationDTO.getPolicyDetailsDTO().getRiskType(), 
						registrationDTO.getPolicyDetailsDTO().getPolicyNumber(),
						registrationDTO.getPolicyDetailsDTO().getRiskCOB(), 
						riskFromDate,
						riskToDate,
						registrationDTO.getPolicyDetailsDTO().getRiskAmndVerId(),
						registrationDTO.getPolicyDetailsDTO().getRiskFlex1(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlex2(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlex3(),
						registrationDTO.getPolicyDetailsDTO().getRiskFlex4(),
						registrationDTO.getPolicyDetailsDTO().getRiskFlex5(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlex6(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlex7(),
						registrationDTO.getPolicyDetailsDTO().getRiskFlex8(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlex9(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlex10(), 
						registrationDTO.getPolicyDetailsDTO().getRiskCurrentExchangeRate(),
						registrationDTO.getPolicyDetailsDTO().getRiskCurrencyId(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc1(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc2(),
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc3(),
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc4(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc5(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc6(),
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc7(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc8(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc9(), 
						registrationDTO.getPolicyDetailsDTO().getRiskFlexDesc10(), 
						registrationDTO.getSourceType(), 
						compId});

		return true;
	}

	private boolean insertCTDSLEVELMR(String compId,RegistrationDTO registrationDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date memberDOB = null;
		java.sql.Date cardReceivedDate = null;
		if (registrationDTO.getMemberDetailsDTO().getMemberDOB() != null) {
			memberDOB = java.sql.Date.valueOf(registrationDTO.getMemberDetailsDTO().getMemberDOB());
		}
		if (registrationDTO.getMemberDetailsDTO().getCardReceivedDate() != null) {
			cardReceivedDate = java.sql.Date.valueOf(registrationDTO.getMemberDetailsDTO().getCardReceivedDate());
		}
		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_MR,
				new Object[] { registrationDTO.getId(), 
						registrationDTO.getPolicyDetailsDTO().getRiskId(), 
						registrationDTO.getMemberDetailsDTO().getMemberNumber(), 
						registrationDTO.getMemberDetailsDTO().getMemberName(),
						registrationDTO.getMemberDetailsDTO().getMemberType(), 
						registrationDTO.getMemberDetailsDTO().getMemberCategory(),
						memberDOB, 
						registrationDTO.getMemberDetailsDTO().getIsDependent(), 
						registrationDTO.getMemberDetailsDTO().getParentMemberId(),
						registrationDTO.getMemberDetailsDTO().getRelationWithPrimary(),
						registrationDTO.getMemberDetailsDTO().getNationalId(), 
						registrationDTO.getMemberDetailsDTO().getUidId(), 
						registrationDTO.getMemberDetailsDTO().getPassportNumber(),
						registrationDTO.getMemberDetailsDTO().getCardNumber(), 
						cardReceivedDate, 
						registrationDTO.getMemberDetailsDTO().getMobileNum1(), 
						registrationDTO.getMemberDetailsDTO().getEmail1(),
						registrationDTO.getMemberDetailsDTO().getMobileNum2(), 
						registrationDTO.getMemberDetailsDTO().getEmail2()
		});

		return true;
	}


	public void insertTDSLEVELD(String compId,RegistrationDTO registrationDTO) throws DAOException {
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			List<RegistrationFileDTO> fileDTOs = registrationDTO.getRegistrationFileDTOs();
			jdbcTemplate.batchUpdate(QUERIES_INSERT_TDS_LEVEL_D, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					RegistrationFileDTO fileDTO = fileDTOs.get(i);
					java.sql.Date uploadedDate = new java.sql.Date(new Date().getTime());
					ps.setLong(1, registrationDTO.getId());
					ps.setString(2, fileDTO.getDocTypeId());
					ps.setString(3, fileDTO.getDescription());
					ps.setString(4, "Y");
					ps.setDate(5, uploadedDate);
					ps.setString(6, fileDTO.getDocName());
					ps.setString(7, CLAIM_MOD_TYPE);
					ps.setString(8, registrationDTO.getClaimRefNo());
					ps.setString(9, null);
					ps.setString(10, registrationDTO.getCreatedBy());
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
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}
	
	
}
