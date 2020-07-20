package com.yamidev.multitenancy.util;

import com.yamidev.multitenancy.mastertenant.entity.MasterTenant;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public final class DataSourceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceUtil.class);

    public static DataSource createAndConfigureDataSource(MasterTenant masterTenant) {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername(masterTenant.getUserName());
        ds.setPassword(masterTenant.getPassword());
        ds.setJdbcUrl(masterTenant.getUrl());
        ds.setDriverClassName(masterTenant.getDriverClass());
        ds.setConnectionTimeout(20000);
        ds.setMinimumIdle(3);
        ds.setMaximumPoolSize(500);
        ds.setIdleTimeout(300000);
        ds.setConnectionTimeout(20000);
        String tenantConnectionPoolName = masterTenant.getDbName() + "-connection-pool";
        ds.setPoolName(tenantConnectionPoolName);
        LOG.info("Configured datasource:" + masterTenant.getDbName() +
                ". Connection pool name:" + tenantConnectionPoolName);
        return ds;
    }
}
