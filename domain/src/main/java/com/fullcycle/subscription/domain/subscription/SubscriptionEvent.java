package com.fullcycle.subscription.domain.subscription;

import com.fullcycle.subscription.domain.DomainEvent;

public sealed interface SubscriptionEvent extends DomainEvent
    permits SubscriptionCanceled, SubscriptionCreated, SubscriptionRenewed, SubscriptionIncomplete {

  String TYPE = "Subscription";

  String subscriptionId();

  @Override
  default String aggregateId() {
    return subscriptionId();
  }

  @Override
  default String aggregateType() {
    return TYPE;
  }
}
