package com.afs.restfulapi;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;

}
