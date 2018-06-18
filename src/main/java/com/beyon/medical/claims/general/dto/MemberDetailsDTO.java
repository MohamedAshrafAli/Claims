package com.beyon.medical.claims.general.dto;

import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("unused")
public class MemberDetailsDTO implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private String memberNumber;
	private String memberName;
	private String memberType;
	private String memberCategory;
	private LocalDate memberDOB;
	private String isDependent;
	private String parentMemberId;	
	private String relationWithPrimary;
	private String nationalId;
	private String uidId;
	private String passportNumber;
	private String cardNumber;
	private LocalDate cardReceivedDate;
	private String mobileNum1;
	private String mobileNum2;
	private String email1;
	private String email2;
	private String gender;
	private String firstName;
	private String lastName;
	private String middleName;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String city;
	private String state;
	private String pincode;
	private String country;
	private String primaryPhoneNo;
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberCategory() {
		return memberCategory;
	}
	public void setMemberCategory(String memberCategory) {
		this.memberCategory = memberCategory;
	}
	public LocalDate getMemberDOB() {
		return memberDOB;
	}
	public void setMemberDOB(LocalDate memberDOB) {
		this.memberDOB = memberDOB;
	}
	public String getIsDependent() {
		return isDependent;
	}
	public void setIsDependent(String isDependent) {
		this.isDependent = isDependent;
	}
	public String getParentMemberId() {
		return parentMemberId;
	}
	public void setParentMemberId(String parentMemberId) {
		this.parentMemberId = parentMemberId;
	}
	public String getRelationWithPrimary() {
		return relationWithPrimary;
	}
	public void setRelationWithPrimary(String relationWithPrimary) {
		this.relationWithPrimary = relationWithPrimary;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getUidId() {
		return uidId;
	}
	public void setUidId(String uidId) {
		this.uidId = uidId;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getCardReceivedDate() {
		return cardReceivedDate;
	}
	public void setCardReceivedDate(LocalDate cardReceivedDate) {
		this.cardReceivedDate = cardReceivedDate;
	}
	public String getMobileNum1() {
		return mobileNum1;
	}
	public void setMobileNum1(String mobileNum1) {
		this.mobileNum1 = mobileNum1;
	}
	public String getMobileNum2() {
		return mobileNum2;
	}
	public void setMobileNum2(String mobileNum2) {
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getAddress4() {
		return address4;
	}
	public void setAddress4(String address4) {
		this.address4 = address4;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPrimaryPhoneNo() {
		return primaryPhoneNo;
	}
	public void setPrimaryPhoneNo(String primaryPhoneNo) {
		this.primaryPhoneNo = primaryPhoneNo;
	}

	
}
