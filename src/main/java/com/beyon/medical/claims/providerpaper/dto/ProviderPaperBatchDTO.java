package com.beyon.medical.claims.providerpaper.dto;

import java.time.LocalDate;

public class ProviderPaperBatchDTO {
	private String compId;
	private String divisionId;
	private String departmentId;
	private String moduleType;
	private String batchId;
	private LocalDate uploadedDate;
	private String uploadedType;
	private String uploadedFormat;
	private String uploadedFileType;
	private String fileLocation;
	private String fileName;
	private String errorFileType;
	private String errorFileLocation;
	private LocalDate errorFileCreatedDate;
	private Long totalTransaction;
	private Long processedTransaction;
	private Long errorTransaction;
	private String status;
	private LocalDate createdDate;
	private String createdUser;
	private String rejectionDescription;
	
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public LocalDate getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(LocalDate uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public String getUploadedType() {
		return uploadedType;
	}
	public void setUploadedType(String uploadedType) {
		this.uploadedType = uploadedType;
	}
	public String getUploadedFormat() {
		return uploadedFormat;
	}
	public void setUploadedFormat(String uploadedFormat) {
		this.uploadedFormat = uploadedFormat;
	}
	public String getUploadedFileType() {
		return uploadedFileType;
	}
	public void setUploadedFileType(String uploadedFileType) {
		this.uploadedFileType = uploadedFileType;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getErrorFileType() {
		return errorFileType;
	}
	public void setErrorFileType(String errorFileType) {
		this.errorFileType = errorFileType;
	}
	public String getErrorFileLocation() {
		return errorFileLocation;
	}
	public void setErrorFileLocation(String errorFileLocation) {
		this.errorFileLocation = errorFileLocation;
	}
	public LocalDate getErrorFileCreatedDate() {
		return errorFileCreatedDate;
	}
	public void setErrorFileCreatedDate(LocalDate errorFileCreatedDate) {
		this.errorFileCreatedDate = errorFileCreatedDate;
	}
	public Long getTotalTransaction() {
		return totalTransaction;
	}
	public void setTotalTransaction(Long totalTransaction) {
		this.totalTransaction = totalTransaction;
	}
	public Long getProcessedTransaction() {
		return processedTransaction;
	}
	public void setProcessedTransaction(Long processedTransaction) {
		this.processedTransaction = processedTransaction;
	}
	public Long getErrorTransaction() {
		return errorTransaction;
	}
	public void setErrorTransaction(Long errorTransaction) {
		this.errorTransaction = errorTransaction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getRejectionDescription() {
		return rejectionDescription;
	}
	public void setRejectionDescription(String rejectionDescription) {
		this.rejectionDescription = rejectionDescription;
	}

}
