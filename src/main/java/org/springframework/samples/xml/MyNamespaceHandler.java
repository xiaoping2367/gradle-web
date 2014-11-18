package org.springframework.samples.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.naon.framework.lifecycle.SimpleDateFormatBeanDefinitionParser;

public class MyNamespaceHandler extends NamespaceHandlerSupport {
    
	 public void init() {
	        registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());        
	    }

}