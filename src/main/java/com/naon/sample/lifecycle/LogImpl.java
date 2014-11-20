package com.naon.sample.lifecycle;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.naon.framework.lifecycle.api.LogEnabled;


@Component
public class LogImpl implements LogEnabled, InitializingBean {

	Logger logger = null;
	
	public  LogImpl() {
		System.out.println("LogImpl Constructor");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("=========================..... init method");
		logger.error("==============================init...................................");
	}
	@Override
	public void setLogger(Logger logger) {
		// TODO Auto-generated method stub
		
		System.out.println("setLogger.....");
		this.logger = logger;
		
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("============afterPropertiesSet======================");
		System.out.println(logger.toString());
		logger.error("==============================debug....................................");
		
	}
	
}
