package com.beyon.medical.claims.preauthorization.service;

import org.springframework.stereotype.Service;

import com.beyon.medical.claims.constants.ClaimConstants;

@Service
public class PreAuthorizationServiceImpl implements PreAuthorizationService {

	private static final String CLASS_NAME = PreAuthorizationServiceImpl.class.getCanonicalName();

	private final ClaimConstants wfcConstants = ClaimConstants.getInstance();

	
}