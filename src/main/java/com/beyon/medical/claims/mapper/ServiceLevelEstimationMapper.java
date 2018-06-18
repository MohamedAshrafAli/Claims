package com.beyon.medical.claims.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import com.beyon.medical.claims.general.dto.ServiceLevelEstimateDTO;

public class ServiceLevelEstimationMapper {
	
	public static ServiceLevelEstimateDTO getServiceLevelEstimateDTO(ResultSet row) throws SQLException {
		ServiceLevelEstimateDTO serviceEstimateDTO = new ServiceLevelEstimateDTO();
		serviceEstimateDTO.setId(row.getLong("id"));
		serviceEstimateDTO.setClaimsId(row.getLong("claimsId"));
		serviceEstimateDTO.setVersion(row.getInt("version"));
		serviceEstimateDTO.setRiskId(row.getString("riskId"));
		serviceEstimateDTO.setBenefitId(row.getString("benefitId"));
		serviceEstimateDTO.setSubBenefitId(row.getString("subBenefitId"));
		serviceEstimateDTO.setLossDescription(row.getString("lossDescription"));
		serviceEstimateDTO.setEstimateType(row.getString("estimateType"));
		serviceEstimateDTO.setEstimateLevel("estimateLevel");
		serviceEstimateDTO.setEstimateDate(getLocalDate(row.getDate("estimateDate")));
		serviceEstimateDTO.setEstimatedAmt(row.getBigDecimal("estimatedAmt"));
		serviceEstimateDTO.setEstimatedAmtBC(row.getBigDecimal("estimatedAmtBC"));
		serviceEstimateDTO.setCreatedBy(row.getString("createdBy"));
		serviceEstimateDTO.setReservedOutstandingAmt(row.getBigDecimal("outStandingAmt"));
		serviceEstimateDTO.setReservedOutstandingAmtBC(row.getBigDecimal("outStandingAmtBC"));
		serviceEstimateDTO.setSettlementReferenceId(row.getLong("settlementReferenceId"));
		serviceEstimateDTO.setExchangeRate(row.getInt("exchangeRate"));
		serviceEstimateDTO.setReserveCurrency(row.getString("reserveCurrency"));
		
		return serviceEstimateDTO;
	}
	
	private static LocalDate getLocalDate(java.sql.Date date) {
		return Optional.ofNullable(date).map(java.sql.Date::toLocalDate).orElse(null);
	}
}
