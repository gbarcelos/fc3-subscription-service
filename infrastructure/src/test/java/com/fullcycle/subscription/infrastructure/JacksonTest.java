package com.fullcycle.subscription.infrastructure;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test-integration")
@JsonTest
@Tag("integrationTest")
@ExtendWith(TimeZoneSetup.class)
public @interface JacksonTest {
}
