package com.jimmy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.jimmy.pojo.Expense;
import com.jimmy.service.ExpenseService;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping("/addNewExpense")
	@ResponseBody
	public String addExpense(HttpServletRequest request) {
		String category=request.getParameter("c");
		String subcategory=request.getParameter("sc");
		Double amt=Double.parseDouble(request.getParameter("am")); 
		String createdDate=request.getParameter("cd");
		String remark=request.getParameter("re");
		
		Object[] objs= {category,subcategory,amt,remark,createdDate};
		expenseService.addExpense(objs);
		
		Gson json=new Gson();
		
		return json.toJson(objs);
	}
	
	@RequestMapping("/ajaxQueryExpense")
	@ResponseBody
	public String queryExpense(@RequestParam("dt") String qDate,@RequestParam("cat") String cat,@RequestParam("subcat") String subcat) {
			List<Expense> expenseList=new ArrayList<Expense>();
			Gson json=new Gson();
			
			String[] objs= {qDate,cat,subcat};
			expenseList=expenseService.getExpense(objs);
			
			return json.toJson(expenseList);
	}
	
	@RequestMapping("/ajaxDelExpense")
	@ResponseBody
	public String delExpense(HttpServletRequest request) {

		String[] ids=request.getParameterValues("d");
		List<Integer> idLst=new ArrayList<Integer>();
		for(int i=0;i<ids.length;i++)
			idLst.add(Integer.parseInt(ids[i]));
		String dt=request.getParameter("dt");
		String cat=request.getParameter("cat");
		String subcat=request.getParameter("subcat");
		
		List<Expense> expenses=expenseService.delExpense(idLst,dt,cat,subcat);
		Gson json=new Gson();
		
		return json.toJson(expenses);
	}
	
	@RequestMapping("/expenseEdit/{id}")
	public ModelAndView editExpense(@PathVariable("id") Integer id) {
		ModelAndView mv=new ModelAndView("expenseEdit");
		Expense expense=expenseService.getExpenseById(id);
		mv.addObject("id", expense.getId());
		mv.addObject("cat", expense.getCategory());
		mv.addObject("subcat", expense.getSubcategory());
		mv.addObject("amt", expense.getAmount());
		mv.addObject("dt", expense.getCreatedTime());
		mv.addObject("re", expense.getRemark());
		
		return mv;
	}
	
	@RequestMapping("/expenseUpdate")
	@ResponseBody
	public String updateExpense(HttpServletRequest request, @PathVariable("id") Integer id) {
		String category=request.getParameter("cat");
		String subcategory=request.getParameter("subcat");
		Double amt=Double.parseDouble(request.getParameter("amt")); 
		String remark=request.getParameter("re");
		
		Object[] objs= {category,subcategory,amt,remark,id};
		expenseService.updateExpense(objs);
		
		Gson json=new Gson();
		
		return json.toJson(objs);
	}
	
}
