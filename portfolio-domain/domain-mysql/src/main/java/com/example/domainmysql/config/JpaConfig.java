package com.example.domainmysql.config;

import com.example.domainmysql.DomainMySqlEntityScan;
import com.example.domainmysql.domains.JpaRepositoryScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = DomainMySqlEntityScan.class)
@EnableJpaRepositories(basePackageClasses = JpaRepositoryScan.class)
public class JpaConfig {
}
