package com.yamidev.multitenancy.mastertenant.service;

import com.yamidev.multitenancy.mastertenant.entity.MasterTenant;

public interface IMasterTenantService {
    MasterTenant findByClientId(Long clientId);
}
