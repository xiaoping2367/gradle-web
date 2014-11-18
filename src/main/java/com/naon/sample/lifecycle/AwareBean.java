package com.naon.sample.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;


//@Component
public class AwareBean implements BeanFactoryAware, BeanNameAware{

	public AwareBean() {
		System.out.println("Constructor of AwareBean");
	}
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory:"+beanFactory);
	}
	
	
	@Override 
	public void setBeanName(String beanName) {
		// BeanNamAware에서 Bean의 이름을 알 수 있음. 
		System.out.println("the name of the bean is " + beanName);
	}
	
}
