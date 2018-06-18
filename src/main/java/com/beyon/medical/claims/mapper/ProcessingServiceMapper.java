package com.beyon.medical.claims.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;
import com.beyon.medical.claims.utils.DateUtil;

public class ProcessingServiceMapper {
	
	public static ProcessingServiceDTO getProcessingServiceDTO(ResultSet row) 
			throws SQLException {
		ProcessingServiceDTO processingServiceDTO = new ProcessingServiceDTO();		
		processingServiceDTO.setReimbursementProcessId(row.getLong("Reimbursementprocessid"));
		processingServiceDTO.setClaimsRegistrationId(row.getLong("Claimsregistrationid"));
		processingServiceDTO.setClaimsSequenceNo(row.getLong("Claimssequenceno"));
		processingServiceDTO.setTreatmentFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Treatmentfromdate")));
		processingServiceDTO.setTreatmentToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Treatmenttodate")));
		processingServiceDTO.setNoOfTreamentDays(row.getInt("Nooftreamentdays"));
		processingServiceDTO.setServiceType(row.getString("Servicetype"));		
		processingServiceDTO.setServiceId(row.getString("Serviceid"));
		processingServiceDTO.setBenefitId(row.getString("Benefitid"));
		processingServiceDTO.setSubBenefitId(row.getString("Subbenefitid"));
		processingServiceDTO.setCurrencyId(row.getString("Currencyid"));
		processingServiceDTO.setRequestedAmount(row.getBigDecimal("Requestedamount"));
		processingServiceDTO.setRequestedAmountBC(row.getBigDecimal("Requestedamountbc"));
		processingServiceDTO.setPolicyDeductibleAmount(row.getBigDecimal("Policydeductibleamount"));		
		processingServiceDTO.setPolicyDeductibleAmountBC(row.getBigDecimal("Policydeductibleamountbc"));
		processingServiceDTO.setManualDeductionAmount(row.getBigDecimal("Manualdeductionamount"));
		processingServiceDTO.setManualDeductionAmountBC(row.getBigDecimal("Manualdeductionamountbc"));
		processingServiceDTO.setPenaltyAmount(row.getBigDecimal("Penaltyamount"));
		processingServiceDTO.setPenaltyAmountBC(row.getBigDecimal("Penaltyamountbc"));
		processingServiceDTO.setSuggestedAmout(row.getBigDecimal("Suggestedamout"));
		processingServiceDTO.setSuggestedAmoutBC(row.getBigDecimal("Suggestedamoutbc"));
		processingServiceDTO.setApprovedAmount(row.getBigDecimal("Approvedamount"));
		processingServiceDTO.setApprovedAmountBC(row.getBigDecimal("Approvedamountbc"));
		processingServiceDTO.setInternalRejectionCode(row.getString("Internalrejectioncode"));
		processingServiceDTO.setRejectedAmount(row.getBigDecimal("Rejectedamount"));
		processingServiceDTO.setRejectedAmountBC(row.getBigDecimal("Rejectedamountbc"));
		processingServiceDTO.setClaimStatus(row.getString("Claimstatus"));
		processingServiceDTO.setStatusDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Statusdate")));
		processingServiceDTO.setInternalRemarks(row.getString("Internalremarks"));		
		processingServiceDTO.setExternalRemarks(row.getString("Externalremarks"));
		processingServiceDTO.setLossType(row.getString("Losstype"));
		processingServiceDTO.setEstType(row.getString("Esttype"));
		processingServiceDTO.setCreatedBy(row.getString("Createdby"));
		processingServiceDTO.setCreatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Createddate")));
		processingServiceDTO.setUpdatedBy(row.getString("Updatedby"));
		processingServiceDTO.setUpdatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Updateddate")));
		processingServiceDTO.setApprovedBy(row.getString("Approvedby"));
		processingServiceDTO.setApprovedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Approveddate")));
		processingServiceDTO.setCurrencyType(row.getString("Currencytype"));
		processingServiceDTO.setTreatmentCodeDisplayName((row.getString("Serviceid")) + " / " + (row.getString("Subbenefitid")));
		
		return processingServiceDTO;
	}
}
