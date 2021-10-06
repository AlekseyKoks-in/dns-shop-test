package com.alex.tests;

import com.alex.framework.managers.DriverManager;
import com.alex.framework.managers.InitManager;
import com.alex.framework.managers.PageManager;
import com.alex.framework.managers.TestPropertiesManager;
import com.alex.framework.utils.PropConst;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTests {

    private final DriverManager driverManager = DriverManager.getInstance();
    private final TestPropertiesManager testPropertiesManager = TestPropertiesManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @BeforeEach
    public void before() {
        driverManager.getDriver().get(testPropertiesManager.getProperty(PropConst.BASE_URL));
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
