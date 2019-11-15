package com.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jimmy.dao.ExpenseDao;
import com.jimmy.pojo.Expense;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseDao expenseDao;

	@Transactional(propagation=Propagation.REQUIRED)
	public void addExpense(Object[] objs) {

		expenseDao.addExpense(objs);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Expense> getExpense(String[] objs) {

		return expenseDao.getExpense(objs);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Expense getExpenseById(Integer id) {
		return expenseDao.getExpenseById(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateExpense(Object[] objs) {

		expenseDao.updateExpense(objs);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<Expense> delExpense(List<Integer> idLst, String dt, String cat, String subcat) {
		expenseDao.delExpense(idLst);
		List<Expense> expenses=expenseDao.getExpense(new String[] {dt,cat,subcat});
		return expenses;
	}

}
