package LoginAPI;

import org.testng.annotations.DataProvider;
import org.json.JSONObject;

public class TestCaseDataProvider {

    @DataProvider(name = "getTestData")
    public static Object[][] getTestData() {
        // Пример положительного теста
        JSONObject expectedPositiveResult = new JSONObject();
        expectedPositiveResult.put("statusCode", 201);
        expectedPositiveResult.put("result", true);
        expectedPositiveResult.put("cookiesExist", true);

        TestCaseContext positiveTestCase = new TestCaseContext("Positive login", new AuthRequest("validUser", "validPassword"), expectedPositiveResult);

        // Пример негативного теста
        JSONObject expectedNegativeResult = new JSONObject();
        expectedNegativeResult.put("statusCode", 400);
        expectedNegativeResult.put("result", false);
        expectedNegativeResult.put("errorMessage", "Invalid credentials");
        expectedNegativeResult.put("errorCode", "INVALID_CREDENTIALS");

        TestCaseContext negativeTestCase = new TestCaseContext("Negative login", new AuthRequest("invalidUser", "invalidPassword"), expectedNegativeResult);

        return new Object[][] {
                { positiveTestCase },
                { negativeTestCase }
        };
    }
}
