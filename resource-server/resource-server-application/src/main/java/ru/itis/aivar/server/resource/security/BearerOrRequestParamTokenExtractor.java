package ru.itis.aivar.server.resource.security;

import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BearerOrRequestParamTokenExtractor extends BearerTokenExtractor {

    @Override
    protected String extractToken(HttpServletRequest request) {
        String token = super.extractToken(request);
        if (token == null) {
            token = request.getParameter("access_token");
        }
        return token;
    }
}
