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
		while(row.next()) {			
			if (row.getRow() == 1) {
				processingDTO.setClaimNumber(row.getString("Claimnumber"));
				processingDTO.setRequestNumber(row.getLong("Requestnumber"));		
				processingDTO.setClaimType(row.getString("Claimtype"));
				processingDTO.setEventCountry(row.getString("Eventcountry"));
				processingDTO.setClaimCondition(row.getString("Claimcondition"));
				processingDTO.setClaimStatusReason(row.getString("Claimstatusreason"));				
				if (row.getString("Diagtype") != null) {
					setDiagnosisDTO(row, processingDTO);
				}
			}
			reimbursementProcessingServiceDTOs.add(ReimbursementProcessingServiceMapper.getReimbursementProcessingServiceDTO(row));			
		}		
		processingDTO.setProcessingServiceDTOs(reimbursementProcessingServiceDTOs);
		
		return processingDTO;
	}

	private static void setDiagnosisDTO(ResultSet row, ReimbursementProcessingDTO processingDTO) throws SQLException {
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
