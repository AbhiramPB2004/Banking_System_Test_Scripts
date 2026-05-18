package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ApiSessionManager;
import utils.ExcelReader;
import utils.LoanTestContext;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoanHooks {

    String excelPath = "src/test/java/resources/data/complete_Loan_test_cases.xlsx";
    String sheetName = "Automation";

    @Before
    public void beforeScenario() throws Exception {
        ApiSessionManager.loginIfNeeded();
    }

    @After
    public void afterScenario(io.cucumber.java.Scenario scenario) throws Exception {
        if (scenario.getSourceTagNames().contains("@applyIndividualLoans")) {
            System.out.println("Skipping cleanup for @applyIndividualLoans until TC_005 completes.");
            return;
        }

        if (LoanTestContext.getLoanIds().isEmpty()) {
            return;
        }

        ExcelReader excel = new ExcelReader(excelPath, sheetName);

        String accountId = excel.getCellDataByTestCaseIDSafe("TC_002", "Account_ID");

        for (String loanId : LoanTestContext.getLoanIds()) {

            try {
                JSONObject body = new JSONObject();
                body.put("source_account_id", accountId);

                Response cleanupResponse = ApiSessionManager.executeWithRefresh(() ->
                        given()
                                .cookies(getSafeCookies())
                                .header("Content-Type", "application/json")
                                .body(body.toString())
                                .when()
                                .post("/loans/foreclose/" + loanId)
                                .then()
                                .extract()
                                .response()
                );

                System.out.println("Cleanup Foreclose Loan ID: " + loanId);
                System.out.println(cleanupResponse.asPrettyString());

            } catch (Exception e) {
                System.out.println("Cleanup skipped for Loan ID: " + loanId);
                System.out.println("Reason: " + e.getMessage());
            }
        }

        LoanTestContext.clear();
    }

    private Map<String, String> getSafeCookies() {
        try {
            return ApiSessionManager.getCookies();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}