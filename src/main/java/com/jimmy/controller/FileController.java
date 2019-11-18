package com.jimmy.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	@RequestMapping("/uploadFile")
	public String uploadFile(@RequestParam("hmsfile")MultipartFile file,Model model) throws Exception {
		if(file.isEmpty())
			model.addAttribute("msg", "File uploaded failed!");
		else {
			String filename=file.getOriginalFilename();
			file.transferTo(new File("D:\\gitprojects\\jimmy-git-test\\"+filename));
			model.addAttribute("msg", "The file has been uploaded successfully!");
		}
		
		return "forward:docs";
	}
}
