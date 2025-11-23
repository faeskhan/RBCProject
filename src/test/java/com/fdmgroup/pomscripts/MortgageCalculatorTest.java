package com.fdmgroup.pomscripts;

import com.fdmgroup.data.DataFile;
import com.fdmgroup.pages.HomeValueEstimatorPage;
import com.fdmgroup.pages.MortgageCalculatorPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static com.fdmgroup.pomscripts.Runner.driverUtilities;
import static org.junit.Assert.assertTrue;

public class MortgageCalculatorTest {

    private static WebDriver driver;
    private static MortgageCalculatorPage mortgageCalculatorPage;

    @BeforeClass
    public static void setUp() {
        driver = driverUtilities.getDriver();
        driver.navigate().to(DataFile.homeURL);
        mortgageCalculatorPage = new MortgageCalculatorPage(driver);

    }

    @Test
    public void testMortgageCalculatorPageDisplays() throws InterruptedException {
        driver.navigate().to(DataFile.mortgageCalcURL);
        //verify the mortgage calculator page is displayed
        boolean pageDisplayed = mortgageCalculatorPage.isPageTitleDisplayed();
        assertTrue("Mortgage Payment Calculator page should be visible", pageDisplayed);
        Thread.sleep(2000);
    }

    @Test
    public void testMortgageCalculatorResults() throws InterruptedException {
        driver.navigate().to(DataFile.mortgageCalcURL);
        Thread.sleep(2000);
        mortgageCalculatorPage.enterMortgageAmount(DataFile.mortAmount);

        //click on Calculate button
        mortgageCalculatorPage.clickCalculateButton();
        Thread.sleep(3000);

        //verify monthly payment result appears
        boolean resultDisplayed = mortgageCalculatorPage.isMonthlyPaymentDisplayed();
        assertTrue("Monthly payment result should be displayed after calculation", resultDisplayed);
    }

}
