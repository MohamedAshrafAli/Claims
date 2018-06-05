package com.beyon.medical.claims.reimbursement.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.beyon.medical.claims.reimbursement.dto.ReimbursementProcessingDTO;

public class ReimbProcessingMapper {
	
	public static ReimbursementProcessingDTO getReimbursementProcessingDTO(ResultSet row) throws SQLException
	{
		ReimbursementProcessingDTO processingDTO = new ReimbursementProcessingDTO();
		return processingDTO;
	}
	
}
