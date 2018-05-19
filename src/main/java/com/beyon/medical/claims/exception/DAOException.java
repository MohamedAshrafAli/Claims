package com.beyon.medical.claims.exception;

public class DAOException extends Exception {

    private static final long serialVersionUID = -4137478577479081073L;

    private final String errorCode;
    private final String errorMessage;

    public DAOException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

   
    public String getErrorMessage() {
        return errorMessage;
    }

 
}
