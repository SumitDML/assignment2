package com.assignment.assignment.exception;

public class ConstraintViolationException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    public ConstraintViolationException(final String message)
    {
        super(message);
    }
}