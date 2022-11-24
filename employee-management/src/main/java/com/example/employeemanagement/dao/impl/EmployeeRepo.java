package com.example.employeemanagement.dao.impl;

import org.springframework.data.repository.CrudRepository;

import com.example.employeemanagement.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, String> {

}
