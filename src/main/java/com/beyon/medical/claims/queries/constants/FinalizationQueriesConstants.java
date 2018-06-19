package com.beyon.medical.claims.queries.constants;

import java.io.Serializable;

import com.beyon.framework.util.FoundationUtils;

public final class FinalizationQueriesConstants implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static FinalizationQueriesConstants commonconstants = null;
	
	private FinalizationQueriesConstants(){
		
	}
	public static FinalizationQueriesConstants getInstance(){
		synchronized (FinalizationQueriesConstants.class) {
			if(commonconstants==null){
				commonconstants = new FinalizationQueriesConstants();
			}
			
		}
		return commonconstants;
	}
	public static final String QUERIES_SELECT_FINALIZE_LIST = FoundationUtils.getProperty("queries.select.finalize.list");
    public static final String QUERIES_INSERT_CTDS_LEVEL_MSRVCP = FoundationUtils.getProperty("queries.insert.ctdslevelmsrvcp");
    public static final String QUERIES_INSERT_CTDS_LEVEL_MSRVCP_SEQUENCE_NAME = FoundationUtils.getProperty("queries.insert.ctdslevelmsrvcp.sequence.name");
}

    
    
