package com.pichincha.account.repository;

import com.pichincha.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByStatus(Boolean status);

    Optional<Account> findByAccountNumberAndStatus(String accountNumber, Boolean status);
}
