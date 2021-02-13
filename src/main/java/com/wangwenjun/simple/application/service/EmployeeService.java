package com.wangwenjun.simple.application.service;

import com.wangwenjun.simple.application.domain.Employee;

import java.util.Optional;

public interface EmployeeService {

  Iterable<Employee> batchSave(Iterable<Employee> employees);

  Iterable<Employee> listEmployee();

  Optional<Employee> getEmployeeById(Integer id);

  Employee updateEmployee(Employee employee);

  void deleteEmployee(Integer id);

  Employee createEmployee(Employee employee);

  long count();
}
