package com.beyon.medical.claims.reimbursement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.beyon.medical.claims.general.dto.RegistrationBaseDTO;

public class ReimbursementRegistrationDTO extends RegistrationBaseDTO {
	private String id;
	private String claimRefNo;
	private String encType;
	private String reqType;
	private LocalDate reqReceivedDate;
	private String prevRequest;
	private String documentLink;
	private String voucherNumber;
	private LocalDate serviceFmDate;
	private BigDecimal requestAmt;
	private BigDecimal requestAmtBC;
	private String paymentWay;
	private String ibanNum;
	
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
	public BigDecimal getRequestAmt() {
		return requestAmt;
	}
	public void setRequestAmt(BigDecimal requestAmt) {
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
	
	public String getEncType() {
		return encType;
	}
	public void setEncType(String encType) {
		this.encType = encType;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public LocalDate getReqReceivedDate() {
		return reqReceivedDate;
	}
	public void setReqReceivedDate(LocalDate reqReceivedDate) {
		this.reqReceivedDate = reqReceivedDate;
	}
	public String getPrevRequest() {
		return prevRequest;
	}
	public void setPrevRequest(String prevRequest) {
		this.prevRequest = prevRequest;
	}
	public String getDocumentLink() {
		return documentLink;
	}
	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClaimRefNo() {
		return claimRefNo;
	}
	public void setClaimRefNo(String claimRefNo) {
		this.claimRefNo = claimRefNo;
	}
	public BigDecimal getRequestAmtBC() {
		return requestAmtBC;
	}
	public void setRequestAmtBC(BigDecimal requestAmtBC) {
		this.requestAmtBC = requestAmtBC;
	}
}
