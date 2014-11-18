package com.naon.framework.lifecycle.spring;

import java.util.List;
import java.util.Map;

/**
 * Allow to change loglevel via JMX
 */
public interface LogProviderManagementMBean {

    List<String> getSupportedLogLevels();

    Map<String, String> getLogLevels();

    String getLogLevel(String component);

    void setLogLevel(String component, String logLevel);

}
