package com.beyon.medical.claims.finalization.dao;

import static com.beyon.medical.claims.queries.constants.FinalizationQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MSRVCP;
import static com.beyon.medical.claims.queries.constants.FinalizationQueriesConstants.QUERIES_INSERT_CTDS_LEVEL_MSRVCP_SEQUENCE_NAME;
import static com.beyon.medical.claims.queries.constants.FinalizationQueriesConstants.QUERIES_SELECT_FINALIZE_LIST;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.medical.claims.base.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.general.dto.ProcessingDTO;
import com.beyon.medical.claims.general.dto.ProcessingServiceDTO;
import com.beyon.medical.claims.mapper.FinalizationMapper;

@Repository
public class ClaimFinalizationDAOImpl extends BaseClaimsDAOImpl {

	public List<ProcessingDTO> getFinalizedList() {
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");		
		return jdbcTemplate.query(QUERIES_SELECT_FINALIZE_LIST,  
				new RowMapper<ProcessingDTO>() {
			@Override
			public ProcessingDTO mapRow(ResultSet row, int count) throws SQLException {						
				return FinalizationMapper.getFinalizedProcessingDTOs(row);						
			}
		});
	}
	
	public ProcessingDTO finalizeClaims(String compId, ProcessingDTO processingDTO) {
		JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
		return insertCTDSLEVELMSRVCP(compId, processingDTO, jdbcTemplate);
	}

	private ProcessingDTO insertCTDSLEVELMSRVCP(String compId, ProcessingDTO processingDTO, JdbcTemplate jdbcTemplate) {		
		ProcessingServiceDTO processingServiceDTO = processingDTO.getProcessingServiceDTOs().get(0);
		Long clmspSgsId = getSequenceNo(QUERIES_INSERT_CTDS_LEVEL_MSRVCP_SEQUENCE_NAME);
		jdbcTemplate.update(QUERIES_INSERT_CTDS_LEVEL_MSRVCP,
				new Object[] { clmspSgsId, 
						processingDTO.getId(), 
						processingServiceDTO.getClaimsSequenceNo(),
						processingServiceDTO.getServiceId(),
						0,
						processingServiceDTO.getApprovedAmount(),
						processingDTO.getPaymentWay(),
						processingDTO.getPaymentRefNum(),
						toSQLDate(LocalDate.now()),
						processingServiceDTO.getApprovedAmountBC(),
						null, //TODO: need to clarify the batch Id
						null  //TODO: need to clarify on invoice #				
		});

		return processingDTO;
	}
}
