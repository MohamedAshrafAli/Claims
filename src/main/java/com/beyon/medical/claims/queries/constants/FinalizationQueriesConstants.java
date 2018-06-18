package com.beyon.medical.claims.queries.constants;

import java.io.Serializable;

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
}