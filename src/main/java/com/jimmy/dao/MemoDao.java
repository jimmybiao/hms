package com.jimmy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jimmy.pojo.Memo;

@Repository
public class MemoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 public void addMemo(Memo memo) {
		String sqlAdd="insert into memo(title,amount,remark) values(?,?,?)";
		jdbcTemplate.update(sqlAdd, memo.getTitle(),memo.getAmount(),memo.getRemark());
//		System.out.println("memodao transaction...");
//		System.out.println(memo);
	}
	
	public void updateMemo(Memo memo) {
		
	}
	
	public List<Memo> getMemo(DateTime memoDate) throws Exception {
		DateTime dt=new DateTime(memoDate);
		//System.out.println("memoDao:"+dt.toString("yyyy-MM-dd"));
		String sqlGet="select title,amount,remark from memo where date_format(memo_date,'%Y-%m-%d')=?";
		List<Memo> memos=jdbcTemplate.query(sqlGet, new RowMapper<Memo>() {
			public Memo mapRow(ResultSet rs, int arg) throws SQLException{
				Memo memo=new Memo();
				memo.setTitle(rs.getString("title"));
				memo.setAmount(rs.getDouble("amount"));
				memo.setRemark(rs.getString("remark"));
				return memo;
			}
		}, dt.toString("yyyy-MM-dd"));
		return memos;
	}
	
	public void deleteMemo(Integer id) {
		
	}
}
