package com.beyon.medical.claims.pbm.service;

import org.springframework.stereotype.Service;

import com.beyon.medical.claims.constants.ClaimConstants;

@Service
public class PBMClaimsServiceImpl implements PBMClaimsService {

	private static final String CLASS_NAME = PBMClaimsServiceImpl.class.getCanonicalName();

	private final ClaimConstants wfcConstants = ClaimConstants.getInstance();

	
}