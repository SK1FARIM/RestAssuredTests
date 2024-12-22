package LoginAPI;

import org.testng.asserts.SoftAssert;
import org.json.JSONObject;

public class TestCaseContext {

    private String description;       // Описание теста
    private AuthRequest authRequest;  // Данные аутентификационного запроса
    private JSONObject expectedResult; // Ожидаемые результаты (например, статусы, сообщения об ошибках)
    private SoftAssert softAssert;    // SoftAssert для асинхронных проверок

    // Конструктор
    public TestCaseContext(String description, AuthRequest authRequest, JSONObject expectedResult) {
        this.description = description;
        this.authRequest = authRequest;
        this.expectedResult = expectedResult;
        this.softAssert = new SoftAssert();
    }

    // Геттеры и сеттеры
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthRequest getAuthRequest() {
        return authRequest;
    }

    public void setAuthRequest(AuthRequest authRequest) {
        this.authRequest = authRequest;
    }

    public JSONObject getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(JSONObject expectedResult) {
        this.expectedResult = expectedResult;
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }

    public void setSoftAssert(SoftAssert softAssert) {
        this.softAssert = softAssert;
    }
}
