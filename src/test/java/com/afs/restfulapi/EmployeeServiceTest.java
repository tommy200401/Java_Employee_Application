package com.afs.restfulapi;

import org.mockito.Mockito;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    @Test
    void should_return_all_employees_when_find_all_given_2_employees(){
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 20, "M", 12345),
                new Employee(2, "Peter", 25, "M", 123456));
        when(employeeRepository.findAll()).thenReturn(employees);

        //when
        List<Employee> actual = employeeService.findAll();

        //then
        assertEquals(employees, actual);
    }

    @Test
    void should_return_an_employee_when_find_by_id_given_id(){
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee(1, "John", 20, "M", 12345);
        when(employeeRepository.findById(1)).thenReturn(employee);

        //when
        Employee actual = employeeService.findById(1);

        //then
        assertSame(employee, actual);
    }

    @Test
    void should_return_employees_when_find_by_gender_given_gender() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 20, "M", 12345),
                new Employee(2, "Peter", 25, "M", 123456),
                new Employee(3, "Mary", 30, "F", 1234567));
        when(employeeRepository.findByGender("M")).thenReturn(employees);

        //when
        List<Employee> actual = employeeService.findByGender("M");

        //then
        assertEquals(employees, actual);

    }
}
