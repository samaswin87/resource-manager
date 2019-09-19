package com.resource.common.view;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PaginatedList {
	
	// object name which will mapped to model map
	String name();
	String service();
	String pageSize() default "20";
	String method() default "get";
	String sort() default "createdAt";
}
