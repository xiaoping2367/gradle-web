package com.naon.framework.lifecycle.spring;

import org.slf4j.Logger;

/**
 * Provide {@link org.slf4j.Logger} instances for Beans
 */
public interface LogProvider {

    /**
     * Return the Log for the bean with the given name
     *
     * @param beanName
     * @return log
     */
    Logger getLogger(String beanName);

    /**
     * Register a {@link org.slf4j.Logger} for a beanName. The registered Log will get
     * returned by {@link #getLogger(String)}
     * 
     * @param beanName
     * @param logger
     */
    void registerLogger(String beanName, Logger logger);
}
