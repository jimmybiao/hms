package com.jimmy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jimmy.pojo.Expense;

public class ExpenseRowMapper implements RowMapper<Expense>{

	public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
		Expense expense=new Expense();
		expense.setId(rs.getInt("id"));
		expense.setCategory(rs.getString("expense_category"));
		expense.setSubcategory(rs.getString("expense_subcategory"));
		expense.setAmount(rs.getDouble("amount"));
		expense.setRemark(rs.getString("remark"));
		expense.setCreatedTime(rs.getString("created_date").split(" ")[0]);
		
		return expense;
	}

}
