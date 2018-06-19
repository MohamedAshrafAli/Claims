package com.beyon.medical.claims.registration.reimbursement.service;

import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_CARD_NO_CRITERIA;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_EMIRATES_CRITERIA;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_ID;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_ORDER_BY;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_POLICY_CRITERIA;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_DETAILS;
import static com.beyon.medical.claims.queries.constants.RegistrationQueriesConstants.QUERIES_DETAILS_TDS_LEVEL_D;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beyon.framework.util.FoundationUtils;
import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.exception.MedicalClaimsException;
import com.beyon.medical.claims.general.dto.RegistrationDTO;
import com.beyon.medical.claims.general.dto.RegistrationFileDTO;
import com.beyon.medical.claims.registration.dao.ClaimRegistrationDAOImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service(value="reimbursementClaimRegistrationServiceImpl")
public class ReimbursementClaimRegistrationServiceImpl implements ReimbursementClaimRegistrationService {

	@Autowired
	private ClaimRegistrationDAOImpl reimbursementClaimsDAO;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<RegistrationDTO> getRegistrationDetails(ObjectNode paramMap)
			throws DAOException {
		List<RegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = QUERIES_CTDS_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails = reimbursementClaimsDAO.getRegistrationListViewData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}

	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public RegistrationDTO getRegistrationDetailsById(String id) throws DAOException {
		List<RegistrationDTO> reimbursementRegDetails = null;
		RegistrationDTO registrationDTO = null;
		try {
			String strQuery = QUERIES_CTDS_DETAILS_ID;
			Map<String, Object> inputMap = new HashMap<>();
			inputMap.put("id", id);
			reimbursementRegDetails = reimbursementClaimsDAO.getRegistrationDetailsById(strQuery, inputMap);
			if (reimbursementRegDetails != null && !reimbursementRegDetails.isEmpty()) {
				registrationDTO = reimbursementRegDetails.get(0);
				List<RegistrationFileDTO> fileDTOs = reimbursementClaimsDAO
						.getRegistrationFileDetailsById(QUERIES_DETAILS_TDS_LEVEL_D, inputMap);
				registrationDTO.setRegistrationFileDTOs(fileDTOs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return registrationDTO;
	}

	private String getConstructedQuerywithCriterion(String strQuery, Map<String, Object> inputMap) {
		if (strQuery.contains("<CRITERIA>")) {
			Iterator<String> mapIter = inputMap.keySet().iterator();
			StringBuilder builder = new StringBuilder();
			while (mapIter.hasNext()) {
				String key = (String) mapIter.next();
				if (key.equalsIgnoreCase("policyNumber")) {
					builder.append(QUERIES_CTDS_DETAILS_POLICY_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("memberNumber")) {
					builder.append(QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("voucherNumber")) {
					builder.append(QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("cardNumber")) {
					builder.append(QUERIES_CTDS_DETAILS_CARD_NO_CRITERIA + " ");
				} else if (key.equalsIgnoreCase("emiratesId")) {
					builder.append(QUERIES_CTDS_DETAILS_EMIRATES_CRITERIA + " ");
				}
			}
			strQuery = strQuery.replaceAll("<CRITERIA>", builder.toString());
			strQuery += " " + QUERIES_CTDS_DETAILS_ORDER_BY;

		}
		return strQuery;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<RegistrationDTO> getRegistrationDetailsForPolicyAndMemberNo(ObjectNode paramMap)
			throws DAOException {
		List<RegistrationDTO> reimbursementRegDetails = null;
		try {
			Map<String, Object> inputMap = FoundationUtils.getObjectMapper().convertValue(paramMap, Map.class);
			String strQuery = QUERIES_DETAILS;
			strQuery = getConstructedQuerywithCriterion(strQuery, inputMap);
			reimbursementRegDetails = reimbursementClaimsDAO.getRegistrationListData(strQuery, inputMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return reimbursementRegDetails;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public RegistrationDTO saveRegistrationDetails(String compId,
			RegistrationDTO registrationDTO) throws DAOException {
		RegistrationDTO _registrationDTO = null;
		try {
			if (registrationDTO.getId() == null || registrationDTO.getId() == 0L) {
				_registrationDTO = reimbursementClaimsDAO.insertReimbursementRegistrationDetails(compId,
						registrationDTO);
			} else {
				_registrationDTO = reimbursementClaimsDAO.updateReimbursementRegistrationDetails(compId,
						registrationDTO);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return _registrationDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public boolean deleteRegistrationDocument(String id, String docType, String docName, String path)
			throws DAOException {
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
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public void uploadAndSaveDocuments(String compId, RegistrationDTO registrationDTO)
			throws DAOException {
		try {
			for (RegistrationFileDTO registrationFileDTO : registrationDTO.getRegistrationFileDTOs()) {
				transferFilesToFileServer(registrationDTO, registrationFileDTO);
			}
			reimbursementClaimsDAO.insertTDSLEVELD(compId, registrationDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
	public byte[] getDocumentDetails(String path) throws MedicalClaimsException {
		byte[] docBytes = null;
		try {
			docBytes = Files.readAllBytes(
					new File(ClaimConstants.CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER + File.separator + path).toPath());
		} catch (Exception e) {
			e.printStackTrace();
			throw new MedicalClaimsException(e);
		}
		return docBytes;
	}


	private void transferFilesToFileServer(RegistrationDTO registrationDTO,
			RegistrationFileDTO registrationFileDTO) throws DAOException {
		File uploadDir = null;
		try {
			String base64 = registrationFileDTO.getBase64String().split(",")[1];
			byte[] filearray = Base64.getDecoder().decode(base64.getBytes("UTF-8"));
			String filename = registrationFileDTO.getDocName();
			String fileServer = ClaimConstants.CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER + File.separator;

			String path = registrationDTO.getClaimRefNo() + File.separator + registrationFileDTO.getDocTypeDesc();
			registrationFileDTO.setDocPath(path);
			path = fileServer + path;
			uploadDir = new File(path);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			try (FileOutputStream fos = new FileOutputStream(path + File.separator + filename)) {
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