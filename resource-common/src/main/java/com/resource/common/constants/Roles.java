package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Roles {

	ADMIN("ADMIN"), EMPLOYEE("EMPLOYEE"), HRMANAGER("HR-MANAGER"), MANAGER("MANAGER");
	
	private String role;

	Roles(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	public static String[] getRoles() {
		List<Roles> roleList = Arrays.asList(Roles.values());
		List<String> roles = roleList.stream().map(role -> role.getRole()).collect(Collectors.toList()); 
		return roles.toArray(new String[0]);
	}
}
