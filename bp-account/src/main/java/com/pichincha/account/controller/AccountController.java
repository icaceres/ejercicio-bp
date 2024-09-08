package com.pichincha.account.controller;

import com.pichincha.account.service.AccountService;
import com.pichincha.account.service.dto.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cuentas")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable(value = "id") String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountDto account) {
        return new ResponseEntity<>(accountService.saveAccount(account), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(value = "id") String accountNumber, @RequestBody AccountDto accountToUpdate) {
        return ResponseEntity.ok(accountService.updateAccount(accountNumber, accountToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(value = "id") String accountNumber) {
        return ResponseEntity.ok(accountService.deleteAccount(accountNumber));

    }
}
