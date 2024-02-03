package ru.account.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import ru.account.db.Account;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
    private Long id;
    private String firstName;
    private String lastName;
    private UUID passportUUID;

    public static AccountModel getAccountModel() {
        return AccountModel.builder()
                .firstName(RandomStringUtils.random(12, true, false))
                .lastName(RandomStringUtils.random(8, true, false))
                .passportUUID(UUID.randomUUID()).build();
    }

    public void compareTo(Account account) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(this.getPassportUUID().toString(), account.getPassportUUID().toString()),
                () -> Assertions.assertEquals(this.getFirstName(), account.getFirstName()),
                () -> Assertions.assertEquals(this.getLastName(), account.getLastName())
        );
    }
}
