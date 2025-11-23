package com.fdmgroup.pomscripts;

import com.fdmgroup.utilities.DriverUtilities;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openqa.selenium.WebDriver;

@RunWith(Suite.class)
@SuiteClasses({
        HomeValueEstimatorTest.class,
        MortgageCalculatorTest.class,
        ContactUsTest.class
})
public class Runner {
    static DriverUtilities driverUtilities;
    static WebDriver driver;

    @BeforeClass
    public static void init() throws InterruptedException {
        driverUtilities = DriverUtilities.getInstance();
        driver = driverUtilities.getDriver();

        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();     // quit once after all tests
        }
    }
}
