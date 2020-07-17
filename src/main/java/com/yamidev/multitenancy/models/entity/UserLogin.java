package com.yamidev.multitenancy.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements Serializable {

    private String userName;
    private String password;
    private Integer tenantOrClientId;
}
