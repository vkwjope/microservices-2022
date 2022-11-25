package com.example.employeemanagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.employeemanagement.model.Employee;

@Service
public interface EmployeeService {
	
	public Employee addEmployee(Employee employee);
	
	public Employee getEmployee(String id);
	
	public List<Employee> getAllEmployees();
	
	public Employee updateEmployee(Employee employee);
	
	public String deleteEmployee(String id);


}
