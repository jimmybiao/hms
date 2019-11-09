package com.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jimmy.dao.InvestmentDao;
import com.jimmy.pojo.Investment;

@Service
public class InvestmentService {

	@Autowired
	private InvestmentDao investmentDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addInvestment(Investment investment) {
		investmentDao.addInvestment(investment);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Investment> getInvestments(String qDate, String cat, String subcat) {
		return investmentDao.getInvestments(qDate,cat,subcat);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Investment getInvestment(Integer id) {
		
		return investmentDao.getInvestment(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateInvestment(Integer id, Investment investment) {

		investmentDao.updateInvestment(id,investment);
	}
}
