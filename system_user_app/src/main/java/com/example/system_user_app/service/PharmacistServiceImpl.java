package com.example.system_user_app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.system_user_app.jdbc_repository.PharmacistJdbcRepository;
import com.example.system_user_app.model.Pharmacist;

public class PharmacistServiceImpl implements PharmacistService{

	@Autowired
	private PharmacistJdbcRepository pharmacistRepository;
 
	@Override
	public Pharmacist getPharmacistById(Integer id) {
		Pharmacist pharmacist = this.pharmacistRepository.findById(id);
		return pharmacist;
	}

	@Override
	public Collection<Pharmacist> getPharmacists() {
		Collection<Pharmacist> pharmacists = this.pharmacistRepository.findAll();
		return pharmacists;
	}

}
