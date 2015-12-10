package com.itguru.FirstTask.analyzer.exceptions;

public class IncorrectCommandException extends Exception {
    IncorrectCommandException() {}
    public IncorrectCommandException(String errorMessage) {
        super(errorMessage);
    }
}