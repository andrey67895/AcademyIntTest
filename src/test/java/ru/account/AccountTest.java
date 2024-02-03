package ru.account;

import io.restassured.response.Response;
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
        accountsDBService.saveTime(String.valueOf((finish - start)), 10);
    }



    @Execution(ExecutionMode.CONCURRENT)
    @RepeatedTest(1000)
    void createAccount() {
        AccountModel accountModel = AccountModel.getAccountModel();
        Response response = accountsApiService.createAccount(accountModel);
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        Optional<Account> optionalAccount = accountsDBService.getAccountsByPassportUUID(accountModel.getPassportUUID());
        Assertions.assertTrue(optionalAccount.isPresent());
        accountModel.compareTo(optionalAccount.get());
    }
}
