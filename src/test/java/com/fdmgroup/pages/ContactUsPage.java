package com.fdmgroup.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {
    static WebDriver driver;
    WebDriverWait wait;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='answers']")
    private WebElement helpHeading;

    @FindBy(xpath = "//*[@id='intelli-search']")
    private WebElement questionField;

    @FindBy(xpath = "//button[contains(text(), 'Find An Answer') or contains(text(), 'Search')]")
    private WebElement searchButton;

    // Search result section
    @FindBy(xpath = "//*[@id='iResponse']/section[3]")
    private WebElement searchResultsSection;

    public boolean isHelpHeadingDisplayed(String result) {
        try {
            wait.until(ExpectedConditions.visibilityOf(helpHeading));
            return helpHeading.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enterQuestion(String question) {
        wait.until(ExpectedConditions.visibilityOf(questionField));
        questionField.clear();
        questionField.sendKeys(question);
        System.out.println("Entered search query: " + question);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        System.out.println("Clicked on search button.");
    }

    public boolean isSearchResultsDisplayed(String expectedKeyword) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchResultsSection));
            String result = searchResultsSection.getText().toLowerCase();
            return result.contains(expectedKeyword.toLowerCase());
        } catch (TimeoutException e) {
            return false;
        }
    }
}
