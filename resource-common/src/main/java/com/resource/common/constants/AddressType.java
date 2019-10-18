package com.resource.common.constants;

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
		return null;
	}
}
