package com.jimmy.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jimmy.pojo.Expense;

@Repository
public class ExpenseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addExpense(Object[] objs) {
		String sqlAdd="insert into expense(expense_category,expense_subcategory,amount,remark,created_date,updated_date) values(?,?,?,?,?,now())";
		jdbcTemplate.update(sqlAdd, objs);
	}

	public List<Expense> getExpense(String[] objs) {
		String sqlGet="select id,expense_category,expense_subcategory,amount,remark,created_date from expense where 1=1 ";
		List<String> lst=new ArrayList<String>();
		
		if(objs[0]!="") {
			sqlGet+=" and created_date=? ";
			lst.add(objs[0]);
		}
			
		if(objs[1]!="") {
			sqlGet+=" and expense_category=? ";
			lst.add(objs[1]);
		}
			
		if(objs[2]!="") {
			sqlGet+=" and expense_subcategory=? ";
			lst.add(objs[0]);
		}
		
		
		if(lst.size()>0) {
			String[] params=new String[lst.size()];
			for(int i=0;i<lst.size();i++) {
				params[i]=lst.get(i);
			}
			return jdbcTemplate.query(sqlGet, params, new ExpenseRowMapper());
		}
		
		return jdbcTemplate.query(sqlGet, new ExpenseRowMapper());
	}

	public Expense getExpenseById(Integer id) {
		String sqlGet="select id,expense_category,expense_subcategory,amount,remark,created_date from expense where id=?";
		return jdbcTemplate.queryForObject(sqlGet, new ExpenseRowMapper(), id);
	}

	public void updateExpense(Object[] objs) {

		String sqlUpdate="udpate expense set expense_category=?,expense_subcategory=?,amount=?,remark=?,updated_date=now() where id=?";
		jdbcTemplate.update(sqlUpdate, objs);
	}

	public void delExpense(final List<Integer> idLst) {

		String sqlDel="delete from expense where id=?";
		jdbcTemplate.batchUpdate(sqlDel, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, idLst.get(i));
			}
			
			public int getBatchSize() {
				return idLst.size();
			}
		});
	}

}
