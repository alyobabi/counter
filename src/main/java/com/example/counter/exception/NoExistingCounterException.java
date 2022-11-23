package com.example.counter.exception;

public class NoExistingCounterException extends Exception {
    public NoExistingCounterException(String message) {
        super(message);
    }
}
