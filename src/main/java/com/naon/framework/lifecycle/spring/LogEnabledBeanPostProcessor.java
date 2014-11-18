package com.naon.framework.lifecycle.spring;

import com.naon.framework.lifecycle.api.LogEnabled;

/**
 * Inject Commons Log to beans which implement LogEnabled.
 */
public class LogEnabledBeanPostProcessor extends AbstractLifecycleBeanPostProcessor<LogEnabled> {

    private LogProvider provider;

    public void setLogProvider(LogProvider provider) {
        this.provider = provider;
    }

    @Override
    protected Class<LogEnabled> getLifeCycleInterface() {
        return LogEnabled.class;
    }

    @Override
    protected void executeLifecycleMethodBeforeInit(LogEnabled bean, String beanName) throws Exception {
    	
    	System.out.println("*******************************");
    	System.out.println(beanName);
    	System.out.println("*******************************");
    	
    	
    	bean.setLogger(provider.getLogger(beanName));
    }

    @Override
    protected void executeLifecycleMethodAfterInit(LogEnabled bean, String beanName) throws Exception {
        // Do nothing.
    }

}
