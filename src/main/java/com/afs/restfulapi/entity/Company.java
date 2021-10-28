package com.afs.restfulapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String companyName;
    private String basicInfo;
    private List<String> employees;

    //todo: update <String> to <employee>
    //todo: delete ID
    public Company(int id, String companyName, String basicInfo, List<String> employees) {
        this.id = id;
        this.companyName = companyName;
        this.basicInfo = basicInfo;
        this.employees = employees;
    }

    public int getId() {
        return id;
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

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}
