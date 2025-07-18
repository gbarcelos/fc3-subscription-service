package com.fullcycle.subscription.infrastructure.authentication.clientcredentials;

import com.fullcycle.subscription.infrastructure.configuration.properties.KeycloakProperties;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.springframework.stereotype.Component;

@Component
public class ClientCredentialsManager implements GetClientCredentials, RefreshClientCredentials {

  private static final AtomicReferenceFieldUpdater<ClientCredentialsManager, ClientCredentials> UPDATER
      = AtomicReferenceFieldUpdater.newUpdater(ClientCredentialsManager.class,
      ClientCredentials.class, "credentials");

  private volatile ClientCredentials credentials;

  private final AuthenticationGateway authenticationGateway;
  private final KeycloakProperties keycloakProperties;

  public ClientCredentialsManager(
      final AuthenticationGateway authenticationGateway,
      final KeycloakProperties keycloakProperties
  ) {
    this.authenticationGateway = Objects.requireNonNull(authenticationGateway);
    this.keycloakProperties = Objects.requireNonNull(keycloakProperties);
  }

  @Override
  public String retrieve() {
    return this.credentials.accessToken;
  }

  @Override
  public void refresh() {
    final var result = this.credentials == null ? login() : refreshToken();
    UPDATER.set(this,
        new ClientCredentials(clientId(), result.accessToken(), result.refreshToken()));
  }

  private AuthenticationGateway.AuthenticationResult login() {
    return this.authenticationGateway.login(
        new AuthenticationGateway.ClientCredentialsInput(clientId(), clientSecret()));
  }

  private AuthenticationGateway.AuthenticationResult refreshToken() {
    try {
      return this.authenticationGateway.refresh(
          new AuthenticationGateway.RefreshTokenInput(clientId(), clientSecret(),
              this.credentials.refreshToken()));
    } catch (RuntimeException ex) {
      return this.login();
    }
  }

  private String clientId() {
    return this.keycloakProperties.clientId();
  }

  private String clientSecret() {
    return this.keycloakProperties.clientSecret();
  }

  record ClientCredentials(String clientId, String accessToken, String refreshToken) {

  }
}
