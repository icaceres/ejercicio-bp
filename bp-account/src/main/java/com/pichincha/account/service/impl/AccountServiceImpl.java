package com.pichincha.account.service.impl;

import com.pichincha.account.domain.Account;
import com.pichincha.account.exception.InvalidActionException;
import com.pichincha.account.exception.ResourceNotFoundException;
import com.pichincha.account.repository.AccountRepository;
import com.pichincha.account.service.AccountService;
import com.pichincha.account.service.ClientService;
import com.pichincha.account.service.dto.AccountDto;
import com.pichincha.account.service.dto.ClientDto;
import com.pichincha.account.service.mapper.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private AccountMapper accountMapper;

    private ClientService clientService;

    @Override
    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accounts = accountRepository.findByStatus(Boolean.TRUE).stream().map(accountMapper::toAccountDto).toList();
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("No existen cuentas registradas.");
        }
        return accounts;
    }

    @Override
    public AccountDto getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumberAndStatus(accountNumber, Boolean.TRUE).map(accountMapper::toAccountDto).orElseThrow(()
                -> new ResourceNotFoundException("Cuenta no encontrada."));

    }

    @Transactional
    @Override
    public AccountDto saveAccount(AccountDto account) {
        ClientDto clientDto;
        try {
            clientDto = clientService.getClientByIdentification(account.getClientId()).block();
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

        accountRepository.findByAccountNumberAndStatus(account.getAccountNumber(), Boolean.TRUE).ifPresent(res -> {
            throw new InvalidActionException("La cuenta con el número " + account.getAccountNumber() + " ya se encuentra registrada.");
        });

        account.setAccountType(account.getAccountType().toUpperCase());
        Account accountEntity = accountMapper.toAccount(account);
        assert clientDto != null;
        accountEntity.setClientId(clientDto.getId());
        return accountMapper.toAccountDto(accountRepository.save(accountEntity));
    }

    @Override
    public AccountDto updateAccount(String accountNumber, AccountDto accountToUpdate) {
        AccountDto account = getAccountByNumber(accountNumber);
        account.setStatus(accountToUpdate.getStatus());
        account.setInitialBalance(accountToUpdate.getInitialBalance());
        account.setAccountType(accountToUpdate.getAccountType());
        return accountMapper.toAccountDto(accountRepository.save(accountMapper.toAccount(account)));
    }

    @Override
    public AccountDto deleteAccount(String accountNumber) {
        AccountDto account = getAccountByNumber(accountNumber);
        account.setStatus(Boolean.FALSE);
        return accountMapper.toAccountDto(accountRepository.save(accountMapper.toAccount(account)));
    }
}
