package com.resource.common.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Day {

	ONE(1, "1 Days"),
	TWO(2, "2 Days"),
	THREE(3, "3 Days"),
	FOUR(4, "4 Days"),
	FIVE(5, "5 Days"),
	SIX(6, "6 Days"),
	SEVEN(7, "7 Days"),
	EIGHT(8, "8 Days"),
	NINE(9, "9 Days"),
	TEN(10, "10 Days"),
	ELEVEN(11, "11 Days"),
	TWELVE(12, "12 Days"),
	THIRTEEN(13, "13 Days"),
	FOURTEEN(14, "14 Days"),
	FIFTEEN(15, "15 Days"),
	SIXTEEN(16, "16 Days"),
	SEVENTEEN(17, "17 Days"),
	EIGHTEEN(18, "18 Days"),
	NINETEEN(19, "19 Days"),
	TWENTY(20, "20 Days"),
	TWENTY_ONE(21, "21 Days"),
	TWENTY_TWO(22, "22 Days"),
	TWENTY_THREE(23, "23 Days"),
	TWENTY_FOUR(24, "24 Days"),
	TWENTY_FIVE(25, "25 Days"),
	TWENTY_SIX(26, "26 Days"),
	TWENTY_SEVEN(27, "27 Days"),
	TWENTY_EIGHT(28, "28 Days"),
	TWENTY_NINE(29, "29 Days"),
	THIRTY(30, "30 Days"),
	THIRTY_ONE(31, "31 Days");
	
	private Integer id;
	private String name;
	
	Day(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public static Day getDay(Integer id) {
		for (Day day : Day.values()) {
			if (day.getId() == id) {
				return day;
			}
		}
		return Day.ONE;
	}
	
	public static List<Day> getDays() {
		return Arrays.asList(Day.values());
	}
	
	public static List<Day> getDaysforWeek() {
		return Arrays.asList(Day.values()).stream().limit(7).collect(Collectors.toList());
	}
}
