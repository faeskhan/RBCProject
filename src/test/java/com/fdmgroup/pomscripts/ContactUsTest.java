package com.fdmgroup.pomscripts;

import com.fdmgroup.data.DataFile;
import com.fdmgroup.pages.ContactUsPage;
import com.fdmgroup.pages.HomeValueEstimatorPage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

import static com.fdmgroup.pomscripts.Runner.driverUtilities;
import static org.junit.Assert.assertTrue;

public class ContactUsTest {
    private static WebDriver driver;
    private static ContactUsPage contactUsPage;

    @BeforeClass
    public static void setUp() {
        driver = driverUtilities.getDriver();
        driver.navigate().to(DataFile.homeURL);
        contactUsPage = new ContactUsPage(driver);
    }

    @Test
    public void testHelpSearchFunctionality() throws InterruptedException {
        driver.navigate().to(DataFile.contactUsURL);
        Thread.sleep(2000);

        contactUsPage.enterQuestion("credit card");
        contactUsPage.clickSearchButton();
        Thread.sleep(3000);

        assertTrue("Search results should contain 'credit card'",
                contactUsPage.isSearchResultsDisplayed("credit card"));
    }

    @Test
    public void testTakingScreenshot() throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcFile, new File("src/test/resources/images/screenshot1.png"));
    }
}
