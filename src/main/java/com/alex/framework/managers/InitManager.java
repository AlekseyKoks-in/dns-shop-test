package com.alex.framework.managers;

import java.util.concurrent.TimeUnit;

import static com.alex.framework.utils.PropConst.IMPLICITLY_WAIT;
import static com.alex.framework.utils.PropConst.PAGE_LOAD_TIMEOUT;

public class InitManager {

    private static final TestPropertiesManager testPropertiesManager = TestPropertiesManager.getInstance();
    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts()
                .pageLoadTimeout(Integer.parseInt(testPropertiesManager.getProperty(PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts()
                .implicitlyWait(Integer.parseInt(testPropertiesManager.getProperty(IMPLICITLY_WAIT)), TimeUnit.SECONDS);
    }

    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
