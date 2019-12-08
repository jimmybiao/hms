package com.jimmy.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jimmy.pojo.Memo;

@Repository
public class MemoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	 public void addMemo(Object[] objs) {
		String sqlAdd="insert into memo(title,amount,remark,memo_date) values(?,?,?,?)";
		jdbcTemplate.update(sqlAdd, objs);
	}
	
	public void updateMemo(Object[] objs) {
		String sqlUpdate="update memo set title=?,amount=?,remark=?,memo_date=now() where id=?";
		jdbcTemplate.update(sqlUpdate, objs);
	}
	
	public Memo getMemo(Integer id) {
		String sqlGetSingleMemo="select id,title,amount,remark from memo where id=?";
		List<Memo> memos=jdbcTemplate.query(sqlGetSingleMemo, new MemoRowMapper(),id);
		if (memos.size()>0) {
			return memos.get(0);
		}
		return null;
	}
	
	public List<Memo> getMemo(DateTime memoDate) throws Exception {
		DateTime dt=new DateTime(memoDate);
		//System.out.println("memoDao:"+dt.toString("yyyy-MM-dd"));
		String sqlGet="select id,title,amount,remark from memo where date_format(memo_date,'%Y-%m-%d')=?";
		List<Memo> memos=jdbcTemplate.query(sqlGet, new MemoRowMapper(), dt.toString("yyyy-MM-dd"));
		return memos;
	}
	
	public List<Memo> getAllMemos() throws Exception {
		String sqlGet="select id,title,amount,remark from memo";
		List<Memo> memos=jdbcTemplate.query(sqlGet, new MemoRowMapper());
		return memos;
	}
	
	public void deleteMemo(final List<Integer> ids) {
		String sqlDel="delete from memo where id=?";
		jdbcTemplate.batchUpdate(sqlDel,new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, ids.get(i));
			}
			
			public int getBatchSize() {
				return ids.size();
			}
		});
	}
}
