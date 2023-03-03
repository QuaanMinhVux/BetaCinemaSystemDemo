package com.betacinema.demo.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class PayPalConfig {
    @Value("AQ0YpWjbc4HJ0nH4q3n0IQFPbChIseKYMNWyQfpwTrhqzaddN_dsD9xsicNJJWH3dsL1tzaLg3VHZJWT")
    private String clientId;
    @Value("ELoPzw8bNsCari0bA4DNr_wgd5cIXaSg6mWS571z2rClTp-7y0uvC0McEW1xo7KzE3OYZ7mws1UQ6qOP")
    private String clientSecret;

    @Value("sandbox")
    private String mode;

    @Bean
    public Map<String, String> paypalSdkConfig() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put("mode", mode);
        return configMap;
    }

    @Bean
    public OAuthTokenCredential oAuthTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
        context.setConfigurationMap(paypalSdkConfig());
        return context;
    }
}
