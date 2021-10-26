package com.afs.restfulapi;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Company not found.");
    }
}
