package com.beyon.medical.claims.general.dto;

import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("unused")
public class DiagnosisDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long sgsId;
	private String diagId;
	private String diagType;
	private String createdBy;
	private String updatedBy;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	private boolean changed;
	public Long getSgsId() {
		return sgsId;
	}
	public void setSgsId(Long sgsId) {
		this.sgsId = sgsId;
	}
	public String getDiagId() {
		return diagId;
	}
	public void setDiagId(String diagId) {
		this.diagId = diagId;
	}
	public String getDiagType() {
		return diagType;
	}
	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isChanged() {
		return changed;
	}
	public void setChanged(boolean isChanged) {
		this.changed = isChanged;
	}
	
}
