package com.afs.restfulapi;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Employee not found.");
    }
}
