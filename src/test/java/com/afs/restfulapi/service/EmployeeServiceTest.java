package com.afs.restfulapi.service;

import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

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
        Employee employee = new Employee("John", 20, "M", 12345);
        when(employeeRepository.findById(1)).thenReturn(java.util.Optional.of(employee));

        //when
        Employee actual = employeeService.findById(1);

        //then
        assertSame(employee, actual);
    }

    @Test
    void should_return_employees_of_same_gender_when_find_by_gender_given_gender() {
        //given
        List<Employee> employees = Arrays.asList(
                new Employee("John", 20, "M", 12345),
                new Employee("Peter", 25, "M", 123456),
                new Employee("Mary", 30, "F", 1234567));
        List<Employee> expectedEmployees = employees.stream().filter(e -> e.getGender().equals("M")).collect(Collectors.toList());
        when(employeeRepository.findAllByGender("M")).thenReturn(expectedEmployees);

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
        when(employeeRepository.findAll(pageable)).thenReturn(new PageImpl<>(employees));
        //when
        List<Employee> actual = employeeService.findPagingEmployees(0,2);
        //then
        assertEquals(employees, actual);
    }

    @Test
    void should_return_new_employee_when_add_employee_given_employee(){
        //given
        Employee employee1 = new Employee("John", 20, "M", 12345);
        Employee employee2 = new Employee(2,"John", 20, "M", 12345);
        when(employeeRepository.save(employee1)).thenReturn(employee2);

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
        employeeRepository.save(employee);
        Employee employeeUpdated = new Employee("John", 21, "M", 99999);
        when(employeeRepository.save(employeeUpdated)).thenReturn(employeeUpdated);

        //when
        Employee actual = employeeRepository.save(employeeUpdated);

        //then
        assertEquals(employeeUpdated, actual);

    }


    @Test
    void should_delete_employee_when_delete_employee_given_employee_id(){
        //given
        willDoNothing().given(employeeRepository).deleteById(anyInt());
        employeeService.deleteEmployee(1);
        //then
        verify(employeeRepository).deleteById(anyInt());
        verifyNoMoreInteractions(employeeRepository);
    }
}
