package com.instrument.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * This class configures OAuth2 application security that restricts access to API endpoints based on scopes provides in the JWT token.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Profile("oauth2")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.cors()
                .and()
                    // disable sessions
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    // limit security restrictions to requests below "authors" and "books" only
                    .requestMatchers()
                        .antMatchers("/v*/authors/**", "/v*/books/**")
                .and()
                    .authorizeRequests()
                        .mvcMatchers(HttpMethod.GET, "/v*/authors/**")
                            .hasAuthority("SCOPE_authors:read")
                        .mvcMatchers("/v*/authors/**")
                            .hasAuthority("SCOPE_authors:write")
                        .mvcMatchers(HttpMethod.GET, "/v*/books/**")
                            .hasAuthority("SCOPE_books:read")
                        .mvcMatchers("/v*/books/**")
                            .hasAuthority("SCOPE_books:write")
                        .anyRequest()
                            .authenticated()
                .and()
                    .oauth2ResourceServer()
                        .jwt(); // enable JWT based security
        // @formatter:on
    }
}