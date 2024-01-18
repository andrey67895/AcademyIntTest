package ru.account;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;
import ru.account.api.controller.AccountsApiService;
import ru.account.api.model.AccountModel;
import ru.account.db.Account;
import ru.account.db.AccountsDBService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Execution(ExecutionMode.SAME_THREAD)
public class AccountTest extends BaseClass{


    private static long start;

    @BeforeAll
    static void getStart() {
        start = System.currentTimeMillis();
    }

    @AfterAll
    void getFinish() {
        long finish = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (finish - start));
        accountsDBService.saveTime(String.valueOf((finish-start)), 2);
    }



    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(1000)
    void test() {
        AccountModel accountModel = AccountModel.builder()
                .firstName(RandomStringUtils.random(12, true, false))
                .lastName(RandomStringUtils.random(8, true, false))
                .passportUUID(UUID.randomUUID()).build();
        Response response = accountsApiService.post(accountModel);
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        Optional<Account> optionalAccount = accountsDBService.getAccountsByPassportUUID(accountModel.getPassportUUID());
        Assertions.assertTrue(optionalAccount.isPresent());
        Assertions.assertAll(
                () -> Assertions.assertEquals(accountModel.getPassportUUID().toString(), optionalAccount.get().getPassportUUID().toString()),
                () -> Assertions.assertEquals(accountModel.getFirstName(), optionalAccount.get().getFirstName()),
                () -> Assertions.assertEquals(accountModel.getLastName(), optionalAccount.get().getLastName())
        );
    }
}
