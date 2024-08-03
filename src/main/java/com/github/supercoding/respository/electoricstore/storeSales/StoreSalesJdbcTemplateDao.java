package com.github.supercoding.respository.electoricstore.storeSales;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StoreSalesJdbcTemplateDao implements StoreSalesRepository{

	private final JdbcTemplate jdbcTemplate;

	public StoreSalesJdbcTemplateDao(@Qualifier("jdbcTemplate1") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


//	static RowMapper<StoreSalesEntity> storeSalesRowMapper = (((rs, rowNum) ->
//			new StoreSalesEntity(
//					rs.getInt("id"),
//					rs.getNString("store_name"),
//					rs.getInt("amount")
//			)));
static RowMapper<StoreSalesEntity> storeSalesRowMapper = (((rs, rowNum) ->
		new StoreSalesEntity.StoreSalesEntityBuilder()
				.id(rs.getInt("id"))
				.storeName(rs.getNString("store_name"))
				.amount(rs.getInt("amount"))
				.build()));


	@Override
	public StoreSalesEntity findStoreSalesById(Integer storeId) {
		return jdbcTemplate.queryForObject(" SELECT * from store_sales WHERE id = ? ", storeSalesRowMapper, storeId);
	}

	@Override
	public void updateSalesAmount(Integer storeId, Integer amount) {
		jdbcTemplate.update("UPDATE store_sales " +
				" SET amount = ? " +
				" WHERE id = ? ", amount, storeId);
	}
}
