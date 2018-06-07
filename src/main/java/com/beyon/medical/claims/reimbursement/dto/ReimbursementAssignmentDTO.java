package com.beyon.medical.claims.reimbursement.dto;

import com.beyon.medical.claims.general.dto.AssignedUserDetailsDTO;

public class ReimbursementAssignmentDTO extends ReimbursementDTO {
	
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
