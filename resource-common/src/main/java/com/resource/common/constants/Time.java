package com.resource.common.constants;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum Time {
	NOT_SET("00:00", "Not Set"),
	ONE("01:00", "01:00 AM"),
	ONE_FIFTEEN("01:15", "01:15 AM"),
	ONE_THIRTY("01:30", "01:30 AM"),
	ONE_FORTY_FIVE("01:45", "01:45 AM"),
	TWO("02:00", "02:00 AM"),
	TWO_FIFTEEN("02:15", "02:15 AM"),
	TWO_THIRTY("02:30", "02:30 AM"),
	TWO_FORTY_FIVE("02:45", "02:45 AM"),
	THREE("03:00", "03:00 AM"),
	THREE_FIFTEEN("03:15", "03:15 AM"),
	THREE_THIRTY("03:30", "03:30 AM"),
	THREE_FORTY_FIVE("03:45", "03:45 AM"),
	FOUR("04:00", "04:00 AM"),
	FOUR_FIFTEEN("04:15", "04:15 AM"),
	FOUR_THIRTY("04:30", "04:30 AM"),
	FOUR_FORTY_FIVE("04:45", "04:45 AM"),
	FIVE("05:00", "05:00 AM"),
	FIVE_FIFTEEN("05:15", "05:15 AM"),
	FIVE_THIRTY("05:30", "05:30 AM"),
	FIVE_FORTY_FIVE("05:45", "05:45 AM"),
	SIX("06:00", "06:00 AM"),
	SIX_FIFTEEN("06:15", "06:15 AM"),
	SIX_THIRTY("06:30", "06:30 AM"),
	SIX_FORTY_FIVE("06:45", "06:45 AM"),
	SEVEN("07:00", "07:00 AM"),
	SEVEN_FIFTEEN("07:15", "07:15 AM"),
	SEVEN_THIRTY("07:30", "07:30 AM"),
	SEVEN_FORTY_FIVE("07:45", "07:45 AM"),
	EIGHT("08:00", "08:00 AM"),
	EIGHT_FIFTEEN("08:15", "08:15 AM"),
	EIGHT_THIRTY("08:30", "08:30 AM"),
	EIGHT_FORTY_FIVE("08:45", "08:45 AM"),
	NINE("09:00", "09:00 AM"),
	NINE_FIFTEEN("09:15", "09:15 AM"),
	NINE_THIRTY("09:30", "09:30 AM"),
	NINE_FORTY_FIVE("09:45", "09:45 AM"),
	TEN("10:00", "10:00 AM"),
	TEN_FIFTEEN("10:15", "10:15 AM"),
	TEN_THIRTY("10:30", "10:30 AM"),
	TEN_FORTY_FIVE("10:45", "10:45 AM"),
	ELEVEN("11:00", "11:00 AM"),
	ELEVEN_FIFTEEN("11:15", "11:15 AM"),
	ELEVEN_THIRTY("11:30", "11:30 AM"),
	ELEVEN_FORTY_FIVE("11:45", "11:45 AM"),
	TWELVE_AM("12:00", "12:00 AM"),
	TWELVE_AM_FIFTEEN("12:15", "12:15 AM"),
	TWELVE_AM_THIRTY("12:30", "12:30 AM"),
	TWELVE_AM_FORTY_FIVE("12:45", "12:45 AM"),
	TWELVE_PM("12:00", "12:00 PM"),
	TWELVE_PM_FIFTEEN("12:15", "12:15 PM"),
	TWELVE_PM_THIRTY("12:30", "12:30 PM"),
	TWELVE_PM_FORTY_FIVE("12:45", "12:45 PM"),
	THIRTEEN("13:00", "13:00 PM"),
	THIRTEEN_FIFTEEN("13:15", "13:15 PM"),
	THIRTEEN_THIRTY("13:30", "13:30 PM"),
	THIRTEEN_FORTY_FIVE("13:45", "13:45 PM"),
	FOURTEEN("14:00", "14:00 PM"),
	FOURTEEN_FIFTEEN("14:15", "14:15 PM"),
	FOURTEEN_THIRTY("14:30", "14:30 PM"),
	FOURTEEN_FORTY_FIVE("14:45", "14:45 PM"),
	FIFTEEN("15:00", "15:00 PM"),
	FIFTEEN_FIFTEEN("15:15", "15:15 PM"),
	FIFTEEN_THIRTY("15:30", "15:30 PM"),
	FIFTEEN_FORTY_FIVE("15:45", "15:45 PM"),
	SIXTEEN("16:00", "16:00 PM"),
	SIXTEEN_FIFTEEN("16:15", "16:15 PM"),
	SIXTEEN_THIRTY("16:30", "16:30 PM"),
	SIXTEEN_FORTY_FIVE("16:45", "16:45 PM"),
	SEVENTEEN("17:00", "17:00 PM"),
	SEVENTEEN_FIFTEEN("17:15", "17:15 PM"),
	SEVENTEEN_THIRTY("17:30", "17:30 PM"),
	SEVENTEEN_FORTY_FIVE("17:45", "17:45 PM"),
	EIGHTEEN("18:00", "18:00 PM"),
	EIGHTEEN_FIFTEEN("18:15", "18:15 PM"),
	EIGHTEEN_THIRTY("18:30", "18:30 PM"),
	EIGHTEEN_FORTY_FIVE("18:45", "18:45 PM"),
	NINETEEN("19:00", "19:00 PM"),
	NINETEEN_FIFTEEN("19:15", "19:15 PM"),
	NINETEEN_THIRTY("19:30", "19:30 PM"),
	NINETEEN_FORTY_FIVE("19:45", "19:45 PM"),
	TWENTY("20:00", "20:00 PM"),
	TWENTY_FIFTEEN("20:15", "20:15 PM"),
	TWENTY_THIRTY("20:30", "20:30 PM"),
	TWENTY_FORTY_FIVE("20:45", "20:45 PM"),
	TWENTY_ONE("21:00", "21:00 PM"),
	TWENTY_ONE_FIFTEEN("21:15", "21:15 PM"),
	TWENTY_ONE_THIRTY("21:30", "21:30 PM"),
	TWENTY_ONE_FORTY_FIVE("21:45", "21:45 PM"),
	TWENTY_TWO("22:00", "22:00 PM"),
	TWENTY_TWO_FIFTEEN("22:15", "22:15 PM"),
	TWENTY_TWO_THIRTY("22:30", "22:30 PM"),
	TWENTY_TWO_FORTY_FIVE("22:45", "22:45 PM"),
	TWENTY_THREE("23:00", "23:00 PM"),
	TWENTY_THREE_FIFTEEN("23:15", "23:15 PM"),
	TWENTY_THREE_THIRTY("23:30", "23:30 PM"),
	TWENTY_THREE_FORTY_FIVE("23:45", "23:45 PM"),
	TWENTY_FOUR("24:00", "24:00 PM"),
	TWENTY_FOUR_FIFTEEN("24:15", "24:15 PM"),
	TWENTY_FOUR_THIRTY("24:30", "24:30 PM"),
	TWENTY_FOUR_FORTY_FIVE("24:45", "24:45 PM");

	private String id;
	private String name;

	Time(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public static Time getTime(String id) {
		for (Time time : Time.values()) {
			if (StringUtils.equals(time.getId(), id)) {
				return time;
			}
		}
		return Time.ONE;
	}

	public static List<Time> getTimes() {
		return Arrays.asList(Time.values());
	}

	public Boolean isSelected(String selected, String current) {
		return StringUtils.equals(selected, current);
	}
	
	public static String timeDisplay(String time) {
		return time.replace(":", ".");
	}

	public static BigDecimal timeDecimal(String time) {
		return new BigDecimal(timeDisplay(time));
	}
	
	public static int timeToMinutes(String time) {
		time = timeDisplay(time);
		int index = time.indexOf(".");
		return (timeDecimal(time).intValue() * 60) + Integer.valueOf(time.substring(index + 1));
	}
}
