package com.yamidev.multitenancy.tenant.config;

import com.yamidev.multitenancy.mastertenant.config.DBContextHolder;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.apache.commons.lang3.StringUtils;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_TENANT_ID = "client_tenant_1";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenant = DBContextHolder.getCurrentDb();
        return StringUtils.isNotBlank(tenant) ? tenant : DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
