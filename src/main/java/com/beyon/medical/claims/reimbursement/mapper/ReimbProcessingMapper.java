package com.beyon.medical.claims.reimbursement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beyon.medical.claims.general.dto.DiagnosisDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingServiceDTO;
import com.beyon.medical.claims.utils.DateUtil;

public class ReimbProcessingMapper {
	
	public static ReimbursementProcessingDTO getReimbursementProcessingDTO(ResultSet row) 
			throws SQLException {
		ReimbursementProcessingDTO processingDTO = new ReimbursementProcessingDTO();
		List<ReimbursementProcessingServiceDTO> reimbursementProcessingServiceDTOs = new ArrayList<>();
		processingDTO.setClaimNumber(row.getString("Claimnumber"));
		processingDTO.setRequestNumber(row.getLong("Requestnumber"));		
		processingDTO.setClaimType(row.getString("Claimtype"));
		processingDTO.setEventCountry(row.getString("Eventcountry"));
		processingDTO.setClaimCondition(row.getString("Claimcondition"));
		processingDTO.setClaimStatusReason(row.getString("Claimstatusreason"));		
		processingDTO.setDiagType(row.getString("Diagtype"));
		processingDTO.setId(row.getLong("Claimsregistrationid"));
		processingDTO.setBaseCurrency(row.getString("Currencyid"));
		processingDTO.setPolicyNumber(row.getString("Policynumber"));
		processingDTO.setCustomerId(row.getString("CustomerId"));
		processingDTO.setProductId(row.getString("ProductId"));
		processingDTO.setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		processingDTO.setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		processingDTO.setProviderId(row.getString("ProviderId"));
		processingDTO.setCompId(row.getString("compId"));
		processingDTO.setReqType(row.getString("RequestType"));
		processingDTO.setEncType(row.getString("EncounterType"));
		processingDTO.setVoucherNumber(row.getString("VoucherNumber"));
		processingDTO.setMemberNumber(row.getString("MemberNumber"));		
		processingDTO.setMemberName(row.getString("MemberName"));
		processingDTO.setMemberType(row.getString("MemberType"));
		processingDTO.setMemberCategory(row.getString("MemberCategory"));
		processingDTO.setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		processingDTO.setRelationWithPrimary(row.getString("Relation"));
		processingDTO.setCardNumber(row.getString("CardNumber"));
		processingDTO.setRiskId(row.getString("riskId"));
		processingDTO.setCardReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("CardReceivedDate")));
		
		if (row.getString("Diagtype") != null) {
			setDiagnosisDTO(row, processingDTO);
		}		
		reimbursementProcessingServiceDTOs.add(ReimbursementProcessingServiceMapper.getReimbursementProcessingServiceDTO(row));		
		processingDTO.setProcessingServiceDTOs(reimbursementProcessingServiceDTOs);		
		
		return processingDTO;
	}
	
	public static ReimbursementProcessingDTO getReimbursementProcessingDTOForAssignment(ResultSet row) 
			throws SQLException {
		ReimbursementProcessingDTO processingDTO = new ReimbursementProcessingDTO();
		List<ReimbursementProcessingServiceDTO> reimbursementProcessingServiceDTOs = new ArrayList<>();
		processingDTO.setClaimNumber(row.getString("Claimnumber"));
		processingDTO.setRequestNumber(row.getLong("Requestnumber"));		
		processingDTO.setClaimType(row.getString("Claimtype"));
		processingDTO.setEventCountry(row.getString("Eventcountry"));
		processingDTO.setClaimCondition(row.getString("Claimcondition"));
		processingDTO.setClaimStatusReason(row.getString("Claimstatusreason"));		
		processingDTO.setDiagType(row.getString("Diagtype"));
		/*processingDTO.setId(row.getLong("Claimsregistrationid"));
		processingDTO.setBaseCurrency(row.getString("Currencyid"));
		processingDTO.setPolicyNumber(row.getString("Policynumber"));
		processingDTO.setCustomerId(row.getString("CustomerId"));
		processingDTO.setProductId(row.getString("ProductId"));
		processingDTO.setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		processingDTO.setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		processingDTO.setProviderId(row.getString("ProviderId"));
		processingDTO.setCompId(row.getString("compId"));
		processingDTO.setReqType(row.getString("RequestType"));
		processingDTO.setEncType(row.getString("EncounterType"));
		processingDTO.setVoucherNumber(row.getString("VoucherNumber"));
		processingDTO.setMemberNumber(row.getString("MemberNumber"));		
		processingDTO.setMemberName(row.getString("MemberName"));
		processingDTO.setMemberType(row.getString("MemberType"));
		processingDTO.setMemberCategory(row.getString("MemberCategory"));
		processingDTO.setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		processingDTO.setRelationWithPrimary(row.getString("Relation"));
		processingDTO.setCardNumber(row.getString("CardNumber"));
		processingDTO.setRiskId(row.getString("riskId"));
		processingDTO.setCardReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("CardReceivedDate")));*/
		
		if (row.getString("Diagtype") != null) {
			setDiagnosisDTO(row, processingDTO);
		}		
//		reimbursementProcessingServiceDTOs.add(ReimbursementProcessingServiceMapper.getReimbursementProcessingServiceDTO(row));		
		processingDTO.setProcessingServiceDTOs(reimbursementProcessingServiceDTOs);		
		
		return processingDTO;
	}

	private static void setDiagnosisDTO(ResultSet row, ReimbursementProcessingDTO processingDTO) throws SQLException {
		if ("Primary".equalsIgnoreCase(row.getString("Diagtype"))) {
			DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
			diagnosisDTO.setDiagId(row.getString("Diagid"));
			diagnosisDTO.setDiagType(row.getString("Diagtype"));
			diagnosisDTO.setCreatedBy(row.getString("Diagcreatedby"));
			diagnosisDTO.setCreatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Diagcreateddate")));
			diagnosisDTO.setUpdatedBy(row.getString("Diagupdatedby"));
			diagnosisDTO.setUpdatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("DiagUpdateddate")));
			processingDTO.setPrimaryDiagnosis(diagnosisDTO);
		} else {
			DiagnosisDTO diagnosisDTO = new DiagnosisDTO();
			diagnosisDTO.setDiagId(row.getString("Diagid"));
			diagnosisDTO.setDiagType(row.getString("Diagtype"));
			diagnosisDTO.setCreatedBy(row.getString("Diagcreatedby"));
			diagnosisDTO.setCreatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("Diagcreateddate")));
			diagnosisDTO.setUpdatedBy(row.getString("Diagupdatedby"));
			diagnosisDTO.setUpdatedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("DiagUpdateddate")));
			processingDTO.setSecondaryDiagnosis(diagnosisDTO);
		}
	}	
}
