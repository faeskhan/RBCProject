package com.fdmgroup.stepdefinitions;

import com.fdmgroup.data.DataFile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.fdmgroup.stepdefinitions.Hooks.driver;

public class F3_ContactUsTest_StepDef {

    @Given("User navigates to the RBC home page")
    public void user_navigates_to_the_rbc_home_page() {
        //navigate
        Hooks.driver.navigate().to(DataFile.homeURL);
    }

    @When("User navigates to the Contact Us page in the footer")
    public void user_navigates_to_the_contact_us_page_in_the_footer() {
        Hooks.driver.navigate().to(DataFile.contactUsURL);
    }

    @Then("The page should contain the text {string}")
    public void the_page_should_contain_the_text(String result) {
        boolean heading = Hooks.contactUsPage.isHelpHeadingDisplayed(result);
        Assert.assertTrue(
                "Expected page to contain: ", heading);
    }

    @When("User enters {string} in the search box")
    public void user_enters_in_the_search_box(String question) {
        Hooks.contactUsPage.enterQuestion(question);
    }
    @When("Clicks the search button")
    public void clicks_the_search_button() throws InterruptedException {
        Hooks.contactUsPage.clickSearchButton();
        Thread.sleep(2000);
    }
    @Then("User should see search results related to {string}")
    public void user_should_see_search_results_related_to(String result) {
        boolean searchedResults = Hooks.contactUsPage.isSearchResultsDisplayed(result);
        Assert.assertTrue(
                "Expected search results to the question should be displayed",
                searchedResults);
    }
}
