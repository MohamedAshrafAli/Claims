package com.beyon.medical.claims.reimbursement.service;

import org.springframework.stereotype.Service;

import com.beyon.medical.claims.constants.ClaimConstants;

@Service
public class ReimbursementClaimsServiceImpl implements ReimbursementClaimsService {

	private static final String CLASS_NAME = ReimbursementClaimsServiceImpl.class.getCanonicalName();

	private final ClaimConstants wfcConstants = ClaimConstants.getInstance();

	
}