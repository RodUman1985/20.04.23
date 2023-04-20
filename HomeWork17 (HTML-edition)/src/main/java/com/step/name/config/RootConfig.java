package com.step.name.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.step.name.service","com.step.name.repository"})
public class RootConfig {
}
