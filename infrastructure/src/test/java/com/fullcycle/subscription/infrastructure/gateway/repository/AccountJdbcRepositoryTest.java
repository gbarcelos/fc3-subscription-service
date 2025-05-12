package com.fullcycle.subscription.infrastructure.gateway.repository;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fullcycle.subscription.infrastructure.AbstractRepositoryTest;
import org.junit.jupiter.api.Test;

public class AccountJdbcRepositoryTest extends AbstractRepositoryTest {

  @Test
  public void testAssertDependencies() {
    assertNotNull(accountRepository());
  }

}