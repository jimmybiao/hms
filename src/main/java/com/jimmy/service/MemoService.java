package com.jimmy.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jimmy.dao.MemoDao;
import com.jimmy.pojo.Memo;

@Service
public class MemoService {

	@Autowired
	private MemoDao memoDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addMemo(Memo memo) {
		memoDao.addMemo(memo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Memo> getMemos(DateTime memoDate) throws Exception{
		
		return memoDao.getMemo(memoDate);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Memo> getAllMemos() throws Exception{
		return memoDao.getAllMemos();
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Memo getMemo(Integer id) {
		Memo memo=memoDao.getMemo(id);
		return memo;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateMemo(Integer id, Memo memo) {
		memoDao.updateMemo(id,memo);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteMemo(List<Integer> ids) {
		memoDao.deleteMemo(ids);
	}
}
