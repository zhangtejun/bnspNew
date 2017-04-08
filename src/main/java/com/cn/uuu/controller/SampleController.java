package com.cn.uuu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class SampleController {

	 @RequestMapping("/jsp/index")
	 public String getSampleJsp() {
		 return "index";
	 }
	 
	 @RequestMapping("/config/sample.config")
	 public String getSampleConfig() {
		 return "sample.config";
	 }
	 
	 
	 @RequestMapping("/swf/sample.swf")
	 public String getSampleSwf() {
		 return "sample.swf";
	 }
	 
}
