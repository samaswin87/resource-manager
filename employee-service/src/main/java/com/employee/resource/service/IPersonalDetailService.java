package com.employee.resource.service;

import com.resource.common.model.PersonalDetail;
import com.resource.common.service.ModelService;

public interface IPersonalDetailService extends ModelService<PersonalDetail> {

	public PersonalDetail findByEmployeeCode(String employeeCode);
}
