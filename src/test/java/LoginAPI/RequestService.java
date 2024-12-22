package LoginAPI;

import io.restassured.response.Response;

public class RequestService {
    private RequestContext requestContext;

    public RequestService(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public Response makeAuthRequest(AuthRequest authRequest) {
        // Преобразуем AuthRequest в тело запроса (например, JSON)
        String requestBody = "{ \"username\": \"" + authRequest.getUsername() + "\", \"password\": \"" + authRequest.getPassword() + "\" }";

        // Отправляем запрос на сервер и получаем ответ
        Response response = requestContext.sendRequest("POST", "/auth", requestBody);

        return response;
    }
}
