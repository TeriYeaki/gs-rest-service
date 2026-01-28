package com.example.restservice;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest
@AutoConfigureRestTestClient
public class EmployeeControllerTests {

  @Autowired
  private RestTestClient restTestClient;

  @Test
  public void getEmployeesShouldReturnListOfEmployees() throws Exception {
    this.restTestClient.get().uri("/employees")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.Employees").exists()
        .jsonPath("$.Employees.length()").isEqualTo(4)
        .jsonPath("$.Employees[0].employee_id").isEqualTo("E001")
        .jsonPath("$.Employees[0].first_name").isEqualTo("John")
        .jsonPath("$.Employees[0].last_name").isEqualTo("Doe")
        .jsonPath("$.Employees[0].email").isEqualTo("john.doe@example.com")
        .jsonPath("$.Employees[0].title").isEqualTo("Software Engineer");
  }

  @Test
  public void postEmployeeShouldAddNewEmployee() throws Exception {
    String newEmployeeJson = "{\"employee_id\":\"E005\",\"first_name\":\"Charlie\",\"last_name\":\"Brown\",\"email\":\"charlie.brown@example.com\",\"title\":\"QA Engineer\"}";

    // Add new employee via POST
    this.restTestClient.post().uri("/employees")
        .bodyValue(newEmployeeJson)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.employee_id").isEqualTo("E005")
        .jsonPath("$.first_name").isEqualTo("Charlie")
        .jsonPath("$.last_name").isEqualTo("Brown")
        .jsonPath("$.email").isEqualTo("charlie.brown@example.com")
        .jsonPath("$.title").isEqualTo("QA Engineer");

    // Verify the employee was added by querying GET endpoint
    this.restTestClient.get().uri("/employees")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.Employees.length()").isEqualTo(5)
        .jsonPath("$.Employees[4].employee_id").isEqualTo("E005")
        .jsonPath("$.Employees[4].first_name").isEqualTo("Charlie");
  }

  @Test
  public void getEmployeesShouldReturnCorrectStructure() throws Exception {
    this.restTestClient.get().uri("/employees")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.Employees").isArray()
        .jsonPath("$.Employees[0]").exists()
        .jsonPath("$.Employees[0].employee_id").exists()
        .jsonPath("$.Employees[0].first_name").exists()
        .jsonPath("$.Employees[0].last_name").exists()
        .jsonPath("$.Employees[0].email").exists()
        .jsonPath("$.Employees[0].title").exists();
  }

}
