package ru.account;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.account.api.controller.AccountsApiService;
import ru.account.db.AccountsDBService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseClass {
    @Autowired
    protected AccountsApiService accountsApiService;
    @Autowired
    protected AccountsDBService accountsDBService;
}
