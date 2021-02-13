package com.wangwenjun.simple.application.conf;

import com.wangwenjun.simple.application.domain.Employee;
import com.wangwenjun.simple.application.service.EmployeeService;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialTableRunner implements CommandLineRunner {
  @Autowired
  private EmployeeService employeeService;

  private static final Logger LOG = LoggerFactory.getLogger(InitialTableRunner.class);

  @Override
  public void run(String... args) throws Exception {
    if (employeeService.count() > 0) {
      LOG.info("The initial data exist already.");
      return;
    }

    LOG.info("initialize the data...");
    List<Employee> employees = Arrays.asList(
            new Employee("Alex", "China", new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis()), "Alex Remark"),
            new Employee("Tina", "China", new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis()), "Tina Remark"),
            new Employee("Jack", "America", new Date(System.currentTimeMillis()),
                    new Date(System.currentTimeMillis()), "Jack Remark")
    );
    LOG.info("initial data finished and effected records [{}]", employees.size());
    employeeService.batchSave(employees);
  }
}
