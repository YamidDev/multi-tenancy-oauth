package com.yamidev.multitenancy.mastertenant.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("multitenancy.mtappp.master.datasource")
public class MasterDatabaseConfigProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private long connectionTimeout;
    private int maxPoolSize;
    private long idleTimeout;
    private int minIdle;
    private String poolName;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MasterDatabaseConfigProperties [url=");
        builder.append(url);
        builder.append(", username=");
        builder.append(username);
        builder.append(", password=");
        builder.append(password);
        builder.append(", driverClassName=");
        builder.append(driverClassName);
        builder.append(", connectionTimeout=");
        builder.append(connectionTimeout);
        builder.append(", maxPoolSize=");
        builder.append(maxPoolSize);
        builder.append(", idleTimeout=");
        builder.append(idleTimeout);
        builder.append(", minIdle=");
        builder.append(minIdle);
        builder.append(", poolName=");
        builder.append(poolName);
        builder.append("]");
        return builder.toString();
    }

}
