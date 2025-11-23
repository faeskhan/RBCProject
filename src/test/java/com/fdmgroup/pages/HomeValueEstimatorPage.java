package com.fdmgroup.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeValueEstimatorPage {
    static WebDriver driver;
    WebDriverWait wait;

    public HomeValueEstimatorPage(WebDriver driver) {
        HomeValueEstimatorPage.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='address']")
    private WebElement addressField;

    @FindBy(css = ".pac-item")  // Google autocomplete first suggestion
    private WebElement firstSuggestion;

    @FindBy(xpath = "//button[contains(text(),'Get Started')]")
    private WebElement getStartedButton;

    @FindBy(xpath = "//*[contains(text(),'Invalid address')]")
    private WebElement invalidAddressAlert;

    public void enterAddress(String address) {
        try {
            //some RBC calculators load inside an iframe – handle it dynamically
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                    By.cssSelector("iframe[src*='home-value-estimator']")));
        } catch (Exception e) {
            System.out.println("No iframe detected. Continuing on main page...");
        }

        wait.until(ExpectedConditions.elementToBeClickable(addressField));

        //scroll into view before interaction (important for RBC site)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addressField);

        addressField.clear();
        addressField.sendKeys(address);
    }

    public void selectFirstSuggestion() {
        wait.until(ExpectedConditions.visibilityOf(firstSuggestion));
        firstSuggestion.click();
    }

    public boolean isAddressFieldDisplayed(){
        return addressField.isDisplayed();
    }

    public boolean isGetStartedButtonEnabled() {
        //wait until the button is visible, then check enabled state
        wait.until(ExpectedConditions.visibilityOf(getStartedButton));
        return getStartedButton.isEnabled();
    }

    public void clickGetStartedButton() {
        wait.until(ExpectedConditions.elementToBeClickable(getStartedButton));
        getStartedButton.click();
    }

    public String getInvalidAddressAlert(){
        return invalidAddressAlert.getText();
    }

    public boolean isInvalidAddressMessageDisplayed(){
        try {
            wait.until(ExpectedConditions.visibilityOf(invalidAddressAlert));
            return invalidAddressAlert.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


}
