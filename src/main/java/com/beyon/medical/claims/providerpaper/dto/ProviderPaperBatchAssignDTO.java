package com.beyon.medical.claims.providerpaper.dto;

import java.time.LocalDate;

public class ProviderPaperBatchAssignDTO {
	private Long batchId;
	private Long sgsId;
	private Long level1TxnRef;
	private Long level2TxnRef;
	private String assignType;
	private String assignGroup;
	private String assignedUser;
	private String assignedUserGroupId;
	private String createdUser;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	private LocalDate approvedDate;
	private String updatedUser;
	private String approvedUser;
	
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public Long getSgsId() {
		return sgsId;
	}
	public void setSgsId(Long sgsId) {
		this.sgsId = sgsId;
	}
	public Long getLevel1TxnRef() {
		return level1TxnRef;
	}
	public void setLevel1TxnRef(Long level1TxnRef) {
		this.level1TxnRef = level1TxnRef;
	}
	public Long getLevel2TxnRef() {
		return level2TxnRef;
	}
	public void setLevel2TxnRef(Long level2TxnRef) {
		this.level2TxnRef = level2TxnRef;
	}
	public String getAssignType() {
		return assignType;
	}
	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}
	public String getAssignGroup() {
		return assignGroup;
	}
	public void setAssignGroup(String assignGroup) {
		this.assignGroup = assignGroup;
	}
	public String getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}
	public String getAssignedUserGroupId() {
		return assignedUserGroupId;
	}
	public void setAssignedUserGroupId(String assignedUserGroupId) {
		this.assignedUserGroupId = assignedUserGroupId;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
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
	public LocalDate getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
	public String getApprovedUser() {
		return approvedUser;
	}
	public void setApprovedUser(String approvedUser) {
		this.approvedUser = approvedUser;
	}
	
}
