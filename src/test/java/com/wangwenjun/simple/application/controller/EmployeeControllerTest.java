package com.wangwenjun.simple.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangwenjun.simple.application.SimpleApplication;
import com.wangwenjun.simple.application.domain.Employee;
import java.sql.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SimpleApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.wangwenjun.simple.application")
@ActiveProfiles("test")
@WebAppConfiguration
public class EmployeeControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void getAllEmployeeAPI() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .get("/employee")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", containsString("Alex")));
  }

  @Test
  public void getSpecificEmployeeAPI() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .get("/employee/1")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", containsString("Alex")));
  }

  @Test
  public void getSpecificEmployeeNotExistAPI() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .get("/employee/1000")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void deleteSpecificEmployeeAPI() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .delete("/employee/3")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  public void deleteSpecificEmployeeNotExistAPI() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .delete("/employee/1000")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void updateSpecificEmployeeAPI() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Employee employee = new Employee("Alex Wang", "China", new Date(System.currentTimeMillis()),
            new Date(System.currentTimeMillis()), "Alex Remark");
    employee.setId(1);
    mvc.perform(MockMvcRequestBuilders
            .put("/employee")
            .content(mapper.writeValueAsString(employee))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Alex Wang")));
  }

  @Test
  public void updateSpecificEmployeeNotExistAPI() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Employee employee = new Employee("Alex Wang", "China", new Date(System.currentTimeMillis()),
            new Date(System.currentTimeMillis()), "Alex Remark");
    employee.setId(1000);
    mvc.perform(MockMvcRequestBuilders
            .put("/employee")
            .content(mapper.writeValueAsString(employee))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().is4xxClientError());
  }

  @Test
  public void createSpecificEmployeeAPI() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Employee employee = new Employee("Andy Liu", "China", new Date(System.currentTimeMillis()),
            new Date(System.currentTimeMillis()), "Andy Liu Remark");
    mvc.perform(MockMvcRequestBuilders
            .post("/employee")
            .content(mapper.writeValueAsString(employee))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", equalTo("Andy Liu")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", greaterThan(3)));
  }
}