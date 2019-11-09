package com.jimmy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jimmy.pojo.Investment;
import com.jimmy.service.InvestmentService;

@Controller
public class InvestmentController {

	@Autowired
	private InvestmentService investmentService;

	@RequestMapping("/addNewInvestment")
	@ResponseBody
	public String addNewInvestment(HttpServletRequest request) {
		String category = request.getParameter("c");
		String subcategory = request.getParameter("sc");
		String amount = request.getParameter("am");
		String investDt = request.getParameter("indt");
		String remark = request.getParameter("re");

		Investment investment = new Investment();
		investment.setInvestCategory(category);
		investment.setInvestSubCategory(subcategory);
		investment.setAmount(Double.parseDouble(amount));
		investment.setInvestDate(investDt);
		investment.setRemark(remark);

		investmentService.addInvestment(investment);
		
		Gson json=new Gson();

		return json.toJson(investment);
	}

	@RequestMapping("/investment")
	public String showInvestment() {
		return "investment";
	}
	
	@RequestMapping("/ajaxQueryInvestments")
	@ResponseBody
	public String getAllInvestments(@RequestParam("dt") String qDate,@RequestParam("cat") String cat,@RequestParam("subcat") String subcat) {
		List<Investment> investList=new ArrayList<Investment>();
		Gson json=new Gson();
		
		investList=investmentService.getInvestments(qDate,cat,subcat);
		
		for(int i=0;i<investList.size();i++)
			System.out.println(investList.get(i));
		
		return json.toJson(investList);
	}
	
	@RequestMapping("/investmentDel")
	public String delInvestment() {
		
		return null;
	}
	
	@RequestMapping("/investmentEdit")
	public String editInvestment() {
		return null;
	}
}
