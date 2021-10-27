package com.afs.restfulapi;

public class Employee {

    private Integer id;
    private Integer age;
    private String gender;
    private Integer salary;
    private String name;

    //todo: delete ID
    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }


    public void setId(Integer id) {
        this.id = id;
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


}
