package com.example.restservice;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employees {
  @JsonProperty("Employees")
  private List<Employee> Employees;

  public Employees() {
  }

  public Employees(List<Employee> employees) {
    this.Employees = employees;
  }

  public List<Employee> getEmployees() {
    return Employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.Employees = employees;
  }
}
