package com.naon.sample.lifecycle;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.naon.framework.lifecycle.api.LogEnabled;


@Component
public class LogImpl implements LogEnabled{

	Logger logger = null;
	
	@Override
	public void setLogger(Logger logger) {
		// TODO Auto-generated method stub
		this.logger = logger;
	}

	
}
