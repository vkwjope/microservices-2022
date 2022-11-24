/**
 * 
 */
package com.example.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.impl.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author KVINOVIN
 *
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Operation(summary = "Add new employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee Added", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid Employee Id", content = @Content),
			@ApiResponse(responseCode = "404", description = "Invalid Employee Id", content = @Content) })
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);

	}

	@Operation(summary = "Get employee details by Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid Employee Id", content = @Content),
			@ApiResponse(responseCode = "404", description = "Invalid Employee Id", content = @Content) })
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable String id) {
		return employeeService.getEmployee(id);

	}

	@Operation(summary = "Get all employee details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee details fetched", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Employees not found", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employees not found", content = @Content) })
	@GetMapping()
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();

	}

	@Operation(summary = "Update employee details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee details updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid Employee details", content = @Content),
			@ApiResponse(responseCode = "404", description = "Invalid Employee details", content = @Content) })
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);

	}

	@Operation(summary = "Delete employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Employee deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid Employee Id", content = @Content),
			@ApiResponse(responseCode = "404", description = "Invalid Employee Id", content = @Content) })
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable String id) {
		return employeeService.deleteEmployee(id);

	}

}
