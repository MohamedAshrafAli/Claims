package com.beyon.medical.claims.eclaims.service;

import org.springframework.stereotype.Service;

import com.beyon.medical.claims.constants.ClaimConstants;

@Service
public class EClaimsServiceImpl implements  EClaimsService {

	private static final String CLASS_NAME = EClaimsServiceImpl.class.getCanonicalName();

	private final ClaimConstants wfcConstants = ClaimConstants.getInstance();

	
}