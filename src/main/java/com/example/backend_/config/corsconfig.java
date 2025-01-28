package com.example.backend_.config;
 // Adjust the package name based on your project structure

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsconfig {

    @Bean
    public WebMvcConfigurer corsconfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                        .allowedOrigins("http://localhost:3000") // React frontend URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow cookies and authorization headers
            }
        };
    }
}
