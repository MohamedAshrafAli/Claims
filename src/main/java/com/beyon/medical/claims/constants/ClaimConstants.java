package com.beyon.medical.claims.constants;

import java.util.ResourceBundle;

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
	
	
	public static ResourceBundle claimsResourceBundle = ResourceBundle.getBundle("com.beyon.medical.claims.properties.Claims");
	public static final String CLAIM_REIMBURSEMENT_REGISTRATION_FILE_SERVER = claimsResourceBundle.getString("reimbursement.registration.file.server");

	
	public final String DATE_FORMAT = this.claimsResourceBundle.getString("key.dateFormat");
	
	public String getCMErrPropValue(String key){
		ResourceBundle bundle = ResourceBundle.getBundle("com.beyon.medical.properties.CMErrorMessages");
		String value = bundle.getString(key);
		return value;
	}
	
	
}
