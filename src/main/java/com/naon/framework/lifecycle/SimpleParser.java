package com.naon.framework.lifecycle;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class SimpleParser implements BeanDefinitionParser {

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		
		System.out.println("=================================================");
		System.out.println("=================================================");
		System.out.println("=================================================");
		System.out.println("=================================================");
		
		Object source = parserContext.extractSource(element);
		
		return null;
	}


}///~
