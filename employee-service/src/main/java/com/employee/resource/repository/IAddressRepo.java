package com.employee.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resource.common.model.Address;

public interface IAddressRepo extends JpaRepository<Address, Integer> {

}
