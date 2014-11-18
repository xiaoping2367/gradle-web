package com.naon.framework.lifecycle;

import org.springframework.beans.factory.InitializingBean;

public abstract class NaonInitializingBean implements InitializingBean{
	

	public abstract String getName(); 

	
	/**
	 * InitializingBean의 메서드. 구현해야 함. Bean이 생성된 이 후에 호출될 메서드.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
	
		System.out.println("============#############===============");
		System.out.println("===========================");
		System.out.println("===========================");
		System.out.println("AfterPropertiesSet");
		System.out.println("===========================");
		System.out.println("===========================");
		
	}

	
	
}
