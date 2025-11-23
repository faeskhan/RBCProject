package com.fdmgroup.stepdefinitions;

import com.fdmgroup.data.DataFile;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.fdmgroup.stepdefinitions.Hooks.driver;
import static com.fdmgroup.stepdefinitions.Hooks.mortgageCalculatorPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class F2_MortgageCalculatorTest_StepDef {
    @Given("User launches the RBC home page")
    public void user_launches_the_rbc_home_page() {
        //navigate
        Hooks.driver.navigate().to(DataFile.homeURL);
    }

    @When("User navigates to Mortgage Calculator page")
    public void user_navigates_to() {
        //navigate to mortgage payment calculator page
        Hooks.driver.navigate().to(DataFile.mortgageCalcURL);
    }

    @Then("The calculator page should be displayed")
    public void the_calculator_page_should_be_displayed() {
        Assert.assertTrue("Mortgage Calculator page should be displayed",
                Hooks.mortgageCalculatorPage.isPageTitleDisplayed()
        );
    }

    @Then("The heading should contain {string}")
    public void the_heading_should_contain(String pageTitle) {
        assertEquals(pageTitle, Hooks.mortgageCalculatorPage.getPageTitle());
    }

    @When("User enters amount in the Mortgage Amount field")
    public void userEntersInTheMortgageAmountField() {
        Hooks.mortgageCalculatorPage.enterMortgageAmount(DataFile.mortAmount);
    }

    @When("User clicks the Calculate button")
    public void user_clicks_the_calculate_button() throws InterruptedException {
        Hooks.mortgageCalculatorPage.clickCalculateButton();
        Thread.sleep(2000);
    }

    @Then("The results page should display the estimated monthly payment")
    public void the_results_page_should_display_the_estimated_monthly_payment() throws InterruptedException {
        Assert.assertTrue(
                "Expected the monthly payment result to be displayed",
                Hooks.mortgageCalculatorPage.isMonthlyPaymentDisplayed());
    }

    @Then("The result should include the text {string}")
    public void the_result_should_include_the_text(String result) {
        String actualText = Hooks.mortgageCalculatorPage.getMonthlyPaymentText();
        Assert.assertTrue(
                "Expected the text to contain: " + result + " but got: " + actualText,
                actualText.toLowerCase().contains(result.toLowerCase().replace(":", ""))  // ignore case and colon
        );
    }

}
