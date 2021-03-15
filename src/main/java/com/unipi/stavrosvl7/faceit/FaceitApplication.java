package com.unipi.stavrosvl7.faceit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class FaceitApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceitApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.unipi.stavrosvl7"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "User managment API",
                "Sample API for faceit interview \n" +
                        "5 microservices included\n" +
                        "Functionallity for :\n" +
                        "Adding a new user (POST)\n" +
                        "Retrieving all users (GET)\n" +
                        "Retrieving all users by specific country (GET)\n" +
                        "Modifying existing user (PUT)\n" +
                        "Removing existing user (DELETE)\n",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Stavros Vlachakis", "https://www.github.com/stavrosvl7", "stavrosvl7@gmail.com"),
                "API Licence",
                "https://www.github.com/stavrosvl7",
                Collections.emptyList()
        );
    }
}
