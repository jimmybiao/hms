package com.jimmy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jimmy.pojo.Memo;

public class MemoRowMapper implements RowMapper<Memo>{

	public Memo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Memo memo=new Memo();
		memo.setId(rs.getInt("id"));
		memo.setTitle(rs.getString("title"));
		memo.setAmount(rs.getDouble("amount"));
		memo.setRemark(rs.getString("remark"));
		return memo;
	}

}
