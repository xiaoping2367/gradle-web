package com.naon.framework.lifecycle.api;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;

/**
 * 설정 파일에 접근하려면 이 인터페이스를 구현해야 한다.

 * @author 최종성(jhonson@naonsoft.com)
 */
public interface Configurable {

    /**
     * 객체를 설정한다.
     * 이 것이 호출될 때는 어떤 서비스도 주입되지 않았다는 것을 주의해야 한다.
     * 주입된 것에 접근하려면 @PostConstruct 를 사용해야 한다.
     * 
     * @param config
     * @throws org.apache.commons.configuration.ConfigurationException
     */
    void configure(HierarchicalConfiguration config) throws ConfigurationException;

}
