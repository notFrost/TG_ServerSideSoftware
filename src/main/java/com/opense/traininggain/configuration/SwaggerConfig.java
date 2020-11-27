package com.opense.traininggain.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//http://localhost:8080/api/swagger-ui.html
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.opense.traininggain.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Training Gain API",
                "Backend RESTful API for Training Gain Web Application",
                "1.0",
                "https://notfrost.github.io/Training-Gain/terms",
                new Contact("Training Gain", "https://notfrost.github.io/Training-Gain/", "apis@hampcode.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
