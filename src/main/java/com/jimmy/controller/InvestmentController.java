package com.jimmy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jimmy.pojo.Investment;
import com.jimmy.service.InvestmentService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
public class InvestmentController {

	@Autowired
	private InvestmentService investmentService;

	@RequestMapping("/addNewInvestment")
	@ResponseBody
	public String addNewInvestment(HttpServletRequest request) {
		String category = request.getParameter("c");
		String subcategory = request.getParameter("sc");
		Double amount =Double.parseDouble( request.getParameter("am"));
		String investDt = request.getParameter("indt");
		String remark = request.getParameter("re");
		
		Object[] params= {category,subcategory,amount,investDt,remark,investDt};

		investmentService.addInvestment(params);
		
		Gson json=new Gson();

		return json.toJson("ok");
	}

//	@RequestMapping("/investment")
//	public String showInvestment() {
//		return "investment";
//	}
	
	@RequestMapping("/ajaxQueryInvestments")
	@ResponseBody
	public String getAllInvestments(@RequestParam("dt") String qDate,@RequestParam("cat") String cat,@RequestParam("subcat") String subcat) {
		List<Investment> investList=new ArrayList<Investment>();
		Gson json=new Gson();
		
		investList=investmentService.getInvestments(qDate,cat,subcat);
		
		return json.toJson(investList);
	}
	
	@RequestMapping("/ajaxDelInvestment")
	@ResponseBody
	public String delInvestment(HttpServletRequest request) {
		List<Integer> idints=new ArrayList<Integer>();
		String[] ids=request.getParameterValues("d");
		if(ids.length>0) {			
			for(String id:ids)
				idints.add(Integer.parseInt(id));
		}
		String dt=request.getParameter("dt");
		String cat=request.getParameter("cat");
		String subcat=request.getParameter("subcat");
		
		Gson json=new Gson();
		List<Investment> investments=investmentService.delInvestments(idints,dt,cat,subcat);
		return json.toJson(investments);
	}
	
	@RequestMapping("/investmentEdit/{id}")
	public ModelAndView editInvestment(@PathVariable("id") Integer id) {
		ModelAndView mv=new ModelAndView("investmentEdit");
		Investment investment=investmentService.getInvestment(id);
		mv.addObject("id", investment.getId());
		mv.addObject("sub", investment.getCategory());
		mv.addObject("subcat", investment.getSubcategory());
		mv.addObject("amt", investment.getAmount());
		mv.addObject("remark", investment.getRemark());
		return mv;
	}
	
	@RequestMapping("/investmentUpdate/{id}")
	@ResponseBody
	public String updateInvestment(@PathVariable("id") Integer id,HttpServletRequest request) {
		Investment investment=new Investment();
		String cat=request.getParameter("cat");
		String subcat=request.getParameter("subcat");
		Double amt=Double.parseDouble(request.getParameter("amt"));
		String remark=request.getParameter("re");
		investment.setCategory(cat);
		investment.setSubcategory(subcat);
		investment.setAmount(amt);
		investment.setRemark(remark);
		investmentService.updateInvestment(id,investment);
		Gson json=new Gson();
		return json.toJson(investment);
	}
}
