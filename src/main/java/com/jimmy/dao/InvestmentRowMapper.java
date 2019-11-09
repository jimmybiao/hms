package com.jimmy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jimmy.pojo.Investment;

public class InvestmentRowMapper implements RowMapper<Investment>{

	public Investment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Investment investment=new Investment();
		investment.setId(rs.getInt("id"));
		investment.setInvestCategory(rs.getString("invest_category"));
		investment.setInvestSubCategory(rs.getString("invest_subcategory"));
		investment.setRemark(rs.getString("remark"));
		investment.setInvestDate(rs.getString("invest_date").split(" ")[0]);
		return investment;
	}

}
