package com.beyon.medical.claims.general.dto;

public class AssignedUserDetailsDTO {
	private Long slId;
	private String userId;
	private String userGroupId;
	private String jobStatus;
	public Long getSlId() {
		return slId;
	}
	public void setSlId(Long slId) {
		this.slId = slId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
}
