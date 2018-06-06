package com.beyon.medical.claims.reimbursement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingServiceDTO;
import com.beyon.medical.claims.utils.DateUtil;

public class ReimbursementProcessingServiceMapper {
	
	public static ReimbursementProcessingServiceDTO getReimbursementProcessingServiceDTO(ResultSet row) 
			throws SQLException {
		ReimbursementProcessingServiceDTO reimbursementProcessingServiceDTO = new ReimbursementProcessingServiceDTO();		
		reimbursementProcessingServiceDTO.setReimbursementProcessId(row.getLong("Reimbursementprocessid"));
		reimbursementProcessingServiceDTO.setClaimsRegistrationId(row.getLong("Claimsregistrationid"));
		reimbursementProcessingServiceDTO.setClaimsSequenceNo(row.getLong("Claimssequenceno"));
		reimbursementProcessingServiceDTO.setTreatmentFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Treatmentfromdate")));
		reimbursementProcessingServiceDTO.setTreatmentToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Treatmenttodate")));
		reimbursementProcessingServiceDTO.setNoOfTreamentDays(row.getInt("Nooftreamentdays"));
		reimbursementProcessingServiceDTO.setServiceType(row.getString("Servicetype"));		
		reimbursementProcessingServiceDTO.setServiceId(row.getString("Serviceid"));
		reimbursementProcessingServiceDTO.setBenefitId(row.getString("Benefitid"));
		reimbursementProcessingServiceDTO.setSubBenefitId(row.getString("Subbenefitid"));
		reimbursementProcessingServiceDTO.setCurrencyId(row.getString("Currencyid"));
		reimbursementProcessingServiceDTO.setRequestedAmount(row.getBigDecimal("Requestedamount"));
		reimbursementProcessingServiceDTO.setRequestedAmountBC(row.getBigDecimal("Requestedamountbc"));
		reimbursementProcessingServiceDTO.setPolicyDeductibleAmount(row.getBigDecimal("Policydeductibleamount"));		
		reimbursementProcessingServiceDTO.setPolicyDeductibleAmountBC(row.getBigDecimal("Policydeductibleamountbc"));
		reimbursementProcessingServiceDTO.setManualDeductionAmount(row.getBigDecimal("Manualdeductionamount"));
		reimbursementProcessingServiceDTO.setManualDeductionAmountBC(row.getBigDecimal("Manualdeductionamountbc"));
		reimbursementProcessingServiceDTO.setPenaltyAmount(row.getBigDecimal("Penaltyamount"));
		reimbursementProcessingServiceDTO.setPenaltyAmountBC(row.getBigDecimal("Penaltyamountbc"));
		reimbursementProcessingServiceDTO.setSuggestedAmout(row.getBigDecimal("Suggestedamout"));
		reimbursementProcessingServiceDTO.setSuggestedAmoutBC(row.getBigDecimal("Suggestedamoutbc"));
		reimbursementProcessingServiceDTO.setApprovedAmount(row.getBigDecimal("Approvedamount"));
		reimbursementProcessingServiceDTO.setApprovedAmountBC(row.getBigDecimal("Approvedamountbc"));
		reimbursementProcessingServiceDTO.setInternalRejectionCode(row.getString("Internalrejectioncode"));
		reimbursementProcessingServiceDTO.setRejectedAmount(row.getBigDecimal("Rejectedamount"));
		reimbursementProcessingServiceDTO.setRejectedAmountBC(row.getBigDecimal("Rejectedamountbc"));
		reimbursementProcessingServiceDTO.setClaimStatus(row.getString("Claimstatus"));
		reimbursementProcessingServiceDTO.setStatusDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Statusdate")));
		reimbursementProcessingServiceDTO.setInternalRemarks(row.getString("Internalremarks"));		
		reimbursementProcessingServiceDTO.setExternalRemarks(row.getString("Externalremarks"));
		reimbursementProcessingServiceDTO.setLossType(row.getString("Losstype"));
		reimbursementProcessingServiceDTO.setEstType(row.getString("Esttype"));
		reimbursementProcessingServiceDTO.setCreatedBy(row.getString("Createdby"));
		reimbursementProcessingServiceDTO.setCreatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Createddate")));
		reimbursementProcessingServiceDTO.setUpdatedBy(row.getString("Updatedby"));
		reimbursementProcessingServiceDTO.setUpdatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Updateddate")));
		reimbursementProcessingServiceDTO.setApprovedBy(row.getString("Approvedby"));
		reimbursementProcessingServiceDTO.setApprovedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Approveddate")));
		reimbursementProcessingServiceDTO.setCurrencyType(row.getString("Currencytype"));
		
		return reimbursementProcessingServiceDTO;
	}
}
