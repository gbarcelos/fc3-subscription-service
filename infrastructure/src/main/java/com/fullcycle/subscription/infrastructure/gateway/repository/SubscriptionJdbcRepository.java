package com.fullcycle.subscription.infrastructure.gateway.repository;

import com.fullcycle.subscription.domain.account.AccountId;
import com.fullcycle.subscription.domain.subscription.Subscription;
import com.fullcycle.subscription.domain.subscription.SubscriptionGateway;
import com.fullcycle.subscription.domain.subscription.SubscriptionId;
import com.fullcycle.subscription.infrastructure.jdbc.DatabaseClient;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionJdbcRepository implements SubscriptionGateway {

  private final DatabaseClient database;
  private final EventJdbcRepository eventJdbcRepository;

  public SubscriptionJdbcRepository(final DatabaseClient databaseClient, final EventJdbcRepository eventJdbcRepository) {
    this.database = Objects.requireNonNull(databaseClient);
    this.eventJdbcRepository = Objects.requireNonNull(eventJdbcRepository);
  }

  @Override
  public Optional<Subscription> latestSubscriptionOfAccount(AccountId accountId) {
    return Optional.empty();
  }

  @Override
  public Optional<Subscription> subscriptionOfId(SubscriptionId subscriptionId) {
    return Optional.empty();
  }

  @Override
  public Subscription save(Subscription subscription) {
    return null;
  }

  @Override
  public SubscriptionId nextId() {
    return null;
  }
}
