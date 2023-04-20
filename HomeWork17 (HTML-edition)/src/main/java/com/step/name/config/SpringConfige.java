package com.step.name.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.step.name")
@PropertySource("classpath:dataBase/dataBase.properties")
public class SpringConfige {
}
