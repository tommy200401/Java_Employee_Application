package com.afs.restfulapi;

import java.util.List;

public class Company {
    private int id;
    private String companyName;
    private String basicInfo;
    private List<String> employees;

    public Company(int id, String companyName, String basicInfo, List<String> employees) {
        this.id = id;
        this.companyName = companyName;
        this.basicInfo = basicInfo;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}
