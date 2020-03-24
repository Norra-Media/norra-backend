package com.norra.auth;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.norra.constants.Constants;

import javax.servlet.http.HttpServletRequest;

public class PreAuthTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String authHeaderName;

    public PreAuthTokenHeaderFilter(String authHeaderName) {
        this.authHeaderName = authHeaderName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(authHeaderName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return Constants.NOT_AVAILABLE;
    }
}
