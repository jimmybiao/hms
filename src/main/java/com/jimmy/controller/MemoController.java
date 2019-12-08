package com.jimmy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.jimmy.pojo.Memo;
import com.jimmy.service.MemoService;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService memoService;

	@RequestMapping("/addNewMemo")
	@ResponseBody
	public String addMemo(HttpServletRequest request) {
		String title=request.getParameter("t");
		Double amt=Double.parseDouble(request.getParameter("am"));
		String remark=request.getParameter("re");
		String dt=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		Object[] objs= {title,amt,remark,dt};
		
		memoService.addMemo(objs);
		
		Gson json=new Gson();
		
		return json.toJson("ok");
	}
	
	@RequestMapping("/ajaxQueryMemos")
	@ResponseBody
	public String getMemos(@RequestParam("q")String q) throws Exception {
		List<Memo> memos=new ArrayList<Memo>();
		Gson json=new Gson();
		if(q==null || q.equals("")) {
			memos=memoService.getAllMemos();
		}
		else {
			
			DateTime dt=DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(q);
			memos=memoService.getMemos(dt);
		}
		
		return json.toJson(memos);
	}
	
	@RequestMapping("/memoEdit/{id}")
	public ModelAndView editMemo(@PathVariable("id") Integer id) {
		ModelAndView mv=new ModelAndView("memoEdit");
		//System.out.println("id:"+id);
		Memo memo=memoService.getMemo(id);
		mv.addObject("title", memo.getTitle());
		mv.addObject("amount", memo.getAmount());
		mv.addObject("remark", memo.getRemark());
		
		return mv;
	}
	
	@RequestMapping("/memoUpdate/{id}")
	@ResponseBody
	public String updateMemo(@PathVariable("id") Integer id, HttpServletRequest request) {
		String title=request.getParameter("t");
		Double amt=Double.parseDouble(request.getParameter("amt"));
		String remark=request.getParameter("re");
		
		Object[] objs= {title,amt,remark,id};
		memoService.updateMemo(objs);
		
		Gson json=new Gson();
		return json.toJson("ok");
	}
	
	@RequestMapping("/ajaxDelMemo")
	@ResponseBody
	public String delMemo(@RequestParam("d") String delIds, @RequestParam("q") String qDate) throws Exception {
		String[] ids=delIds.replace("\"", "").replace("[", "").replace("]", "").split(",");
		List<Integer> idList=new ArrayList<Integer>();
		for(int i=0;i<ids.length;i++) {
			idList.add(Integer.parseInt(ids[i]));
		}
		memoService.deleteMemo(idList);
		List<Memo> memos=new ArrayList<Memo>();
		Gson json=new Gson();
		if(qDate==null || qDate.equals("")) {
			memos=memoService.getAllMemos();
		}
		else {
			
			DateTime dt=DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(qDate);
			memos=memoService.getMemos(dt);
		}
		
		return json.toJson(memos);
	}

}
