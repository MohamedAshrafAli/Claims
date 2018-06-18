package com.beyon.medical.claims.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beyon.medical.claims.general.dto.DiagnosisDTO;
import com.beyon.medical.claims.general.dto.ProcessingDTO;
import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;
import com.beyon.medical.claims.utils.DateUtil;

public class ProcessingMapper {
	
	public static ProcessingDTO getProcessingDTO(ResultSet row) 
			throws SQLException {
		ProcessingDTO processingDTO = new ProcessingDTO();
		List<ProcessingServiceDTO> processingServiceDTOs = new ArrayList<>();
		processingDTO.setClaimNumber(row.getString("Claimnumber"));
		processingDTO.setRequestNumber(row.getLong("Requestnumber"));		
		processingDTO.setClaimType(row.getString("Claimtype"));
		processingDTO.setEventCountry(row.getString("Eventcountry"));
		processingDTO.setClaimCondition(row.getString("Claimcondition"));
		processingDTO.setClaimStatusReason(row.getString("Claimstatusreason"));		
		processingDTO.setDiagType(row.getString("Diagtype"));
		processingDTO.setId(row.getLong("Claimsregistrationid"));
		processingDTO.setBaseCurrency(row.getString("Currencyid"));
		processingDTO.getPolicyDetailsDTO().setPolicyNumber(row.getString("Policynumber"));
		processingDTO.getPolicyDetailsDTO().setCustomerId(row.getString("CustomerId"));
		processingDTO.getPolicyDetailsDTO().setProductId(row.getString("ProductId"));
		processingDTO.getPolicyDetailsDTO().setPolicyFromDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyFromDate")));
		processingDTO.getPolicyDetailsDTO().setPolicyToDate(DateUtil.convertSQlDateToLocalDate(row.getDate("PolicyToDate")));
		processingDTO.setProviderId(row.getString("ProviderId"));
		processingDTO.setCompId(row.getString("compId"));
		processingDTO.setReqType(row.getString("RequestType"));
		processingDTO.setEncType(row.getString("EncounterType"));
		processingDTO.setVoucherNumber(row.getString("VoucherNumber"));
		processingDTO.getMemberDetailsDTO().setMemberNumber(row.getString("MemberNumber"));		
		processingDTO.getMemberDetailsDTO().setMemberName(row.getString("MemberName"));
		processingDTO.getMemberDetailsDTO().setMemberType(row.getString("MemberType"));
		processingDTO.getMemberDetailsDTO().setMemberCategory(row.getString("MemberCategory"));
		processingDTO.getMemberDetailsDTO().setMemberDOB(DateUtil.convertSQlDateToLocalDate(row.getDate("MemberDOB")));
		processingDTO.getMemberDetailsDTO().setRelationWithPrimary(row.getString("Relation"));
		processingDTO.getMemberDetailsDTO().setCardNumber(row.getString("CardNumber"));
		processingDTO.getPolicyDetailsDTO().setRiskId(row.getString("riskId"));
		processingDTO.getMemberDetailsDTO().setCardReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("CardReceivedDate")));
		
		if (row.getString("Diagtype") != null) {
			setDiagnosisDTO(row, processingDTO);
		}		
		processingServiceDTOs.add(ProcessingServiceMapper.getProcessingServiceDTO(row));		
		processingDTO.setProcessingServiceDTOs(processingServiceDTOs);		
		
		return processingDTO;
	}
	
	public static ProcessingDTO getProcessingDTOForAssignment(ResultSet row) 
			throws SQLException {
		ProcessingDTO processingDTO = new ProcessingDTO();
		processingDTO.setClaimNumber(row.getString("Claimnumber"));
		processingDTO.setRequestNumber(row.getLong("Requestnumber"));		
		processingDTO.setClaimType(row.getString("Claimtype"));
		processingDTO.setEventCountry(row.getString("Eventcountry"));
		processingDTO.setClaimCondition(row.getString("Claimcondition"));
		processingDTO.setClaimStatusReason(row.getString("Claimstatusreason"));		
		processingDTO.setDiagType(row.getString("Diagtype"));
		if (row.getString("Diagtype") != null) {
			setDiagnosisDTO(row, processingDTO);
		}		
		return processingDTO;
	}

	private static void setDiagnosisDTO(ResultSet row, ProcessingDTO processingDTO) throws SQLException {
		if ("Primary".equalsIgnoreCase(row.getString("Diagtype"))) {
			DiagnosisDTO diagnosisDTO = processingDTO.getPrimaryDiagnosis();
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
