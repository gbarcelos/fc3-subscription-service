package com.fullcycle.subscription.infrastructure.gateway.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.fullcycle.subscription.infrastructure.AbstractRepositoryTest;
import org.junit.jupiter.api.Test;

class SubscriptionJdbcRepositoryTest  extends AbstractRepositoryTest {

  @Test
  public void testAssertDependencies() {
    assertNotNull(subscriptionRepository());
  }

}