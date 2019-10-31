package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum WorkType {
	
	FULL_TIME(1, "Full Time"),
	CONTRACT_WORKER(2, "Contract Worker"),
	PART_TIME(3, "Part Time"),
	TEMP_STAFF(4, "Temp Staff"),
	TRAINEES(5, "Trainees"),
	CONSULTANT(6, "Consaltant"),
	OTHER(7, "Other");

	private Integer id;
	private String name;
	
	WorkType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public static WorkType getWorkType(Integer id) {
		for (WorkType type : WorkType.values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return WorkType.FULL_TIME;
	}
	
	public static List<WorkType> getWorkTypes() {
		return Arrays.asList(WorkType.values());
	}
}
