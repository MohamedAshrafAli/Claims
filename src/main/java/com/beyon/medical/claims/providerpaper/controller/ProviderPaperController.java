package com.beyon.medical.claims.providerpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyon.medical.claims.providerpaper.service.ProviderPaperClaimsService;

@RestController
@RequestMapping("/api/medical/claims/providerpaper")
@CrossOrigin(origins = "*")
public class ProviderPaperController{
	
	@Autowired
	private ProviderPaperClaimsService providerPaperClaimsService;
	
}