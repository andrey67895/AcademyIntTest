package ru.account.api.helpers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Data
public abstract class RestHelpers {
    private static final String APPLICATION_JSON = "application/json";
    private final String BASE_URL;

    public RestHelpers(String baseUrl) {
        this.BASE_URL = baseUrl;
    }

    protected Response postRequest(String path, Object jsonObject) {
        return getRequestSpecification().body(jsonObject).post(path);
    }

    protected Response getByPathParams(String path, Map<String, String> pathParams) {
        return getRequestSpecification().pathParams(pathParams).get(path);
    }

    private RequestSpecification getRequestSpecification() {
        return given().log().all(false).baseUri(BASE_URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON).then().log().all(false).request();
    }
}
