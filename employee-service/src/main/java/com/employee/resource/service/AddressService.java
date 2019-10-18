package com.employee.resource.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.resource.repository.IAddressRepo;
import com.resource.common.model.Address;

@Service("addressService")
@Transactional
public class AddressService implements IAddressService {
	
	@Autowired
	IAddressRepo addressRepo;

	@Override
	public Address create(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public Address findById(int id) {
		Optional<Address> address = addressRepo.findById(id);
		return address.orElse(null);
	}

	@Override
	public Address update(Address address) {
		return addressRepo.save(address);
	}

	@Override
	public void delete(int id) {
		addressRepo.deleteById(id);
	}


}
