package com.beyon.medical.claims.general.dto;

public class RegistrationBaseDTO {
	private String memberName;
	private Integer mobileNum1;
	private Integer mobileNum2;
	private String email1;
	private String email2;
	private String policyNumber;
	private String memberNumber;
	private String encType;
	private String reqType;
	private String reqReceivedDate;
	private String prevRequest;
	private String source;
	private String documentLink;
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Integer getMobileNum1() {
		return mobileNum1;
	}
	public void setMobileNum1(Integer mobileNum1) {
		this.mobileNum1 = mobileNum1;
	}
	public Integer getMobileNum2() {
		return mobileNum2;
	}
	public void setMobileNum2(Integer mobileNum2) {
		this.mobileNum2 = mobileNum2;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
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
	public String getReqReceivedDate() {
		return reqReceivedDate;
	}
	public void setReqReceivedDate(String reqReceivedDate) {
		this.reqReceivedDate = reqReceivedDate;
	}
	public String getPrevRequest() {
		return prevRequest;
	}
	public void setPrevRequest(String prevRequest) {
		this.prevRequest = prevRequest;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDocumentLink() {
		return documentLink;
	}
	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}
}
