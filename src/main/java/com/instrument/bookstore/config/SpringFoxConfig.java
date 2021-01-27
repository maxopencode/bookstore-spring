package com.instrument.bookstore.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for configuring Swagger API docket.
 */
@Configuration
public class SpringFoxConfig {

    private static final String securitySchemaOAuth2 = "oauth2";

    @Value("${spring.security.oauth2.token-url}")
    private String tokenUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("bookstore")
                .select()
                // limit documentation to rest controllers only
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
                .securitySchemes(Lists.newArrayList(apiSecuritySchema()))
                .ignoredParameterTypes(AuthenticationPrincipal.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Instrument Bookstore Api")
                .description("Bookstore Api endpoints")
                .version("1.0")
                .build();
    }

    /*
     * This method configures OAuth2 flow in Swagger UI (doesn't work currently for Auth0 or Okta)
     * @return oauth config
     */
    private OAuth apiSecuritySchema() {
        final GrantType grantType = new ClientCredentialsGrant(tokenUrl);
        // final GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);

        final List<AuthorizationScope> scopes = List.of(
                new AuthorizationScope("authors:read", "Read authors"),
                new AuthorizationScope("authors:write", "Create, update and delete authors"),
                new AuthorizationScope("books:read", "Read books"),
                new AuthorizationScope("books:write", "Create, update and delete books"));

        return new OAuth(securitySchemaOAuth2, scopes, Collections.singletonList(grantType));
    }
}