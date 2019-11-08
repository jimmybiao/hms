package com.jimmy.service;

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
}
