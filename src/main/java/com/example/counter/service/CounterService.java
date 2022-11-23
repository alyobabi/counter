package com.example.counter.service;

import com.example.counter.exception.NoExistingCounterException;
import java.math.BigInteger;
import java.util.Set;

public interface CounterService {

    /**
     * Create new counter with name and value 0
     * @param counterName unique name for the counter
     * @return true if created otherwise false
     */
    boolean create(String counterName);

    /**
     * Increment value of the counter
     * @param counterName unique name for the counter
     * @return result of incrementing
     * @throws NoExistingCounterException if counter with name doesn't exist
     */
    BigInteger increment(String counterName) throws NoExistingCounterException;

    /**
     * Get value of the counter
     * @param counterName unique name for counter
     * @return value
     * @throws NoExistingCounterException if counter with name doesn't exist
     */
    BigInteger getCounterValue(String counterName) throws NoExistingCounterException;

    /**
     * Deleted the counter
     * @param counterName unique name for counter
     * @throws NoExistingCounterException if counter with name doesn't exist
     */
    void deleteCounter(String counterName) throws NoExistingCounterException;

    /**
     * Get values' sum of all counters
     * @return sum
     */
    BigInteger getSumOfAllCounters();

    /**
     * Get names of all counters
     * @return set of string
     */
    Set<String> getNamesOfAllCounters();
}
