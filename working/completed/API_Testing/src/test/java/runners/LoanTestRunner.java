package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/resources/features/loan.feature",
        glue = {"stepdefinitions.api.Loans", "Hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber-report.json",
        },
//        tags = "@Decimal_educationLoan",
        monochrome = true
)
public class LoanTestRunner extends AbstractTestNGCucumberTests {
}