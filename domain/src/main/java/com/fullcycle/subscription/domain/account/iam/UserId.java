package com.fullcycle.subscription.domain.account.iam;

import com.fullcycle.subscription.domain.Identifier;

public record UserId(String value) implements Identifier {

  public UserId {
    this.assertArgumentNotEmpty(value, "'userId' should not be empty");
  }
}
