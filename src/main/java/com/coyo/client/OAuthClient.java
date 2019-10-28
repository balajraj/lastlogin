package com.coyo.client;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class OAuthClient {

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    protected ClientCredentialsResourceDetails oAuthDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        return details;
    }


    @Bean
    public RestTemplate restTemplate() {

        DefaultAccessTokenRequest defaultAccessTokenRequest = new DefaultAccessTokenRequest();
        OAuth2RestTemplate restTemplate =
                new OAuth2RestTemplate(
                        oAuthDetails(), new DefaultOAuth2ClientContext(defaultAccessTokenRequest));
        ClientCredentialsAccessTokenProvider clientCredentialsAccessTokenProvider =
                new ClientCredentialsAccessTokenProvider();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        restTemplate.setAccessTokenProvider(clientCredentialsAccessTokenProvider);
        return restTemplate;

    }

}
