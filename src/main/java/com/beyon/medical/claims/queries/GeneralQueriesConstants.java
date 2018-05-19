package com.beyon.medical.claims.queries;

import java.io.Serializable;
import java.util.ResourceBundle;

public final class GeneralQueriesConstants implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static GeneralQueriesConstants commonconstants = null;
	
	private GeneralQueriesConstants(){
		
	}
	public static GeneralQueriesConstants getInstance(){
		synchronized (GeneralQueriesConstants.class) {
			if(commonconstants==null){
				commonconstants = new GeneralQueriesConstants();
			}
			
		}
		return commonconstants;
	}
	public static ResourceBundle claimsResourceBundle = ResourceBundle.getBundle("com.beyon.medical.claims.properties.queries.GeneralQueries");

	public static final String GENERAL_QUERIES_GET_CARD_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.cardnumbers");
	public static final String GENERAL_QUERIES_GET_MEMBER_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.membernumbers");
	public static final String GENERAL_QUERIES_GET_POLICY_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.policynumbers");
	public static final String GENERAL_QUERIES_GET_ENCOUNTER_TYPES = claimsResourceBundle.getString("medical.claim.general.queries.encountertypes");
	public static final String GENERAL_QUERIES_GET_REPORT_BY_TYPES = claimsResourceBundle.getString("medical.claim.general.queries.reportbytypes");
	public static final String GENERAL_QUERIES_GET_REQUEST_TYPES = claimsResourceBundle.getString("medical.claim.general.queries.requesttypes");
	public static final String GENERAL_QUERIES_GET_PAYMENT_TYPES = claimsResourceBundle.getString("medical.claim.general.queries.paymenttypes");
	public static final String GENERAL_QUERIES_GET_DOCUMENT_TYPES = claimsResourceBundle.getString("medical.claim.general.queries.documenttypes");

}