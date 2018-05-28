package com.beyon.medical.claims.reimbursement.service;

import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DETAILS;
import static com.beyon.medical.claims.constants.ClaimConstants.*;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.reimbursement.dao.ReimbursementClaimsDAOImpl;
import com.beyon.medical.claims.reimbursement.dto.RegistrationFileDTO;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.ui.facade.service.GeneralServiceFacade;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ReimbursementClaimsServiceImpl implements ReimbursementClaimsService {

	@Autowired
	private ReimbursementClaimsDAOImpl reimbursementClaimsDAO;
	
	@Autowired
	private GeneralServiceFacade generalServiceFacade;

	@Override
	public List<ReimbursementRegistrationDTO> getReimbursementRegistrationDetails(ObjectNode paramMap) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}
	
	public ReimbursementRegistrationDTO getReimbursementRegistrationDetailsById(String id) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID;
			Map<String,Object> inputMap = new HashMap<>();
			inputMap.put("id", id);
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationDetailsById(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails.get(0);
	}
	
	private String getConstructedQuerywithCriterion(String strQuery,Map<String,Object> inputMap) {
		if(strQuery.contains("<CRITERIA>")) {
			Iterator<String> mapIter = inputMap.keySet().iterator();
			StringBuilder builder = new StringBuilder();
			while (mapIter.hasNext()) {
				String key = (String) mapIter.next();
				if(key.equalsIgnoreCase("policyNumber")) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA + " ");
				} else if(key.equalsIgnoreCase("memberNumber")) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA+ " ");
				} else if(key.equalsIgnoreCase("voucherNumber")) {
					builder.append(REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA + " ");
				} 
			}
			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
		}
		return strQuery;
	}
	
	@Override
	public List<ReimbursementRegistrationDTO> getRegistrationDetailsForPolicyAndMemberNo(ObjectNode paramMap) throws DAOException {
		List<ReimbursementRegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = REIMBURSEMENT_QUERIES_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationListData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}
	
	@Override
	public ReimbursementRegistrationDTO saveRegistrationDetails(String compId,ReimbursementRegistrationDTO registrationDTO) throws DAOException {
		ReimbursementRegistrationDTO _registrationDTO = null;
		try {
			_registrationDTO =  reimbursementClaimsDAO.insertReimbursementRegistrationDetails(compId, registrationDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _registrationDTO;
	}
	
	@Override
	public void uploadAndSaveDocuments(String compId,ReimbursementRegistrationDTO registrationDTO) throws DAOException {
		try {
			reimbursementClaimsDAO.insertTDSLEVELD(compId, registrationDTO);
			for (RegistrationFileDTO  registrationFileDTO : registrationDTO.getRegistrationFileDTOs()) {
				transferFilesToFileServer(registrationDTO,registrationFileDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}
	
	private void transferFilesToFileServer(ReimbursementRegistrationDTO registrationDTO,RegistrationFileDTO  registrationFileDTO) throws DAOException {
	        String filename = registrationFileDTO.getFile().getOriginalFilename();
	        String path = CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER + File.separator + registrationDTO.getClaimRefNo() + File.separator+registrationFileDTO.getDocType();
	        File uploadDir = null;			
	        File uploadFile = null;
	        try {
	            uploadDir = new File(path);
	            if (!uploadDir.exists()) {
	                uploadDir.mkdirs();
	            }
	            uploadFile = new File(path + File.separator + filename);
	            registrationFileDTO.getFile().transferTo(uploadFile);
	        } catch (Exception ex) {
	            throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
	        }
	}

}