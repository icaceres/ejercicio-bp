package com.pichincha.client.exception;

public class ResourceNotFoundException  extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
