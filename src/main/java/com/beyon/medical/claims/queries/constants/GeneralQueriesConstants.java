package com.beyon.medical.claims.queries.constants;

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
	public static final String GENERAL_QUERIES_GET_EMIRATES_IDS = claimsResourceBundle.getString("medical.claim.general.queries.emiratesid");
	public static final String GENERAL_QUERIES_GET_VOUCHER_NUMBERS = claimsResourceBundle.getString("medical.claim.general.queries.vouchernumbers");
	public static final String GENERAL_QUERIES_GET_MEMBER_NAMES = claimsResourceBundle.getString("medical.claim.general.queries.membernames");
	public static final String GENERAL_QUERIES_GET_PROVIDER_DETAILS = claimsResourceBundle.getString("medical.claim.general.queries.providerdetails");
	public static final String GENERAL_QUERIES_GET_UID_DEFINITION_TYPES = claimsResourceBundle.getString("medical.claim.general.queries.uiddefinitiontype");

	public static final String GENERAL_QUERIES_UID_STATUS_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.statustype");
	public static final String GENERAL_QUERIES_UID_ENCOUNTER_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.encountertype");
	public static final String GENERAL_QUERIES_UID_REPORT_BY_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.reportbytype");
	public static final String GENERAL_QUERIES_UID_REQUEST_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.requesttype");
	public static final String GENERAL_QUERIES_UID_PAYMENT_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.paymenttype");
	public static final String GENERAL_QUERIES_UID_DOCUMENT_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.documenttype");
	public static final String GENERAL_QUERIES_UID_SOURCE_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.sourcetype");

}