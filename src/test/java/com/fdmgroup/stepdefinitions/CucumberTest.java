package com.fdmgroup.stepdefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "com.fdmgroup.stepdefinitions",
        tags = "",
        plugin = {
                "pretty",
                "json:Report/cucumber.json",
                "junit:Report/cucumber.junit",
                "html:Report/cucumber.html"
        },
       // dryRun = true,
        publish = true
)
public class CucumberTest {

}
