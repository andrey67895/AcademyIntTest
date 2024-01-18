package ru.account.db;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountsDBService {
    private final AccountsRepository accountsRepository;

    public AccountsDBService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

   public Optional<Account> getAccountsByPassportUUID(UUID uuid){
        return accountsRepository.getAccountsByPassportUUID(uuid);
    }

    public void saveTime(String time, Integer threads){
        accountsRepository.save(Long.parseLong(time), threads);
    }
}
