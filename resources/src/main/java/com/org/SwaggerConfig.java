package com.org;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("HR Portal REST API Document")
                .description("work in progress")
                .termsOfServiceUrl("localhost")
                .version("1.0")
                .build();
    }
    
    private ApiKey apiKeyJWT() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }
    
    private ApiKey apiKeyBasic() {
        return new ApiKey("basicAuth", "Basic", "header");
    }

    @Bean
    public Docket api() {    	
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.org.resources.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKeyJWT(), apiKeyBasic()));
    }
}
