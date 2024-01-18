package ru.account.api.helpers;

import io.restassured.response.Response;

import java.util.Map;


public class ApiHelpers extends RestHelpers {

    public ApiHelpers(String baseUrl) {
        super(baseUrl);
    }

    public Response post(String path, Object jsonObject) {
        return postRequest(path, jsonObject);
    }

    public Response get(String path, Map<String, String> pathParams) {
        return getByPathParams(path, pathParams);
    }



}
