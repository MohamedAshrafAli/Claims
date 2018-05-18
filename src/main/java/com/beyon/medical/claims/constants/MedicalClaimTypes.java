package com.beyon.medical.claims.constants;

public enum MedicalClaimTypes {
	REIMBURSEMENT("ReImbursement"),
	ECLAIMS("EClaims"),
	PROVIDERPAPER("ProviderPaper"),
	PBM("PBM"),
	PREAUTHORIZATION("PreAuthorization");
	
	private String claimType;
	
	private MedicalClaimTypes(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	
	

}
