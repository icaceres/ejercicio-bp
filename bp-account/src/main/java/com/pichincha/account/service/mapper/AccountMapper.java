package com.pichincha.account.service.mapper;

import com.pichincha.account.domain.Account;
import com.pichincha.account.service.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(AccountDto accountDto);

    AccountDto toAccountDto(Account account);
}
