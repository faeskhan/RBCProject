package com.fdmgroup.pomscripts;

import com.fdmgroup.data.DataFile;
import com.fdmgroup.pages.HomeValueEstimatorPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.fdmgroup.pomscripts.Runner.driverUtilities;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HomeValueEstimatorTest {
    private static WebDriver driver;
    private static HomeValueEstimatorPage homeValueEstimatorPage;

    @BeforeClass
    public static void setUp() {
        driver = driverUtilities.getDriver();
        driver.navigate().to(DataFile.homeURL);
        homeValueEstimatorPage = new HomeValueEstimatorPage(driver);

    }

    @Test
    public void testValidHomeAddress() throws InterruptedException {

        driver.navigate().to("https://apps.royalbank.com/apps/home-value-estimator");

        //send home address
        homeValueEstimatorPage.enterAddress(DataFile.address);
        homeValueEstimatorPage.selectFirstSuggestion();
        Thread.sleep(2000);

        //wait until button status is enabled
        assertTrue("Get Started button should be enabled for a valid address",
                homeValueEstimatorPage.isGetStartedButtonEnabled());

        //click the Get Started button
        homeValueEstimatorPage.clickGetStartedButton();

        //verify page navigation after clicking button
        boolean pageLoaded = driver.getPageSource().contains("What is Your Property Type?");
        assertTrue("User should be redirected to property type selection page", pageLoaded);
        Thread.sleep(2000);
    }

    @Test
    public void testInvalidHomeAddress() throws InterruptedException {

        driver.navigate().to("https://apps.royalbank.com/apps/home-value-estimator");

        //send home address
        homeValueEstimatorPage.enterAddress(DataFile.invalidAddress);
        homeValueEstimatorPage.selectFirstSuggestion();
        Thread.sleep(2000);

        //wait until button status is enabled
        assertTrue("Get Started button should be disabled for an invalid address",
                homeValueEstimatorPage.isGetStartedButtonEnabled());

        //click the Get Started button
        homeValueEstimatorPage.clickGetStartedButton();

        assertTrue("Error message 'Invalid address' should be displayed",
                homeValueEstimatorPage.isInvalidAddressMessageDisplayed());
        Thread.sleep(2000);
    }
}
