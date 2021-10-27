package com.afs.restfulapi.controllerTest;

import com.afs.restfulapi.entity.Employee;
import com.afs.restfulapi.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    ObjectMapper jsonMapper;

    @BeforeEach
    void setUp(){
        repo.deleteAll();
    }

    @Test
    void should_return_all_employees_when_get_given_two_employees() throws Exception {
        //given
        Employee employee1 = new Employee("Tommy", 20, "M", 123);
        Employee employee2 = new Employee("John", 25, "M", 12345);
        repo.createEmployee(employee1);
        repo.createEmployee(employee2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Tommy"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[0].salary").value(123))

                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("John"))
                .andExpect(jsonPath("$[1].age").value(25))
                .andExpect(jsonPath("$[1].gender").value("M"))
                .andExpect(jsonPath("$[1].salary").value(12345));
    }

    // todo
    @Test
    void should_return_correct_employee_when_get_given_employee_id() throws Exception{
        //given
        Employee employee1 = new Employee("Tommy", 20, "M", 123);
        Employee employee2 = new Employee("John", 25, "M", 12345);
        repo.createEmployee(employee1);
        repo.createEmployee(employee2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/2"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.salary").value(12345));
    }

    @Test
    void should_return_male_employees_when_find_by_male_employees_given_1_male_employee() throws Exception {
        //given
        Employee employee1 = new Employee("Peter", 20, "M", 123);
        Employee employee2 = new Employee("Jeany", 25, "F", 12345);
        repo.createEmployee(employee1);
        repo.createEmployee(employee2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees?gender=M"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Peter"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[0].salary").value(123));
    }

    @Test
    void should_return_paged_employees_when_get_employees_when_get_page_given_page_and_page_size() throws Exception {
        //given
        Employee employee1 = new Employee("Peter", 20, "M", 123);
        Employee employee2 = new Employee("Jeany", 25, "F", 12345);
        Employee employee3 = new Employee("Tommy", 25, "M", 123456);
        repo.createEmployee(employee1);
        repo.createEmployee(employee2);
        repo.createEmployee(employee3);

        //when
        ResultActions resultActions = mockMvc.perform(get("employees?page=1&pageSize=2"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee3.getId()))
                .andExpect(jsonPath("$[0].name").value(employee3.getName()))
                .andExpect(jsonPath("$[0].age").value(employee3.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee3.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee3.getSalary()))
                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void should_create_employee_when_post_given_an_employee() throws Exception {
        Employee employee = new Employee("Peter", 20, "M", 123);
        String url = "/employees";

        ResultActions result = mockMvc.perform(
                post(url)
                        .contentType(APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(employee))
        );

        result
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.age").value(employee.getAge()))
                .andExpect(jsonPath("$.gender").value(employee.getGender()))
                .andExpect(jsonPath("$.salary").value(employee.getSalary()))
        ;
    }

    @Test
    void should_update_employee_when_put_given_an_updated_employee() throws Exception {
        Employee employee = new Employee("Peter", 20, "M", 123);
        Employee updated = repo.createEmployee(employee);
        updated.setAge(33);
        updated.setSalary(12345);
        String url = String.format("/employees/%d", updated.getId());

        ResultActions result = mockMvc.perform(
                put(url)
                        .contentType(APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(updated))
        );

        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updated.getId()))
                .andExpect(jsonPath("$.name").value(updated.getName()))
                .andExpect(jsonPath("$.age").value(updated.getAge()))
                .andExpect(jsonPath("$.gender").value(updated.getGender()))
                .andExpect(jsonPath("$.salary").value(updated.getSalary()))
        ;
    }

    @Test
    void should_delete_employee_when_given_an_employee_id() throws Exception {
        Employee employee = new Employee("Peter", 20, "M", 123);
        Employee saved = repo.createEmployee(employee);
        String url = String.format("/employees/%d", saved.getId());

        assertEquals(1, repo.findAll().size());
        ResultActions result = mockMvc.perform(delete(url));
        assertEquals(0, repo.findAll().size());

        result
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist())
        ;
    }
}