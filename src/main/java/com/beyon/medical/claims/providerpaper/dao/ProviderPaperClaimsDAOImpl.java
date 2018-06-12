package com.beyon.medical.claims.providerpaper.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.beyon.medical.claims.general.dao.BaseClaimsDAOImpl;

@Repository("providerPaperClaimsDAOImpl")
@Scope(value="prototype")
public class ProviderPaperClaimsDAOImpl extends BaseClaimsDAOImpl {

	private final String CLASS_NAME = ProviderPaperClaimsDAOImpl.class.getCanonicalName();
	
}
