package com.fullcycle.subscription.infrastructure.gateway.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fullcycle.subscription.domain.account.AccountId;
import com.fullcycle.subscription.domain.account.idp.UserId;
import com.fullcycle.subscription.domain.person.Address;
import com.fullcycle.subscription.domain.person.Document;
import com.fullcycle.subscription.domain.person.Email;
import com.fullcycle.subscription.domain.person.Name;
import com.fullcycle.subscription.infrastructure.AbstractRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

public class AccountJdbcRepositoryTest extends AbstractRepositoryTest {

  @Test
  public void testAssertDependencies() {
    assertNotNull(accountRepository());
  }

  @Test
  @Sql({"classpath:/sql/accounts/seed-account-johndoe.sql"})
  public void givenPersistedAccount_whenQueriesSuccessfully_shouldReturnIt() {
    // given
    assertEquals(1, countAccounts());

    var expectedId = new AccountId("4625a9f45b2f4fc49c291bfe0781f789");
    var expectedVersion = 1;
    var expectedUserId = new UserId("ee1ea4dc-9e70-45c3-af66-cb8ae426adaf");
    var expectedEmail = new Email("john@gmail.com");
    var expectedName = new Name("John", "Doe");
    var expectedDocument = new Document.Cpf("12312312332");
    var expectedAddress = new Address("12332123", "1", "Casa 1", "BR");

    // when
    var actualAccount = this.accountRepository().accountOfId(expectedId).get();

    // then
    assertEquals(expectedId, actualAccount.id());
    assertEquals(expectedVersion, actualAccount.version());
    assertEquals(expectedUserId, actualAccount.userId());
    assertEquals(expectedEmail, actualAccount.email());
    assertEquals(expectedName, actualAccount.name());
    assertEquals(expectedDocument, actualAccount.document());
    assertEquals(expectedAddress, actualAccount.billingAddress());
  }

}