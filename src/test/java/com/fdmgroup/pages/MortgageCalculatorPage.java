package com.fdmgroup.pages;

import com.fdmgroup.data.DataFile;
import com.fdmgroup.pomscripts.MortgageCalculatorTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MortgageCalculatorPage {
    static WebDriver driver;
    WebDriverWait wait;

    public MortgageCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@id='page-title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//*[@id='basicMortAmount']")
    private WebElement mortgageField;

    @FindBy(xpath = "//button[contains(text(), 'Calculate')]")
    private WebElement calculateButton;

    @FindBy(xpath = "//*[@id='results-1']/div/div/div[1]/p[2]/span[5]")
    private WebElement monthlyPaymentResult;

    public boolean isPageTitleDisplayed(){
        return pageTitle.isDisplayed();
    }

    public String getPageTitle(){
        return pageTitle.getText();
    }

    public void enterMortgageAmount(int amount) {
        switchToCalculatorFrame();
        try {
            wait.until(ExpectedConditions.visibilityOf(mortgageField));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mortgageField);
            mortgageField.clear();
            mortgageField.sendKeys(String.valueOf(amount));
            System.out.println("Entered mortgage amount: " + amount);
        } catch (TimeoutException e) {
            throw new TimeoutException("Could not find mortgage amount field after waiting.");
        }
    }

    public void clickCalculateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(calculateButton));
        calculateButton.click();
    }

    public boolean isMonthlyPaymentDisplayed(){
        try {
            WebElement result = wait.until(ExpectedConditions.visibilityOf(monthlyPaymentResult));
            return result.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getMonthlyPaymentText() {
        wait.until(ExpectedConditions.visibilityOf(monthlyPaymentResult));
        return monthlyPaymentResult.getText();
    }

    private void switchToCalculatorFrame() {
        // Always reset to the top-level DOM
        driver.switchTo().defaultContent();

        try {
            //wait up to 15s for the calculator iframe to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement calcFrame = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("iframe[src*='mortgage-payment-calculator']")
            ));

            //switch to frame
            driver.switchTo().frame(calcFrame);
            System.out.println("Switched to Mortgage Calculator iframe.");

        } catch (TimeoutException e) {
            System.out.println("No iframe detected — continuing on main page.");
        }
//            driver.switchTo().defaultContent(); // always reset first
//            try {
//                List<WebElement> frames = driver.findElements(By.tagName("iframe"));
//                boolean switched = false;
//                for (WebElement frame : frames) {
//                    String src = frame.getAttribute("src");
//                    if (src != null && src.contains("mortgage-payment-calculator")) {
//                        driver.switchTo().frame(frame);
//                        switched = true;
//                        System.out.println("✅ Switched to Mortgage Calculator iframe.");
//                        break;
//                    }
//                }
//                if (!switched) {
//                    System.out.println("⚠️ No matching iframe found — continuing on main page.");
//                }
//            } catch (Exception e) {
//                System.out.println("⚠️ No iframe detected — continuing on main page.");
    }
}
