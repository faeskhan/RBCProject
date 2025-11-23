package com.fdmgroup.stepdefinitions;

import com.fdmgroup.pages.ContactUsPage;
import com.fdmgroup.pages.HomeValueEstimatorPage;
import com.fdmgroup.pages.MortgageCalculatorPage;
import com.fdmgroup.pomscripts.MortgageCalculatorTest;
import com.fdmgroup.utilities.DriverUtilities;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class Hooks {
    static DriverUtilities driverUtilities;
    static WebDriver driver;
    static HomeValueEstimatorPage homeValueEstimatorPage;
    static MortgageCalculatorPage mortgageCalculatorPage;
    static ContactUsPage contactUsPage;

    @BeforeAll
    public static void init() {
        driverUtilities = DriverUtilities.getInstance();
        driver = driverUtilities.getDriver();
        homeValueEstimatorPage = new HomeValueEstimatorPage(driver);
        mortgageCalculatorPage = new MortgageCalculatorPage(driver);
        contactUsPage = new ContactUsPage(driver);

        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();     // quit once after all tests
        }
    }
}
