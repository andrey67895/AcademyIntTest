package ru.account.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account a WHERE a.passport_uuid = :passportUUID", nativeQuery = true)
    Optional<Account> getAccountsByPassportUUID(@Param("passportUUID") UUID passportUUID);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO public.account_check (\"time\",threads) VALUES (:time, :threads);", nativeQuery = true)
    void save(@Param("time") Long time, @Param("threads") Integer threads);
}
