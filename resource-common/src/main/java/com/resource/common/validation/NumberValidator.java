package com.resource.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidator implements ConstraintValidator<ValidNumber, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			Integer intValue = Integer.valueOf(value);
			if(intValue < 0)
				return false;
		}catch(Exception e) {
			return false;
		}
		return true;
	}

}
