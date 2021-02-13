package com.wangwenjun.simple.application.service.impl;

import com.wangwenjun.simple.application.domain.Employee;
import com.wangwenjun.simple.application.repository.EmployeeRepository;
import com.wangwenjun.simple.application.service.EmployeeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  @Transactional
  @Override
  public Iterable<Employee> batchSave(Iterable<Employee> employees) {
    return this.employeeRepository.saveAll(employees);
  }

  @Transactional(readOnly = true)
  @Override
  public Iterable<Employee> listEmployee() {
    return this.employeeRepository.findAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Employee> getEmployeeById(Integer id) {
    return this.employeeRepository.findById(id);
  }

  @Transactional
  @Override
  public Employee updateEmployee(Employee employee) {
    return this.employeeRepository.save(employee);
  }

  @Transactional
  @Override
  public void deleteEmployee(Integer id) {
    this.employeeRepository.deleteById(id);
  }

  @Transactional
  @Override
  public Employee createEmployee(Employee employee) {
    return this.employeeRepository.save(employee);
  }

  @Override
  public long count() {
    return this.employeeRepository.count();
  }
}
