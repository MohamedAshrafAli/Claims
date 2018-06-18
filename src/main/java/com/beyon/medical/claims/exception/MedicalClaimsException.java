package com.beyon.medical.claims.exception;

public class MedicalClaimsException extends  Exception
{
	private static final long serialVersionUID = 1L;
	
	private String classname; 					// the name of the class
	private String method; 						// the name of the method
	private String key; 						// a Custom Error Message Key
	private String separator = "\n"; 			// line separator
	
	public MedicalClaimsException(){
		super();
	}
	
	public MedicalClaimsException(String string)
	{
		super(string);	
	}

	public MedicalClaimsException(String string, Exception e){
		super(string,e);
	}
	
	public MedicalClaimsException(Exception e){
		super(e);
	}
	
	public MedicalClaimsException(String classname, String method, String key, Exception e) {
		super(e);
		this.classname = classname;
		this.method    = method;
		this.key = key;
	}
	
}
