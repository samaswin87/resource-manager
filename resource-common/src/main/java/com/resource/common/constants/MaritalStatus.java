package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum MaritalStatus {

	SINGLE(1, "Single"), MARRIED(2, "Married"), WIDOWED(3, "Widowed"), DIVORCED(4, "Divorced");
	
	private Integer id;
	private String name;
	
	MaritalStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public static MaritalStatus getMaritalStatus(Integer id) {
		for (MaritalStatus status : MaritalStatus.values()) {
			if (status.getId() == id) {
				return status;
			}
		}
		return null;
	}
	
	public static List<MaritalStatus> getStatuses() {
   	 	return Arrays.asList(MaritalStatus.values());
    }
}
