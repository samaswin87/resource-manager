package com.company.resource.service;

import java.util.List;

import com.resource.common.model.ShiftType;

public interface IShiftTypeService {

	public List<ShiftType> list();
	
	public ShiftType create(String name);
}
