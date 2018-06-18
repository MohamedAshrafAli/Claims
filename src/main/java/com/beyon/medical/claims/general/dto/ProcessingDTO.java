package com.beyon.medical.claims.general.dto;

import java.util.List;
@SuppressWarnings("unused")
public class ProcessingDTO extends ClaimBaseDTO {
	
	private static final long serialVersionUID = 1L;
	
	private DiagnosisDTO primaryDiagnosis;
	private DiagnosisDTO secondaryDiagnosis;
	private List<ProcessingServiceDTO> processingServiceDTOs;
	private Long requestNumber;
	private String claimType;
	private String eventCountry;
	private String claimCondition;
	private String claimStatusReason;
	private boolean isChanged;
	private Long serviceGroupId;
	private String diagType;//for internal; use only

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
	public List<ProcessingServiceDTO> getProcessingServiceDTOs() {
		return processingServiceDTOs;
	}
	public void setProcessingServiceDTOs(List<ProcessingServiceDTO> processingServiceDTOs) {
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
	public boolean isChanged() {
		return isChanged;
	}
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	public Long getServiceGroupId() {
		return serviceGroupId;
	}
	public void setServiceGroupId(Long serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}
	public String getDiagType() {
		return diagType;
	}
	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}
	
}
