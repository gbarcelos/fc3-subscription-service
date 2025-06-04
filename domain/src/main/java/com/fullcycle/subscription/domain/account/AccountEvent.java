package com.fullcycle.subscription.domain.account;

import com.fullcycle.subscription.domain.DomainEvent;
import com.fullcycle.subscription.domain.utils.InstantUtils;

import java.time.Instant;

public sealed interface AccountEvent extends DomainEvent permits AccountCreated {

  String TYPE = "Account";

  String accountId();

  @Override
  default String aggregateId() {
    return accountId();
  }

  @Override
  default String aggregateType() {
    return TYPE;
  }
}
