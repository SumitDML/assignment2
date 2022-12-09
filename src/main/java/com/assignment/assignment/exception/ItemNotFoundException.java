package com.assignment.assignment.exception;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ItemNotFoundException(final String message) {
        super(message);
    }
}