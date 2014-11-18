package com.naon.sample.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

//@Component
public class ExampleBean  implements InitializingBean, DisposableBean {
	
	// init-method
	@PostConstruct
	 public void init() {
	      // do some initialization work
		System.out.println("Init-postConstuctor");
	 }
	
	 public void afterPropertiesSet() {
	      // do some initialization work
		 System.out.println("After Properties Set");
	 }

	 // destroy-method
	 @PreDestroy
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Destroying");
	}
	 
	 
}///~
