/**
 * 
 */
package com.example.employeemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author KVINOVIN
 *
 */
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@EqualsAndHashCode
public class Employee {
	
	@Column
	@Id
	@NotBlank
	private String id;
	
	@NotBlank
	@Column
	private String lastName;
	
	@Column
	private String firstName;
	

}
