package com.fullcycle.subscription.infrastructure.gateway.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.fullcycle.subscription.domain.money.Money;
import com.fullcycle.subscription.domain.plan.PlanId;
import com.fullcycle.subscription.infrastructure.AbstractRepositoryTest;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

class PlanJdbcRepositoryTest  extends AbstractRepositoryTest {

  @Test
  public void testAssertDependencies() {
    assertNotNull(planRepository());
  }

  @Test
  @Sql({"classpath:/sql/plans/seed-plan-master.sql"})
  public void givenPersistedPlan_whenQueriesSuccessfully_shouldReturnIt() {
    // given
    Assertions.assertEquals(1, countPlans());

    var expectedId = new PlanId(1L);
    var expectedVersion = 1;
    var expectedName = "Master";
    var expectedDescription = "O plano mais custo benefício";
    var expectedActive = false;
    var expectedPrice = new Money("BRL", 20.0);
    var expectedCreatedAt = Instant.parse("2024-04-28T10:57:11.111Z");
    var expectedUpdatedAt = Instant.parse("2024-04-28T10:58:11.111Z");
    var expectedDeletedAt = Instant.parse("2024-04-28T10:59:11.111Z");

    // when
    var actualPlan = this.planRepository().planOfId(expectedId).get();

    // then
    assertEquals(expectedId, actualPlan.id());
    assertEquals(expectedVersion, actualPlan.version());
    assertEquals(expectedName, actualPlan.name());
    assertEquals(expectedDescription, actualPlan.description());
    assertEquals(expectedActive, actualPlan.active());
    assertEquals(expectedPrice, actualPlan.price());
    assertEquals(expectedCreatedAt, actualPlan.createdAt());
    assertEquals(expectedUpdatedAt, actualPlan.updatedAt());
    assertEquals(expectedDeletedAt, actualPlan.deletedAt());
  }

}