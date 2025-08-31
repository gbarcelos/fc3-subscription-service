package com.fullcycle.subscription.infrastructure.mediator;

import com.fullcycle.subscription.domain.DomainEvent;
import com.fullcycle.subscription.domain.Fixture;
import com.fullcycle.subscription.domain.Fixture.Accounts;
import com.fullcycle.subscription.domain.UnitTest;
import com.fullcycle.subscription.domain.account.AccountCreated;
import com.fullcycle.subscription.infrastructure.gateway.repository.EventJdbcRepository;
import com.fullcycle.subscription.infrastructure.observer.Publisher;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;

class EventMediatorTest extends UnitTest {

  @Mock
  private EventJdbcRepository eventRepository;

  @Mock
  private Publisher<DomainEvent> domainEventPublisher;

  @InjectMocks
  private EventMediator eventMediator;

  @Test
  public void givenEvent_whenCallsMediate_shouldFinishOk() {
    // given
    var expectedEventId = 123L;

    AccountCreated accountCreated = new AccountCreated(Accounts.john());
    when(eventRepository.eventOfIdAndUnprocessed(expectedEventId))
        .thenReturn(Optional.of(accountCreated));

    when(domainEventPublisher.publish(accountCreated))
        .thenReturn(true);

    doNothing().when(eventRepository).markAsProcessed(expectedEventId);

    // when
    eventMediator.mediate(expectedEventId);

    // then
    verify(eventRepository, times(1)).markAsProcessed(expectedEventId);
  }
}