package com.jimmy.controller;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jimmy.pojo.Memo;
import com.jimmy.service.MemoService;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService memoService;

	@RequestMapping("/memo")
	public String addMemo(Memo memo) {
		memoService.addMemo(memo);
		return "success";
	}
	
	@RequestMapping("/ajaxQueryMemos")
	@ResponseBody
	public String getMemos(@RequestParam("q")String q) throws Exception {
		//System.out.println("memocontroller:"+q);
		Gson json=new Gson();
		//System.out.println(json.toJson(memoService.getMemos(DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(q))));
		DateTime dt=DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(q);
		List<Memo> memos=memoService.getMemos(dt);
		return json.toJson(memos);
	}
}
