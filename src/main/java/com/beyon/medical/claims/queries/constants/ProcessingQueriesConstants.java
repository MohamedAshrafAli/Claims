package com.beyon.medical.claims.queries.constants;

import java.io.Serializable;

import com.beyon.framework.util.FoundationUtils;

public final class ProcessingQueriesConstants implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static ProcessingQueriesConstants commonconstants = null;
	
	private ProcessingQueriesConstants(){
		
	}
	public static ProcessingQueriesConstants getInstance(){
		synchronized (ProcessingQueriesConstants.class) {
			if(commonconstants==null){
				commonconstants = new ProcessingQueriesConstants();
			}
			
		}
		return commonconstants;
	}
	
	public static final String QUERIES_INSERT_CTDS_LEVEL_MDIAG = FoundationUtils.getProperty("queries.insert.ctdslevelmdiag");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_MDIAG = FoundationUtils.getProperty("queries.update.ctdslevelmdiag");
	public static final String QUERIES_INSERT_CTDS_LEVEL_MSRVC = FoundationUtils.getProperty("queries.insert.ctdslevelmsrvc");
	public static final String QUERIES_INSERT_CHDS_LEVEL_MSRVC = FoundationUtils.getProperty("queries.insert.chdslevelmsrvc");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_MSRVC = FoundationUtils.getProperty("queries.update.ctdslevelmsrvc");
	public static final String QUERIES_DELETE_CTDS_LEVEL_MSRVC = FoundationUtils.getProperty("queries.delete.ctdslevelmsrvc");
	public static final String QUERIES_APPROVE_CTDS_LEVEL_MSRVC = FoundationUtils.getProperty("queries.approve.ctdslevelmsrvc");
	public static final String QUERIES_DETAILS_CTDS_LEVEL_MSRVC_VERSION = FoundationUtils.getProperty("queries.details.ctdslevelmsrvc.version");
	public static final String QUERIES_INSERT_CTDS_LEVEL_MSRVC_SEQUENCE_NAME = FoundationUtils.getProperty("queries.insert.ctdslevelmsrvc.sequence.name");	
	public static final String QUERIES_INSERT_CTDS_LEVEL_MC = FoundationUtils.getProperty("queries.insert.ctdslevelmc");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_MC = FoundationUtils.getProperty("queries.update.ctdslevelmc");
	public static final String QUERIES_UPDATE_STATUS_CTDS_LEVEL_MC = FoundationUtils.getProperty("queries.update.status.ctdslevelmc");
	public static final String QUERIES_PROCESSING_DETAILS = FoundationUtils.getProperty("queries.processing.details");
	public static final String QUERIES_PROCESSING_DETAILS_FOR_ASSIGNMENT = FoundationUtils.getProperty("queries.processing.details.for.assignment");
	public static final String QUERIES_PROCESSING_SERVICE_DETAILS = FoundationUtils.getProperty("queries.processing.service.details");
	public static final String QUERIES_PROCESSING_DETAILS_ID_CRITERIA = FoundationUtils.getProperty("queries.processing.details.id.criteria");
	public static final String QUERIES_PROCESSING_DETAILS_CLAIMNUMBER_CRITERIA = FoundationUtils.getProperty("queries.processing.details.claimnumber.criteria");
	public static final String QUERIES_PROCESSING_DETAILS_REQUESTNUMBER_CRITERIA = FoundationUtils.getProperty("queries.processing.details.requestnumber.criteria");
	public static final String QUERIES_INSERT_CTDS_LEVEL_E = FoundationUtils.getProperty("queries.insert.ctdslevele");
	public static final String QUERIES_INSERT_CHDS_LEVEL_E = FoundationUtils.getProperty("queries.insert.chdslevele");
	public static final String QUERIES_UPDATE_CTDS_LEVEL_E = FoundationUtils.getProperty("queries.update.approve.ctdslevele");
	public static final String QUERIES_CHECK_EXISTENCE_CTDS_LEVEL_E = FoundationUtils.getProperty("queries.check.existance.ctdslevele");
	public static final String QUERIES_INSERT_CTDS_LEVEL_E_SEQUENCE_NAME = FoundationUtils.getProperty("queries.insert.ctdslevele.sequence.name");
	public static final String QUERIES_UPDATE_STATUS_CTDS_LEVEL_C = FoundationUtils.getProperty("queries.update.status.ctdslevelc");
	public static final String QUERIES_PROCESSING_DETAILS_ORDER_BY = FoundationUtils.getProperty("queries.processing.details.orderby");

}