package com.jms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = JMSConfig.class)
@ComponentScan(basePackages = "com.jms.service")
public class RootConfig {

}
