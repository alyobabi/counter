package com.example.counter.controller;

import com.example.counter.exception.NoExistingCounterException;
import com.example.counter.service.CounterService;
import java.math.BigInteger;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counter")
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createCounter(@PathVariable @NotBlank String name) {
        if (counterService.create(name)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(String.format("Counter '%s' already exists", name), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BigInteger incrementCounter(@PathVariable @NotBlank String name) throws NoExistingCounterException {
        return counterService.increment(name);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BigInteger getCounter(@PathVariable @NotBlank String name) throws NoExistingCounterException {
        return counterService.getCounterValue(name);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCounter(@PathVariable @NotBlank String name) throws NoExistingCounterException {
        counterService.deleteCounter(name);
    }

    @GetMapping("/all/sum")
    public BigInteger getSum() {
        return counterService.getSumOfAllCounters();
    }

    @GetMapping("/all/names")
    public Set<String> getNamesOfAllCounters() {
        return counterService.getNamesOfAllCounters();
    }
}

