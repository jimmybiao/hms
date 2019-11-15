package com.jimmy.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jimmy.pojo.Investment;

@Repository
public class InvestmentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void addInvestment(Object[] params) {
		String sqlAdd="insert into investment(invest_category,invest_subcategory,amount,invest_date,remark) values(?,?,?,?,?)";
		jdbcTemplate.update(sqlAdd, params);
	}

	public List<Investment> getInvestments(String qDate, String cat, String subcat) {
		List<Investment> investList=new ArrayList<Investment>();
		
		List<Object> lst=new ArrayList<Object>(); 
		
		String sqlGet="select id,invest_category,invest_subcategory,amount,invest_date,remark from investment where 1=1 ";
		if(!"".equals(qDate)) {
			sqlGet+=" and date_format(invest_date,'%Y-%m-%d')=?";
			lst.add(qDate);
		}
			
		if(!"".equals(cat)) {
			sqlGet+=" and invest_category=?";
			lst.add(cat);
		}
			
		if(!"".equals(subcat)) {
			sqlGet+=" and invest_subcategory=?";
			lst.add(subcat);
		}
		
		if(lst.size()>0) {
			Object[] objs=new Object[lst.size()];
			for(int i=0;i<lst.size();i++)
				objs[i]=lst.get(i);
			investList=jdbcTemplate.query(sqlGet, objs, new InvestmentRowMapper());
		}
		else
			investList=jdbcTemplate.query(sqlGet, new InvestmentRowMapper());
		
		return investList;
	}

	public Investment getInvestment(Integer id) {
		String sqlGet="select id,invest_category,invest_subcategory,amount,remark,invest_date from investment where id=?";
		List<Investment> lst=jdbcTemplate.query(sqlGet, new InvestmentRowMapper(),id);
		if(lst.size()>0)
			return lst.get(0);
		return null;
	}

	public void updateInvestment(Integer id, Investment investment) {
		String sqlUpdate="update investment set invest_category=?,invest_subcategory=?,amount=?,remark=?,updated_date=now() where id=?";
		jdbcTemplate.update(sqlUpdate, investment.getCategory(),investment.getSubcategory(),investment.getAmount(),investment.getRemark(),id);
	}

	public void delInvestments(final List<Integer> ids) {

		String sqlDel="delete from investment where id=?";
		jdbcTemplate.batchUpdate(sqlDel, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, ids.get(i));
			}
			
			public int getBatchSize() {
				return ids.size();
			}
		});
	}
}
