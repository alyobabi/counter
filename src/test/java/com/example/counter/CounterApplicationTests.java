package com.example.counter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class CounterApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {
        Assertions.assertThat(context).isNotNull();
    }
}
