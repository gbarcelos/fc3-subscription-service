package com.fullcycle.subscription.application.account.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fullcycle.subscription.application.account.AddToGroup;
import com.fullcycle.subscription.domain.Fixture;
import com.fullcycle.subscription.domain.UnitTest;
import com.fullcycle.subscription.domain.account.AccountGateway;
import com.fullcycle.subscription.domain.account.idp.GroupId;
import com.fullcycle.subscription.domain.account.idp.IdentityProviderGateway;
import com.fullcycle.subscription.domain.subscription.SubscriptionGateway;
import com.fullcycle.subscription.domain.subscription.SubscriptionId;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class DefaultAddToGroupTest extends UnitTest {

  @Mock
  private AccountGateway accountGateway;

  @Mock
  private IdentityProviderGateway identityProviderGateway;

  @Mock
  private SubscriptionGateway subscriptionGateway;

  @InjectMocks
  private DefaultAddToGroup target;

  @Test
  public void givenTrailSubscription_whenCallsExecute_shouldCallIdentityProvider() {
    // given
    final var john = Fixture.Accounts.john();
    final var expectedGroupId = new GroupId("GROUP-123");
    final var expectedAccountId = john.id();
    final var expectedSubscriptionId = new SubscriptionId("SUB-123");
    final var johnsSubscription = Fixture.Subscriptions.johns();

    Assertions.assertTrue(johnsSubscription.isTrail(),
        "Para esse teste a subscription precisa estar com Trial");

    when(subscriptionGateway.subscriptionOfId(any())).thenReturn(Optional.of(johnsSubscription));
    when(accountGateway.accountOfId(any())).thenReturn(Optional.of(john));
    doNothing().when(identityProviderGateway).addUserToGroup(any(), any());

    // when
    this.target.execute(new AddToGroupTestInput(expectedAccountId.value(), expectedGroupId.value(),
        expectedSubscriptionId.value()));

    // then
    verify(subscriptionGateway, times(1)).subscriptionOfId(eq(expectedSubscriptionId));
    verify(accountGateway, times(1)).accountOfId(eq(john.id()));
    verify(identityProviderGateway, times(1)).addUserToGroup(eq(john.userId()),
        eq(expectedGroupId));
  }

  record AddToGroupTestInput(
      String accountId,
      String groupId,
      String subscriptionId
  ) implements AddToGroup.Input {

  }
}