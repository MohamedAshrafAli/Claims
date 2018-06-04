package com.beyon.medical.claims.reimbursement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementAssignmentDTO;
import com.beyon.medical.claims.utils.DateUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReimbAssignmentMapper {
	
	public static ReimbursementAssignmentDTO getViewReimbursementAssignmentDTO(ResultSet row) throws SQLException
	{
		ReimbursementAssignmentDTO assignmentDTO = new ReimbursementAssignmentDTO();
		assignmentDTO.setCardNumber(row.getString("CardNumber"));
		assignmentDTO.setCustomerId(row.getString("CustomerId"));
		assignmentDTO.setDeptId(row.getString("DeptId"));
		assignmentDTO.setDivisionId(row.getString("DivisionId"));
		assignmentDTO.setEmail1(row.getString("EmailId1"));
		assignmentDTO.setEmail2(row.getString("EmailId2"));
		assignmentDTO.setInsuredId(row.getString("InsuredId"));
		assignmentDTO.setIsDependent(row.getString("IsDependent"));
		assignmentDTO.setMemberCategory(row.getString("MemberCategory"));
		assignmentDTO.setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		assignmentDTO.setMemberName(row.getString("MemberName"));
		assignmentDTO.setMemberNumber(row.getString("MemberNumber"));
		assignmentDTO.setMemberType(row.getString("MemberType"));
		assignmentDTO.setMobileNum1(row.getString("MobileNumber1"));
		assignmentDTO.setMobileNum2(row.getString("MobileNumber2"));
		assignmentDTO.setNationalId(row.getString("NationalId"));
		assignmentDTO.setParentMemberId(row.getString("ParentId"));
		assignmentDTO.setPassportNumber(row.getString("PassportNumber"));
		assignmentDTO.setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		assignmentDTO.setPolicyNumber(row.getString("Policynumber"));
		assignmentDTO.setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		assignmentDTO.setProductId(row.getString("ProductId"));
		assignmentDTO.setRelationWithPrimary(row.getString("Relation"));
		assignmentDTO.setRiskAmndVerId(row.getInt("RiskAmendmentId"));
		assignmentDTO.setRiskCOB(row.getString("CobId"));
		assignmentDTO.setRiskFlex1(row.getString("RiskFlex1"));
		assignmentDTO.setRiskFlex2(row.getString("RiskFlex2"));
		assignmentDTO.setRiskFlex3(row.getString("RiskFlex3"));
		assignmentDTO.setRiskFlex4(row.getString("RiskFlex4"));
		assignmentDTO.setRiskFlex5(row.getString("RiskFlex5"));
		assignmentDTO.setRiskFlex6(row.getString("RiskFlex6"));
		assignmentDTO.setRiskFlex7(row.getString("RiskFlex7"));
		assignmentDTO.setRiskFlex8(row.getString("RiskFlex8"));
		assignmentDTO.setRiskFlex9(row.getString("RiskFlex9"));
		assignmentDTO.setRiskFlex10(row.getString("RiskFlex10"));
		assignmentDTO.setRiskFlexDesc1(row.getString("RiskFlexDescription1"));
		assignmentDTO.setRiskFlexDesc2(row.getString("RiskFlexDescription2"));
		assignmentDTO.setRiskFlexDesc3(row.getString("RiskFlexDescription3"));
		assignmentDTO.setRiskFlexDesc4(row.getString("RiskFlexDescription4"));
		assignmentDTO.setRiskFlexDesc5(row.getString("RiskFlexDescription5"));
		assignmentDTO.setRiskFlexDesc6(row.getString("RiskFlexDescription6"));
		assignmentDTO.setRiskFlexDesc7(row.getString("RiskFlexDescription7"));
		assignmentDTO.setRiskFlexDesc8(row.getString("RiskFlexDescription8"));
		assignmentDTO.setRiskFlexDesc9(row.getString("RiskFlexDescription9"));
		assignmentDTO.setRiskFlexDesc10(row.getString("RiskFlexDescription10"));
		assignmentDTO.setRiskFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskFromDate")));
		assignmentDTO.setRiskToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RiskToDate")));
		assignmentDTO.setRiskId(row.getString("RiskId"));
		assignmentDTO.setRiskType(row.getString("RiskType"));
		assignmentDTO.setUidId(row.getString("UIDId"));		
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
		assignmentDTO.setCobId(row.getString("CobId"));
		assignmentDTO.setClaimNumber(row.getString("ClaimNo"));
		assignmentDTO.setReportedBy(row.getString("ReportedByMethod"));
		assignmentDTO.setReportedByDesc(row.getString("ReportedByMethodDesc"));
		assignmentDTO.setReportedById(row.getString("ReportedById"));
		assignmentDTO.setReportedByInsured(row.getString("ReportedByInsured"));
		assignmentDTO.setUserDivision(row.getString("UserDivision"));
		assignmentDTO.setModuleType(row.getString("ModuleType"));
		assignmentDTO.setProviderId(row.getString("ProviderId"));
		assignmentDTO.setBaseCurrency(row.getString("CurrencyId"));
		assignmentDTO.setRiskCOB(row.getString("RiskCob"));
		assignmentDTO.setRiskCurrentExchangeRate(row.getString("RiskCurrencyExchangeRate"));
		assignmentDTO.setRiskCurrencyId(row.getString("CurrencyId"));
		assignmentDTO.setCardReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("CardReceivedDate")));
		return assignmentDTO;
	}
	
	public static ReimbursementAssignmentDTO getReimbursmentDTOWithMemberDetails(ObjectNode node,ReimbursementAssignmentDTO assignmentDTO) throws SQLException
	{
		Map<String, Object> nodeMap = FoundationUtils.getObjectMapper().convertValue(node, Map.class);
		assignmentDTO.setAddress1((String)nodeMap.get("Address1"));
		assignmentDTO.setAddress2((String)nodeMap.get("Address2"));
		assignmentDTO.setAddress3((String)nodeMap.get("Address3"));
		assignmentDTO.setAddress4((String)nodeMap.get("Address4"));
		assignmentDTO.setFirstName((String)nodeMap.get("FirstName"));
		assignmentDTO.setLastName((String)nodeMap.get("LastName"));
		assignmentDTO.setMiddleName((String)nodeMap.get("MiddleName"));
		assignmentDTO.setCity((String)nodeMap.get("City"));
		assignmentDTO.setState((String)nodeMap.get("State"));
		assignmentDTO.setPincode((String)nodeMap.get("Pincode"));
		assignmentDTO.setCountry((String)nodeMap.get("Country"));
		assignmentDTO.setPrimaryPhoneNo((String)nodeMap.get("PhoneNumber"));
		assignmentDTO.setMobileNum1((String)nodeMap.get("MobileNumber"));
		assignmentDTO.setEmail1((String)nodeMap.get("EmailId"));
		assignmentDTO.setGender((String)nodeMap.get("Gender"));
		assignmentDTO.setMemberName((String)nodeMap.get("MemberName"));
		assignmentDTO.setRelationWithPrimary((String)nodeMap.get("Relation"));
		assignmentDTO.setNationalId((String)nodeMap.get("Nationality"));
		
		return assignmentDTO;
	}
	
}
