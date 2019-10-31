package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;

public enum Hour {

	ONE(1, "1 hour"),
	TWO(2, "2 hour"),
	THREE(3, "3 hour"),
	FOUR(4, "4 hour"),
	FIVE(5, "5 hour"),
	SIX(6, "6 hour"),
	SEVEN(7, "7 hour"),
	EIGHT(8, "8 hour"),
	NINE(9, "9 hour"),
	TEN(10, "10 hour"),
	ELEVEN(11, "11 hour"),
	TWELVE(12, "12 hour"),
	THIRTEEN(13, "13 hour"),
	FOURTEEN(14, "14 hour"),
	FIFTEEN(15, "15 hour"),
	SIXTEEN(16, "16 hour"),
	SEVENTEEN(17, "17 hour"),
	EIGHTEEN(18, "18 hour"),
	NINETEEN(19, "19 hour"),
	TWENTY(20, "20 hour"),
	TWENTY_ONE(21, "21 hour"),
	TWENTY_TWO(22, "22 hour"),
	TWENTY_THREE(23, "23 hour"),
	TWENTY_FOUR(24, "24 hour");
	
	private Integer id;
	private String name;
	
	Hour(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public static Hour getHour(Integer id) {
		for (Hour hour : Hour.values()) {
			if (hour.getId() == id) {
				return hour;
			}
		}
		return Hour.ONE;
	}
	
	public static List<Hour> getHours() {
		return Arrays.asList(Hour.values());
	}
}
