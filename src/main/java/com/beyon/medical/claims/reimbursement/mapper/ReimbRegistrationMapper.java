package com.beyon.medical.claims.reimbursement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.utils.DateUtil;

public class ReimbRegistrationMapper {
	
	public static ReimbursementRegistrationDTO getViewReimbursementRegistrationDTO(ResultSet row) throws SQLException
	{
		ReimbursementRegistrationDTO registrationDTO = new ReimbursementRegistrationDTO();
		registrationDTO.setDocumentLink(null);
		registrationDTO.setEmail1(row.getString("EmailId1"));
		registrationDTO.setEmail2(row.getString("EmailId2"));
		registrationDTO.setUidId(row.getString("EmiratesId"));
		registrationDTO.setEncType(row.getString("EncounterType"));
		registrationDTO.setIbanNum(row.getString("PaymentRefNo"));
		registrationDTO.setMemberName(row.getString("MemberName"));
		registrationDTO.setMemberNumber(row.getString("MemberNumber"));
		registrationDTO.setMobileNum1(row.getString("MobileNumber1"));
		registrationDTO.setMobileNum2(row.getString("MobileNumber2"));
		registrationDTO.setPaymentWay(row.getString("PaymentRefNo"));
		registrationDTO.setPolicyNumber(row.getString("Policynumber"));
		registrationDTO.setPrevRequest(row.getString("PrevReqNo"));
		registrationDTO.setReqReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RequestReceivedDate")));
		registrationDTO.setReqType(row.getString("RequestType"));
		registrationDTO.setRequestAmt(row.getBigDecimal("RequestedAmt"));
		registrationDTO.setServiceFmDate(DateUtil.convertSQlDateToLocalDate(row.getDate("ServiceFromDate")));
		registrationDTO.setSourceType(null);
		registrationDTO.setVoucherNumber(row.getString("VoucherNumber"));
		registrationDTO.setCardNumber(row.getString("CardNumber"));
		registrationDTO.setId(row.getString("Id"));
		registrationDTO.setClaimRefNo(row.getString("ClaimRefNo"));
		return registrationDTO;
	}
	
	public static ReimbursementRegistrationDTO getReimbursementRegistrationDTO(ResultSet row) throws SQLException
	{
		ReimbursementRegistrationDTO registrationDTO = new ReimbursementRegistrationDTO();
		registrationDTO.setCardNumber(row.getString("CardNumber"));
		registrationDTO.setCustomerId(row.getString("CustomerId"));
		registrationDTO.setDeptId(row.getString("DeptId"));
		registrationDTO.setDivisionId(row.getString("DivisionId"));
		registrationDTO.setEmail1(row.getString("EmailId1"));
		registrationDTO.setEmail2(null);
		registrationDTO.setInsuredId(row.getString("InsuredId"));
		registrationDTO.setIsDependent(row.getString("IsDependent"));
		registrationDTO.setMemberCategory(row.getString("MemberCategory"));
		registrationDTO.setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		registrationDTO.setMemberName(row.getString("MemberName"));
		registrationDTO.setMemberNumber(row.getString("MemberNumber"));
		registrationDTO.setMemberType(row.getString("MemberType"));
		registrationDTO.setMobileNum1(row.getString("Mobile1"));
		registrationDTO.setMobileNum2(null);
		registrationDTO.setNationalId(row.getString("NationalId"));
		registrationDTO.setParentMemberId(null);
		registrationDTO.setPassportNumber(null);
		registrationDTO.setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		registrationDTO.setPolicyNumber(row.getString("PolicyNumber"));
		registrationDTO.setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		registrationDTO.setProductId(row.getString("ProductId"));
		registrationDTO.setRelationWithPrimary(row.getString("RelationWithPrimary"));
		registrationDTO.setRiskAmndVerId(row.getInt("RiskAmndVerId"));
		registrationDTO.setRiskCOB(row.getString("CobId"));
		registrationDTO.setRiskFlex1(row.getString("RiskFlex1"));
		registrationDTO.setRiskFlex2(row.getString("RiskFlex2"));
		registrationDTO.setRiskFlex3(row.getString("RiskFlex3"));
		registrationDTO.setRiskFlex4(row.getString("RiskFlex4"));
		registrationDTO.setRiskFlex5(row.getString("RiskFlex5"));
		registrationDTO.setRiskFlex6(row.getString("RiskFlex6"));
		registrationDTO.setRiskFlex7(row.getString("RiskFlex7"));
		registrationDTO.setRiskFlex8(row.getString("RiskFlex8"));
		registrationDTO.setRiskFlex9(row.getString("RiskFlex9"));
		registrationDTO.setRiskFlex10(row.getString("RiskFlex10"));
		registrationDTO.setRiskFlexDesc1(row.getString("RiskFlexDesc1"));
		registrationDTO.setRiskFlexDesc2(row.getString("RiskFlexDesc2"));
		registrationDTO.setRiskFlexDesc3(row.getString("RiskFlexDesc3"));
		registrationDTO.setRiskFlexDesc4(row.getString("RiskFlexDesc4"));
		registrationDTO.setRiskFlexDesc5(row.getString("RiskFlexDesc5"));
		registrationDTO.setRiskFlexDesc6(row.getString("RiskFlexDesc6"));
		registrationDTO.setRiskFlexDesc7(row.getString("RiskFlexDesc7"));
		registrationDTO.setRiskFlexDesc8(row.getString("RiskFlexDesc8"));
		registrationDTO.setRiskFlexDesc9(row.getString("RiskFlexDesc9"));
		registrationDTO.setRiskFlexDesc10(row.getString("RiskFlexDesc10"));
		registrationDTO.setRiskFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskFromDate")));
		registrationDTO.setRiskToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskToDate")));
		registrationDTO.setRiskId(row.getString("RiskId"));
		registrationDTO.setRiskType(row.getString("RiskType"));
		registrationDTO.setUidId(row.getString("EmiratesId"));
		return registrationDTO;
	}

}
