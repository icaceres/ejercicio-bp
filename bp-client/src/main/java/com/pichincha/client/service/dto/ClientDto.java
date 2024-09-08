package com.pichincha.client.service.dto;

import lombok.Data;

@Data
public class ClientDto {

    private Long id;
    private String personName;
    private String gender;
    private Integer age;
    private String address;
    private String telephone;
    private String password;
    private Boolean status;
    private String identification;
}
