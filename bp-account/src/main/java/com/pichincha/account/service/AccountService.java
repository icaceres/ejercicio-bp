package com.pichincha.account.service;

import com.pichincha.account.service.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    AccountDto getAccountByNumber(String accountNumber);

    AccountDto saveAccount(AccountDto account);

    AccountDto updateAccount(String accountNumber, AccountDto accountToUpdate);

    AccountDto deleteAccount(String accountNumber);
}
