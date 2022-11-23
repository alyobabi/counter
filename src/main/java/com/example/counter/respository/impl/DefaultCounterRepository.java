package com.example.counter.respository.impl;

import com.example.counter.respository.CounterRepository;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultCounterRepository implements CounterRepository {

    private final Map<String, BigInteger> counterMap = new ConcurrentHashMap<>();

    @Override
    public void create(String counterName) {
        counterMap.putIfAbsent(counterName, BigInteger.ZERO);
    }

    @Override
    public BigInteger increment(String counterName) {
        return counterMap.computeIfPresent(counterName,
                (key, value) -> counterMap.get(counterName).add(BigInteger.ONE));
    }

    @Override
    public void remove(String counterName) {
        counterMap.remove(counterName);
    }

    @Override
    public BigInteger getValue(String counterName) {
        return counterMap.get(counterName);
    }

    @Override
    public Set<String> getNames() {
        return counterMap.keySet();
    }

    @Override
    public Collection<BigInteger> getValues() {
        return counterMap.values();
    }
}
