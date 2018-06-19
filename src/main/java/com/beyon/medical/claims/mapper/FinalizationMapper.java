package com.beyon.medical.claims.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.beyon.medical.claims.general.dto.ProcessingDTO;
import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;

public class FinalizationMapper {
	
	public static ProcessingDTO getFinalizedProcessingDTOs(ResultSet row) throws SQLException {
		ProcessingDTO processingDTO = new ProcessingDTO();
		List<ProcessingServiceDTO> processingServiceDTOs = new ArrayList<>();
		ProcessingServiceDTO processingServiceDTO = new ProcessingServiceDTO();
		processingDTO.setClaimNumber(row.getString("Claimnumber"));
		processingDTO.setReqReceivedDate(Optional.ofNullable(row.getDate("RequestReceivedDate")).map(java.sql.Date::toLocalDate).orElse(null));
		processingDTO.setPaymentWay(row.getString(""));
		processingDTO.setPaymentRefNum(row.getString(""));
		processingServiceDTO.setApprovedAmount(row.getBigDecimal(""));
		processingServiceDTOs.add(processingServiceDTO);
		processingDTO.setProcessingServiceDTOs(processingServiceDTOs);
		return processingDTO;		
	}
}
