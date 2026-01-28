package com.example.restservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeManager {
  private Employees employees;

  public EmployeeManager() {
    List<Employee> employeeList = Arrays.asList(
        new Employee("E001", "John", "Doe", "john.doe@example.com", "Software Engineer"),
        new Employee("E002", "Jane", "Smith", "jane.smith@example.com", "Product Manager"),
        new Employee("E003", "Bob", "Johnson", "bob.johnson@example.com", "DevOps Engineer"),
        new Employee("E004", "Alice", "Williams", "alice.williams@example.com", "UX Designer")
    );
    this.employees = new Employees(employeeList);
  }

  public Employees getEmployees() {
    return employees;
  }
}
