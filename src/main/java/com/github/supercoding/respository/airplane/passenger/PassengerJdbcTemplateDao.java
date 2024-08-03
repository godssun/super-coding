//package com.github.supercoding.respository.airplane.passenger;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public class PassengerJdbcTemplateDao implements PassengerRepository {
//
//	private JdbcTemplate template;
//
//	public PassengerJdbcTemplateDao(@Qualifier("jdbcTemplate2") JdbcTemplate template) {
//		this.template = template;
//	}
//
//	static RowMapper<PassengerEntity> passengerRowMapper = (((rs, rowNum) ->
//			new PassengerEntity.PassengerEntityBuilder()
//					.passengerId(rs.getInt("passenger_id"))
//					.userId(rs.getInt("user_id"))
//					.passportNum(rs.getNString("passport_num"))
//					.build()));
//	@Override
//	public Optional<PassengerEntity> findPassengerByUserId(Integer userId) {
//		try {
//			return Optional.ofNullable(template.queryForObject("SELECT * FROM passenger WHERE user_id = ?", passengerRowMapper, userId));
//		} catch (Exception e) {
//			return Optional.empty();
//		}
//	}
//}