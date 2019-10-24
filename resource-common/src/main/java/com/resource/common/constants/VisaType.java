package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum VisaType {
	
	NOT_SET(0, "Not Set"),
	EMPLOYMENT_VISA(1 ,"Employment Visa"),
	BUSINESS_VISA(2 ,"Business Visa"),
	PROJECT_VISA(3 ,"Project Visa"),
	ENTRY_VISA(4 ,"X/Entry visa"),
	TOURIST_VISA(5 ,"Tourist Visa"),
	RESEARCH_VISA(6 ,"Research Visa"),
	TRANSIT_VISA(7 ,"Transit Visa"),
	CONFERENCE_VISA(8 ,"Conference Visa"),
	MEDICAL_VISA(9 ,"Medical Visa");
	
	private Integer id;
	private String name;

	VisaType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getId() {
		return this.id;
	}

	public static VisaType getVisaType(Integer id) {
		for (VisaType type : VisaType.values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return VisaType.TOURIST_VISA;
	}
	
	public static List<VisaType> getVisaTypes() {
   	 	return Arrays.asList(VisaType.values());
    }

}
