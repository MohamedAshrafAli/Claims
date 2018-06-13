package com.beyon.medical.claims.providerpaper.dto;

import java.math.BigDecimal;

public class ProviderPaperBatchDetailsDTO {
	private Long batchId;
	private Long level1TxnRef;
	private Long level2TxnRef;
	private String status;
	private String errorRemarks;
	private String riskId;
	private String voucherNumber;
	private String invoiceNumber;
	private BigDecimal requestedAmount;
	private BigDecimal requestedAmountBC;
	private String currencyId;
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorRemarks() {
		return errorRemarks;
	}
	public void setErrorRemarks(String errorRemarks) {
		this.errorRemarks = errorRemarks;
	}
	public String getRiskId() {
		return riskId;
	}
	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public BigDecimal getRequestedAmountBC() {
		return requestedAmountBC;
	}
	public void setRequestedAmountBC(BigDecimal requestedAmountBC) {
		this.requestedAmountBC = requestedAmountBC;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
}
