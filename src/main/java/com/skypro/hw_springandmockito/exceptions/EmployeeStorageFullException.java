package com.skypro.hw_springandmockito.exceptions;

public class EmployeeStorageFullException extends RuntimeException {
    public EmployeeStorageFullException(String message) {
        super(message);
    }
}