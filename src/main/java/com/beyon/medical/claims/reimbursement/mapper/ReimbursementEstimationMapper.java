package com.beyon.medical.claims.reimbursement.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import com.beyon.medical.claims.reimbursement.dto.ReimbursementEstimateDTO;

public class ReimbursementEstimationMapper {
	
	public static ReimbursementEstimateDTO getReimbursementEstimateDTO(ResultSet row) throws SQLException {
		ReimbursementEstimateDTO reimbursementEstimateDTO = new ReimbursementEstimateDTO();
		reimbursementEstimateDTO.setId(row.getLong("id"));
		reimbursementEstimateDTO.setClaimsId(row.getLong("claimsId"));
		reimbursementEstimateDTO.setVersion(row.getInt("version"));
		reimbursementEstimateDTO.setRiskId(row.getString("riskId"));
		reimbursementEstimateDTO.setBenefitId(row.getString("benefitId"));
		reimbursementEstimateDTO.setSubBenefitId(row.getString("subBenefitId"));
		reimbursementEstimateDTO.setLossDescription(row.getString("lossDescription"));
		reimbursementEstimateDTO.setEstimateType(row.getString("estimateType"));
		reimbursementEstimateDTO.setEstimateLevel("estimateLevel");
		reimbursementEstimateDTO.setEstimateDate(getLocalDate(row.getDate("estimateDate")));
		reimbursementEstimateDTO.setEstimatedAmt(row.getBigDecimal("estimatedAmt"));
		reimbursementEstimateDTO.setEstimatedAmtBC(row.getBigDecimal("estimatedAmtBC"));
		reimbursementEstimateDTO.setCreatedBy(row.getString("createdBy"));
		reimbursementEstimateDTO.setOutStandingAmt(row.getBigDecimal("outStandingAmt"));
		reimbursementEstimateDTO.setOutStandingAmtBC(row.getBigDecimal("outStandingAmtBC"));
		reimbursementEstimateDTO.setSettlementReferenceId(row.getLong("settlementReferenceId"));
		reimbursementEstimateDTO.setExchangeRate(row.getInt("exchangeRate"));
		reimbursementEstimateDTO.setReserveCurrency(row.getString("reserveCurrency"));
		
		return reimbursementEstimateDTO;
	}
	
	private static LocalDate getLocalDate(java.sql.Date date) {
		return Optional.ofNullable(date).map(java.sql.Date::toLocalDate).orElse(null);
	}
}
