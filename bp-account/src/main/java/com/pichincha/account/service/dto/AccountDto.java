package com.pichincha.account.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    private Long id;
    private String accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean status;
    private String clientId;


}
