package com.example.restservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeManagerTests {

  @Autowired
  private EmployeeManager employeeManager;

  @Test
  public void getEmployeesShouldReturnEmployeesObject() {
    Employees employees = employeeManager.getEmployees();
    assertNotNull(employees, "Employees object should not be null");
    assertNotNull(employees.getEmployees(), "Employees list should not be null");
  }

  @Test
  public void getEmployeesShouldReturnInitialFourEmployees() {
    Employees employees = employeeManager.getEmployees();
    List<Employee> employeeList = employees.getEmployees();

    assertEquals(4, employeeList.size(), "Initial employee count should be 4");

    // Verify first employee
    Employee firstEmployee = employeeList.get(0);
    assertEquals("E001", firstEmployee.getEmployee_id());
    assertEquals("John", firstEmployee.getFirst_name());
    assertEquals("Doe", firstEmployee.getLast_name());
    assertEquals("john.doe@example.com", firstEmployee.getEmail());
    assertEquals("Software Engineer", firstEmployee.getTitle());

    // Verify second employee
    Employee secondEmployee = employeeList.get(1);
    assertEquals("E002", secondEmployee.getEmployee_id());
    assertEquals("Jane", secondEmployee.getFirst_name());
    assertEquals("Smith", secondEmployee.getLast_name());
  }

  @Test
  public void addEmployeeShouldIncreaseEmployeeCount() {
    Employees employeesBefore = employeeManager.getEmployees();
    int initialCount = employeesBefore.getEmployees().size();

    Employee newEmployee = new Employee("E005", "Test", "User", "test.user@example.com", "Tester");
    employeeManager.addEmployee(newEmployee);

    Employees employeesAfter = employeeManager.getEmployees();
    int newCount = employeesAfter.getEmployees().size();

    assertEquals(initialCount + 1, newCount, "Employee count should increase by 1");
  }

  @Test
  public void addEmployeeShouldAddCorrectEmployeeData() {
    Employee newEmployee = new Employee("E006", "Alice", "Test", "alice.test@example.com", "Developer");
    employeeManager.addEmployee(newEmployee);

    Employees employees = employeeManager.getEmployees();
    List<Employee> employeeList = employees.getEmployees();

    // Find the newly added employee
    Employee addedEmployee = employeeList.stream()
        .filter(e -> "E006".equals(e.getEmployee_id()))
        .findFirst()
        .orElse(null);

    assertNotNull(addedEmployee, "New employee should be found in the list");
    assertEquals("E006", addedEmployee.getEmployee_id());
    assertEquals("Alice", addedEmployee.getFirst_name());
    assertEquals("Test", addedEmployee.getLast_name());
    assertEquals("alice.test@example.com", addedEmployee.getEmail());
    assertEquals("Developer", addedEmployee.getTitle());
  }

  @Test
  public void addEmployeeShouldPreserveExistingEmployees() {
    Employees employeesBefore = employeeManager.getEmployees();
    List<Employee> beforeList = employeesBefore.getEmployees();
    int initialCount = beforeList.size();

    Employee newEmployee = new Employee("E007", "New", "Employee", "new.employee@example.com", "Manager");
    employeeManager.addEmployee(newEmployee);

    Employees employeesAfter = employeeManager.getEmployees();
    List<Employee> afterList = employeesAfter.getEmployees();

    assertEquals(initialCount + 1, afterList.size(), "Total count should increase by 1");

    // Verify first employee still exists
    Employee firstEmployee = afterList.get(0);
    assertEquals("E001", firstEmployee.getEmployee_id());
    assertEquals("John", firstEmployee.getFirst_name());
  }

}
