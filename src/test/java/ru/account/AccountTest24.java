package ru.account;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.http.HttpStatus;
import ru.account.api.model.AccountModel;
import ru.account.db.Account;

import java.util.Optional;
import java.util.UUID;


@Execution(ExecutionMode.SAME_THREAD)
public class AccountTest24 extends BaseClass{


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
        AccountModel accountModel = AccountModel.builder().firstName(RandomStringUtils.random(12, true, false)).lastName(RandomStringUtils.random(8, true, false)).passportUUID(UUID.randomUUID()).build();
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
