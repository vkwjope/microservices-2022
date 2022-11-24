package com.example.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemanagement.dao.impl.EmployeeRepo;
import com.example.employeemanagement.exceptions.EmployeeAlreadyExistsException;
import com.example.employeemanagement.exceptions.EmployeeNotFoundException;
import com.example.employeemanagement.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo empRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		Optional<Employee> emp = empRepo.findById(employee.getId());
		if (emp != null &&  !emp.isEmpty() &&  emp.get() != null) {
			throw new EmployeeAlreadyExistsException();
		} else {
			return empRepo.save(employee);
		}
	}

	@Override
	public Employee getEmployee(String id) {
		Optional<Employee> emp = empRepo.findById(id);
		if (emp != null &&  !emp.isEmpty() &&  emp.get() != null) {
			return empRepo.findById(id).get();
		} else {
			throw new EmployeeNotFoundException();
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Optional<Employee> emp = empRepo.findById(employee.getId());
		if (emp != null &&  !emp.isEmpty() &&  emp.get() != null) {
			return empRepo.save(employee);
		} else {
			throw new EmployeeNotFoundException("Employee not found");
		}

	}

	@Override
	public String deleteEmployee(String id) {
		Optional<Employee> emp = empRepo.findById(id);
		if (emp != null &&  !emp.isEmpty() &&  emp.get() != null) {
			empRepo.deleteById(id);
			return "Employee Deleted Succesfully";
		} else {
			throw new EmployeeNotFoundException("Employee not found");
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		Iterable<Employee> employeeList = empRepo.findAll();
		List<Employee> empList = new ArrayList<Employee>();
		for (Employee emp : employeeList) {
			empList.add(emp);
		}
		return empList;
	}

}
