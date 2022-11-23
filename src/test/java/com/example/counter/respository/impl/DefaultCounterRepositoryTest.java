package com.example.counter.respository.impl;

import java.math.BigInteger;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DefaultCounterRepositoryTest {

    public static final String COUNTER_NAME_1 = "COUNTER_NAME_1";
    public static final String COUNTER_NAME_2 = "COUNTER_NAME_2";
    @Autowired
    private DefaultCounterRepository counterRepository;

    @AfterEach
    public void cleanUp() {
        counterRepository.remove(COUNTER_NAME_1);
        counterRepository.remove(COUNTER_NAME_2);
    }

    @Test
    public void shouldCreate() {
        counterRepository.create(COUNTER_NAME_1);

        assertTrue(counterRepository.getNames().contains(COUNTER_NAME_1));
    }

    @Test
    public void shouldNotCreateIfAlreadyExists() {
        counterRepository.create(COUNTER_NAME_1);

        assertEquals(1, counterRepository.getNames().size());
    }

    @Test
    public void shouldIncrement() {
        counterRepository.create(COUNTER_NAME_1);
        counterRepository.increment(COUNTER_NAME_1);

        assertEquals(BigInteger.ONE, counterRepository.getValue(COUNTER_NAME_1));
    }

    @Test
    public void shouldNotIncrementIfCounterNotExists() {
        counterRepository.increment(COUNTER_NAME_2);

        assertNull(counterRepository.getValue(COUNTER_NAME_2));
    }

    @Test
    public void shouldRemove() {
        counterRepository.create(COUNTER_NAME_1);
        counterRepository.remove(COUNTER_NAME_1);

        assertNull(counterRepository.getValue(COUNTER_NAME_1));
    }

    @Test
    void shouldGetValue() {
        counterRepository.create(COUNTER_NAME_1);
        counterRepository.increment(COUNTER_NAME_1);

        assertEquals(BigInteger.ONE, counterRepository.getValue(COUNTER_NAME_1));
    }

    @Test
    void getAllCounterNames() {
        counterRepository.create(COUNTER_NAME_1);
        counterRepository.create(COUNTER_NAME_2);

        assertEquals(2, counterRepository.getNames().size());
        assertTrue(counterRepository.getNames().contains(COUNTER_NAME_1));
        assertTrue(counterRepository.getNames().contains(COUNTER_NAME_2));
    }

    @Test
    void getAllCounterNamesAsEmptySetWhenNoCounters() {
        assertTrue(counterRepository.getNames().isEmpty());
    }

    @Test
    void getCounterValues() {
        counterRepository.create(COUNTER_NAME_1);
        counterRepository.increment(COUNTER_NAME_1);
        counterRepository.create(COUNTER_NAME_2);

        val result = counterRepository.getValues();
        val resultIterator = result.iterator();

        assertEquals(2, result.size());
        assertEquals(BigInteger.ONE, resultIterator.next());
        assertEquals(BigInteger.ZERO, resultIterator.next());
    }

    @Test
    void returnEmptyCollectionWhenNoCounters() {
        assertEquals(0, counterRepository.getValues().size());
    }
}