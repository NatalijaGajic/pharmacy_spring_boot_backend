package com.example.system_user_app.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.system_user_app.dto.SystemRoleDTO;
import com.example.system_user_app.exceptions.InvalidIdException;
import com.example.system_user_app.jdbc_repository.PharmacistJdbcRepository;
import com.example.system_user_app.model.Pharmacist;
import com.example.system_user_app.util.SystemRoleService;

@Service
public class PharmacistServiceImpl implements PharmacistService{

	@Autowired
	private PharmacistJdbcRepository pharmacistRepository;

	@Autowired
	private SystemRoleService systemRoleService;
 
	@Override
	public Pharmacist getPharmacistById(Integer id) throws Exception{
		Pharmacist pharmacist = this.pharmacistRepository.findById(id);
		SystemRoleDTO role = systemRoleService.getRoleById(pharmacist.getSystemRole().getId());
		if(role != null) {
			pharmacist.setSystemRole(role);
		}
		else {
			throw new InvalidIdException("Invalid role id");
		}
		return pharmacist;
	}

	@Override
	public Collection<Pharmacist> getPharmacists(String username) throws Exception {
		Collection<Pharmacist> pharmacists;
		if(username!= null && !username.isEmpty()) {
			pharmacists = this.pharmacistRepository.findByUsernameContainingIgnoreCase(username);
		}else {
			pharmacists = this.pharmacistRepository.findAll();
		}
		for(Pharmacist pharmacist: pharmacists) {
			SystemRoleDTO role = systemRoleService.getRoleById(pharmacist.getSystemRole().getId());
			if(role != null) {
				pharmacist.setSystemRole(role);
			}
			else {
				throw new InvalidIdException("Invalid role id");
			}
		}
		return pharmacists;
	}

}
