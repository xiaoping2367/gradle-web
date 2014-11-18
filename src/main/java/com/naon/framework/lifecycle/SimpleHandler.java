package com.naon.framework.lifecycle;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class SimpleHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("simplehandler", new SimpleParser());
	}
}///~
