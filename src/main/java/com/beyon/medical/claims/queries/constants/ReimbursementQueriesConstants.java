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

	public static final String REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MFNOL = reimbursementResourceBundle.getString("reimbursement.queries.update.ctdslevelmfnol");

	public static final String REIMBURSEMENT_QUERIES_INSERT_TDS_LEVEL_D = reimbursementResourceBundle.getString("reimbursement.queries.insert.tdsleveld");
	public static final String REIMBURSEMENT_QUERIES_DETAILS_TDS_LEVEL_D = reimbursementResourceBundle.getString("reimbursement.queries.details.tdsleveld");
	public static final String REIMBURSEMENT_QUERIES_DELETE_TDS_LEVEL_D = reimbursementResourceBundle.getString("reimbursement.queries.delete.tdsleveld");

	public static final String REIMBURSEMENT_QUERIES_UPDATE_TDS_LEVEL_D = reimbursementResourceBundle.getString("reimbursement.queries.update.tdsleveld");

	
	public static final String CLAIMANT_IS_THE_CUSTOMER = "Y";
	public static final String CLAIM_REPORTED_BY_INSURED = "01";
	public static final String CLAIM_MOD_TYPE = "GM";
	
	
	public static final String REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.ctds.assignment.details");

	public static final String REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.assignment.claimnumber.criteria");
	public static final String REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_FROM_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.assignment.reqreceivedfrom.criteria");
	public static final String REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_TO_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.assignment.reqreceivedto.criteria");
	public static final String REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_BETWEEN_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.ctds.details.assignment.reqreceivedfromto.criteria");
	
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_C = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelc");
	public static final String REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_C = reimbursementResourceBundle.getString("reimbursement.queries.update.ctdslevelc");
	public static final String REIMBURSEMENT_QUERIES_CTDS_ASSIGNMENT_MEMBER_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.ctds.assignment.member.details");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_CP = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelcp");
	public static final String REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_CP = reimbursementResourceBundle.getString("reimbursement.queries.update.ctdslevelcp");

	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_CP_SEQUENCE_NAME = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelcp.sequence.name");
	
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_SL = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelsl");
	public static final String REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_SL = reimbursementResourceBundle.getString("reimbursement.queries.update.ctdslevelsl");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_SL = reimbursementResourceBundle.getString("reimbursement.queries.insert.chdslevelsl");

	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_SL_SEQUENCE_NAME = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelsl.sequence.name");

	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MDIAG = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelmdiag");
	public static final String REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MDIAG = reimbursementResourceBundle.getString("reimbursement.queries.update.ctdslevelmdiag");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MSRVC = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelmsrvc");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CHDS_LEVEL_MSRVC = reimbursementResourceBundle.getString("reimbursement.queries.insert.chdslevelmsrvc");
	public static final String REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MSRVC = reimbursementResourceBundle.getString("reimbursement.queries.update.ctdslevelmsrvc");
	public static final String REIMBURSEMENT_QUERIES_DETAILS_CTDS_LEVEL_MSRVC_VERSION = reimbursementResourceBundle.getString("reimbursement.queries.details.ctdslevelmsrvc.version");
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MSRVC_SEQUENCE_NAME = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelmsrvc.sequence.name");	
	public static final String REIMBURSEMENT_QUERIES_INSERT_CTDS_LEVEL_MC = reimbursementResourceBundle.getString("reimbursement.queries.insert.ctdslevelmc");
	public static final String REIMBURSEMENT_QUERIES_UPDATE_CTDS_LEVEL_MC = reimbursementResourceBundle.getString("reimbursement.queries.update.ctdslevelmc");
	public static final String REIMBURSEMENT_QUERIES_PROCESSING_DETAILS = reimbursementResourceBundle.getString("reimbursement.queries.processing.details");
	public static final String REIMBURSEMENT_QUERIES_PROCESSING_DETAILS_ID_CRITERIA = reimbursementResourceBundle.getString("reimbursement.queries.processing.details.id.criteria");
	public static final String REIMBURSEMENT_QUERIES_PROCESSING_DETAILS_CLAIMNUMBER_CRITERIA = reimbursementResourceBundle.getString("reimbursement.queries.processing.details.claimnumber.criteria");
	public static final String REIMBURSEMENT_QUERIES_PROCESSING_DETAILS_REQUESTNUMBER_CRITERIA = reimbursementResourceBundle.getString("reimbursement.queries.processing.details.requestnumber.criteria");
	
	
}