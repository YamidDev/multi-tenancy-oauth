package com.yamidev.multitenancy.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTenantInformation {

    private Map<String, String> map = new HashMap<>();
}
