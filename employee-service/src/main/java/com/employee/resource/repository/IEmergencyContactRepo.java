package com.employee.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.common.model.EmergencyContact;

public interface IEmergencyContactRepo extends JpaRepository<EmergencyContact, Integer>{

}
