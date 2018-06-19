package com.beyon.medical.claims.queries.constants;

import java.io.Serializable;

import com.beyon.framework.util.FoundationUtils;

public final class RegistrationQueriesConstants implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static RegistrationQueriesConstants commonconstants = null;
	
	private RegistrationQueriesConstants(){
		
	}
	public static RegistrationQueriesConstants getInstance(){
		synchronized (RegistrationQueriesConstants.class) {
			if(commonconstants==null){
				commonconstants = new RegistrationQueriesConstants();
			}
			
		}
		return commonconstants;
	}

	
	public static final String QUERIES_CTDS_DETAILS = FoundationUtils.getProperty("queries.ctds.details");
	public static final String QUERIES_CTDS_DETAILS_POLICY_CRITERIA = FoundationUtils.getProperty("queries.ctds.details.policynumber.criteria");
	public static final String QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA = FoundationUtils.getProperty("queries.ctds.details.membernumber.criteria");
	public static final String QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA = FoundationUtils.getProperty("queries.ctds.details.vouchernumber.criteria");
	public static final String QUERIES_CTDS_DETAILS_CARD_NO_CRITERIA = FoundationUtils.getProperty("queries.ctds.details.cardnumber.criteria");
	public static final String QUERIES_CTDS_DETAILS_EMIRATES_CRITERIA = FoundationUtils.getProperty("queries.ctds.details.emiratesid.criteria");
	public static final String QUERIES_CTDS_DETAILS_ID = FoundationUtils.getProperty("queries.ctds.details.id");
	public static final String QUERIES_DETAILS = FoundationUtils.getProperty("queries.details");
	public static final String QUERIES_INSERT_SEQUENCE_NAME = FoundationUtils.getProperty("queries.insert.sequence");
	public static final String QUERIES_INSERT_PROCEDURE_CLAIM_REF_NO = FoundationUtils.getProperty("queries.insert.procedure.claimrefno");
	public static final String QUERIES_INSERT_CTDS_LEVEL_R = FoundationUtils.getProperty("queries.insert.ctdslevelr");
	public static final String QUERIES_INSERT_CTDS_LEVEL_MR = FoundationUtils.getProperty("queries.insert.ctdslevelmr");
	public static final String QUERIES_INSERT_CTDS_LEVEL_FNOL = FoundationUtils.getProperty("queries.insert.ctdslevelfnol");
	public static final String QUERIES_INSERT_CTDS_LEVEL_MFNOL = FoundationUtils.getProperty("queries.insert.ctdslevelmfnol");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_MFNOL = FoundationUtils.getProperty("queries.update.ctdslevelmfnol");
	public static final String QUERIES_INSERT_TDS_LEVEL_D = FoundationUtils.getProperty("queries.insert.tdsleveld");
	public static final String QUERIES_DETAILS_TDS_LEVEL_D = FoundationUtils.getProperty("queries.details.tdsleveld");
	public static final String QUERIES_DELETE_TDS_LEVEL_D = FoundationUtils.getProperty("queries.delete.tdsleveld");
	public static final String QUERIES_UPDATE_TDS_LEVEL_D = FoundationUtils.getProperty("queries.update.tdsleveld");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_FNOL_FOR_CLAIM_NUMBER = FoundationUtils.getProperty("queries.update.ctdslevelfnol");
	public static final String CLAIMANT_IS_THE_CUSTOMER = "Y";
	public static final String CLAIM_REPORTED_BY_INSURED = "01";
	public static final String CLAIM_MOD_TYPE = "GM";
	public static final String QUERIES_CTDS_DETAILS_ORDER_BY = FoundationUtils.getProperty("queries.ctds.details.orderby");

}