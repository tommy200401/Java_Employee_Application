package com.afs.restfulapi.serviceTest;

import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.repository.EmployeeRepository;
import com.afs.restfulapi.service.EmployeeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void should_return_all_employees_when_find_all_given_2_employees(){
        //given
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
        Employee employee = new Employee(1, "John", 20, "M", 12345);
        when(employeeRepository.findById(1)).thenReturn(employee);

        //when
        Employee actual = employeeService.findById(1);

        //then
        assertSame(employee, actual);
    }

    @Test
    void should_return_employees_of_same_gender_when_find_by_gender_given_gender() {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", 20, "M", 12345),
                new Employee(2, "Peter", 25, "M", 123456),
                new Employee(3, "Mary", 30, "F", 1234567));
        List<Employee> expectedEmployees = employees.stream().filter(e -> e.getGender().equals("M")).collect(Collectors.toList());
        when(employeeRepository.findByGender("M")).thenReturn(expectedEmployees);

        //when
        List<Employee> actual = employeeService.findByGender("M");

        //then
        assertEquals(expectedEmployees, actual);

    }

    @Test
    void should_return_pages_of_employees_when_find_by_page_and_pageSize_given_page(){
        //given
        List<Employee> employees = Arrays.asList(
                new Employee("John", 20, "M", 12345),
                new Employee("Peter", 25, "M", 123456));
        Pageable pageable = PageRequest.of(0, 2);
        PageImpl<Employee> content = new PageImpl<Employee>(employees, pageable, employees.size());
        when(employeeRepository.findPagingEmployees(pageable)).thenReturn(content);
        //when
        PageImpl<Employee> actual = employeeService.findPagingEmployees(pageable);
        //then
        assertEquals(content, actual);
    }

    @Test
    void should_return_new_employee_when_add_employee_given_employee(){
        //given
        Employee employee1 = new Employee("John", 20, "M", 12345);
        Employee employee2 = new Employee(2,"John", 20, "M", 12345);
        when(employeeRepository.createEmployee(employee1)).thenReturn(employee2);

        //when
        Employee actual = employeeService.createEmployee(employee1);

        //then
        assertNotNull(actual.getId());
        assertEquals(employee1.getName(), actual.getName());
        assertEquals(employee1.getAge(), actual.getAge());
        assertEquals(employee1.getGender(), actual.getGender());
        assertEquals(employee1.getSalary(), actual.getSalary());
    }



    @Test
    void should_return_updated_employee_when_edit_employee_given_employee_update_info(){
        //given
        Employee employee = new Employee("John", 20, "M", 12345);
        employeeRepository.createEmployee(employee);
        Employee employeeUpdated = new Employee("John", 21, "M", 99999);
        when(employeeRepository.updateEmployee(1, employeeUpdated)).thenReturn(employeeUpdated);

        //when
        Employee actual = employeeService.updateEmployee(1, employeeUpdated);

        //then
        assertEquals(employeeUpdated, actual);

    }
}
