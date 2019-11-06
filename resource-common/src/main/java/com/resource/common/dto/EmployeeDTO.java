package com.resource.common.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String employeeCode;
	
	public String getName() {
		return StringUtils.capitalize(this.firstName) +" "+ StringUtils.capitalize(this.lastName);
	}
}
