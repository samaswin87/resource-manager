package com.resource.common.utils;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

	private String[] exceptFields;

	public <T> T merge(T actual, T duplicate) {
		Class<?> actualClazz = actual.getClass();
		Field[] fields = actualClazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (StringUtils.equals(field.getName(), "id") || field.isAnnotationPresent(ManyToOne.class)
						|| field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(OneToOne.class)
						|| StringUtils.equals(field.getName(), "serialVersionUID"))
					continue;
				
				if (!ArrayUtils.isEmpty(exceptFields) && Stream.of(exceptFields).anyMatch(e -> StringUtils.equals(field.getName(), e)))
					continue;
				
				field.setAccessible(true);
				Object value = field.get(duplicate);
				field.set(actual, value);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		return (T) actual;
	}

	public void setExceptFields(String... fields) {
		this.exceptFields = fields;
	}
}
