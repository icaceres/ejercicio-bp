package com.pichincha.client.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "clientId")
public class Client extends Person {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;
}
