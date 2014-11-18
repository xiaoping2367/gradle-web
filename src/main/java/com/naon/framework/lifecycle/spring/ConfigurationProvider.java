package com.naon.framework.lifecycle.spring;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;

/**
 * Load {@link org.apache.commons.configuration.HierarchicalConfiguration} for beans
 */
public interface ConfigurationProvider {

    /**
     * Register a {@link org.apache.commons.configuration.HierarchicalConfiguration} for a bean name.<br>
     * It is not mandatory to use the registerConfiguration to have the
     * configuration available as the {@link #getConfiguration(String)} method may load
     * it based on conventions (naming,...).
     *
     * @param beanName
     *            The bean name for which the configuration has to be
     *            registered.
     * @param conf
     *            The hierarchical configuration to register for the bean name.
     */
    void registerConfiguration(String beanName, HierarchicalConfiguration conf);

    /**
     * Load and return the configuration for the bean with the given name. The
     * configuration may already be loaded from a previous method invocation or
     * from a previous configuration registration via the
     * {@link #registerConfiguration(String, org.apache.commons.configuration.HierarchicalConfiguration)}.<br>
     * This method may implement convention based configuration loading based on
     * naming,...
     *
     * @param beanName
     * @return config
     * @throws org.apache.commons.configuration.ConfigurationException
     */
    HierarchicalConfiguration getConfiguration(String beanName) throws ConfigurationException;

}
