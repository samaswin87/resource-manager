package com.resource.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "email") 
	})
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@Column(name = "employee_code", length = 10)
	private String employeeCode;

	@NotNull
	@Column(name = "first_name", length = 40)
	private String firstName;

	@NotNull
	@Column(name = "last_name", length = 40)
	private String lastName;

	@Column(name = "middle_name", length = 40)
	private String middleName;

	@NotNull
	@Column(name = "email", length = 40)
	private String email;

//	@NotNull
//	@Past
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//	@JsonFormat(pattern = "YYYY-MM-DD")
//	@Column(name = "birth_date")
//	private Date birthDate;

	@NotNull
	@Column(name = "gender")
	private Boolean gender;

//	@NotNull
//	@Column(name = "martial_status")
//	private Boolean martialStatus;
//
//	@NotNull
//	@Column(name = "national_id")
//	private int nationalId;
//
//	@Column(name = "blood_group", length = 5)
//	private String bloodGroup;

	@ManyToOne
	private Company company;

	public String fullName() {
		return this.firstName + this.lastName; 
	}
}
