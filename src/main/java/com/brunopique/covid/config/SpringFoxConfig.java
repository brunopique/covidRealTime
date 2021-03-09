package com.brunopique.covid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    // TODO: replace with below
    @Bean
    public Docket api() {

        ApiInfo apiInfo = new ApiInfo("COVID-19 DATA API",
                "Swagger implementation for the `com.brunopique.covid` web application.",
                "1.0",
                "",
                new Contact("Bruno Piqué",
                        "http://brunopique.com",
                        "hola@brunopique.com"),
                "Open Source",
                "http://brunopique.com/covid",
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
