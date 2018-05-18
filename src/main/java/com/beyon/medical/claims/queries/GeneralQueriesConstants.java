package com.beyon.medical.claims.queries;

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
	public ResourceBundle claimsResourceBundle = ResourceBundle.getBundle("com.beyon.medical.claims.properties.queries.GeneralQueries");


}