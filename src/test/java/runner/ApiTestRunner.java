package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/api",
    glue = {"stepdefinitions.api"},
    plugin = {
        "pretty",
        "html:target/api-cucumber-report.html",
        "json:target/api-cucumber-report.json"
    },
    monochrome = true,
    publish = false
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
}