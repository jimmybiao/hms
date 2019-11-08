package com.jimmy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		investment.setInvestDate(DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(investDt));
		investment.setRemark(remark);

		investmentService.addInvestment(investment);
		
		Gson json=new Gson();

		return json.toJson(investment);
	}

	@RequestMapping("/investment")
	public String showInvestment() {
		return "investment";
	}
}
