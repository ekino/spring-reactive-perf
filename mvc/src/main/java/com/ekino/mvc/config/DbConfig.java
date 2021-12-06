package com.ekino.mvc.config;

import com.ekino.mvc.Application;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration(proxyBeanMethods = false)
@EnableJdbcRepositories(basePackageClasses = Application.class)
public class DbConfig {

}
