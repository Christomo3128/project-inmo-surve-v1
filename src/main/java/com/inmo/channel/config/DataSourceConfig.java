package com.inmo.channel.config;

import com.microsoft.sqlserver.jdbc.SQLServerColumnEncryptionAzureKeyVaultProvider;
import com.microsoft.sqlserver.jdbc.SQLServerColumnEncryptionKeyStoreProvider;
import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Lazy
@Configuration
@Slf4j
public class DataSourceConfig {

  @Bean
  DataSource dataSource(ApplicationProperties applicationProperties) {

    DataSourceBuilder<? extends DataSource> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(applicationProperties.getDriverClassName());
    dataSourceBuilder.url(applicationProperties.getUrl());
    dataSourceBuilder.username(applicationProperties.getUsername());
    dataSourceBuilder.password(applicationProperties.getPassword());

//    try {
//      SQLServerColumnEncryptionAzureKeyVaultProvider akvProvider =
//          new SQLServerColumnEncryptionAzureKeyVaultProvider(
//              applicationProperties.getClientId(),
//              applicationProperties.getClientKey());
//      Map<String, SQLServerColumnEncryptionKeyStoreProvider> keyStoreMap = new HashMap<>();
//      keyStoreMap.put(akvProvider.getName(), akvProvider);
//      SQLServerConnection.registerColumnEncryptionKeyStoreProviders(keyStoreMap);
//    } catch (Exception e) {
//      log.error("Error registerColumnEncryptionKey in DataSourceConfig: {}", ExceptionUtils.getStackTrace(e));
//    }

    return dataSourceBuilder.build();
  }

}
