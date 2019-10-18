package com.employee.resource.constants.view;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum EmployeePartial {
	
	PERSONAL("personal"), ADDRESSES("addresses"), CONTACTS("contacts"), DEPENDENTS("dependents");
	
	private String view;
	
	EmployeePartial(String name) {
		this.view = name;
	}
	
	public String getName() {
		return this.view;
	}
	
	public List<EmployeePartial> names() {
   	 return Arrays.asList(EmployeePartial.values());
    }
	
	public static EmployeePartial getPartial(String text) {
		for (EmployeePartial partial : EmployeePartial.values()) {
			if (StringUtils.equals(partial.getName(), text)) {
				return partial;
			}
		}
		return null;
	}
	
	public boolean isPersonal() {
		 return StringUtils.equals(this.getName(), EmployeePartial.PERSONAL.view);
	}
	
	public boolean isAddresses() {
		 return StringUtils.equals(this.getName(), EmployeePartial.ADDRESSES.view);
	}
	
	public boolean isContacts() {
		 return StringUtils.equals(this.getName(), EmployeePartial.CONTACTS.view);
	}
	
	
	public boolean isDependents() {
		 return StringUtils.equals(this.getName(), EmployeePartial.DEPENDENTS.view);
	}
	
}
