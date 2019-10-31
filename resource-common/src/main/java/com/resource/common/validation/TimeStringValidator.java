package com.resource.common.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeStringValidator implements ConstraintValidator<TimeString, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			String newTime = value.replace(":", ".");
			new BigDecimal(newTime);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

}
