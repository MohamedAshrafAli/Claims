package com.beyon.medical.claims.general.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@SuppressWarnings("unused")
public class ProcessingServiceDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long reimbursementProcessId;
	private Long claimsRegistrationId;
	private Long claimsSequenceNo;
	private LocalDate treatmentFromDate;
	private LocalDate treatmentToDate;
	private Integer noOfTreamentDays;
	private String serviceType;
	private String serviceId;
	private String benefitId;
	private String subBenefitId;
	private String currencyId;
	private BigDecimal requestedAmount;
	private BigDecimal requestedAmountBC;
	private BigDecimal policyDeductibleAmount;
	private BigDecimal policyDeductibleAmountBC;
	private BigDecimal manualDeductionAmount;
	private BigDecimal manualDeductionAmountBC;
	private BigDecimal penaltyAmount;
	private BigDecimal penaltyAmountBC;
	private BigDecimal suggestedAmout;
	private BigDecimal suggestedAmoutBC;
	private BigDecimal approvedAmount;
	private BigDecimal approvedAmountBC;
	private String internalRejectionCode;
	private BigDecimal rejectedAmount;
	private BigDecimal rejectedAmountBC;
	private String claimStatus;
	private LocalDate statusDate;
	private String internalRemarks;
	private String externalRemarks;
	private String lossType;
	private String estType;
	private String createdBy;
	private LocalDate createdDate;
	private String updatedBy;
	private LocalDate updatedDate;
	private String approvedBy;
	private LocalDate approvedDate;
	private String currencyType;
	private boolean isChanged;
	private String treatmentCodeDisplayName;

	
	public Long getReimbursementProcessId() {
		return reimbursementProcessId;
	}
	public void setReimbursementProcessId(Long reimbursementProcessId) {
		this.reimbursementProcessId = reimbursementProcessId;
	}
	public Long getClaimsRegistrationId() {
		return claimsRegistrationId;
	}
	public void setClaimsRegistrationId(Long claimsRegistrationId) {
		this.claimsRegistrationId = claimsRegistrationId;
	}
	public Long getClaimsSequenceNo() {
		return claimsSequenceNo;
	}
	public void setClaimsSequenceNo(Long claimsSequenceNo) {
		this.claimsSequenceNo = claimsSequenceNo;
	}
	public LocalDate getTreatmentFromDate() {
		return treatmentFromDate;
	}
	public void setTreatmentFromDate(LocalDate treatmentFromDate) {
		this.treatmentFromDate = treatmentFromDate;
	}
	public LocalDate getTreatmentToDate() {
		return treatmentToDate;
	}
	public void setTreatmentToDate(LocalDate treatmentToDate) {
		this.treatmentToDate = treatmentToDate;
	}
	public Integer getNoOfTreamentDays() {
		return noOfTreamentDays;
	}
	public void setNoOfTreamentDays(Integer noOfTreamentDays) {
		this.noOfTreamentDays = noOfTreamentDays;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public BigDecimal getRequestedAmountBC() {
		return requestedAmountBC;
	}
	public void setRequestedAmountBC(BigDecimal requestedAmountBC) {
		this.requestedAmountBC = requestedAmountBC;
	}
	public BigDecimal getPolicyDeductibleAmount() {
		return policyDeductibleAmount;
	}
	public void setPolicyDeductibleAmount(BigDecimal policyDeductibleAmount) {
		this.policyDeductibleAmount = policyDeductibleAmount;
	}
	public BigDecimal getPolicyDeductibleAmountBC() {
		return policyDeductibleAmountBC;
	}
	public void setPolicyDeductibleAmountBC(BigDecimal policyDeductibleAmountBC) {
		this.policyDeductibleAmountBC = policyDeductibleAmountBC;
	}
	public BigDecimal getManualDeductionAmount() {
		return manualDeductionAmount;
	}
	public void setManualDeductionAmount(BigDecimal manualDeductionAmount) {
		this.manualDeductionAmount = manualDeductionAmount;
	}
	public BigDecimal getManualDeductionAmountBC() {
		return manualDeductionAmountBC;
	}
	public void setManualDeductionAmountBC(BigDecimal manualDeductionAmountBC) {
		this.manualDeductionAmountBC = manualDeductionAmountBC;
	}
	public BigDecimal getPenaltyAmount() {
		return penaltyAmount;
	}
	public void setPenaltyAmount(BigDecimal penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}
	public BigDecimal getPenaltyAmountBC() {
		return penaltyAmountBC;
	}
	public void setPenaltyAmountBC(BigDecimal penaltyAmountBC) {
		this.penaltyAmountBC = penaltyAmountBC;
	}
	public BigDecimal getSuggestedAmout() {
		return suggestedAmout;
	}
	public void setSuggestedAmout(BigDecimal suggestedAmout) {
		this.suggestedAmout = suggestedAmout;
	}
	public BigDecimal getSuggestedAmoutBC() {
		return suggestedAmoutBC;
	}
	public void setSuggestedAmoutBC(BigDecimal suggestedAmoutBC) {
		this.suggestedAmoutBC = suggestedAmoutBC;
	}
	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public BigDecimal getApprovedAmountBC() {
		return approvedAmountBC;
	}
	public void setApprovedAmountBC(BigDecimal approvedAmountBC) {
		this.approvedAmountBC = approvedAmountBC;
	}
	public String getInternalRejectionCode() {
		return internalRejectionCode;
	}
	public void setInternalRejectionCode(String internalRejectionCode) {
		this.internalRejectionCode = internalRejectionCode;
	}
	public BigDecimal getRejectedAmount() {
		return rejectedAmount;
	}
	public void setRejectedAmount(BigDecimal rejectedAmount) {
		this.rejectedAmount = rejectedAmount;
	}
	public BigDecimal getRejectedAmountBC() {
		return rejectedAmountBC;
	}
	public void setRejectedAmountBC(BigDecimal rejectedAmountBC) {
		this.rejectedAmountBC = rejectedAmountBC;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public LocalDate getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(LocalDate statusDate) {
		this.statusDate = statusDate;
	}
	public String getInternalRemarks() {
		return internalRemarks;
	}
	public void setInternalRemarks(String internalRemarks) {
		this.internalRemarks = internalRemarks;
	}
	public String getExternalRemarks() {
		return externalRemarks;
	}
	public void setExternalRemarks(String externalRemarks) {
		this.externalRemarks = externalRemarks;
	}
	public String getLossType() {
		return lossType;
	}
	public void setLossType(String lossType) {
		this.lossType = lossType;
	}
	public String getEstType() {
		return estType;
	}
	public void setEstType(String estType) {
		this.estType = estType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
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
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public boolean isChanged() {
		return isChanged;
	}
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
	public String getTreatmentCodeDisplayName() {
		return this.serviceId +" / "+ this.subBenefitId;
	}
	public void setTreatmentCodeDisplayName(String treatmentCodeDisplayName) {
		this.treatmentCodeDisplayName = treatmentCodeDisplayName;
	}	
}
