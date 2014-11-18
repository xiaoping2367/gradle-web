package com.naon.framework.lifecycle.spring;

import com.naon.framework.lifecycle.api.Configurable;
import org.apache.commons.configuration.HierarchicalConfiguration;

/**
 * Configurable 인터페이스를 구현한 클래스에 Commons Configuration을 주입한다.
 *
 * @author 최종성(jhonson@naonsoft.com)
 */
public class ConfigurableBeanPostProcessor extends AbstractLifecycleBeanPostProcessor<Configurable> {

    private ConfigurationProvider provider;

    public void setConfigurationProvider(ConfigurationProvider provider) {
        this.provider = provider;
    }

    @Override
    protected Class<Configurable> getLifeCycleInterface() {
        return Configurable.class;
    }

    @Override
    protected void executeLifecycleMethodBeforeInit(Configurable bean, String beanName) throws Exception {    	
        HierarchicalConfiguration config = provider.getConfiguration(beanName);
        bean.configure(config);
    }

    @Override
    protected void executeLifecycleMethodAfterInit(Configurable bean, String beanName) throws Exception {
        // Do nothing.
    }

}
