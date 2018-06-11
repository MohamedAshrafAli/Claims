package com.beyon.medical.claims.reimbursement.dto;

import java.util.List;

import com.beyon.medical.claims.general.dto.DiagnosisDTO;

public class ReimbursementProcessingDTO extends ReimbursementDTO {
	
	private DiagnosisDTO primaryDiagnosis;
	private DiagnosisDTO secondaryDiagnosis;
	private List<ReimbursementProcessingServiceDTO> processingServiceDTOs;
	private Long requestNumber;
	private String claimType;
	private String eventCountry;
	private String claimCondition;
	private String claimStatusReason;
	private boolean isChanged;
	
	public List<ReimbursementProcessingServiceDTO> getProcessingServiceDTOs() {
		return processingServiceDTOs;
	}
	public void setProcessingServiceDTOs(List<ReimbursementProcessingServiceDTO> processingServiceDTOs) {
		this.processingServiceDTOs = processingServiceDTOs;
	}
	public Long getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(Long requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getEventCountry() {
		return eventCountry;
	}
	public void setEventCountry(String eventCountry) {
		this.eventCountry = eventCountry;
	}
	public String getClaimCondition() {
		return claimCondition;
	}
	public void setClaimCondition(String claimCondition) {
		this.claimCondition = claimCondition;
	}
	public String getClaimStatusReason() {
		return claimStatusReason;
	}
	public void setClaimStatusReason(String claimStatusReason) {
		this.claimStatusReason = claimStatusReason;
	}
	public DiagnosisDTO getPrimaryDiagnosis() {
		return primaryDiagnosis;
	}
	public void setPrimaryDiagnosis(DiagnosisDTO primaryDiagnosis) {
		this.primaryDiagnosis = primaryDiagnosis;
	}
	public DiagnosisDTO getSecondaryDiagnosis() {
		return secondaryDiagnosis;
	}
	public void setSecondaryDiagnosis(DiagnosisDTO secondaryDiagnosis) {
		this.secondaryDiagnosis = secondaryDiagnosis;
	}
	public boolean isChanged() {
		return isChanged;
	}
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	
}
