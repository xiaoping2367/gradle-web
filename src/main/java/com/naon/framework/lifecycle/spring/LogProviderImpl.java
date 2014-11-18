package com.naon.framework.lifecycle.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provide a Log object for components
 */
public class LogProviderImpl implements LogProvider, InitializingBean, LogProviderManagementMBean {

    private final ConcurrentHashMap<String, Logger> logMap = new ConcurrentHashMap<String, Logger>();
    private Map<String, String> logs;
    private final static String PREFIX = "ngw.";

    /**
     * Use {@link org.slf4j.Logger} to create the Log
     *
     * @param loggerName
     * @return log
     */
    protected Logger createLogger(String loggerName) {
        return LoggerFactory.getLogger(loggerName);
    }

    public void setLogMappings(Map<String, String> logs) {
        this.logs = logs;
    }

    /**
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
    	System.out.println("=====================================");
    	System.out.println("=====================================");
    	System.out.println("=====================================");
    	System.out.println("=====================================");
    	System.out.println("=====================================");
    	
        if (logs != null) {
            Iterator<String> it = logs.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = logs.get(key);
                
                
                System.out.println("============= key/value");
                System.out.println(key + "/" + value);
                registerLogger(key, createLogger(PREFIX + value));
            }
        }
    }

    /**
     * @see
     * LogProvider#getLogger(String)
     */
    public Logger getLogger(String name) {
        logMap.putIfAbsent(name, createLogger(PREFIX + name));
        return logMap.get(name);
    }

    /**
     * @see
     * LogProvider#registerLogger(String, org.slf4j.Logger)
     */
    public void registerLogger(String beanName, Logger logger) {
        logMap.put(beanName, logger);
    }

    /**
     * @see LogProviderManagementMBean#getSupportedLogLevels()
     */
    public List<String> getSupportedLogLevels() {
        return Arrays.asList("DEBUG", "INFO", "WARN", "ERROR", "OFF");
    }

    /**
     * @see LogProviderManagementMBean#getLogLevels()
     */
    public Map<String, String> getLogLevels() {
        TreeMap<String, String> levels = new TreeMap<String, String>();
        Iterator<String> names = logMap.keySet().iterator();
        while (names.hasNext()) {
            String name = names.next();
            String level = getLogLevel(name);
            if (level != null)
                levels.put(name, level);
        }
        return levels;

    }

    /**
     * @see LogProviderManagementMBean#getLogLevel(String)
     */
    public String getLogLevel(String component) {
        Logger log = logMap.get(component);
        if (log == null) {
            throw new IllegalArgumentException("No Log for component " + component);
        }
//        Logger logger = log.getRootLogger();
//        if (logger == null || logger.getLevel() == null) {
//            return null;
//        }
//        Level level = logger.getLevel();
//        return level.toString();
        return "INFO";
    }

    /**
     * @see LogProviderManagementMBean#setLogLevel(String, String)
     */
    public void setLogLevel(String component, String loglevel) {
        if (getSupportedLogLevels().contains(loglevel) == false) {
            throw new IllegalArgumentException("Not supported loglevel given");
        } else {
//            ((org.apache.log4j.Logger) logMap.get(component)).getRootLogger().setLevel(Level.toLevel(loglevel));
        }
    }

}
