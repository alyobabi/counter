package com.example.counter.service.impl;

import com.example.counter.exception.NoExistingCounterException;
import com.example.counter.respository.CounterRepository;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DefaultCounterServiceTest {

    public static final String COUNTER_NAME = "counterName";
    @Autowired
    private DefaultCounterService counterService;

    @MockBean
    private CounterRepository counterRepository;

    @Test
    public void shouldCreateCounter() {
        when(counterRepository.getNames()).thenReturn(Collections.emptySet());

        val isCounterCreated = counterService.create(COUNTER_NAME);

        verify(counterRepository, atMostOnce()).create(COUNTER_NAME);
        assertTrue(isCounterCreated);
    }

    @Test
    public void shouldNotCreateCounterIfAlreadyExists() {
        when(counterRepository.getNames()).thenReturn(Collections.singleton(COUNTER_NAME));

        val isCounterCreated = counterService.create(COUNTER_NAME);

        verify(counterRepository, never()).create(COUNTER_NAME);
        assertFalse(isCounterCreated);
    }

    @Test
    @SneakyThrows
    public void shouldIncrementCounter() {
        when(counterRepository.getNames()).thenReturn(Collections.singleton(COUNTER_NAME));

        counterService.increment(COUNTER_NAME);

        verify(counterRepository, atMostOnce()).increment(COUNTER_NAME);
    }

    @Test
    public void shouldNotIncrementCounterIfNoExists() {
        when(counterRepository.getNames()).thenReturn(Collections.emptySet());

        assertThrows(NoExistingCounterException.class,
                () -> counterService.increment(COUNTER_NAME));
        verify(counterRepository, never()).increment(COUNTER_NAME);
    }

    @Test
    @SneakyThrows
    public void shouldGetCounterValue() {
        when(counterRepository.getNames()).thenReturn(Collections.singleton(COUNTER_NAME));
        when(counterRepository.getValue(COUNTER_NAME)).thenReturn(BigInteger.TEN);

        assertEquals(BigInteger.TEN, counterService.getCounterValue(COUNTER_NAME));
        verify(counterRepository, atMostOnce()).getValue(COUNTER_NAME);
    }

    @Test
    public void shouldNotIGetCounterIfNoExists() {
        when(counterRepository.getNames()).thenReturn(Collections.emptySet());

        assertThrows(NoExistingCounterException.class,
                () -> counterService.getCounterValue(COUNTER_NAME));
        verify(counterRepository, never()).getValue(COUNTER_NAME);
    }

    @Test
    @SneakyThrows
    public void shouldDeleteCounter() {
        when(counterRepository.getNames()).thenReturn(Collections.singleton(COUNTER_NAME));

        counterService.deleteCounter(COUNTER_NAME);

        verify(counterRepository, atMostOnce()).remove(COUNTER_NAME);
    }

    @Test
    public void shouldNotDeleteCounterIfNoExists() {
        when(counterRepository.getNames()).thenReturn(Collections.emptySet());

        assertThrows(NoExistingCounterException.class,
                () -> counterService.deleteCounter(COUNTER_NAME));
        verify(counterRepository, never()).remove(COUNTER_NAME);
    }

    @Test
    public void shouldGetSumOfAllCounters() {
        when(counterRepository.getValues()).thenReturn(List.of(BigInteger.TWO, BigInteger.TWO, BigInteger.TWO));

        assertEquals(new BigInteger("6"), counterService.getSumOfAllCounters());
        verify(counterRepository, atMostOnce()).getNames();
    }

    @Test
    public void shouldGetNamesOfAllCounters() {
        when(counterRepository.getNames()).thenReturn(Collections.singleton(COUNTER_NAME));

        assertEquals(1, counterService.getNamesOfAllCounters().size());
        assertTrue(counterService.getNamesOfAllCounters().contains(COUNTER_NAME));
        verify(counterRepository, times(2)).getNames();
    }
}