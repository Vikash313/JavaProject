package com.example.demo.controllers;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
        		.paths(PathSelectors.ant("/api/*"))
        		.apis(RequestHandlerSelectors.basePackage("com.example"))
                .build()
                .apiInfo(apiDetails());     
    }
    
    private ApiInfo apiDetails() {
    	return new ApiInfo(
    			"Address book API",
    			"Sample API for Vikash",
    			"1.0",
    			"Free to use",
    			new springfox.documentation.service.Contact("Vikash Dubey", "http://vikash.io", "v.dubey2311@gmail.com"),
    			"API License",
    			"http://vikash.io",
    			Collections.emptyList());
    }
}
