package com.beyon.medical.claims.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

	public static void main(String[] args) {

	}

	public static LocalDate convertSQlDateToLocalDate(java.sql.Date inputDate) {
		LocalDate localDate = null;
		if (inputDate != null) {
			Date date = new Date(inputDate.getTime());
			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		return localDate;
	}

	public static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	
	public static LocalDate convertSQLTimeStampToLocalDate(java.sql.Timestamp timeStamp){
		LocalDate localDate = null;
		if (timeStamp != null) {
			localDate = timeStamp.toLocalDateTime().toLocalDate();;
		}
		return localDate;
	}

}
