package com.afs.restfulapi.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    //Todo: renamed companyId -> id, etc.
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer companyId;
    private String companyName;
    @OneToMany(mappedBy = "companyId")
    private List<Employee> employees;

    public Company(){}

    public Company(int companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }

    public Company(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
