package ru.account.api.controller;


import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.account.AppProperties;
import ru.account.api.helpers.ApiHelpers;
import ru.account.api.model.AccountModel;

import java.util.Map;

@Service
public class AccountsApiService {

    @Autowired
    private AppProperties appProperties;
    private static final String ACCOUNTS_BY_ID_PATH = "accounts/{id}";
    private static final String ACCOUNTS_PATH = "/accounts";
    private ApiHelpers apiHelpers;

    @Bean
    public void init() {
        apiHelpers = new ApiHelpers(appProperties.getBASE_URL());
    }
    public Response post(AccountModel model) {
        return apiHelpers.post(ACCOUNTS_PATH, model);
    }

    public Response get(AccountModel model) {
        return apiHelpers.get(ACCOUNTS_BY_ID_PATH, Map.of("id", String.valueOf(model.getId())));
    }

}
