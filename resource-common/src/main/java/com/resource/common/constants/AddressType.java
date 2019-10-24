package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum AddressType {
		
	GENERAL_ADDRESS(1, "General Address"), PERMANENT_ADDRESS(2, "Perment Address"), WORK_ADDRESS(3, "Work Address");
	
	private Integer id;
	private String name;
	
	AddressType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public static AddressType getAddressType(Integer id) {
		for (AddressType type : AddressType.values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return AddressType.GENERAL_ADDRESS;
	}
	
	public static List<AddressType> getAddressTypes() {
		return Arrays.asList(AddressType.values());
	}
}
