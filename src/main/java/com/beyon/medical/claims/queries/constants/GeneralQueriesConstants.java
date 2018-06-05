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

	
	public static final String GENERAL_QUERIES_GET_MEMBER_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.ids.membernumbers");
	
	public static final String GENERAL_QUERIES_GET_IDS_COLUMN_MEMBER_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.ids.column.membernumbers");

	public static final String GENERAL_QUERIES_GET_POLICY_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.ids.policynumbers");
	
	public static final String GENERAL_QUERIES_GET_IDS_COLUMN_POLICY_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.ids.column.policynumbers");

	public static final String GENERAL_QUERIES_GET_PROVIDER_DETAILS = claimsResourceBundle.getString("medical.claim.general.queries.providerdetails");
	
	public static final String GENERAL_QUERIES_GET_CURRENCY_DETAILS = claimsResourceBundle.getString("medical.claim.general.queries.currencydetail");

	public static final String GENERAL_QUERIES_GET_USER_DIVISION = claimsResourceBundle.getString("medical.claim.general.queries.user.division");

	public static final String GENERAL_QUERIES_GET_COB_DETAIL = claimsResourceBundle.getString("medical.claim.general.queries.cob.detail");
	
	public static final String GENERAL_QUERIES_GET_UNIVERSAL_CURRENCIES = claimsResourceBundle.getString("medical.claim.general.queries.universal.currencies");

	
	
	public static final String GENERAL_QUERIES_GET_CTDS_LISTS	 = claimsResourceBundle.getString("medical.claim.general.queries.ctds.lists");
	public static final String GENERAL_QUERIES_GET_CTDS_COLUMN_CARD_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.ctds.column.cardnumbers");
	public static final String GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.ctds.column.membernumbers");
	public static final String GENERAL_QUERIES_GET_CTDS_COLUMN_POLICY_NUMBERS	 = claimsResourceBundle.getString("medical.claim.general.queries.ctds.column.policynumbers");
	public static final String GENERAL_QUERIES_GET_CTDS_COLUMN_EMIRATES_IDS = claimsResourceBundle.getString("medical.claim.general.queries.ctds.column.emiratesid");
	public static final String GENERAL_QUERIES_GET_CTDS_COLUMN_VOUCHER_NUMBERS = claimsResourceBundle.getString("medical.claim.general.queries.ctds.column.vouchernumbers");
	public static final String GENERAL_QUERIES_GET_CTDS_COLUMN_CLAIM_NUMBERS = claimsResourceBundle.getString("medical.claim.general.queries.ctds.column.claimnumbers");
	public static final String GENERAL_QUERIES_GET_CTDS_COLUMN_MEMBER_NAMES = claimsResourceBundle.getString("medical.claim.general.queries.ctds.column.membernames");

	public static final String GENERAL_QUERIES_GET_UID_DEFINITION_TYPES = claimsResourceBundle.getString("medical.claim.general.queries.uiddefinitiontype");
	public static final String GENERAL_QUERIES_UID_STATUS_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.statustype");
	public static final String GENERAL_QUERIES_UID_ENCOUNTER_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.encountertype");
	public static final String GENERAL_QUERIES_UID_REPORT_BY_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.reportbytype");
	public static final String GENERAL_QUERIES_UID_REQUEST_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.requesttype");
	public static final String GENERAL_QUERIES_UID_PAYMENT_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.paymenttype");
	public static final String GENERAL_QUERIES_UID_DOCUMENT_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.documenttype");
	public static final String GENERAL_QUERIES_UID_SOURCE_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.sourcetype");
	public static final String GENERAL_QUERIES_UID_CLAIMS_STATUS_REASON_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.claimstatusreason");
	public static final String GENERAL_QUERIES_UID_EST_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.esttype");
	public static final String GENERAL_QUERIES_UID_LOSS_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.losstype");
	public static final String GENERAL_QUERIES_UID_DIAG_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.diagtype");
	public static final String GENERAL_QUERIES_UID_CLAIM_TYPE = claimsResourceBundle.getString("medical.claim.general.queries.uid.claimtype");
	public static final String GENERAL_QUERIES_UID_CLAIM_CONDITION = claimsResourceBundle.getString("medical.claim.general.queries.uid.claimcondition");

	

	public static final String GENERAL_QUERIES_USER_LIST = claimsResourceBundle.getString("medical.claim.general.queries.user.lists");
	public static final String GENERAL_QUERIES_SERVICE_CODE_DETAILS = claimsResourceBundle.getString("medical.claim.general.queries.service.code.details");
	public static final String GENERAL_QUERIES_DIAGNOSIS_CODE_DETAILS = claimsResourceBundle.getString("medical.claim.general.queries.diagnosis.code.details");
	public static final String GENERAL_QUERIES_REJECTION_CODE_DETAILS = claimsResourceBundle.getString("medical.claim.general.queries.rejection.code.details");



}