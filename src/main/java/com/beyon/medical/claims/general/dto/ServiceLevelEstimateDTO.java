package com.beyon.medical.claims.general.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServiceLevelEstimateDTO {
	private Long id;
	private Long claimsId;
	private Integer version;
	private String riskId;
	private String benefitId;
	private String subBenefitId;
	private String lossDescription;
	private String estimateType;
	private String estimateLevel;
	private LocalDate estimateDate;
	private BigDecimal revisedEstimatedAmt;
	private BigDecimal oldEstimatedAmt;
	private BigDecimal revisedEstimatedAmtBC;
	private BigDecimal oldEstimatedAmtBC;
	private BigDecimal estimatedAmt;
	private BigDecimal estimatedAmtBC;
	private String createdBy;
	private LocalDate createdDate;
	private String approvedBy;
	private LocalDate approvedDate;
	private String updatedBy;
	private LocalDate updatedDate;
	private BigDecimal reservedOutstandingAmt;
	private BigDecimal reservedOutstandingAmtBC;
	private Long settlementReferenceId;	
	private Integer exchangeRate;
	private String reserveCurrency;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClaimsId() {
		return claimsId;
	}
	public void setClaimsId(Long claimsId) {
		this.claimsId = claimsId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	public String getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}
	public String getSubBenefitId() {
		return subBenefitId;
	}
	public void setSubBenefitId(String subBenefitId) {
		this.subBenefitId = subBenefitId;
	}
	public String getLossDescription() {
		return lossDescription;
	}
	public void setLossDescription(String lossDescription) {
		this.lossDescription = lossDescription;
	}
	public String getEstimateType() {
		return estimateType;
	}
	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}
	public String getEstimateLevel() {
		return estimateLevel;
	}
	public void setEstimateLevel(String estimateLevel) {
		this.estimateLevel = estimateLevel;
	}
	public LocalDate getEstimateDate() {
		return estimateDate;
	}
	public void setEstimateDate(LocalDate estimateDate) {
		this.estimateDate = estimateDate;
	}
	public BigDecimal getRevisedEstimatedAmt() {
		return revisedEstimatedAmt;
	}
	public void setRevisedEstimatedAmt(BigDecimal revisedEstimatedAmt) {
		this.revisedEstimatedAmt = revisedEstimatedAmt;
	}
	public BigDecimal getOldEstimatedAmt() {
		return oldEstimatedAmt;
	}
	public void setOldEstimatedAmt(BigDecimal oldEstimatedAmt) {
		this.oldEstimatedAmt = oldEstimatedAmt;
	}
	public BigDecimal getRevisedEstimatedAmtBC() {
		return revisedEstimatedAmtBC;
	}
	public void setRevisedEstimatedAmtBC(BigDecimal revisedEstimatedAmtBC) {
		this.revisedEstimatedAmtBC = revisedEstimatedAmtBC;
	}
	public BigDecimal getOldEstimatedAmtBC() {
		return oldEstimatedAmtBC;
	}
	public void setOldEstimatedAmtBC(BigDecimal oldEstimatedAmtBC) {
		this.oldEstimatedAmtBC = oldEstimatedAmtBC;
	}	
	public BigDecimal getEstimatedAmt() {
		return estimatedAmt;
	}
	public void setEstimatedAmt(BigDecimal estimatedAmt) {
		this.estimatedAmt = estimatedAmt;
	}
	public BigDecimal getEstimatedAmtBC() {
		return estimatedAmtBC;
	}
	public void setEstimatedAmtBC(BigDecimal estimatedAmtBC) {
		this.estimatedAmtBC = estimatedAmtBC;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public BigDecimal getReservedOutstandingAmt() {
		return reservedOutstandingAmt;
	}
	public void setReservedOutstandingAmt(BigDecimal reservedOutstandingAmt) {
		this.reservedOutstandingAmt = reservedOutstandingAmt;
	}
	public BigDecimal getReservedOutstandingAmtBC() {
		return reservedOutstandingAmtBC;
	}
	public void setReservedOutstandingAmtBC(BigDecimal reservedOutstandingAmtBC) {
		this.reservedOutstandingAmtBC = reservedOutstandingAmtBC;
	}
	public Long getSettlementReferenceId() {
		return settlementReferenceId;
	}
	public void setSettlementReferenceId(Long settlementReferenceId) {
		this.settlementReferenceId = settlementReferenceId;
	}
	public Integer getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Integer exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getReserveCurrency() {
		return reserveCurrency;
	}
	public void setReserveCurrency(String reserveCurrency) {
		this.reserveCurrency = reserveCurrency;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public LocalDate getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}	
}