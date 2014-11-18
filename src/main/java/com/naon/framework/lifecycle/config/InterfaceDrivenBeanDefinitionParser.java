package com.naon.framework.lifecycle.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.naon.framework.lifecycle.spring.ConfigurableBeanPostProcessor;
import com.naon.framework.lifecycle.spring.ConfigurationProviderImpl;
import com.naon.framework.lifecycle.spring.LogEnabledBeanPostProcessor;
import com.naon.framework.lifecycle.spring.LogProviderImpl;

/**
 * 로거 설정과 계층적 설정 설정을 해준다.
 * 
 * @author Jhonson Choi(jhonson@naonsoft.com)
 */
public class InterfaceDrivenBeanDefinitionParser implements BeanDefinitionParser {

	public BeanDefinition parse(Element element, ParserContext parserContext) {

		Object source = parserContext.extractSource(element);

		RuntimeBeanReference logProviderService = getLogProviderService(element, source, parserContext);

		RootBeanDefinition lpDef = new RootBeanDefinition(LogEnabledBeanPostProcessor.class);
		lpDef.setSource(source);
		lpDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		lpDef.getPropertyValues().add("logProvider", logProviderService);
		String lpMappingName = parserContext.getReaderContext().registerWithGeneratedName(lpDef);

		parserContext.registerComponent(new BeanComponentDefinition(lpDef, lpMappingName));

		RuntimeBeanReference configurationProviderService = getConfigurationProviderService(element, source, parserContext);

		RootBeanDefinition cpDef = new RootBeanDefinition(ConfigurableBeanPostProcessor.class);
		cpDef.setSource(source);
		cpDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		cpDef.getPropertyValues().add("configurationProvider", configurationProviderService);
		String cpMappingName = parserContext.getReaderContext().registerWithGeneratedName(cpDef);

		parserContext.registerComponent(new BeanComponentDefinition(cpDef, cpMappingName));
		
		return null;
	}

	private RuntimeBeanReference getLogProviderService(Element element, Object source, ParserContext parserContext) {
		RuntimeBeanReference logProviderServiceRef;
		if (element.hasAttribute("logProvider")) {
			logProviderServiceRef = new RuntimeBeanReference(element.getAttribute("logProvider"));
		} else {
			RootBeanDefinition logProviderDef = new RootBeanDefinition(LogProviderImpl.class);
			logProviderDef.setSource(source);
			logProviderDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			String logProviderName = parserContext.getReaderContext().registerWithGeneratedName(logProviderDef);
			parserContext.registerComponent(new BeanComponentDefinition(logProviderDef, logProviderName));
			logProviderServiceRef = new RuntimeBeanReference(logProviderName);
		}
		return logProviderServiceRef;
	}

	private RuntimeBeanReference getConfigurationProviderService(Element element, Object source, ParserContext parserContext) {
		RuntimeBeanReference configurationProviderServiceRef;
		if (element.hasAttribute("configurationProvider")) {
			configurationProviderServiceRef = new RuntimeBeanReference(element.getAttribute("configurationProvider"));
		} else {
			RootBeanDefinition configurationProviderDef = new RootBeanDefinition(ConfigurationProviderImpl.class);
			configurationProviderDef.setSource(source);
			configurationProviderDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			String configurationProviderName = parserContext.getReaderContext().registerWithGeneratedName(configurationProviderDef);
			parserContext.registerComponent(new BeanComponentDefinition(configurationProviderDef, configurationProviderName));
			configurationProviderServiceRef = new RuntimeBeanReference(configurationProviderName);
		}
		return configurationProviderServiceRef;
	}
}
