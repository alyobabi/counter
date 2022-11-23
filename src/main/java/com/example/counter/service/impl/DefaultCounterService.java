package com.example.counter.service.impl;

import com.example.counter.exception.NoExistingCounterException;
import com.example.counter.respository.CounterRepository;
import com.example.counter.service.CounterService;
import java.math.BigInteger;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCounterService implements CounterService {

    private final CounterRepository counterRepository;

    @Override
    public boolean create(String counterName) {
        if (isCounterExists(counterName)) {
            return false;
        } else {
            counterRepository.create(counterName);
            return true;
        }
    }

    @Override
    public BigInteger increment(String counterName) throws NoExistingCounterException {
        ifCounterNotExistsThrowException(counterName);
        return counterRepository.increment(counterName);
    }

    @Override
    public BigInteger getCounterValue(String counterName) throws NoExistingCounterException {
        ifCounterNotExistsThrowException(counterName);
        return counterRepository.getValue(counterName);
    }

    @Override
    public void deleteCounter(String counterName) throws NoExistingCounterException {
        ifCounterNotExistsThrowException(counterName);
        counterRepository.remove(counterName);
    }

    @Override
    public BigInteger getSumOfAllCounters() {
        return counterRepository.getValues()
                .stream()
                .reduce(BigInteger::add)
                .orElse(BigInteger.ZERO);
    }

    @Override
    public Set<String> getNamesOfAllCounters() {
        return counterRepository.getNames();
    }

    private boolean isCounterExists(String counterName) {
        return counterRepository.getNames().contains(counterName);
    }

    private void ifCounterNotExistsThrowException(String counterName) throws NoExistingCounterException {
        if (!isCounterExists(counterName)) {
            throw new NoExistingCounterException(String.format("Counter %s doesn't exist", counterName));
        }
    }
}
