package com.beyon.medical.claims.reimbursement.dao;

import static com.beyon.medical.claims.queries.constants.ReimbursementQueriesConstants.REIMBURSEMENT_QUERIES_CTDS_DETAILS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.beyon.framework.dao.DAOFactory;
import com.beyon.medical.claims.exception.DAOException;
import com.beyon.medical.claims.general.dao.BaseClaimsDAOImpl;
import com.beyon.medical.claims.reimbursement.dto.ReimbursementRegistrationDTO;
import com.beyon.medical.claims.utils.DateUtil;

@Repository("reimbursementClaimsDAOImpl")
@Scope(value="prototype")
public class ReimbursementClaimsDAOImpl extends BaseClaimsDAOImpl {

	private final String CLASS_NAME = ReimbursementClaimsDAOImpl.class.getCanonicalName();

	public List<?> getReimbursementRegistrationDetails(String id,String compId) {
		JdbcTemplate template = DAOFactory.getJdbcTemplate("gm");
		return template.query(REIMBURSEMENT_QUERIES_CTDS_DETAILS, new Object[] {id, compId }, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet row, int count) throws SQLException {
				return null;
			}

		});
	}

	public List<ReimbursementRegistrationDTO> getRegistrationListViewData(String query ,Map<String,Object> inputMap) throws DAOException {
		NamedParameterJdbcTemplate  namedParameterJdbcTemplate = DAOFactory.getNamedTemplate("gm");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValues(inputMap);
		return namedParameterJdbcTemplate.query(query, parameters, new RowMapper<ReimbursementRegistrationDTO>() {
			@Override
			public ReimbursementRegistrationDTO mapRow(ResultSet row, int count) throws SQLException {
				ReimbursementRegistrationDTO registrationDTO = new ReimbursementRegistrationDTO();
				registrationDTO.setDocumentLink(null);
				registrationDTO.setEmail1(row.getString("EmailId1"));
				registrationDTO.setEmail2(row.getString("EmailId2"));
				registrationDTO.setEmiratesId(row.getString("EmiratesId"));
				registrationDTO.setEncType(row.getString("EncounterType"));
				registrationDTO.setIbanNum(null);
				registrationDTO.setMemberName(row.getString("MemberName"));
				registrationDTO.setMemberNumber(row.getString("MemberNumber"));
				registrationDTO.setMobileNum1(row.getString("MobileNumber1"));
				registrationDTO.setMobileNum2(row.getString("MobileNumber2"));
				registrationDTO.setPaymentWay(row.getString("PaymentRefNo"));
				registrationDTO.setPolicyNumber(row.getString("Policynumber"));
				registrationDTO.setPrevRequest(row.getString("PrevReqNo"));
				registrationDTO.setReqReceivedDate(DateUtil.convertSQlDateToLocalDate(row.getDate("RequestReceivedDate")));
				registrationDTO.setReqType(row.getString("RequestType"));
				registrationDTO.setRequestAmt(row.getBigDecimal("RequestedAmt"));
				registrationDTO.setServiceFmDate(DateUtil.convertSQlDateToLocalDate(row.getDate("ServiceFromDate")));
				registrationDTO.setSource(null);
				registrationDTO.setVoucherNumber(row.getString("VoucherNumber"));
				registrationDTO.setMemberCardNumber(row.getString("CardNumber"));
				registrationDTO.setId(row.getString("Id"));
				registrationDTO.setClaimRefNo(row.getString("ClaimRefNo"));
				return registrationDTO;
			}
		});
	}


}
