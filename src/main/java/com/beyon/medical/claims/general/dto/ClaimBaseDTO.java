package com.beyon.medical.claims.general.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@SuppressWarnings("unused")
public class ClaimBaseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String description;
	private String status;
	private String createdBy;
	private String deptId;
	private String divisionId;
	private String sourceType;
	private String userDivision;
	private String moduleType;
	private String providerId;
	private String baseCurrency;
	private String reportedBy;
	private String reportedMethodDesc;
	private String reportedByDesc;
	private String reportedById;
	private PolicyDetailsDTO policyDetailsDTO;
	private MemberDetailsDTO memberDetailsDTO;
	private String compId;
	private LocalDate serviceFmDate;
	private BigDecimal requestAmtBC;
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
	private String claimantIsCustomer;
	private String reportedByCustomer;
	private String reportedByInsured;
	private String claimNumber;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getUserDivision() {
		return userDivision;
	}
	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getReportedMethodDesc() {
		return reportedMethodDesc;
	}
	public void setReportedMethodDesc(String reportedMethodDesc) {
		this.reportedMethodDesc = reportedMethodDesc;
	}
	public String getReportedByDesc() {
		return reportedByDesc;
	}
	public void setReportedByDesc(String reportedByDesc) {
		this.reportedByDesc = reportedByDesc;
	}
	public String getReportedById() {
		return reportedById;
	}
	public void setReportedById(String reportedById) {
		this.reportedById = reportedById;
	}
	public PolicyDetailsDTO getPolicyDetailsDTO() {
		return policyDetailsDTO;
	}
	public void setPolicyDetailsDTO(PolicyDetailsDTO policyDetailsDTO) {
		this.policyDetailsDTO = policyDetailsDTO;
	}
	public MemberDetailsDTO getMemberDetailsDTO() {
		return memberDetailsDTO;
	}
	public void setMemberDetailsDTO(MemberDetailsDTO memberDetailsDTO) {
		this.memberDetailsDTO = memberDetailsDTO;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
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
	public String getPaymentRefNum() {
		return paymentRefNum;
	}
	public void setPaymentRefNum(String paymentRefNum) {
		this.paymentRefNum = paymentRefNum;
	}
	public String getClaimantIsCustomer() {
		return claimantIsCustomer;
	}
	public void setClaimantIsCustomer(String claimantIsCustomer) {
		this.claimantIsCustomer = claimantIsCustomer;
	}
	public String getReportedByCustomer() {
		return reportedByCustomer;
	}
	public void setReportedByCustomer(String reportedByCustomer) {
		this.reportedByCustomer = reportedByCustomer;
	}
	public String getReportedByInsured() {
		return reportedByInsured;
	}
	public void setReportedByInsured(String reportedByInsured) {
		this.reportedByInsured = reportedByInsured;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

}
