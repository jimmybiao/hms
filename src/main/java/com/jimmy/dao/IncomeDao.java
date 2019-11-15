package com.jimmy.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jimmy.pojo.Income;

@Repository
public class IncomeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addIncome(Object[] objs) {

		String sqlAdd="insert into income(income_category,amount,remark,created_date,updated_date) values(?,?,?,?,now())";
		jdbcTemplate.update(sqlAdd, objs);
	}

	public List<Income> queryIncome(String[] params) {
		String sqlGet="select id,income_category,amount,remark,created_date from income where 1=1 ";
		List<String> pList=new ArrayList<String>();
		if(params[0]!="") {
			sqlGet+=" and date_format(created_date,'%Y-%m-%d')=? ";
			pList.add(params[0]);
		}
		if(params[1]!="") {
			sqlGet+=" and income_category=? ";
			pList.add(params[1]);
		}
		
		if(pList.size()>0) {
			String p[]=new String[pList.size()];
			for(int i=0;i<pList.size();i++)
				p[i]=pList.get(i);
			return jdbcTemplate.query(sqlGet, p, new IncomeRowMapper());
		}
		else
			return jdbcTemplate.query(sqlGet, new IncomeRowMapper());
	}

	public void delIncome(final Integer[] idints) {

		String sqlDel="delete from income where id=?";
		jdbcTemplate.batchUpdate(sqlDel, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, idints[i]);
			}
			
			public int getBatchSize() {
				return idints.length;
			}
		});
	}

	public Income queryIncomeById(Integer id) {
		String sqlGet="select id,income_category,amount,remark,created_date from income where id=?";
		Income income=jdbcTemplate.queryForObject(sqlGet, new IncomeRowMapper(),id);
		return income;
	}

	public void updateIncome(Object[] objs) {

		String sqlUpdate="update income set income_category=?,amount=?,remark=?,updated_date=now() where id=?";
		jdbcTemplate.update(sqlUpdate, objs);
	}

}
