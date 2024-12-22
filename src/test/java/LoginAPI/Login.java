package LoginAPI;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


/**
 * <p>Класс, тестирующий функционал, касающийся авторизации юзера</p>
 */
@Listeners(TestListener.class)
public class Login {

    public Login() {

        this.description = "Проверка успешного входа.\n\n";
        this.requestContext =
                RequestContext
                        .builder()
                        .authURL("https://petstore.swagger.io")
                        .build();

    }

    private final String description;

    private final RequestContext requestContext;

    /**
     * <p>Инициализация фильтроов перед тестами</p>
     */
    @BeforeClass
    public void setUp() {

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }

    /**
     * <p>Позитивный тест входа.</p>
     */
    @Test(dataProvider = "getTestData", dataProviderClass = TestCaseDataProvider.class)
    @Epic("Pozitiv")
    @Feature(value = "Простые математические операции")
    @Story(value = "Сложение")
    @Owner(value = "Пупкин Валерий Иванович")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPositiveLogin(TestCaseContext testCase) {

        Allure.description(description + testCase.getDescription());

        RequestService myRequestService = new RequestService(requestContext);

        Response response = myRequestService.makeAuthRequest(testCase.getAuthRequest());

        assertPositiveLogin(testCase, response);

    }

    /**
     * <p>Негативный тест входа.</p>
     */
    @Test(dataProvider = "getTestData", dataProviderClass = TestCaseDataProvider.class)
    @Epic("Negativ")
    public void testNegativeLogin(TestCaseContext testCase) {

        Allure.description(description + testCase.getDescription());

        RequestService myRequestService = new RequestService(requestContext);

        Response response = myRequestService.makeAuthRequest(testCase.getAuthRequest());

        assertNegativeLogin(testCase, response);
    }

    /**
     * <p>Проверки для позитивного теста входа.</p>
     */
    private void assertPositiveLogin(TestCaseContext testCase, Response response) {

        testCase.getSoftAssert().assertEquals(
                response.getStatusCode(),
                200,
                "Проверка на статус-код OK");
        testCase.getSoftAssert().assertEquals(
                response.jsonPath().getBoolean("result"),
                true,
                "Проверка на ответ result true");
        testCase.getSoftAssert().assertEquals(
                response.getDetailedCookies().exist(),
                testCase.getExpectedResult().getBoolean("cookiesExist"),
                "Проверка на наличие cookies в ответе");

        testCase.getSoftAssert().assertAll();
    }

    /**
     * <p>Проверки для негативного теста входа.</p>
     */
    private void assertNegativeLogin(TestCaseContext testCase, Response response) {

        testCase.getSoftAssert().assertEquals(
                response.getStatusCode(),
                testCase.getExpectedResult().getInt("statusCode"),
                "Проверка на статус-код");
        testCase.getSoftAssert().assertEquals(
                response.jsonPath().getBoolean("result"),
                testCase.getExpectedResult().getBoolean("result"),
                "Проверка на ответ result false");
        testCase.getSoftAssert().assertEquals(
                response.jsonPath().getString("errorMessage"),
                testCase.getExpectedResult().getString("errorMessage"),
                "Проверка на соответствие errorMessage");
        testCase.getSoftAssert().assertEquals(
                response.jsonPath().getString("errorCode"),
                testCase.getExpectedResult().getString("errorCode"),
                "Проверка на соответствие errorCode");
        testCase.getSoftAssert().assertEquals(
                response.jsonPath().getString("path"),
                testCase.getExpectedResult().optString("path", null),
                "Проверка на соответствие path");
        testCase.getSoftAssert().assertNotEquals(
                response.jsonPath().getString("errorId"),
                testCase.getExpectedResult().optString("notExpectedErrorId", null),
                "Проверка на существование errorId");
        testCase.getSoftAssert().assertNotEquals(
                response.jsonPath().getString("errorCode"),
                testCase.getExpectedResult().getString("errorCode"),
                "Проверка кода ошибки");

        testCase.getSoftAssert().assertAll();
    }
}