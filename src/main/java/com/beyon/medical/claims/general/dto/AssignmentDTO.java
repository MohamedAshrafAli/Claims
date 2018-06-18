package com.beyon.medical.claims.general.dto;

@SuppressWarnings("unused")
public class AssignmentDTO extends ClaimBaseDTO {
	
	private static final long serialVersionUID = 1L;
	private Long assignmentId; 
	private AssignedUserDetailsDTO assignedUserDetailsDTO;
	public Long getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}
	public AssignedUserDetailsDTO getAssignedUserDetailsDTO() {
		return assignedUserDetailsDTO;
	}
	public void setAssignedUserDetailsDTO(AssignedUserDetailsDTO assignedUserDetailsDTO) {
		this.assignedUserDetailsDTO = assignedUserDetailsDTO;
	}
     
}
