package com.beyon.medical.claims.ui.facade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.GeneralClaimsDAOImpl;

@Service
public class MedicalClaimsUIServiceFacadeImpl implements MedicalClaimsUIServiceFacade {
	
	@Autowired
	private GeneralClaimsDAOImpl generalClaimsDao;

	private final ClaimConstants claimConstants = ClaimConstants.getInstance();

	@Override
	public List<String> getMemberCardNumberList(String compId,String cardNumber) throws DAOException {
		List<String> memberCardNumers = generalClaimsDao.getMedicalCardNumbers(compId, cardNumber);
		return memberCardNumers;
	}  

	@Override
	public List<String> getMemberNumberList(String compId,String memberNumber) throws DAOException {
		List<String> memberNumbers = generalClaimsDao.getMemberNumberList(compId, memberNumber);
		return memberNumbers;
	}

	@Override
	public List<String> getPolicyNumberList(String compId,String policyNumber) throws DAOException {
		List<String> policyNumbers = generalClaimsDao.getPolicyNumbersList(compId, policyNumber);
		return policyNumbers;
	}

	@Override
	public List<String> getEncounterTypes(String compId) throws DAOException {
		List<String> encounterTypes = generalClaimsDao.getEncounterTypes(compId);
		return encounterTypes;
	}

	@Override
	public List<String> getRequestTypes(String compId) throws DAOException {
		List<String> requestTypes = generalClaimsDao.getRequestTypes(compId);
		return requestTypes;
	}

	@Override
	public List<String> getReportByTypes(String compId) throws DAOException {
		List<String> reportByTypes = generalClaimsDao.getReportByTypes(compId);
		return reportByTypes;
	}

	@Override
	public List<String> getPaymentTypes(String compId) throws DAOException {
		List<String> paymentTypes = generalClaimsDao.getPaymentTypes(compId);
		return paymentTypes;
	}

	@Override
	public List<String> getDocumentTypes(String compId) throws DAOException {
		List<String> documentTypes = generalClaimsDao.getDocumentTypes(compId);
		return documentTypes;
	}

	
}