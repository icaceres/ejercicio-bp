package com.pichincha.account.domain;

import com.pichincha.account.domain.enums.AccountTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Account implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String accountNumber;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private AccountTypeEnum accountType;

        @Column(nullable = false)
        private BigDecimal initialBalance;

        @Column(nullable = false)
        private Boolean status;

        @Column(nullable = false)
        private Long clientId;
}
