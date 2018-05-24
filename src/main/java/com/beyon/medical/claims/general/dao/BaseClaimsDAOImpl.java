package com.beyon.medical.claims.general.dao;

import static com.beyon.framework.util.AppLogger.DEBUG;
import static com.beyon.framework.util.AppLogger.writeLog;

import org.springframework.jdbc.core.JdbcTemplate;

import com.beyon.framework.dao.DAOFactory;

public class BaseClaimsDAOImpl {

	private final String CLASS_NAME = BaseClaimsDAOImpl.class.getCanonicalName();

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
