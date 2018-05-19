package com.beyon.medical.claims.general.dao;

import static com.beyon.framework.util.AppLogger.DEBUG;
import static com.beyon.framework.util.AppLogger.ERROR;
import static com.beyon.framework.util.AppLogger.writeLog;
import static com.beyon.framework.util.Constants.INTERNAL_ERROR_OCCURED;
import static com.beyon.medical.claims.queries.GeneralQueriesConstants.*;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.medical.claims.exception.DAOException;

@Repository("generalClaimsDAOImpl")
@Scope(value="prototype")
public class GeneralClaimsDAOImpl {

    private final String CLASS_NAME = GeneralClaimsDAOImpl.class.getCanonicalName();
    
	public List<String> getMedicalCardNumbers(String compId,String cardNumber) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_CARD_NUMBERS, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	public List<String> getMemberNumberList(String compId,String memberNumber) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_MEMBER_NUMBERS, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMemberNumberList", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	public List<String> getPolicyNumbersList(String compId,String policyNumber) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_POLICY_NUMBERS, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	
	public List<String> getEncounterTypes(String compId) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_ENCOUNTER_TYPES, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	public List<String> getRequestTypes(String compId) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_REQUEST_TYPES, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMemberNumberList", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	public List<String> getReportByTypes(String compId) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_REPORT_BY_TYPES, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	public List<String> getPaymentTypes(String compId) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_PAYMENT_TYPES, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	public List<String> getDocumentTypes(String compId) throws DAOException {
		List<String> lstBrIds = null;
		try {
			JdbcTemplate jdbcTemplate = DAOFactory.getJdbcTemplate("gm");
			lstBrIds = jdbcTemplate.queryForList(GENERAL_QUERIES_GET_DOCUMENT_TYPES, new Object[] { compId }, String.class);
		} catch (Exception e) {
			writeLog(CLASS_NAME, "Exception occured while executing getMedicalCardNumbers", ERROR, e);
			throw new DAOException(INTERNAL_ERROR_OCCURED[0], INTERNAL_ERROR_OCCURED[1]);
		}
		return lstBrIds;
	}
	
	private Long getSequenceNo(String sequenceName) {
		writeLog(CLASS_NAME, "Entered method getSequenceNo --> sequenceName:" + sequenceName, DEBUG);
		if (sequenceName == null) {
			throw new IllegalArgumentException("The passed sequence name was null", new Throwable());
		}

		JdbcTemplate template = DAOFactory.getJdbcTemplate("gm");
		String strSeqNoQry = "SELECT " + sequenceName + ".nextval FROM DUAL";
		Long seqNo = template.queryForObject(strSeqNoQry, Long.class);
		writeLog(CLASS_NAME, "Exit method getSequenceNo  --> sequenceName:" + sequenceName + " and seqNo:" + seqNo,
				DEBUG);
		return seqNo;
	}
 
}
