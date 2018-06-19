package com.beyon.medical.claims.finalization.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.finalization.dao.ClaimFinalizationDAOImpl;
import com.beyon.medical.claims.general.dto.ProcessingDTO;

@Service(value="reimbursementClaimFinalizationServiceImpl")
public class ReimbursementClaimFinalizationServiceImpl implements ReimbursementClaimFinalizationService {

	@Autowired
	private ClaimFinalizationDAOImpl claimFinalizationDAOImpl;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<ProcessingDTO> getFinalizedProcessingDTOs(String compId) throws DAOException {
		List<ProcessingDTO> processingDTOs = null;
		try {
			processingDTOs = claimFinalizationDAOImpl.getFinalizedList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return processingDTOs;
	}
	
}