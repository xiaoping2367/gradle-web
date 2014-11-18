package com.naon.framework.lifecycle.spring;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Register Configuration and act as Configuration Provider.
 */
public class ConfigurationProviderImpl implements ConfigurationProvider, ResourceLoaderAware, InitializingBean {
	
    private static final String CONFIGURATION_FILE_SUFFIX = ".conf";

    /**
     * A map of loaded configuration per bean.
     */
    private Map<String, HierarchicalConfiguration> configurations = new HashMap<String, HierarchicalConfiguration>();

    /**
     * Mappings for bean names associated with their related
     * "resourceName.configPart" pattern.<br>
     * The resourceName is the XML configuration file name, the configPart is
     * the tag within the XML to look for.
     */
    private Map<String, String> configurationMappings;

    /**
     * The Spring Resource Loader. Injected via setResourceLoader because this
     * class implements ResourceLoaderAware.
     */
    private ResourceLoader loader;

    /**
	 * Inject the needed configuration mappings.
	 * 
	 * @param configurationMappings
	 *            the new mappings for bean names associated with their related
	 *            "resourceName
	 */
    public void setConfigurationMappings(Map<String, String> configurationMappings) {
        this.configurationMappings = configurationMappings;
    }

    /**
     * @see ConfigurationProvider#registerConfiguration(String, org.apache.commons.configuration.HierarchicalConfiguration)
     */
    public void registerConfiguration(String beanName, HierarchicalConfiguration conf) {
        configurations.put(beanName, conf);
    }

    /**
     * Responsible to register additional configurations for the injected
     * configurationMappings.
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        if (configurationMappings != null) {
            Iterator<String> it = configurationMappings.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = configurationMappings.get(key);
                registerConfiguration(key, getConfiguration(value));
            }
        }
    }

    /**
     * @see ConfigurationProvider#getConfiguration(String)
     */
    public HierarchicalConfiguration getConfiguration(String name) throws ConfigurationException {

        HierarchicalConfiguration conf = configurations.get(name);

        // Simply return the configuration if it is already loaded.
        if (conf != null) {
            return conf;
        }

        // Load the configuration.
        else {

            // Compute resourceName and configPart (if any, configPart can
            // remain null).
            int i = name.indexOf(".");
            String resourceName;
            String configPart = null;

            if (i > -1) {
                resourceName = name.substring(0, i);
                configPart = name.substring(i + 1);
            } else {
                resourceName = name;
            }

            Resource resource = loader.getResource(getConfigPrefix() + resourceName + CONFIGURATION_FILE_SUFFIX);

            if (resource.exists()) {
            	
                try {
                    HierarchicalConfiguration config = getConfig(resource);

                    if (configPart != null) {
                        return config.configurationAt(configPart);
                    } else {
                        return config;
                    }

                } catch (Exception e) {
                    throw new ConfigurationException("Unable to load configuration for component " + name, e);
                }
            }
        }

        // Configuration was not loaded, throw exception.
        throw new ConfigurationException("Unable to load configuration for component " + name);

    }

    /**
     * @see
     * org.springframework.context.ResourceLoaderAware#setResourceLoader(org.springframework.core.io.ResourceLoader)
     */
    public void setResourceLoader(ResourceLoader loader) {
        this.loader = loader;
    }

    /**
     * Load the xmlConfiguration from the given resource.
     *
     * @param r
     * @return
     * @throws org.apache.commons.configuration.ConfigurationException
     * @throws java.io.IOException
     */
    private XMLConfiguration getConfig(Resource r) throws ConfigurationException, IOException {
    	
        XMLConfiguration config = new XMLConfiguration();
        
        config.setDelimiterParsingDisabled(true);
        
        // Don't split attributes which can have bad side-effects with matcher-conditions.
        // See JAMES-1233
        config.setAttributeSplittingDisabled(true);
        
        // Use InputStream so we are not bound to File implementations of the
        // config
        config.load(r.getInputStream());
        
        return config;
    }

    /**
     * Return the configuration prefix to load the configuration. In this case
     * it is classpath:, but could be also file://conf/
     * 
     * @return prefix
     */
    private String getConfigPrefix() {
        return "classpath:";
    }

}
