package com.fyl.sso.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源参数
 */
@Data
@Component
@ConfigurationProperties(prefix = "cas.authn.jdbc.query[0]")
public class DataSourceProperties {
    private String url;
    private String user;
    private String password;
    private String driverClass;

    private String sql;
}
