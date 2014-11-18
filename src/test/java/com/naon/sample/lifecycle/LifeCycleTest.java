package com.naon.sample.lifecycle;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class LifeCycleTest {
	

	/*
	 * DisposableBean
	 */
	@Test
	public void testDisposableBean() {
		AbstractApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");
		HelloWorld obj = (HelloWorld)context.getBean("helloWorld");
		obj.getMessage(); 
		context.registerShutdownHook();
	}
	
	
	/**
	 * InitializingBean, DisposableBean
	 */
	@Test
	public void testInitialize() {
		AbstractApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");
		ExampleBean obj = (ExampleBean)context.getBean("exampleBean");
		context.registerShutdownHook();
	}
	
	
	@Test
	public void testBeanFactoryAware() {
		AbstractApplicationContext context =  new ClassPathXmlApplicationContext("spring-config.xml");
		AwareBean obj = (AwareBean)context.getBean("awareBean");
		context.registerShutdownHook();
	}
	
}
