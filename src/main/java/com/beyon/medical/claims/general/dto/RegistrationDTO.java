package com.beyon.medical.claims.general.dto;

import java.util.List;
@SuppressWarnings("unused")
public class RegistrationDTO extends ClaimBaseDTO {
	private static final long serialVersionUID = 1L;
	private List<RegistrationFileDTO> registrationFileDTOs;
	public List<RegistrationFileDTO> getRegistrationFileDTOs() {
		return registrationFileDTOs;
	}
	public void setRegistrationFileDTOs(List<RegistrationFileDTO> registrationFileDTOs) {
		this.registrationFileDTOs = registrationFileDTOs;
	}

}
