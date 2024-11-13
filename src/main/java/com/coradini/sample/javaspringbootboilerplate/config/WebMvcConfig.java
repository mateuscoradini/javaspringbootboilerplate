package com.coradini.sample.javaspringbootboilerplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile("!mvcIT")
public class WebMvcConfig implements WebMvcConfigurer {
    private final String swaggerUiPath;


    public WebMvcConfig(
        @Value("${springdoc.swagger-ui.path}") String swaggerUiPath
    ) {
        this.swaggerUiPath = swaggerUiPath;
    }

    @Override
    public final void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", swaggerUiPath);
    }

    @Override
    public final void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .exposedHeaders("*")
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
    }


}
