package com.resource.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resource.common.constants.Time;
import com.resource.common.model.EmployeeType;
import com.resource.common.reporsitory.ICompanyEmployeeTypeRepo;

@Service
public class EmployeeTypeService extends CompanyBaseService implements IEmployeeTypeService {

	public List<String> errors;

	public EmployeeTypeService(){
		errors = new ArrayList<String>();
	}

	@Autowired
	ICompanyEmployeeTypeRepo repo;

	@Override
	public List<EmployeeType> list() {
		return repo.findAll(company());
	}

	@Override
	public EmployeeType find(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public EmployeeType updateWorTimeSettings(EmployeeType employeeType) {
		EmployeeType existingEmployeeType = repo.findById(employeeType.getId()).orElse(null);
		if (existingEmployeeType != null) {
			existingEmployeeType.setWorkType(employeeType.getWorkType());

			existingEmployeeType.setCoreStartTime(employeeType.getCoreStartTime());
			existingEmployeeType.setCoreEndTime(employeeType.getCoreEndTime());
			existingEmployeeType.setDailyWorkHours(employeeType.getDailyWorkHours());
			existingEmployeeType.setDaysPerWeek(employeeType.getDaysPerWeek());
			existingEmployeeType.setWorkStartTime(employeeType.getWorkStartTime());
			existingEmployeeType.setWorkEndTime(employeeType.getWorkEndTime());

			existingEmployeeType.setFirstHalfDayStartTime(employeeType.getFirstHalfDayStartTime());
			existingEmployeeType.setFirstHalfDayEndTime(employeeType.getFirstHalfDayEndTime());
			existingEmployeeType.setSecondHalfDayStartTime(employeeType.getSecondHalfDayStartTime());
			existingEmployeeType.setSecondHalfDayEndTime(employeeType.getSecondHalfDayEndTime());

			existingEmployeeType.setFirstBreakStartTime(employeeType.getFirstBreakStartTime());
			existingEmployeeType.setFirstBreakEndTime(employeeType.getFirstBreakEndTime());
			existingEmployeeType.setFirstBreakMinutes(countMinutes(employeeType, "firstBreak"));
			existingEmployeeType.setSecondBreakStartTime(employeeType.getSecondBreakStartTime());
			existingEmployeeType.setSecondBreakEndTime(employeeType.getSecondBreakEndTime());
			existingEmployeeType.setSecondBreakMinutes(countMinutes(employeeType, "secondBreak"));
			existingEmployeeType.setLunchBreakStartTime(employeeType.getLunchBreakStartTime());
			existingEmployeeType.setLunchBreakEndTime(employeeType.getLunchBreakEndTime());
			existingEmployeeType.setLunchBreakMinutes(countMinutes(employeeType, "lunch"));

			existingEmployeeType.setIncludePermissions(employeeType.getIncludePermissions());
			existingEmployeeType.setNoOfPermissions(employeeType.getNoOfPermissions());
			existingEmployeeType.setPermissionsMinutes(employeeType.getPermissionsMinutes());
		}

		return repo.save(existingEmployeeType);
	}

	private Integer countMinutes(EmployeeType employeeType, String name) {
		int minutes = 0;
		switch(name) {
		case "firstBreak":
			minutes = calculateMinutes(employeeType.getFirstBreakStartTime(), employeeType.getFirstBreakEndTime());
			break;
		case "secondBreak":
			minutes = calculateMinutes(employeeType.getSecondBreakStartTime(), employeeType.getSecondBreakEndTime());
			break;
		case "lunch":
			minutes = calculateMinutes(employeeType.getLunchBreakStartTime(), employeeType.getLunchBreakEndTime());
			break;
		}
		return minutes;
	}

	private Integer calculateMinutes(String start, String end) {
		Integer startTime = Time.timeToMinutes(start);
		Integer endTime = Time.timeToMinutes(end);
		Integer differedTime = endTime - startTime;
		int result = (int) TimeUnit.MINUTES.toMinutes(differedTime.longValue());
		return result > 0 ? result : 0;
	}

	@Override
	public List<String> errors() {
		return errors;
	}

	@Override
	public void updateHRSettings(EmployeeType employeeType) {
		EmployeeType existingEmployeeType = repo.findById(employeeType.getId()).orElse(null);
		if(existingEmployeeType != null) {
			existingEmployeeType.setCode(employeeType.getCode());
			existingEmployeeType.setName(employeeType.getName());
			existingEmployeeType.setDescription(employeeType.getDescription());
			existingEmployeeType.setNoticePeriod(employeeType.getNoticePeriod());
			existingEmployeeType.setProbationPeriod(employeeType.getProbationPeriod());
		}
		repo.save(existingEmployeeType);
	}

	@Override
	public void add(EmployeeType employeeType) {
		repo.save(employeeType);
	}
}
