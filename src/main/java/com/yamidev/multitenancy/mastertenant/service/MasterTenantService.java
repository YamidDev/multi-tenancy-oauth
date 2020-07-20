package com.yamidev.multitenancy.mastertenant.service;

import com.yamidev.multitenancy.mastertenant.entity.MasterTenant;

public interface MasterTenantService {
    MasterTenant findByClientId(Long clientId);
}
