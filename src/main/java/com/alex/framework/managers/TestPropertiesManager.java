package com.alex.framework.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropertiesManager {

    private final Properties properties = new Properties();

    private static TestPropertiesManager testPropertiesManager;

    private TestPropertiesManager() {
        loadApplicationProperties();
        loadCustomProperties();
    }

    public static TestPropertiesManager getInstance() {
        if (testPropertiesManager == null) {
            testPropertiesManager = new TestPropertiesManager();
        }
        return testPropertiesManager;
    }

    private void loadApplicationProperties() {
        try {
            properties.load(new FileInputStream("src/main/resources/" +
                    System.getProperty("propFile", "application") + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomProperties() {
        properties.forEach((key, value) -> System.getProperties()
                .forEach((customUserKey, customUserValue) -> {
                    if (key.toString().equals(customUserKey.toString()) &&
                            !value.toString().equals(customUserValue.toString())) {
                        properties.setProperty(key.toString(), customUserValue.toString());
                    }
                }));
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
