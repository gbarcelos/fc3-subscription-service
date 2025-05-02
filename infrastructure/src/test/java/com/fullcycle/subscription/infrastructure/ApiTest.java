package com.fullcycle.subscription.infrastructure;

import com.fullcycle.subscription.infrastructure.authentication.principal.CodeflixAuthentication;
import com.fullcycle.subscription.infrastructure.authentication.principal.CodeflixUser;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

public interface ApiTest {

  static RequestPostProcessor admin() {
    return admin("123");
  }

  static RequestPostProcessor admin(final String accountId) {
    Jwt.Builder jwtBuilder = Jwt.withTokenValue("token")
        .header("alg", "none")
        .claim(JwtClaimNames.SUB, "user")
        .claim("scope", "read");

    return SecurityMockMvcRequestPostProcessors.authentication(new CodeflixAuthentication(
        jwtBuilder.build(),
        new CodeflixUser("Test user", "KEYCLOAK123", accountId),
        List.of(new SimpleGrantedAuthority("ROLE_SUBSCRIPTION_ADMIN"))
    ));
  }
}
