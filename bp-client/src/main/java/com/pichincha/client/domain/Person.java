package com.pichincha.client.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Person implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(nullable = false)
        private String personName;

        @Column(length = 25)
        private String gender;

        @Column
        private Integer age;

        @Column(nullable = false)
        private String address;

        @Column(length = 15)
        private String telephone;

        @Column(nullable = false, length = 10)
        private String identification;
}
