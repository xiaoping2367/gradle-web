package com.naon.framework.lifecycle;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
	}
	
}
