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

}