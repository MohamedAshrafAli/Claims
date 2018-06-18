package com.beyon.medical.claims.queries.constants;

import java.io.Serializable;

import com.beyon.framework.util.FoundationUtils;

public final class AssignmentQueriesConstants implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static AssignmentQueriesConstants commonconstants = null;
	
	private AssignmentQueriesConstants(){
		
	}
	public static AssignmentQueriesConstants getInstance(){
		synchronized (AssignmentQueriesConstants.class) {
			if(commonconstants==null){
				commonconstants = new AssignmentQueriesConstants();
			}
			
		}
		return commonconstants;
	}

	public static final String QUERIES_CTDS_ASSIGNMENT_DETAILS = FoundationUtils.getProperty("queries.ctds.assignment.details");
	public static final String QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_DETAILS = FoundationUtils.getProperty("queries.ctds.details.assignment.claimnumber.criteria");
	public static final String QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_FROM_DETAILS = FoundationUtils.getProperty("queries.ctds.details.assignment.reqreceivedfrom.criteria");
	public static final String QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_TO_DETAILS = FoundationUtils.getProperty("queries.ctds.details.assignment.reqreceivedto.criteria");
	public static final String QUERIES_CTDS_ASSIGNMENT_REQ_RECEIVED_BETWEEN_DETAILS = FoundationUtils.getProperty("queries.ctds.details.assignment.reqreceivedfromto.criteria");
	public static final String QUERIES_INSERT_CTDS_LEVEL_C = FoundationUtils.getProperty("queries.insert.ctdslevelc");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_C = FoundationUtils.getProperty("queries.update.ctdslevelc");
	public static final String QUERIES_CTDS_ASSIGNMENT_MEMBER_DETAILS = FoundationUtils.getProperty("queries.ctds.assignment.member.details");
	public static final String QUERIES_INSERT_CTDS_LEVEL_CP = FoundationUtils.getProperty("queries.insert.ctdslevelcp");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_CP = FoundationUtils.getProperty("queries.update.ctdslevelcp");
	public static final String QUERIES_CTDS_ASSIGNMENT_STATUS_CRITERIA = FoundationUtils.getProperty("queries.ctds.details.assignment.status.criteria");
	public static final String QUERIES_CTDS_ASSIGNMENT_CLAIMNUMBER_EMPTY_CRITERIA = FoundationUtils.getProperty("queries.ctds.details.assignment.claimnumber.empty.criteria");
	public static final String QUERIES_CTDS_ASSIGNMENT_BY_STATUS = FoundationUtils.getProperty("queries.ctds.details.assignment.by.status");
	public static final String QUERIES_INSERT_CTDS_LEVEL_CP_SEQUENCE_NAME = FoundationUtils.getProperty("queries.insert.ctdslevelcp.sequence.name");
	public static final String QUERIES_INSERT_CTDS_LEVEL_SL = FoundationUtils.getProperty("queries.insert.ctdslevelsl");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_SL = FoundationUtils.getProperty("queries.update.ctdslevelsl");
	public static final String QUERIES_INSERT_CHDS_LEVEL_SL = FoundationUtils.getProperty("queries.insert.chdslevelsl");
	public static final String QUERIES_INSERT_CTDS_LEVEL_SL_SEQUENCE_NAME = FoundationUtils.getProperty("queries.insert.ctdslevelsl.sequence.name");

}