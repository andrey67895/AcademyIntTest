package ru.account.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
