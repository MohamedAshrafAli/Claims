package com.beyon.medical.claims.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DETAILS;
import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_DETAILS_TDS_LEVEL_D;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.exception.MedicalClaimsException;
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
		ReimbursementRegistrationDTO registrationDTO = null;
		try {
			String strQuery = REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID;
			Map<String,Object> inputMap = new HashMap<>();
			inputMap.put("id", id);
			reimbursementRegDetails =  reimbursementClaimsDAO.getRegistrationDetailsById(strQuery, inputMap);
			if(reimbursementRegDetails != null && !reimbursementRegDetails.isEmpty()) {
				registrationDTO = reimbursementRegDetails.get(0);
				List<RegistrationFileDTO> fileDTOs= reimbursementClaimsDAO.getRegistrationFileDetailsById(REIMBURSEMENT_QUERIES_DETAILS_TDS_LEVEL_D,inputMap);
				registrationDTO.setRegistrationFileDTOs(fileDTOs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return registrationDTO;
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
	public 	boolean deleteRegistrationDocument(String id, String docType, String docName, String path ) throws DAOException {
		boolean isDeleted = false;
		try {
			isDeleted = reimbursementClaimsDAO.deleteRegistrationDocument(id, docType, docName, path);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return isDeleted;
	}
	
	@Override
	public void uploadAndSaveDocuments(String compId,ReimbursementRegistrationDTO registrationDTO) throws DAOException {
		try {
			for (RegistrationFileDTO  registrationFileDTO : registrationDTO.getRegistrationFileDTOs()) {
				transferFilesToFileServer(registrationDTO,registrationFileDTO);
			}
			reimbursementClaimsDAO.insertTDSLEVELD(compId, registrationDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}
	
	@Override
	public byte[] getDocumentDetails(String path) throws MedicalClaimsException {
		byte[] docBytes = null;
		try {
			docBytes = Files.readAllBytes(new File(ClaimConstants.CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER + File.separator + path).toPath());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MedicalClaimsException(e);
		}
		return docBytes;
	}
	
	private void transferFilesToFileServer(ReimbursementRegistrationDTO registrationDTO,RegistrationFileDTO  registrationFileDTO) throws DAOException {
			File uploadDir = null;			
		 try {
			 	String base64 = registrationFileDTO.getBase64String().split(",")[1];
				byte [] filearray = Base64.getDecoder().decode(base64.getBytes("UTF-8"));
				String filename = registrationFileDTO.getDocName();
				String fileServer = ClaimConstants.CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER + File.separator;

				String path = registrationDTO.getClaimRefNo() + File.separator+registrationFileDTO.getDocTypeDesc();
				registrationFileDTO.setDocPath(path);
				path = fileServer + path ;
	            uploadDir = new File(path);
	            if (!uploadDir.exists()) {
	                uploadDir.mkdirs();
	            }
	            try (FileOutputStream fos = new FileOutputStream(path+ File.separator+filename)) {
	    		    fos.write(filearray);
	    		} catch (IOException ioe) {
	    		    ioe.printStackTrace();
	    		}
	        } catch (Exception ex) {
	        	ex.printStackTrace();
	            throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
	        }
	}

}