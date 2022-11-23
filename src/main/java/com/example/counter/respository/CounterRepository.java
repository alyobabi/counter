package com.example.counter.respository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

public interface CounterRepository {

    /**
     * Create counter with counterName and default value is zero
     * @param counterName unique name for counter
     */
    void create(String counterName);

    /**
     * Increment counter value on 1
     * @param counterName unique name for the counter
     * @return value of the counter
     */
    BigInteger increment(String counterName);

    /**
     * Remove counter by name
     * @param counterName unique name for the counter
     */
    void remove(String counterName);

    /**
     * Get value for counter by name
     * @param counterName unique name for the counter
     */
    BigInteger getValue(String counterName);

    /**
     * Get names for all counters, empty set if no counter exist
     * @return set of strings
     */
    Set<String> getNames();

    /**
     * Get values for all counters, empty collection if no counter exist
     * @return collection of integers
     */
    Collection<BigInteger> getValues();
}
