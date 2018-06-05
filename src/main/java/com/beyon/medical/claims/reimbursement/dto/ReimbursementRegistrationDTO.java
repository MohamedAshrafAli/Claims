package com.beyon.medical.claims.reimbursement.dto;

import java.util.List;

public class ReimbursementRegistrationDTO extends ReimbursementDTO {

	private List<RegistrationFileDTO> registrationFileDTOs;

	public List<RegistrationFileDTO> getRegistrationFileDTOs() {
		return registrationFileDTOs;
	}
	public void setRegistrationFileDTOs(List<RegistrationFileDTO> registrationFileDTOs) {
		this.registrationFileDTOs = registrationFileDTOs;
	}
}
