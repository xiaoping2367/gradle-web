package com.naon.framework.lifecycle.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * XML schema 네임스페이스 핸들러
 * 
 * @author Jhonson Choi(jhonson@naonsoft.com)
 */
public class NgwNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("interface-driven", new InterfaceDrivenBeanDefinitionParser());
    }
}
