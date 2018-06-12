package com.beyon.medical.claims.reimbursement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReimbursementEstimateDTO {
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
	private BigDecimal estimatedAmt;
	private BigDecimal estimatedAmtBC;
	private String createdBy;
	private BigDecimal outStandingAmt;
	private BigDecimal outStandingAmtBC;
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
	public BigDecimal getOutStandingAmt() {
		return outStandingAmt;
	}
	public void setOutStandingAmt(BigDecimal outStandingAmt) {
		this.outStandingAmt = outStandingAmt;
	}
	public BigDecimal getOutStandingAmtBC() {
		return outStandingAmtBC;
	}
	public void setOutStandingAmtBC(BigDecimal outStandingAmtBC) {
		this.outStandingAmtBC = outStandingAmtBC;
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
}