package LoginAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RequestContext {
    private String authURL;  // URL для авторизации
    private String username; // Имя пользователя
    private String password; // Пароль
    private String token;    // Токен для авторизации

    // Приватный конструктор, чтобы создавать объекты только через Builder
    private RequestContext(Builder builder) {
        this.authURL = builder.authURL;
        this.username = builder.username;
        this.password = builder.password;
        this.token = builder.token;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Геттеры для всех полей
    public String getAuthURL() {
        return authURL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public Response sendRequest(String method, String endpoint, String body) {
        // Настройка базового URL для RestAssured
//        RestAssured.baseURI = baseUrl;

        // В зависимости от типа запроса, выполняем соответствующий метод
        switch (method.toUpperCase()) {
            case "POST":
                return RestAssured.given()
                        .contentType(ContentType.JSON) // Устанавливаем тип контента
                        .body(body) // Добавляем тело запроса
                        .when()
                        .post(endpoint) // Отправляем POST-запрос
                        .then()
                        .extract().response(); // Извлекаем ответ
            case "GET":
                return RestAssured.given()
                        .when()
                        .get(endpoint) // Отправляем GET-запрос
                        .then()
                        .extract().response(); // Извлекаем ответ
            // Добавьте другие методы (PUT, DELETE и т.д.) по аналогии
            default:
                throw new UnsupportedOperationException("Метод " + method + " не поддерживается");
        }
    }

    // Вложенный класс Builder
    public static class Builder {
        private String authURL;
        private String username;
        private String password;
        private String token;

        public Builder authURL(String authURL) {
            this.authURL = authURL;
            return this;  // Возвращаем текущий объект Builder для цепочки вызовов
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public RequestContext build() {
            return new RequestContext(this);
        }
    }
}
