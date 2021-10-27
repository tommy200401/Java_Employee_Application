package com.afs.restfulapi;

public class Employee {
    public void setId(Integer id) {
        this.id = id;
    }

    //todo: delete ID
    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
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
    private Integer age;
    private final String gender;
    private Integer salary;

}
