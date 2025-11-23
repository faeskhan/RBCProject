package com.fdmgroup.stepdefinitions;

import com.fdmgroup.data.DataFile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.fdmgroup.stepdefinitions.Hooks.driver;
import static com.fdmgroup.stepdefinitions.Hooks.homeValueEstimatorPage;
import static org.junit.Assert.assertEquals;

public class F1_HomeValueEstimatorTest_StepDef {
    @Given("User launches the RBC Home Value Estimator page")
    public void user_launches_the_rbc_home_value_estimator_page() {
        //navigation
        Hooks.driver.navigate().to(DataFile.homeValueURL);
    }

    @When("User enters valid address in the address field")
    public void userEntersValidAddressInTheAddressField() throws InterruptedException {
        //enters address
        Hooks.homeValueEstimatorPage.enterAddress(DataFile.address);
    }

    @When("User enters invalid {string} in the address field")
    public void userEntersInvalidInTheAddressField(String address) throws InterruptedException {
        Thread.sleep(2000);
        //enters address
        Hooks.homeValueEstimatorPage.enterAddress(address);
    }

    @When("Selects the first suggested address")
    public void selectsTheFirstSuggestedAddress() throws InterruptedException {
        Hooks.homeValueEstimatorPage.selectFirstSuggestion();
        Thread.sleep(2000);
    }

    @When("Clicks on the Get Started button")
    public void clicks_on_the_get_started_button() {
        Hooks.homeValueEstimatorPage.clickGetStartedButton();
    }

    @Then("User should be redirected to the What is Your Property Type page")
    public void user_should_be_redirected_to_the_what_is_your_property_type_page() throws InterruptedException {
        boolean pageLoaded = driver.getPageSource().contains("What is Your Property Type?");
        Assert.assertTrue("User should be redirected to the correct page", pageLoaded);
        Thread.sleep(2000);
    }

    @Then("The system should display an error message {string}")
    public void the_system_should_display_an_error_message(String alertMessage) throws InterruptedException {
        Assert.assertEquals(alertMessage, Hooks.homeValueEstimatorPage.getInvalidAddressAlert());
        Thread.sleep(2000);
    }

}
