package com.yamidev.multitenancy.tenant.config;

import com.yamidev.multitenancy.mastertenant.config.DBContextHolder;
import com.yamidev.multitenancy.mastertenant.entity.MasterTenant;
import com.yamidev.multitenancy.mastertenant.repository.MasterTenantRepository;
import com.yamidev.multitenancy.util.DataSourceUtil;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl extends
        AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final Logger LOG = LoggerFactory.getLogger(
            DataSourceBasedMultiTenantConnectionProviderImpl.class);

    private static final long serialVersionUID = 1L;

    private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();

    @Autowired
    private MasterTenantRepository masterTenantRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected DataSource selectAnyDataSource() {
        if (dataSourcesMtApp.isEmpty()) {
            List<MasterTenant> masterTenants = masterTenantRepository.findAll();
            LOG.info("selectAnyDataSource() method call...Total tenants:" + masterTenants.size());
            for (MasterTenant masterTenant : masterTenants) {
                dataSourcesMtApp.put(
                        masterTenant.getDbName(),
                        DataSourceUtil.createAndConfigureDataSource(masterTenant)
                );
            }
        }
        return this.dataSourcesMtApp.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        tenantIdentifier = initializeTenantIfLost(tenantIdentifier);
        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            List<MasterTenant> masterTenants = masterTenantRepository.findAll();
            LOG.info("selectDataSource() method call...Tenant:" + tenantIdentifier + " Total tenants:" + masterTenants.size());
            for (MasterTenant masterTenant : masterTenants) {
                dataSourcesMtApp.put(masterTenant.getDbName(), DataSourceUtil.createAndConfigureDataSource(masterTenant));
            }
        }

        if (!this.dataSourcesMtApp.containsKey(tenantIdentifier)) {
            LOG.warn("Trying to get tenant:" + tenantIdentifier + " which was not found in master db after rescan");
            throw new UsernameNotFoundException(String.format("Tenant not found after rescan, " + " tenant=%s", tenantIdentifier));
        }
        return this.dataSourcesMtApp.get(tenantIdentifier);
    }

    private String initializeTenantIfLost(String tenantIdentifier) {
        if (tenantIdentifier != DBContextHolder.getCurrentDb()) {
            tenantIdentifier = DBContextHolder.getCurrentDb();
        }
        return tenantIdentifier;
    }
}
