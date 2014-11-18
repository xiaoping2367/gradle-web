package com.andy.biz.guide.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naon.framework.lifecycle.NaonInitializingBean;



@Controller
@RequestMapping("{sitemesh}/guide")
public class GuideController  extends NaonInitializingBean{
	
	
	@Override
	public String getName() {
		return this.getClass().getCanonicalName(); 
	}

	@RequestMapping("/guideWidget")
	public String guideWidget(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/biz/guide/jquery.widget/guideWidget"; 
	}//:

	
	
	
	
	
}///~
