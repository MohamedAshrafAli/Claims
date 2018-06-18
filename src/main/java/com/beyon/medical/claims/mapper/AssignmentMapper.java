package com.beyon.medical.claims.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.general.dto.AssignmentDTO;
import com.beyon.medical.claims.general.dto.MemberDetailsDTO;
import com.beyon.medical.claims.general.dto.PolicyDetailsDTO;
import com.beyon.medical.claims.utils.DateUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AssignmentMapper {
	
	
	public static AssignmentDTO getAssignmentDTO(ResultSet row) throws SQLException
	{
		AssignmentDTO assignmentDTO = new AssignmentDTO();
		PolicyDetailsDTO policyDetailsDTO = new PolicyDetailsDTO();
		MemberDetailsDTO memberDetailsDTO = new MemberDetailsDTO();
		assignmentDTO.setMemberDetailsDTO(memberDetailsDTO);
		assignmentDTO.setPolicyDetailsDTO(policyDetailsDTO);
		assignmentDTO.getMemberDetailsDTO().setCardNumber(row.getString("CardNumber"));
		assignmentDTO.getPolicyDetailsDTO().setCustomerId(row.getString("CustomerId"));
		assignmentDTO.setDeptId(row.getString("DeptId"));
		assignmentDTO.setDivisionId(row.getString("DivisionId"));
		assignmentDTO.getMemberDetailsDTO().setEmail1(row.getString("EmailId1"));
		assignmentDTO.getMemberDetailsDTO().setEmail2(row.getString("EmailId2"));
		assignmentDTO.getPolicyDetailsDTO().setInsuredId(row.getString("InsuredId"));
		assignmentDTO.getMemberDetailsDTO().setIsDependent(row.getString("IsDependent"));
		assignmentDTO.getMemberDetailsDTO().setMemberCategory(row.getString("MemberCategory"));
		assignmentDTO.getMemberDetailsDTO().setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		assignmentDTO.getMemberDetailsDTO().setMemberName(row.getString("MemberName"));
		assignmentDTO.getMemberDetailsDTO().setMemberNumber(row.getString("MemberNumber"));
		assignmentDTO.getMemberDetailsDTO().setMemberType(row.getString("MemberType"));
		assignmentDTO.getMemberDetailsDTO().setMobileNum1(row.getString("MobileNumber1"));
		assignmentDTO.getMemberDetailsDTO().setMobileNum2(row.getString("MobileNumber2"));
		assignmentDTO.getMemberDetailsDTO().setNationalId(row.getString("NationalId"));
		assignmentDTO.getMemberDetailsDTO().setParentMemberId(row.getString("ParentId"));
		assignmentDTO.getMemberDetailsDTO().setPassportNumber(row.getString("PassportNumber"));
		assignmentDTO.getPolicyDetailsDTO().setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		assignmentDTO.getPolicyDetailsDTO().setPolicyNumber(row.getString("Policynumber"));
		assignmentDTO.getPolicyDetailsDTO().setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		assignmentDTO.getPolicyDetailsDTO().setProductId(row.getString("ProductId"));
		assignmentDTO.getMemberDetailsDTO().setRelationWithPrimary(row.getString("Relation"));
		assignmentDTO.getPolicyDetailsDTO().setRiskAmndVerId(row.getInt("RiskAmendmentId"));
		assignmentDTO.getPolicyDetailsDTO().setRiskCOB(row.getString("CobId"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex1(row.getString("RiskFlex1"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex2(row.getString("RiskFlex2"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex3(row.getString("RiskFlex3"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex4(row.getString("RiskFlex4"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex5(row.getString("RiskFlex5"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex6(row.getString("RiskFlex6"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex7(row.getString("RiskFlex7"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex8(row.getString("RiskFlex8"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex9(row.getString("RiskFlex9"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlex10(row.getString("RiskFlex10"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc1(row.getString("RiskFlexDescription1"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc2(row.getString("RiskFlexDescription2"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc3(row.getString("RiskFlexDescription3"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc4(row.getString("RiskFlexDescription4"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc5(row.getString("RiskFlexDescription5"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc6(row.getString("RiskFlexDescription6"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc7(row.getString("RiskFlexDescription7"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc8(row.getString("RiskFlexDescription8"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc9(row.getString("RiskFlexDescription9"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFlexDesc10(row.getString("RiskFlexDescription10"));
		assignmentDTO.getPolicyDetailsDTO().setRiskFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskFromDate")));
		assignmentDTO.getPolicyDetailsDTO().setRiskToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskToDate")));
		assignmentDTO.getPolicyDetailsDTO().setRiskId(row.getString("RiskId"));
		assignmentDTO.getPolicyDetailsDTO().setRiskType(row.getString("RiskType"));
		assignmentDTO.getMemberDetailsDTO().setUidId(row.getString("UIDId"));		
		assignmentDTO.setEncType(row.getString("EncounterType"));
		assignmentDTO.setPaymentRefNum(row.getString("PaymentGatewayRef"));
		assignmentDTO.setPaymentWay(row.getString("PaymentWay"));
		assignmentDTO.setPrevRequest(row.getString("PrevReqId"));
		assignmentDTO.setReqReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RequestReceivedDate")));
		assignmentDTO.setReqType(row.getString("RequestType"));
		assignmentDTO.setRequestAmt(row.getBigDecimal("RequestedAmt"));
		assignmentDTO.setRequestAmtBC(row.getBigDecimal("RequestAmountBaseCurrency"));
		assignmentDTO.setServiceFmDate(DateUtil.convertSQlDateToLocalDate(row.getDate("ServiceFromDate")));
		assignmentDTO.setSourceType(row.getString("SourceType"));
		assignmentDTO.setVoucherNumber(row.getString("VoucherNumber"));
		assignmentDTO.setId(row.getLong("Id"));
		assignmentDTO.setClaimRefNo(row.getString("ClaimRefNo"));
		assignmentDTO.setCompId(row.getString("compId"));
		assignmentDTO.setDescription(row.getString("Description"));
		assignmentDTO.setClaimantIsCustomer(row.getString("ClaimantIsCustomer"));
		assignmentDTO.setReportedByCustomer(row.getString("ReportedByCustomer"));
		assignmentDTO.setStatus(row.getString("Status"));
		assignmentDTO.getPolicyDetailsDTO().setCobId(row.getString("CobId"));
		assignmentDTO.setClaimNumber(row.getString("ClaimNo"));
		assignmentDTO.setReportedBy(row.getString("ReportedByMethod"));
		assignmentDTO.setReportedByDesc(row.getString("ReportedByMethodDesc"));
		assignmentDTO.setReportedById(row.getString("ReportedById"));
		assignmentDTO.setReportedByInsured(row.getString("ReportedByInsured"));
		assignmentDTO.setUserDivision(row.getString("UserDivision"));
		assignmentDTO.setModuleType(row.getString("ModuleType"));
		assignmentDTO.setProviderId(row.getString("ProviderId"));
		assignmentDTO.setBaseCurrency(row.getString("CurrencyId"));
		assignmentDTO.getPolicyDetailsDTO().setRiskCOB(row.getString("RiskCob"));
		assignmentDTO.getPolicyDetailsDTO().setRiskCurrentExchangeRate(row.getString("RiskCurrencyExchangeRate"));
		assignmentDTO.getPolicyDetailsDTO().setRiskCurrencyId(row.getString("CurrencyId"));
		assignmentDTO.getMemberDetailsDTO().setCardReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("CardReceivedDate")));
		assignmentDTO.setCreatedBy(row.getString("CreatedBy"));
		return assignmentDTO;
	}
	
	public static AssignmentDTO getAssignmentDTOWithMemberDetails(ObjectNode node,AssignmentDTO assignmentDTO) throws SQLException
	{
		Map<String, Object> nodeMap = FoundationUtils.getObjectMapper().convertValue(node, Map.class);
		MemberDetailsDTO memberDetailsDTO = new MemberDetailsDTO();
		memberDetailsDTO.setAddress1((String)nodeMap.get("Address1"));
		memberDetailsDTO.setAddress2((String)nodeMap.get("Address2"));
		memberDetailsDTO.setAddress3((String)nodeMap.get("Address3"));
		memberDetailsDTO.setAddress4((String)nodeMap.get("Address4"));
		memberDetailsDTO.setFirstName((String)nodeMap.get("FirstName"));
		memberDetailsDTO.setLastName((String)nodeMap.get("LastName"));
		memberDetailsDTO.setMiddleName((String)nodeMap.get("MiddleName"));
		memberDetailsDTO.setCity((String)nodeMap.get("City"));
		memberDetailsDTO.setState((String)nodeMap.get("State"));
		memberDetailsDTO.setPincode((String)nodeMap.get("Pincode"));
		memberDetailsDTO.setCountry((String)nodeMap.get("Country"));
		memberDetailsDTO.setPrimaryPhoneNo((String)nodeMap.get("PhoneNumber"));
		memberDetailsDTO.setMobileNum1((String)nodeMap.get("MobileNumber"));
		memberDetailsDTO.setEmail1((String)nodeMap.get("EmailId"));
		memberDetailsDTO.setGender((String)nodeMap.get("Gender"));
		memberDetailsDTO.setMemberName((String)nodeMap.get("MemberName"));
		memberDetailsDTO.setRelationWithPrimary((String)nodeMap.get("Relation"));
		memberDetailsDTO.setNationalId((String)nodeMap.get("Nationality"));
		assignmentDTO.setMemberDetailsDTO(memberDetailsDTO);
		return assignmentDTO;
	}
	
}
