package com.vtiger.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(


        features = "src/test/resources/Features",
        glue ="com.vtiger.stepdefinitions",
        dryRun = false,
        plugin = {
                "pretty",                               // console output
                "html:target/cucumber-reports.html",    // HTML report
                "json:target/cucumber.json",            // JSON report
                "junit:target/cucumber.xml"             // JUnit XML report
        },
        tags = "@CreateLead"
        //monochrome = true



)

public class TestRunner {
}
