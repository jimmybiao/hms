package com.jimmy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jimmy.pojo.Income;

public class IncomeRowMapper implements RowMapper<Income>{

	public Income mapRow(ResultSet rs, int rowNum) throws SQLException {
		Income income=new Income();
		income.setId(rs.getInt("id"));
		income.setCategory(rs.getString("income_category"));
		income.setAmount(rs.getDouble("amount"));
		income.setRemark("remark");
		income.setCreatedTime(rs.getString("created_date"));
		
		return income;
	}

}
