package com.beyon.medical.claims.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.beyon.medical.claims.general.dto.MemberDetailsDTO;
import com.beyon.medical.claims.general.dto.PolicyDetailsDTO;
import com.beyon.medical.claims.general.dto.RegistrationDTO;
import com.beyon.medical.claims.general.dto.RegistrationFileDTO;
import com.beyon.medical.claims.utils.DateUtil;

public class RegistrationMapper {
	
	public static RegistrationDTO getViewRegistrationDTO(ResultSet row) throws SQLException
	{
		RegistrationDTO registrationDTO = new RegistrationDTO();
		PolicyDetailsDTO policyDetailsDTO = new PolicyDetailsDTO();
		MemberDetailsDTO memberDetailsDTO = new MemberDetailsDTO();
		registrationDTO.setMemberDetailsDTO(memberDetailsDTO);
		registrationDTO.setPolicyDetailsDTO(policyDetailsDTO);
		registrationDTO.getMemberDetailsDTO().setEmail1(row.getString("EmailId1"));
		registrationDTO.getMemberDetailsDTO().setEmail2(row.getString("EmailId2"));
		registrationDTO.getMemberDetailsDTO().setUidId(row.getString("EmiratesId"));
		registrationDTO.setEncType(row.getString("EncounterType"));
		registrationDTO.setPaymentRefNum(row.getString("PaymentRefNo"));
		registrationDTO.getMemberDetailsDTO().setMemberName(row.getString("MemberName"));
		registrationDTO.getMemberDetailsDTO().setMemberNumber(row.getString("MemberNumber"));
		registrationDTO.getMemberDetailsDTO().setMobileNum1(row.getString("MobileNumber1"));
		registrationDTO.getMemberDetailsDTO().setMobileNum2(row.getString("MobileNumber2"));
		registrationDTO.setPaymentWay(row.getString("PaymentWay"));
		registrationDTO.getPolicyDetailsDTO().setPolicyNumber(row.getString("Policynumber"));
		registrationDTO.setPrevRequest(row.getString("PrevReqNo"));
		registrationDTO.setReqReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RequestReceivedDate")));
		registrationDTO.setReqType(row.getString("RequestType"));
		registrationDTO.setRequestAmt(row.getBigDecimal("RequestedAmt"));
		registrationDTO.setServiceFmDate(DateUtil.convertSQlDateToLocalDate(row.getDate("ServiceFromDate")));
		registrationDTO.setVoucherNumber(row.getString("VoucherNumber"));
		registrationDTO.getMemberDetailsDTO().setCardNumber(row.getString("CardNumber"));
		registrationDTO.setId(row.getLong("Id"));
		registrationDTO.setClaimRefNo(row.getString("ClaimRefNo"));
		return registrationDTO;
	}
	
	public static RegistrationDTO getRegistrationDTO(ResultSet row) throws SQLException
	{
		RegistrationDTO registrationDTO = new RegistrationDTO();
		PolicyDetailsDTO policyDetailsDTO = new PolicyDetailsDTO();
		MemberDetailsDTO memberDetailsDTO = new MemberDetailsDTO();
		registrationDTO.setMemberDetailsDTO(memberDetailsDTO);
		registrationDTO.setPolicyDetailsDTO(policyDetailsDTO);
		registrationDTO.getMemberDetailsDTO().setCardNumber(row.getString("CardNumber"));
		registrationDTO.getPolicyDetailsDTO().setCustomerId(row.getString("CustomerId"));
		registrationDTO.setDeptId(row.getString("DeptId"));
		registrationDTO.setDivisionId(row.getString("DivisionId"));
		registrationDTO.getMemberDetailsDTO().setEmail1(row.getString("EmailId1"));
		registrationDTO.getMemberDetailsDTO().setEmail2(null);
		registrationDTO.getPolicyDetailsDTO().setInsuredId(row.getString("InsuredId"));
		registrationDTO.getMemberDetailsDTO().setIsDependent(row.getString("IsDependent"));
		registrationDTO.getMemberDetailsDTO().setMemberCategory(row.getString("MemberCategory"));
		registrationDTO.getMemberDetailsDTO().setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		registrationDTO.getMemberDetailsDTO().setMemberName(row.getString("MemberName"));
		registrationDTO.getMemberDetailsDTO().setMemberNumber(row.getString("MemberNumber"));
		registrationDTO.getMemberDetailsDTO().setMemberType(row.getString("MemberType"));
		registrationDTO.getMemberDetailsDTO().setMobileNum1(row.getString("Mobile1"));
		registrationDTO.getMemberDetailsDTO().setMobileNum2(null);
		registrationDTO.getMemberDetailsDTO().setNationalId(row.getString("NationalId"));
		registrationDTO.getMemberDetailsDTO().setParentMemberId(null);
		registrationDTO.getMemberDetailsDTO().setPassportNumber(null);
		registrationDTO.getPolicyDetailsDTO().setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		registrationDTO.getPolicyDetailsDTO().setPolicyNumber(row.getString("PolicyNumber"));
		registrationDTO.getPolicyDetailsDTO().setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		registrationDTO.getPolicyDetailsDTO().setProductId(row.getString("ProductId"));
		registrationDTO.getMemberDetailsDTO().setRelationWithPrimary(row.getString("RelationWithPrimary"));
		registrationDTO.getPolicyDetailsDTO().setRiskAmndVerId(row.getInt("RiskAmndVerId"));
		registrationDTO.getPolicyDetailsDTO().setRiskCOB(row.getString("CobId"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex1(row.getString("RiskFlex1"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex2(row.getString("RiskFlex2"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex3(row.getString("RiskFlex3"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex4(row.getString("RiskFlex4"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex5(row.getString("RiskFlex5"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex6(row.getString("RiskFlex6"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex7(row.getString("RiskFlex7"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex8(row.getString("RiskFlex8"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex9(row.getString("RiskFlex9"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex10(row.getString("RiskFlex10"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc1(row.getString("RiskFlexDesc1"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc2(row.getString("RiskFlexDesc2"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc3(row.getString("RiskFlexDesc3"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc4(row.getString("RiskFlexDesc4"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc5(row.getString("RiskFlexDesc5"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc6(row.getString("RiskFlexDesc6"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc7(row.getString("RiskFlexDesc7"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc8(row.getString("RiskFlexDesc8"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc9(row.getString("RiskFlexDesc9"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc10(row.getString("RiskFlexDesc10"));
		registrationDTO.getPolicyDetailsDTO().setRiskFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskFromDate")));
		registrationDTO.getPolicyDetailsDTO().setRiskToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskToDate")));
		registrationDTO.getPolicyDetailsDTO().setRiskId(row.getString("RiskId"));
		registrationDTO.getPolicyDetailsDTO().setRiskType(row.getString("RiskType"));
		registrationDTO.getMemberDetailsDTO().setUidId(row.getString("EmiratesId"));
		return registrationDTO;
	}
	
	public static RegistrationDTO getRegistrationDTOById(ResultSet row) throws SQLException
	{
		RegistrationDTO registrationDTO = new RegistrationDTO();
		PolicyDetailsDTO policyDetailsDTO = new PolicyDetailsDTO();
		MemberDetailsDTO memberDetailsDTO = new MemberDetailsDTO();
		registrationDTO.setMemberDetailsDTO(memberDetailsDTO);
		registrationDTO.setPolicyDetailsDTO(policyDetailsDTO);
		registrationDTO.getMemberDetailsDTO().setCardNumber(row.getString("CardNumber"));
		registrationDTO.getPolicyDetailsDTO().setCustomerId(row.getString("CustomerId"));
		registrationDTO.setDeptId(row.getString("DeptId"));
		registrationDTO.setDivisionId(row.getString("DivisionId"));
		registrationDTO.getMemberDetailsDTO().setEmail1(row.getString("EmailId1"));
		registrationDTO.getMemberDetailsDTO().setEmail2(row.getString("EmailId2"));
		registrationDTO.getPolicyDetailsDTO().setInsuredId(row.getString("InsuredId"));
		registrationDTO.getMemberDetailsDTO().setIsDependent(row.getString("IsDependent"));
		registrationDTO.getMemberDetailsDTO().setMemberCategory(row.getString("MemberCategory"));
		registrationDTO.getMemberDetailsDTO().setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		registrationDTO.getMemberDetailsDTO().setMemberName(row.getString("MemberName"));
		registrationDTO.getMemberDetailsDTO().setMemberNumber(row.getString("MemberNumber"));
		registrationDTO.getMemberDetailsDTO().setMemberType(row.getString("MemberType"));
		registrationDTO.getMemberDetailsDTO().setMobileNum1(row.getString("MobileNumber1"));
		registrationDTO.getMemberDetailsDTO().setMobileNum2(row.getString("MobileNumber2"));
		registrationDTO.getMemberDetailsDTO().setNationalId(row.getString("NationalId"));
		registrationDTO.getMemberDetailsDTO().setParentMemberId(row.getString("ParentId"));
		registrationDTO.getMemberDetailsDTO().setPassportNumber(row.getString("PassportNumber"));
		registrationDTO.getPolicyDetailsDTO().setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		registrationDTO.getPolicyDetailsDTO().setPolicyNumber(row.getString("Policynumber"));
		registrationDTO.getPolicyDetailsDTO().setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		registrationDTO.getPolicyDetailsDTO().setProductId(row.getString("ProductId"));
		registrationDTO.getMemberDetailsDTO().setRelationWithPrimary(row.getString("Relation"));
		registrationDTO.getPolicyDetailsDTO().setRiskAmndVerId(row.getInt("RiskAmendmentId"));
		registrationDTO.getPolicyDetailsDTO().setRiskCOB(row.getString("CobId"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex1(row.getString("RiskFlex1"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex2(row.getString("RiskFlex2"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex3(row.getString("RiskFlex3"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex4(row.getString("RiskFlex4"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex5(row.getString("RiskFlex5"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex6(row.getString("RiskFlex6"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex7(row.getString("RiskFlex7"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex8(row.getString("RiskFlex8"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex9(row.getString("RiskFlex9"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlex10(row.getString("RiskFlex10"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc1(row.getString("RiskFlexDescription1"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc2(row.getString("RiskFlexDescription2"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc3(row.getString("RiskFlexDescription3"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc4(row.getString("RiskFlexDescription4"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc5(row.getString("RiskFlexDescription5"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc6(row.getString("RiskFlexDescription6"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc7(row.getString("RiskFlexDescription7"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc8(row.getString("RiskFlexDescription8"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc9(row.getString("RiskFlexDescription9"));
		registrationDTO.getPolicyDetailsDTO().setRiskFlexDesc10(row.getString("RiskFlexDescription10"));
		registrationDTO.getPolicyDetailsDTO().setRiskFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskFromDate")));
		registrationDTO.getPolicyDetailsDTO().setRiskToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskToDate")));
		registrationDTO.getPolicyDetailsDTO().setRiskId(row.getString("RiskId"));
		registrationDTO.getPolicyDetailsDTO().setRiskType(row.getString("RiskType"));
		registrationDTO.getMemberDetailsDTO().setUidId(row.getString("UIDId"));		
		registrationDTO.setEncType(row.getString("EncounterType"));
		registrationDTO.setPaymentRefNum(row.getString("PaymentGatewayRef"));
		registrationDTO.setPaymentWay(row.getString("PaymentWay"));
		registrationDTO.setPrevRequest(row.getString("PrevReqId"));
		registrationDTO.setReqReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RequestReceivedDate")));
		registrationDTO.setReqType(row.getString("RequestType"));
		registrationDTO.setRequestAmt(row.getBigDecimal("RequestedAmt"));
		registrationDTO.setRequestAmtBC(row.getBigDecimal("RequestAmountBaseCurrency"));
		registrationDTO.setServiceFmDate(DateUtil.convertSQlDateToLocalDate(row.getDate("ServiceFromDate")));
		registrationDTO.setSourceType(row.getString("SourceType"));
		registrationDTO.setVoucherNumber(row.getString("VoucherNumber"));
		registrationDTO.setId(row.getLong("Id"));
		registrationDTO.setClaimRefNo(row.getString("ClaimRefNo"));
		registrationDTO.setCompId(row.getString("compId"));
		registrationDTO.setDescription(row.getString("Description"));
		registrationDTO.setClaimantIsCustomer(row.getString("ClaimantIsCustomer"));
		registrationDTO.setReportedByCustomer(row.getString("ReportedByCustomer"));
		registrationDTO.setStatus(row.getString("Status"));
		registrationDTO.getPolicyDetailsDTO().setCobId(row.getString("CobId"));
		registrationDTO.setClaimNumber(row.getString("ClaimNo"));
		registrationDTO.setReportedBy(row.getString("ReportedByMethod"));
		registrationDTO.setReportedByDesc(row.getString("ReportedByMethodDesc"));
		registrationDTO.setReportedById(row.getString("ReportedById"));
		registrationDTO.setReportedByInsured(row.getString("ReportedByInsured"));
		registrationDTO.setUserDivision(row.getString("UserDivision"));
		registrationDTO.setModuleType(row.getString("ModuleType"));
		registrationDTO.setProviderId(row.getString("ProviderId"));
		registrationDTO.setBaseCurrency(row.getString("CurrencyId"));
		registrationDTO.getPolicyDetailsDTO().setRiskCOB(row.getString("RiskCob"));
		registrationDTO.getPolicyDetailsDTO().setRiskCurrentExchangeRate(row.getString("RiskCurrencyExchangeRate"));
		registrationDTO.getPolicyDetailsDTO().setRiskCurrencyId(row.getString("CurrencyId"));
		registrationDTO.getMemberDetailsDTO().setCardReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("CardReceivedDate")));
		return registrationDTO;
	}
	
	public static RegistrationFileDTO getRegistrationFileDTO(ResultSet row) throws SQLException
	{
		RegistrationFileDTO registrationFileDTO = new RegistrationFileDTO();
		registrationFileDTO.setDescription(row.getString("Description"));
		registrationFileDTO.setDocContentType(row.getString("DocContentType"));
		registrationFileDTO.setDocName(row.getString("DocName"));
		registrationFileDTO.setDocPath(row.getString("DocPath"));
		registrationFileDTO.setDocTypeDesc(row.getString("DocTypeDesc"));
		registrationFileDTO.setDocTypeId(row.getString("DocTypeId"));
		registrationFileDTO.setUploadedBy(row.getString("UploadedBy"));
		registrationFileDTO.setUploadedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("UploadedDate")));
		return registrationFileDTO;
	}
}
