package com.yamidev.multitenancy.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse implements Serializable {

    private String userName;
    private String token;

}
