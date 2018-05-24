package com.beyon.medical.claims.reimbursement.dto;

import java.time.LocalDate;

import com.beyon.medical.claims.general.dto.RegistrationBaseDTO;

public class ReimbursementRegistrationDTO extends RegistrationBaseDTO {
	private String voucherNumber;
	private LocalDate serviceFmDate;
	private Integer requestAmt;
	private String paymentWay;
	private String ibanNum;
	private String emiratesId;
	
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public LocalDate getServiceFmDate() {
		return serviceFmDate;
	}
	public void setServiceFmDate(LocalDate serviceFmDate) {
		this.serviceFmDate = serviceFmDate;
	}
	public Integer getRequestAmt() {
		return requestAmt;
	}
	public void setRequestAmt(Integer requestAmt) {
		this.requestAmt = requestAmt;
	}
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
	public String getIbanNum() {
		return ibanNum;
	}
	public void setIbanNum(String ibanNum) {
		this.ibanNum = ibanNum;
	}
	public String getEmiratesId() {
		return emiratesId;
	}
	public void setEmiratesId(String emiratesId) {
		this.emiratesId = emiratesId;
	}
}
