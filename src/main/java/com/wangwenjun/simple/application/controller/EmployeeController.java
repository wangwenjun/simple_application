package com.wangwenjun.simple.application.controller;

import com.wangwenjun.simple.application.domain.Employee;
import com.wangwenjun.simple.application.exception.ApiException;
import com.wangwenjun.simple.application.service.EmployeeService;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  /**
   * List the all of Employee.
   *
   * @return The employee list
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<Employee>> list() {
    final ResponseEntity<Iterable<Employee>> response;
    try {
      Iterable<Employee> employees = this.employeeService.listEmployee();
      if (null != employees) {
        response = new ResponseEntity<>(employees, HttpStatus.OK);
      } else {
        response = new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
      }
      return response;
    } catch (Exception e) {
      throw new ApiException("list employee error", e);
    }
  }

  /**
   * Get Employee according to Employee ID.
   *
   * @param id The Employee ID
   * @return The Employee
   */
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
    try {
      Optional<Employee> empOptional = this.employeeService.getEmployeeById(id);
      return empOptional.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
              .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    } catch (Exception e) {
      throw new ApiException("get the employee error", e);
    }
  }

  /**
   * Delete the Employee according to Employee ID.
   *
   * @param id The Employee ID
   * @return response
   */
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Integer id) {
    final ResponseEntity<Employee> response;
    try {
      Optional<Employee> employeeOptional = this.employeeService.getEmployeeById(id);
      if (!employeeOptional.isPresent()) {
        response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        this.employeeService.deleteEmployee(id);
        response = new ResponseEntity<>(HttpStatus.OK);
      }
      return response;
    } catch (Exception e) {
      throw new ApiException("delete the employee error", e);
    }
  }

  /**
   * Update the exist employee details information.
   *
   * @param employee The new properties of Employee
   * @return Response
   */
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
    final ResponseEntity<Employee> response;
    try {
      Optional<Employee> employeeOptional = this.employeeService.getEmployeeById(employee.getId());
      if (!employeeOptional.isPresent()) {
        response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        response = new ResponseEntity<>(this.employeeService.updateEmployee(employee), HttpStatus.OK);
      }
      return response;
    } catch (Exception e) {
      throw new ApiException("update the employee error", e);
    }
  }

  /**
   * Create The new Employee.
   *
   * @param employee new Employee
   * @return Response
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
    try {
      return new ResponseEntity<>(this.employeeService.createEmployee(employee), HttpStatus.OK);
    } catch (Exception e) {
      throw new ApiException("update the employee error", e);
    }
  }
}
