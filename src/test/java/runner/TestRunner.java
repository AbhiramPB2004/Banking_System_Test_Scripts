package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/resources/features",
    glue = {
        "stepdefinitions",
        "hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber-report.json"
    },
    monochrome = true,
    publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}