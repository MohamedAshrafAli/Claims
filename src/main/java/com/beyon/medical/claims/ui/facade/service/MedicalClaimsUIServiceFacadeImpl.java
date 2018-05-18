package com.beyon.medical.claims.ui.facade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyon.medical.claims.constants.ClaimConstants;
import com.beyon.medical.claims.general.dao.GeneralClaimsDAOImpl;

@Service
public class MedicalClaimsUIServiceFacadeImpl implements MedicalClaimsUIServiceFacade {
	
	@Autowired
	private GeneralClaimsDAOImpl generalClaimsDao;

	private final ClaimConstants claimConstants = ClaimConstants.getInstance();

	@Override
	public List<String> getMemberCardNumberList(String compId,String cardNumber) {
		return null;
	}

	@Override
	public List<String> getMemberNumberList(String compId,String memberNumber) {
		return null;
	}

	@Override
	public List<String> getPolicyNumberList(String compId,String policyNumber) {
		return null;
	}

	@Override
	public List<String> getEncounterTypes(String compId) {
		return null;
	}

	@Override
	public List<String> getRequestTypes(String compId) {
		return null;
	}

	@Override
	public List<String> getReportByTypes(String compId) {
		return null;
	}

	@Override
	public List<String> getPaymentTypes(String compId) {
		return null;
	}

	@Override
	public List<String> getDocumentTypes(String compId) {
		return null;
	}

	
}