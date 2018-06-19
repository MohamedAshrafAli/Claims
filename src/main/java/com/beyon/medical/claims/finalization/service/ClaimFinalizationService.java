package com.beyon.medical.claims.finalization.service;

import java.util.List;

import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dto.ProcessingDTO;

public interface ClaimFinalizationService {

	List<ProcessingDTO> getFinalizedProcessingDTOs(String compId) throws DAOException;

}