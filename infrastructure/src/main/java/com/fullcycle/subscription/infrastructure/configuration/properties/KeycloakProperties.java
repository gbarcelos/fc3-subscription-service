package com.fullcycle.subscription.infrastructure.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakProperties {
  private String clientId;
  private String clientSecret;
  private String tokenUri;
  private String adminUsersUri;

  public String clientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String clientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String tokenUri() {
    return tokenUri;
  }

  public void setTokenUri(String tokenUri) {
    this.tokenUri = tokenUri;
  }

  public void setAdminUsersUri(String adminUsersUri) {
    this.adminUsersUri = adminUsersUri;
  }

  public String adminUsersUri() {
    return adminUsersUri;
  }
}
