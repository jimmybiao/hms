package com.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jimmy.dao.IncomeDao;
import com.jimmy.pojo.Income;

@Service
public class IncomeService {
	
	@Autowired
	private IncomeDao incomeDao;

	@Transactional(propagation=Propagation.REQUIRED)
	public void addIncome(Object[] objs) {

		incomeDao.addIncome(objs);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Income> queryIncome(String[] params) {
		return incomeDao.queryIncome(params);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<Income> delIncome(Integer[] idints, String cat, String dt) {
		incomeDao.delIncome(idints);
		return incomeDao.queryIncome(new String[]{dt,cat});
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Income queryIncomeById(Integer id) {
		return incomeDao.queryIncomeById(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateIncome(Object[] objs) {

		incomeDao.updateIncome(objs);
	}

}
