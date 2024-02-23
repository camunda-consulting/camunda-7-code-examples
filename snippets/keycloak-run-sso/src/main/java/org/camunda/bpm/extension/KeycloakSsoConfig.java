package org.camunda.bpm.extension;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Import({
    WebAppSecurityConfig.class
})
@EnableWebSecurity
public class KeycloakSsoConfig {}
