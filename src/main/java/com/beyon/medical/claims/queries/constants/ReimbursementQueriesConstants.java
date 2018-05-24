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

	
	public static final String REIMBURSEMENT_QUERIES_CTDS_DETAILS = reimbursementResourceBundle.getString("medical.claim.reimbursement.queries.ctds.details");

}