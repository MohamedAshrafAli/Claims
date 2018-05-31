package com.beyon.medical.claims.reimbursement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReimbursementRegistrationDTO extends ReimbursementDTO {
	private LocalDate serviceFmDate;
	private BigDecimal requestAmtBC;
	private List<RegistrationFileDTO> registrationFileDTOs;

	public LocalDate getServiceFmDate() {
		return serviceFmDate;
	}
	public void setServiceFmDate(LocalDate serviceFmDate) {
		this.serviceFmDate = serviceFmDate;
	}
	public BigDecimal getRequestAmtBC() {
		return requestAmtBC;
	}
	public void setRequestAmtBC(BigDecimal requestAmtBC) {
		this.requestAmtBC = requestAmtBC;
	}
	public List<RegistrationFileDTO> getRegistrationFileDTOs() {
		return registrationFileDTOs;
	}
	public void setRegistrationFileDTOs(List<RegistrationFileDTO> registrationFileDTOs) {
		this.registrationFileDTOs = registrationFileDTOs;
	}
}
