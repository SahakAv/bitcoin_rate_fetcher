package com.kuhne_nagel.rate_fetcher.util;

import com.kuhne_nagel.rate_fetcher.exception.InvalidCurrencyCodeException;
import com.kuhne_nagel.rate_fetcher.exception.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class ApplicationPropertiesUtil {
    private static final Map<String, String> PROPERTIES = new HashMap<>();
    private static final String PROPERTIES_FILE_NAME = "application.properties";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationPropertiesUtil.class);

    static {
        initializeProperties();
    }

    /**
     * @param propertyName - Property Name
     * @return Property
     *@throws PropertyNotFoundException if the property not found
     */
    public static String getProperty(final String propertyName) {
        return Optional.ofNullable(PROPERTIES.get(propertyName)).orElseThrow(() -> new PropertyNotFoundException(propertyName + " not found"));
    }


    private static void initializeProperties() {
        try (InputStream inputStream = ApplicationPropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME)) {
            final Properties properties = new Properties();
            properties.load(inputStream);
            properties.stringPropertyNames().forEach(s -> PROPERTIES.put(s, properties.getProperty(s)));
        } catch (IOException e) {
            LOGGER.error("Error occurred while getting properties");
        }
    }


}
