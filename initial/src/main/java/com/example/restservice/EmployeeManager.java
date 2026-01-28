package com.example.restservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmployeeManager {
  private Employees employees;

  public EmployeeManager() {
    List<Employee> employeeList = new ArrayList<>();
    employeeList.add(new Employee("E001", "John", "Doe", "john.doe@example.com", "Software Engineer"));
    employeeList.add(new Employee("E002", "Jane", "Smith", "jane.smith@example.com", "Product Manager"));
    employeeList.add(new Employee("E003", "Bob", "Johnson", "bob.johnson@example.com", "DevOps Engineer"));
    employeeList.add(new Employee("E004", "Alice", "Williams", "alice.williams@example.com", "UX Designer"));
    this.employees = new Employees(employeeList);
  }

  public Employees getEmployees() {
    return employees;
  }

  public void addEmployee(Employee employee) {
    List<Employee> employeeList = employees.getEmployees();
    employeeList.add(employee);
    employees.setEmployees(employeeList);
  }
}
