package com.beyon.medical.claims.processing.dao;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_APPROVE_CTDS_LEVEL_MSRVC;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_CHECK_EXISTENCE_CTDS_LEVEL_E;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_DETAILS_CTDS_LEVEL_MSRVC_VERSION;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CHDS_LEVEL_E;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CHDS_LEVEL_MSRVC;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_E;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_E_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MC;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MDIAG;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MSRVC;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MSRVC_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_E;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_MC;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_MDIAG;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_UPDATE_CTDS_LEVEL_MSRVC;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_UPDATE_STATUS_CTDS_LEVEL_C;
import static com.beyon.medical.claims.queries.constants.ProcessingQueriesConstants.QUERIES_UPDATE_STATUS_CTDS_LEVEL_MC;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.medical.claims.base.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.DiagnosisDTO;
import com.beyon.medical.claims.general.dto.ProcessingDTO;
import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;
import com.beyon.medical.claims.general.dto.ServiceLevelEstimateDTO;
import com.beyon.medical.claims.mapper.ProcessingMapper;
import com.beyon.medical.claims.mapper.ProcessingServiceMapper;
import com.beyon.medical.claims.mapper.ServiceLevelEstimationMapper;

@Repository("ClaimProcessingDAOImpl")
@Scope(value="prototype")
public class ClaimProcessingDAOImpl extends BaseClaimsDAOImpl {


	private ProcessingDTO insertCTDSLEVELMSRVC(String compId,ProcessingDTO processingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		List<ProcessingServiceDTO> processingServiceResultDTOs  = new ArrayList<>();
		try {
			List<ProcessingServiceDTO> processingServiceDTOs  = processingDTO.getProcessingServiceDTOs();			
			jdbcTemplate.batchUpdate(QUERIES_INSERT_CTDS_LEVEL_MSRVC, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ProcessingServiceDTO processingServiceDTO = processingServiceDTOs.get(i);
					Long clcpId = getSequenceNo(QUERIES_INSERT_CTDS_LEVEL_MSRVC_SEQUENCE_NAME);
					processingServiceDTO.setReimbursementProcessId(clcpId);
					java.sql.Date treatmentFromDate = toSQLDate(processingServiceDTO.getTreatmentFromDate());
					java.sql.Date treatmentToDate = toSQLDate(processingServiceDTO.getTreatmentToDate());
					java.sql.Date statusDate = toSQLDate(processingServiceDTO.getStatusDate());
					java.sql.Date approvedDate = toSQLDate(processingServiceDTO.getApprovedDate());
					java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
					
					ps.setLong(1, processingServiceDTO.getReimbursementProcessId());
					ps.setLong(2, processingDTO.getId());
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
					ps.setString(35, processingDTO.getPrimaryDiagnosis().getDiagId());
					ps.setString(36, processingServiceDTO.getCreatedBy());
					ps.setDate(37, createdDate);
					ps.setString(38, processingServiceDTO.getUpdatedBy());
					ps.setDate(39, updatedDate);
					ps.setString(40, processingServiceDTO.getApprovedBy());
					ps.setDate(41, approvedDate);
					ps.setString(42, processingServiceDTO.getCurrencyType());
					processingServiceResultDTOs.add(processingServiceDTO);
				}
				@Override
				public int getBatchSize() {
					return processingServiceDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		processingDTO.setProcessingServiceDTOs(processingServiceResultDTOs);
		return processingDTO;
	}
	
	private boolean insertCHDSLEVELMSRVC(String compId,ProcessingDTO processingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		try {
			List<ProcessingServiceDTO> processingServiceDTOs  = processingDTO.getProcessingServiceDTOs();
			jdbcTemplate.batchUpdate(QUERIES_INSERT_CHDS_LEVEL_MSRVC, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ProcessingServiceDTO processingServiceDTO = processingServiceDTOs.get(i);					
					java.sql.Date treatmentFromDate = toSQLDate(processingServiceDTO.getTreatmentFromDate());
					java.sql.Date treatmentToDate = toSQLDate(processingServiceDTO.getTreatmentToDate());
					java.sql.Date statusDate = toSQLDate(processingServiceDTO.getStatusDate());
					java.sql.Date approvedDate = toSQLDate(processingServiceDTO.getApprovedDate());
					java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
					java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
					
					ps.setLong(1, processingServiceDTO.getReimbursementProcessId());
					ps.setLong(2, processingDTO.getId());
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
					return processingServiceDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return true;
	}	

	private boolean insertCTDSLEVELMC(String compId,ProcessingDTO processingDTO,JdbcTemplate jdbcTemplate) throws DAOException {

		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_MC,
				new Object[] { processingDTO.getId(), 
						processingDTO.getClaimNumber(), 
						processingDTO.getRequestNumber(), 
						processingDTO.getClaimType(),
						processingDTO.getEventCountry(), 
						processingDTO.getClaimCondition(),
						processingDTO.getClaimStatusReason()
		});

		return true;
	}	

	private boolean insertCTDSLEVELMDIAG(String compId,Long sgsId,DiagnosisDTO diagnosisDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_MDIAG,
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
	
	private ProcessingDTO updateCTDSLEVELMSRVC(String compId, ProcessingDTO processingDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		List<ProcessingServiceDTO> processingServiceResultDTOs  = new ArrayList<>();
		try {
			List<ProcessingServiceDTO> processingServiceDTOs  = processingDTO.getProcessingServiceDTOs();			
			jdbcTemplate.batchUpdate(QUERIES_UPDATE_CTDS_LEVEL_MSRVC, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ProcessingServiceDTO processingServiceDTO = processingServiceDTOs.get(i);
					Long version = getLatestVersion(processingServiceDTO.getReimbursementProcessId(), jdbcTemplate);
					processingServiceDTO.setClaimsSequenceNo(version+1);
					java.sql.Date treatmentFromDate = toSQLDate(processingServiceDTO.getTreatmentFromDate());
					java.sql.Date treatmentToDate = toSQLDate(processingServiceDTO.getTreatmentToDate());
					java.sql.Date statusDate = toSQLDate(processingServiceDTO.getStatusDate());
					java.sql.Date approvedDate = toSQLDate(processingServiceDTO.getApprovedDate());
					java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());					
					
					ps.setLong(1, processingDTO.getId());					
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
					processingServiceResultDTOs.add(processingServiceDTO);
				}
				@Override
				public int getBatchSize() {
					return processingServiceDTOs.size();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		processingDTO.setProcessingServiceDTOs(processingServiceResultDTOs);
		return processingDTO;
	}
	
	private boolean updateCTDSLEVELMC(String compId,ProcessingDTO processingDTO,JdbcTemplate jdbcTemplate) throws DAOException {

		jdbcTemplate.update(QUERIES_UPDATE_CTDS_LEVEL_MC,
				new Object[] {  
						processingDTO.getClaimNumber(), 
						processingDTO.getRequestNumber(), 
						processingDTO.getClaimType(),
						processingDTO.getEventCountry(), 
						processingDTO.getClaimCondition(),
						processingDTO.getClaimStatusReason(),
						processingDTO.getId()
		});

		return true;
	}
	


	
	private boolean updateCTDSLEVELMDIAG(String compId,Long sgsId,DiagnosisDTO diagnosisDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());		
		jdbcTemplate.update(QUERIES_UPDATE_CTDS_LEVEL_MDIAG,
				new Object[] {  
						diagnosisDTO.getDiagId(), 
						diagnosisDTO.getDiagType(),						
						diagnosisDTO.getUpdatedBy(),
						updatedDate,
						sgsId
		});
		return true;
	}
	
	private boolean insertCTDSLEVELE(String compId,ProcessingDTO processingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		java.sql.Date approvedDate = toSQLDate(processingDTO.getProcessingServiceDTOs().get(0).getApprovedDate());

		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_E,
				new Object[] { processingDTO.getServiceGroupId(), 
						processingDTO.getId(), 
						processingDTO.getProcessingServiceDTOs().get(0).getClaimsSequenceNo(), 
						processingDTO.getPolicyDetailsDTO().getRiskId(),
						processingDTO.getProcessingServiceDTOs().get(0).getBenefitId(), 
						processingDTO.getProcessingServiceDTOs().get(0).getSubBenefitId(),
						processingDTO.getProcessingServiceDTOs().get(0).getSubBenefitId(),
						
						processingDTO.getProcessingServiceDTOs().get(0).getEstType(), 
						createdDate, 
						processingDTO.getProcessingServiceDTOs().get(0).getApprovedAmount(), 
						processingDTO.getPolicyDetailsDTO().getCustomerId(),
						processingDTO.getProcessingServiceDTOs().get(0).getCreatedBy(), 
						processingDTO.getProcessingServiceDTOs().get(0).getApprovedAmount(),
						null,
						"P",
						processingDTO.getPolicyDetailsDTO().getRiskCurrentExchangeRate(),
						processingDTO.getPolicyDetailsDTO().getRiskCurrencyId(),
						processingDTO.getProcessingServiceDTOs().get(0).getApprovedAmountBC(),
						processingDTO.getProcessingServiceDTOs().get(0).getApprovedAmountBC(),
						"1",
						processingDTO.getProcessingServiceDTOs().get(0).getApprovedBy(),
						approvedDate,
						processingDTO.getProcessingServiceDTOs().get(0).getUpdatedBy(),
						createdDate,
						compId						
		});

		return true;
	}	
	
	private boolean insertCHDSLEVELE(String compId, ProcessingDTO processingDTO,JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		java.sql.Date approvedDate = toSQLDate(processingDTO.getProcessingServiceDTOs().get(0).getApprovedDate());
		ProcessingServiceDTO processingServiceDTO = processingDTO.getProcessingServiceDTOs().get(0);
		
		jdbcTemplate.update(QUERIES_INSERT_CHDS_LEVEL_E,
				new Object[] { processingDTO.getServiceGroupId(), 
						processingDTO.getId(), 
						processingServiceDTO.getClaimsSequenceNo(), 
						processingDTO.getPolicyDetailsDTO().getRiskId(),
						processingServiceDTO.getBenefitId(), 
						processingServiceDTO.getSubBenefitId(),
						processingServiceDTO.getSubBenefitId(),						
						processingServiceDTO.getEstType(), 
						createdDate,
						processingServiceDTO.getApprovedAmount(),
						processingServiceDTO.getApprovedAmount(), 
						processingDTO.getPolicyDetailsDTO().getCustomerId(),
						processingServiceDTO.getCreatedBy(), 
						processingServiceDTO.getApprovedAmount(),
						null,
						"P",
						processingServiceDTO.getApprovedAmount(),
						processingDTO.getPolicyDetailsDTO().getRiskCurrencyId(),
						processingDTO.getPolicyDetailsDTO().getRiskCurrentExchangeRate(),
						processingServiceDTO.getApprovedAmountBC(),
						processingServiceDTO.getApprovedAmountBC(),
						processingServiceDTO.getApprovedBy(),
						approvedDate,
						createdDate,
						processingServiceDTO.getUpdatedBy(),
						"1",
						compId						
		});

		return true;
	}
	
	private boolean insertCHDSLEVELE(String compId, ServiceLevelEstimateDTO serviceLevelEstimateDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());		
		jdbcTemplate.update(QUERIES_INSERT_CHDS_LEVEL_E,
				new Object[] {  
						serviceLevelEstimateDTO.getId(),
						serviceLevelEstimateDTO.getClaimsId(),
						serviceLevelEstimateDTO.getVersion(),
						serviceLevelEstimateDTO.getRiskId(),
						serviceLevelEstimateDTO.getBenefitId(), 
						serviceLevelEstimateDTO.getSubBenefitId(),
						serviceLevelEstimateDTO.getLossDescription(),						
						serviceLevelEstimateDTO.getEstimateType(), 
						toSQLDate(serviceLevelEstimateDTO.getEstimateDate()),
						serviceLevelEstimateDTO.getRevisedEstimatedAmt(),
						serviceLevelEstimateDTO.getOldEstimatedAmt(),
						null,
						serviceLevelEstimateDTO.getCreatedBy(), 
						serviceLevelEstimateDTO.getReservedOutstandingAmt(),
						null,
						"P",
						serviceLevelEstimateDTO.getReservedOutstandingAmtBC(),
						null,
						serviceLevelEstimateDTO.getExchangeRate(),
						serviceLevelEstimateDTO.getRevisedEstimatedAmtBC(),
						serviceLevelEstimateDTO.getOldEstimatedAmtBC(),
						serviceLevelEstimateDTO.getApprovedBy(),
						toSQLDate(serviceLevelEstimateDTO.getApprovedDate()),
						toSQLDate(serviceLevelEstimateDTO.getUpdatedDate()),
						serviceLevelEstimateDTO.getUpdatedBy(),												
						"1",						
						compId						
		});

		return true;
	}
	
	private boolean updateCTDSLEVELE(String compId, ServiceLevelEstimateDTO reimbursementEstimateDTO,
			ProcessingDTO processingDTO, JdbcTemplate jdbcTemplate) throws DAOException {
		java.sql.Date createdDate = new java.sql.Date(new Date().getTime());
		java.sql.Date approvedDate = toSQLDate(processingDTO.getProcessingServiceDTOs().get(0).getApprovedDate());
		ProcessingServiceDTO processingServiceDTO = processingDTO.getProcessingServiceDTOs().get(0);
		jdbcTemplate.update(QUERIES_UPDATE_CTDS_LEVEL_E,
				new Object[] {
						reimbursementEstimateDTO.getReservedOutstandingAmt(),
						reimbursementEstimateDTO.getReservedOutstandingAmtBC(),
						reimbursementEstimateDTO.getEstimatedAmt(),
						reimbursementEstimateDTO.getEstimatedAmtBC(),
						processingServiceDTO.getApprovedBy(),
						toSQLDate(processingServiceDTO.getApprovedDate()),
						toSQLDate(processingServiceDTO.getUpdatedDate()),
						processingServiceDTO.getUpdatedBy(),
						reimbursementEstimateDTO.getVersion()+1,
						processingServiceDTO.getClaimsRegistrationId(),
						processingDTO.getPolicyDetailsDTO().getRiskId(),
						processingServiceDTO.getBenefitId(),
						processingServiceDTO.getSubBenefitId()
		});

		return true;
	}
	
	public ProcessingDTO insertReimbursementProcessingDetails(String compId,ProcessingDTO processingDTO) throws DAOException {
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		ProcessingDTO _processingDTO = insertCTDSLEVELMSRVC(compId, processingDTO, jdbcTemplate);
		insertCHDSLEVELMSRVC(compId, _processingDTO, jdbcTemplate);
		insertCTDSLEVELMC(compId, _processingDTO, jdbcTemplate);
		insertCTDSLEVELMDIAG(compId, _processingDTO.getId(), _processingDTO.getPrimaryDiagnosis(), jdbcTemplate);
		insertCTDSLEVELMDIAG(compId, _processingDTO.getId(), _processingDTO.getSecondaryDiagnosis(), jdbcTemplate);
		updateStatusCTDSLEVELC(compId, processingDTO.getId(),"WIP", jdbcTemplate);
		return _processingDTO;
	}
	
	public ProcessingDTO updateReimbursementProcessingDetails(String compId,ProcessingDTO processingDTO) throws DAOException {
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		ProcessingDTO result = updateCTDSLEVELMSRVC(compId, processingDTO, jdbcTemplate);
		insertCHDSLEVELMSRVC(compId, result, jdbcTemplate);
		updateCTDSLEVELMC(compId, processingDTO, jdbcTemplate);
		updateCTDSLEVELMDIAG(compId, processingDTO.getId(), processingDTO.getPrimaryDiagnosis(), jdbcTemplate);
		updateCTDSLEVELMDIAG(compId, processingDTO.getId(), processingDTO.getSecondaryDiagnosis(), jdbcTemplate);
		return processingDTO;
	}
	
	public List<ProcessingDTO> getProcessingDetails(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ProcessingDTO>() {
			@Override
			public ProcessingDTO mapRow(ResultSet row, int count) throws SQLException {
				return ProcessingMapper.getProcessingDTO(row);
			}
		});
	}
	
	public List<ProcessingDTO> getProcessingDetailsForAssignment(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ProcessingDTO>() {
			@Override
			public ProcessingDTO mapRow(ResultSet row, int count) throws SQLException {
				return ProcessingMapper.getProcessingDTOForAssignment(row);
			}
		});
	}
	
	
	public List<ProcessingServiceDTO> getProcessingServiceDetails(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ProcessingServiceDTO>() {
			@Override
			public ProcessingServiceDTO mapRow(ResultSet row, int count) throws SQLException {
				return ProcessingServiceMapper.getProcessingServiceDTO(row);
			}
		});
	}
	
	private Long getLatestVersion(Long serviceId, JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.queryForObject(QUERIES_DETAILS_CTDS_LEVEL_MSRVC_VERSION, 
				new Object[] {serviceId}, Long.class);
	}
	
	private boolean approveCTDSLEVELMSRVC(String compId, ProcessingServiceDTO processingServiceDTO, JdbcTemplate jdbcTemplate) {		
		java.sql.Date updatedDate = new java.sql.Date(new Date().getTime());
		Long version = getLatestVersion(processingServiceDTO.getReimbursementProcessId(), jdbcTemplate);
		jdbcTemplate.update(QUERIES_APPROVE_CTDS_LEVEL_MSRVC,
				new Object[] {  
						processingServiceDTO.getClaimStatus(),
						toSQLDate(processingServiceDTO.getStatusDate()),
						processingServiceDTO.getInternalRemarks(),
						processingServiceDTO.getExternalRemarks(),
						processingServiceDTO.getUpdatedBy(),
						toSQLDate(processingServiceDTO.getUpdatedDate()),
						processingServiceDTO.getApprovedBy(),
						toSQLDate(processingServiceDTO.getApprovedDate()),
						version+1,
						processingServiceDTO.getApprovedAmount(),
						processingServiceDTO.getApprovedAmountBC(),
						processingServiceDTO.getReimbursementProcessId()
						
		});
		return true;		
	}
	
	private boolean updateStatusCTDSLEVELC(String compId, Long sgsId,String status, JdbcTemplate jdbcTemplate) {		
		jdbcTemplate.update(QUERIES_UPDATE_STATUS_CTDS_LEVEL_C,
				new Object[] {  
						status,						
						sgsId
						
		});
		return true;		
	}
	
	private boolean updateStatusCTDSLEVELMC(String compId, Long sgsId,String status, JdbcTemplate jdbcTemplate) {		
		jdbcTemplate.update(QUERIES_UPDATE_STATUS_CTDS_LEVEL_MC,
				new Object[] {  
						status,						
						sgsId
		});
		return true;		
	}
	
	public ProcessingDTO approveServiceLineItem(String compId,ProcessingDTO processingDTO) throws DAOException {
		boolean isSaved = false;
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		ProcessingServiceDTO processingServiceDTO = processingDTO.getProcessingServiceDTOs().get(0);
		ServiceLevelEstimateDTO reimbursementEstimateDTO = jdbcTemplate.queryForObject(QUERIES_CHECK_EXISTENCE_CTDS_LEVEL_E, 
				new Object[] {
						processingServiceDTO.getClaimsRegistrationId(),
						processingDTO.getPolicyDetailsDTO().getRiskId(),
						processingServiceDTO.getBenefitId(),
						processingServiceDTO.getSubBenefitId()
				}, new RowMapper<ServiceLevelEstimateDTO>() {
					@Override
					public ServiceLevelEstimateDTO mapRow(ResultSet row, int count) throws SQLException {
						return ServiceLevelEstimationMapper.getServiceLevelEstimateDTO(row);
					}
				});
		if (reimbursementEstimateDTO == null) {
			//AutoGenerated Sequence Id
			Long dtlsSgsId = getSequenceNo(QUERIES_INSERT_CTDS_LEVEL_E_SEQUENCE_NAME);
			processingDTO.setServiceGroupId(dtlsSgsId);
			insertCTDSLEVELE(compId, processingDTO, jdbcTemplate);
			insertCHDSLEVELE(compId, processingDTO, jdbcTemplate);
			approveCTDSLEVELMSRVC(compId, processingServiceDTO, jdbcTemplate);
			updateStatusCTDSLEVELC(compId, processingServiceDTO.getClaimsRegistrationId(),processingServiceDTO.getClaimStatus(), jdbcTemplate);
			updateStatusCTDSLEVELMC(compId, processingServiceDTO.getClaimsRegistrationId(),processingDTO.getClaimStatusReason(), jdbcTemplate);
			return processingDTO;
		}
		
		setEstimatedAmt(reimbursementEstimateDTO, processingDTO);
		//setOutstandingAmt(reimbursementEstimateDTO, processingDTO);
		updateCTDSLEVELE(compId, reimbursementEstimateDTO, processingDTO, jdbcTemplate);
		insertCHDSLEVELE(compId, reimbursementEstimateDTO, jdbcTemplate);
		approveCTDSLEVELMSRVC(compId, processingServiceDTO, jdbcTemplate);
		updateStatusCTDSLEVELC(compId, processingServiceDTO.getClaimsRegistrationId(),processingServiceDTO.getClaimStatus(), jdbcTemplate);
		updateStatusCTDSLEVELMC(compId, processingServiceDTO.getClaimsRegistrationId(),processingDTO.getClaimStatusReason(), jdbcTemplate);
		return processingDTO;		
	}
	
	private void setEstimatedAmt(ServiceLevelEstimateDTO reimbursementEstimateDTO, ProcessingDTO processingDTO) {
		ProcessingServiceDTO processingServiceDTO = processingDTO.getProcessingServiceDTOs().get(0);
		BigDecimal oldEstimatedAmt = reimbursementEstimateDTO.getRevisedEstimatedAmt();
		BigDecimal revisedEstimatedAmt = new BigDecimal(0);
		if (oldEstimatedAmt != null) {
			revisedEstimatedAmt = oldEstimatedAmt.add(processingServiceDTO.getApprovedAmount());
		} else {
			revisedEstimatedAmt = processingServiceDTO.getApprovedAmount();
		}
		
		BigDecimal oldEstimatedAmtBC = reimbursementEstimateDTO.getRevisedEstimatedAmtBC();
		BigDecimal revisedEstimatedAmtBC = new BigDecimal(0);
		if (oldEstimatedAmtBC != null) {
			revisedEstimatedAmtBC = oldEstimatedAmtBC.add(processingServiceDTO.getApprovedAmountBC());
		} else {
			revisedEstimatedAmtBC = processingServiceDTO.getApprovedAmountBC();
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
