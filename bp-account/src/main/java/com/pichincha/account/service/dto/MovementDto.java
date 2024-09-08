package com.pichincha.account.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovementDto {

    private Long id;
    private LocalDate date;
    private String movementType;
    private BigDecimal amount;
    private BigDecimal initialBalance;
    private BigDecimal finalBalance;
    private String observation;
    private AccountDto account;
}
