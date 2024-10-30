import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Tests {
    @BeforeAll
    public static void setup() {
        // Устанавливаем базовый URI для тестов
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testGet() {
        RestAssured.baseURI = "https://postman-echo.com";

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/get"))
                .extract()
                .response();

        System.out.println("Response body: " + response.getBody().asString());
    }

    @Test
    @Step("Тестирование POST запроса ")
    public void testPost() {
        // Создание POST-запроса
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body("{\"id\":666,\"name\":\"Helldoggie\",\"status\":\"available\"}")
                .post("https://petstore.swagger.io/v2/pet");

        // Вывод ответа
        System.out.println("Response code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());
    }

    @Test
    public void testGetRequest() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/pet/666")
                .then()
                .statusCode(200)
                .body("name", equalTo("Helldoggie"))
                .body("status", equalTo("available"))
                .extract()
                .response();

        System.out.println("Response body: " + response.getBody().asString());
    }



    @Test
    public void testGetPostsold() {
        // Выполняем GET-запрос на /posts и проверяем статус код и содержимое ответа
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)) // Проверяем, что размер массива постов больше 0
                .body("[0].userId", notNullValue()); // Проверяем, что userId первого поста не null
    }

    @Test
    public void testGetSinglePostold() {
        // Выполняем GET-запрос на /posts/1 и проверяем статус код и содержимое ответа
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response body: " + response.getBody().asString());
    }





//    @Test
//    @Step("Тестирование GET запроса на /posts")
//    public void testGetPosts() {
//        Allure.step("Выполняем GET запрос на /posts");
//
//        given()
//                .header("Content-Type", "application/json")
//                .when()
//                .get("/posts")
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThan(0))
//                .body("[0].userId", notNullValue());
//    }
//
//    @Test
//    @Step("Тестирование GET запроса на /posts/1")
//    public void testGetSinglePost() {
//        Allure.step("Выполняем GET запрос на /posts/1");
//
//        Response response = given()
//                .header("Content-Type", "application/json")
//                .when()
//                .get("/posts/1")
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        Allure.step("Выводим тело ответа: " + response.getBody().asString());
//    }

}
