package ru.itis.aivar.server.resource.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Component
@Slf4j
public class ChangeTokenExtractorListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    BearerOrRequestParamTokenExtractor tokenExtractor;

    @Autowired
    Filter springSecurityFilterChain;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("TokenExtractor change started...");
        OAuth2AuthenticationProcessingFilter filter = (OAuth2AuthenticationProcessingFilter) ((FilterChainProxy) springSecurityFilterChain).getFilterChains().stream()
                .flatMap(chain -> chain.getFilters().stream()).filter(filter1 -> filter1 instanceof OAuth2AuthenticationProcessingFilter).findAny().orElseThrow(IllegalStateException::new);
        filter.setTokenExtractor(tokenExtractor);
        log.info("TokenExtractor change successful");
    }
}
