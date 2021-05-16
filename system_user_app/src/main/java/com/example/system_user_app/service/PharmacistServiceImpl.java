package com.example.system_user_app.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.system_user_app.jdbc_repository.PharmacistJdbcRepository;
import com.example.system_user_app.model.Pharmacist;
import com.example.system_user_app.model.SystemRole;
import com.example.system_user_app.util.SystemRoleService;

@Service
public class PharmacistServiceImpl implements PharmacistService{

	@Autowired
	private PharmacistJdbcRepository pharmacistRepository;

	@Autowired
	private SystemRoleService systemRoleService;
 
	@Override
	public Pharmacist getPharmacistById(Integer id) {
		Pharmacist pharmacist = this.pharmacistRepository.findById(id);
		SystemRole role = systemRoleService.getRoleById(pharmacist.getSystemRole().getId());
		if(role != null) {
			pharmacist.setSystemRole(role);
		}
		else {
			//throw conflict
		}
		return pharmacist;
	}

	@Override
	public Collection<Pharmacist> getPharmacists() {
		Collection<Pharmacist> pharmacists = this.pharmacistRepository.findAll();
		return pharmacists;
	}

}
