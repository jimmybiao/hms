package com.jimmy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jimmy.pojo.Income;
import com.jimmy.service.IncomeService;

@Controller
public class IncomeController {
	
	@Autowired
	private IncomeService incomeService;

	@RequestMapping("/addNewIncome")
	@ResponseBody
	public String addIncome(HttpServletRequest request) {
		String cat=request.getParameter("c");
		Double amt=Double.parseDouble(request.getParameter("am"));
		String dt=request.getParameter("cd");
		String re=request.getParameter("re");
		
		Object[] objs= {cat,amt,re,dt};
		incomeService.addIncome(objs);
		
		Gson json=new Gson();
		return json.toJson("ok");
	}
	
	@RequestMapping("/ajaxQueryIncome")
	@ResponseBody
	public String queryIncome(HttpServletRequest request) {
		String dt=request.getParameter("dt");
		String cat=request.getParameter("cat");
		String[] params= {dt,cat};
		List<Income> incomes=incomeService.queryIncome(params);
		
		Gson json=new Gson();
		return json.toJson(incomes);
	}
	
	@RequestMapping("/ajaxDelIncome")
	@ResponseBody
	public String delIncome(HttpServletRequest request) {
		String[] ids=request.getParameterValues("d");
		String cat=request.getParameter("cat");
		String dt=request.getParameter("dt");
		
		Integer[] idints=new Integer[ids.length];
		for(int i=0;i<ids.length;i++)
			idints[i]=Integer.parseInt(ids[i]);
		
		List<Income> incomes=incomeService.delIncome(idints,cat,dt);
		Gson json=new Gson();
		return json.toJson(incomes);
	}
	
	@RequestMapping("/incomeEdit/{id}")
	public ModelAndView editIncome(@PathVariable("id") Integer id) {
		ModelAndView mv=new ModelAndView("incomeEdit");
		//System.out.println("incomeedit-id: "+id);
		Income income=incomeService.queryIncomeById(id);
		mv.addObject("id", id);
		mv.addObject("cat",income.getCategory());
		mv.addObject("amt", income.getAmount());
		mv.addObject("dt", income.getCreatedTime());
		mv.addObject("remark", income.getRemark());
		
		return mv;
	}
	
	@RequestMapping("/incomeUpdate/{id}")
	@ResponseBody
	public String updateIncome(HttpServletRequest request,@PathVariable("id") Integer id) {
		String cat=request.getParameter("cat");
		Double amt=Double.parseDouble(request.getParameter("amt"));
		String remark=request.getParameter("re");
		
		Object[] objs= {cat,amt,remark,id};
		incomeService.updateIncome(objs);
		
		Gson json=new Gson();
		return json.toJson("ok");
	}
}
