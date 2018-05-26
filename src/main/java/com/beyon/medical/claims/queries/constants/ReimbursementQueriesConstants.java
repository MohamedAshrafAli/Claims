package com.beyon.medical.claims.queries.constants;

import java.io.Serializable;
import java.util.ResourceBundle;

public final class ReimbursementQueriesConstants implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static ReimbursementQueriesConstants commonconstants = null;
	
	private ReimbursementQueriesConstants(){
		
	}
	public static ReimbursementQueriesConstants getInstance(){
		synchronized (ReimbursementQueriesConstants.class) {
			if(commonconstants==null){
				commonconstants = new ReimbursementQueriesConstants();
			}
			
		}
		return commonconstants;
	}
	public static ResourceBundle reimbursementResourceBundle = ResourceBundle.getBundle("com.beyon.medical.claims.properties.queries.ReimbursementQueries");

	
	public static final String REIMBURSEMENT_QUERIES_CTDS_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details");
	public static final String REIMBURSEMENT_QUERIES_CTDS_DETAILS_POLICY_CRITERIA = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.policynumber.criteria");
	public static final String REIMBURSEMENT_QUERIES_CTDS_DETAILS_MEM_NO_CRITERIA = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.membernumber.criteria");
	public static final String REIMBURSEMENT_QUERIES_CTDS_DETAILS_VOUCHER_CRITERIA = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.vouchernumber.criteria");
	
	public static final String REIMBURSEMENT_QUERIES_CTDS_DETAILS_ID = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.id");

	public static final String REIMBURSEMENT_QUERIES_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.details");

	public static final String REIMBURSEMENT_QUERIES_INSERT_SEQUENCE_NAME = reimbursementResourceBundle.getString("reimbursement.queries.insert.sequence");
	public static final String REIMBURSEMENT_QUERIES_INSERT_PROCEDURE_CLAIM_REF_NO = reimbursementResourceBundle.getString("reimbursement.queries.insert.procedure.claimrefno");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_R = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelr");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MR = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelmr");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_FNOL = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelfnol");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MFNOL = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelmfnol");

	
	public static final String CLAIMANT_IS_THE_CUSTOMER = "Y";
	public static final String CLAIM_REPORTED_BY_INSURED = "01";
	public static final String CLAIM_MOD_TYPE = "GM";

	
	
	
	
	
}