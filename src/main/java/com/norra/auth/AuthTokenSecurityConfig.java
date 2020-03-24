package com.norra.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import com.norra.constants.Constants;
import com.norra.util.JwtUtil;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
@Order(1)
/**
 * This method is called when the application runs and is used for authorization
 */
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring-auth-header-name}")
    private String authHeaderName;

    /**
     * This method is called when the application starts and sets the auth header name.
     * @param httpSecurity - of type HttpSecurity
     * @throws Exception if the token is invalid in multiple scenarios
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(authHeaderName);

        filter.setAuthenticationManager(new AuthenticationManager()
        {
            /**
             * Reads the value of AUTH_API_KEY from header
             * Gets the userId from JWT token using util method
             * If userId is not null then authentication is success else returns 403 Access Denied
             */
            @Override
            public Authentication authenticate(Authentication authentication) {
                Long userId;
                String jwtToken = (String) authentication.getPrincipal();
                userId = JwtUtil.getUserIdFromAuthToken(jwtToken);
                if (userId.equals(null))
                {
                    throw new BadCredentialsException(Constants.BAD_CREDENTIALS_EXCEPTION);
                }
                authentication.setAuthenticated(true);
                return authentication;
            }
        });

        httpSecurity.
                antMatcher(Constants.ANT_MATCHER_URL)
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .addFilterBefore(new ExceptionTranslationFilter(
                                new Http403ForbiddenEntryPoint()),
                        filter.getClass()
                )
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}
