package com.norra.config;

import com.google.common.collect.Lists;
import com.norra.constants.SwaggerConstants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring-auth-header-name}")
    private String authHeaderName;

    @Bean
    public Docket api() {
        HashSet<String> consumesAndProduces = new HashSet<>(Arrays.asList(SwaggerConstants.CONTENT_TYPE));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                .consumes(consumesAndProduces)
                .produces(consumesAndProduces)
                .pathMapping("/")
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build();
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title(SwaggerConstants.TITLE)
                .description(SwaggerConstants.DESCRIPTION)
                .version(SwaggerConstants.VERSION)
                .license(SwaggerConstants.LICENSE)
                .licenseUrl(SwaggerConstants.LICENSE_URL)
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(SwaggerConstants.AUTH_TYPE, authHeaderName, SwaggerConstants.HEADER);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(regex(SwaggerConstants.DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope(SwaggerConstants.SCOPE, SwaggerConstants.ACCESS_EVERYTHING);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference(SwaggerConstants.AUTH_TYPE, authorizationScopes));
    }
}