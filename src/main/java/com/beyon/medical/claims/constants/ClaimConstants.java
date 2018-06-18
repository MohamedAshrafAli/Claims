package com.beyon.medical.claims.constants;

import com.beyon.framework.util.FoundationUtils;

public class ClaimConstants
{
	private static ClaimConstants	claimConstants	= null;
	
	private ClaimConstants()
	{

	}

	public static ClaimConstants getInstance()
	{
		synchronized (ClaimConstants.class)
		{
			if (claimConstants == null)
				claimConstants = new ClaimConstants();
		}

		return claimConstants;
	}
	
	public static final String CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER = FoundationUtils.getProperty("reimbursement.registration.file.server");
	
}
