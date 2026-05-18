//package stepdefinitions.api.Loans;
//
//import io.cucumber.java.en.*;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.json.JSONObject;
//import org.testng.Assert;
//import utils.ConfigReader;
//import utils.ExcelReader;
//
//import java.io.IOException;
//import java.util.Map;
//
//import static io.restassured.RestAssured.given;
//
//public class Apply_all_loans {
//
//    ConfigReader config;
//    ExcelReader excel;
//    Response response;
//
//    static Map<String, String> cookies;
//    static boolean isLoggedIn = false;
//
//    String excelPath = "src/test/java/resources/data/complete_Loan_test_cases.xlsx";
//    String sheetName = "Automation";
//
//    @Given("User is logged in for applying multiple loans")
//    public void user_is_logged_in_for_applying_multiple_loans() throws IOException {
//
//        config = new ConfigReader();
//        RestAssured.baseURI = config.getProperty("baseUrl");
//
//        JSONObject loginBody = new JSONObject();
//        loginBody.put("email", config.getProperty("email"));
//        loginBody.put("password", config.getProperty("password"));
//
//        response =
//                given()
//                        .header("Content-Type", "application/json")
//                        .body(loginBody.toString())
//                        .when()
//                        .post(config.getProperty("loginEndpoint"))
//                        .then()
//                        .extract()
//                        .response();
//
//        System.out.println("Login Response:");
//        System.out.println(response.asPrettyString());
//
//        Assert.assertEquals(response.getStatusCode(), 200, "Login failed");
//        Assert.assertEquals(response.jsonPath().getBoolean("success"), true, "Login success mismatch");
//
//        cookies = response.getCookies();
//
//        Assert.assertFalse(cookies.isEmpty(), "Cookies not found after login");
//
//        System.out.println("Saved Cookies: " + cookies);
//    }
//
//    @When("User applies loan for {string}")
//    public void user_applies_loan_for(String tcId) throws IOException {
//
//        excel = new ExcelReader(excelPath, sheetName);
//
//        int row = getRowNumberByTCID(tcId);
//
//        JSONObject loanBody = buildLoanBody(row);
//
//        String endpoint = excel.getCellData(row, "Endpoint");
//
//        System.out.println("Applying Loan For TC_ID: " + tcId);
//        System.out.println("Endpoint: " + endpoint);
//        System.out.println("Request Body:");
//        System.out.println(loanBody.toString());
//
//        response =
//                given()
//                        .cookies(cookies)
//                        .header("Content-Type", "application/json")
//                        .body(loanBody.toString())
//                        .when()
//                        .post(endpoint)
//                        .then()
//                        .extract()
//                        .response();
//
//        System.out.println("Response For " + tcId + ":");
//        System.out.println(response.asPrettyString());
//    }
//
//    @Then("Loan application for {string} should be validated")
//    public void loan_application_for_should_be_validated(String tcId) {
//
//        int row = getRowNumberByTCID(tcId);
//
//        int expectedStatus = Integer.parseInt(
//                excel.getCellData(row, "Expected_Status_Code")
//        );
//
//        String expectedSuccess = excel.getCellData(row, "Expected_Success")
//                .trim()
//                .toLowerCase();
//
//        String expectedMessage = excel.getCellData(row, "Expected_Message")
//                .trim()
//                .toLowerCase();
//
//        Assert.assertEquals(
//                response.getStatusCode(),
//                expectedStatus,
//                "Status code mismatch for " + tcId
//        );
//
//        Boolean actualSuccess = response.jsonPath().getBoolean("success");
//
//        Assert.assertEquals(
//                String.valueOf(actualSuccess).toLowerCase(),
//                expectedSuccess,
//                "Success mismatch for " + tcId
//        );
//
//        if (!expectedMessage.isEmpty()) {
//            Assert.assertTrue(
//                    response.asString().toLowerCase().contains(expectedMessage),
//                    "Expected message not found for " + tcId
//            );
//        }
//
//        if (actualSuccess) {
//            String loanId = response.jsonPath().getString("data.loan_id");
//
//            Assert.assertNotNull(loanId, "Loan ID not generated for " + tcId);
//
//            System.out.println("Loan ID for " + tcId + ": " + loanId);
//        }
//    }
//
//    public JSONObject buildLoanBody(int row) {
//
//        JSONObject loanBody = new JSONObject();
//
//        loanBody.put("loan_type", excel.getCellData(row, "loan_type"));
//        loanBody.put("requested_amount", Double.parseDouble(excel.getCellData(row, "requested_amount")));
//        loanBody.put("tenure_months", Integer.parseInt(excel.getCellData(row, "tenure_months")));
//        loanBody.put("annual_income", Double.parseDouble(excel.getCellData(row, "annual_income")));
//        loanBody.put("occupation", excel.getCellData(row, "Occupation"));
//        loanBody.put("existing_liabilities", Double.parseDouble(excel.getCellData(row, "existing_liabilities")));
//        loanBody.put("linked_account_id", excel.getCellData(row, "Account_ID"));
//
//        return loanBody;
//    }
//
//    public int getRowNumberByTCID(String tcId) {
//
//        int rowCount = excel.getRowCount();
//
//        for (int i = 1; i <= rowCount; i++) {
//
//            String currentTCID = excel.getCellData(i, "TC_ID");
//
//            if (currentTCID.equalsIgnoreCase(tcId)) {
//                return i;
//            }
//        }
//
//        throw new RuntimeException("TC_ID not found in Excel: " + tcId);
//    }
//}