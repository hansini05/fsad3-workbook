package com.fsad.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.fsad") // ✅ correct
public class AppConfig {
}
