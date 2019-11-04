package com.resource.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static DateUtil INSTANCE;
	
	private DateUtil() {}
	
	public static DateUtil getInstance(){
        if(INSTANCE == null){
        	INSTANCE = new DateUtil();
        }
        return INSTANCE;
    }
	
	public String today() {
		String today = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		today = format.format(new Date());
		return today;
	}
}
