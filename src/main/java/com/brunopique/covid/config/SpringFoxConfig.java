package com.brunopique.covid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 A {@code SpringFoxConfig} object implements a simple Swagger
 configuration using a Docket Bean.
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {

        ApiInfo apiInfo = new ApiInfo("COVID-19 DATA API",
                "Swagger implementation for the `com.brunopique.covid` web application.",
                "1.0",
                "",
                new Contact("Bruno Piqué",
                        "http://brunopique.dev",
                        "hi@brunopique.dev"),
                "Open Source",
                "http://brunopique.dev/covid",
                new ArrayList<>());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .pathMapping("/");
    }
}
