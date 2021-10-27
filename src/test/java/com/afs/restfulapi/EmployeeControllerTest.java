package com.afs.restfulapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_find_all_given_two_employees() throws Exception {
        //given
        Employee employee1 = new Employee("Tommy", 20, "M", 123);
        Employee employee2 = new Employee("John", 25, "M", 12345);
        employeeRepository.createEmployee(employee1);
        employeeRepository.createEmployee(employee2);

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

    @Test
    void should_return_male_employees_when_find_by_male_employees_given_1_male_employee() throws Exception {
        //given
        Employee employee1 = new Employee("Tommy", 20, "M", 123);
        Employee employee2 = new Employee("Jeany", 25, "F", 12345);
        employeeRepository.createEmployee(employee1);
        employeeRepository.createEmployee(employee2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees?gender=M"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("Tommy"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[0].salary").value(123));
    }
}