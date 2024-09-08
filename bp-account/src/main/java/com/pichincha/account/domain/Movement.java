package com.pichincha.account.domain;

import com.pichincha.account.domain.enums.MovementTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Movement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementTypeEnum movementType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal initialBalance;

    @Column(nullable = false)
    private BigDecimal finalBalance;

    @Column(length = 300)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
