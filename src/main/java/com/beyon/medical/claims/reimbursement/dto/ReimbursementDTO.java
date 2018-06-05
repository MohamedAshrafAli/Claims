package com.beyon.medical.claims.reimbursement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.beyon.medical.claims.general.dto.ClaimBaseDTO;

public class ReimbursementDTO extends ClaimBaseDTO {
	private Long id;
	private String claimRefNo;
	private String encType;
	private String reqType;
	private LocalDate reqReceivedDate;
	private String prevRequest;
	private String voucherNumber;
	private BigDecimal requestAmt;
	private String paymentWay;
	private String paymentRefNum;
	private LocalDate serviceFmDate;
	private BigDecimal requestAmtBC;
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClaimRefNo() {
		return claimRefNo;
	}
	public void setClaimRefNo(String claimRefNo) {
		this.claimRefNo = claimRefNo;
	}
	public String getPaymentRefNum() {
		return paymentRefNum;
	}
	public void setPaymentRefNum(String paymentRefNum) {
		this.paymentRefNum = paymentRefNum;
	}
	
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
}
