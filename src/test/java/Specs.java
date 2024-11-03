import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {

    public static RequestSpecification requestSpecification(String baseUri, String basePath){
        return new RequestSpecBuilder()
            .setBaseUri(baseUri)
//            .setPort()
            .setBasePath(basePath)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
}

    public static ResponseSpecification responseSpecification(){
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    public static void installSpec(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

}
