package com.jimmy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jimmy.pojo.Investment;

@Repository
public class InvestmentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addInvestment(Investment investment) {
		String sqlAdd="insert into investment(invest_category,invest_subcategory,amount,remark,invest_date) values(?,?,?,?,?)";
		jdbcTemplate.update(sqlAdd, investment.getInvestCategory(),investment.getInvestSubCategory(),
				investment.getAmount(),investment.getRemark(),investment.getInvestDate().toString("yyyy-MM-dd"));
	}
}
