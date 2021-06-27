package com.example.pharmacy.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.jpa.SystemRole;
import com.example.pharmacy.repository.SystemRoleRepository;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	@Autowired
	private SystemRoleRepository systemRoleRepository;
	
	@Override
	public void createSystemRole(SystemRole systemRole) throws Exception {
		systemRoleRepository.save(systemRole);
		
	}

	@Override
	public void deleteSystemRole(Integer id) {
		systemRoleRepository.deleteById(id);
		
	}

	@Override
	public SystemRole updateSystemRole(SystemRole systemRole) throws Exception {
		return systemRoleRepository.save(systemRole);
	}

	@Override
	public SystemRole findSystemRoleById(Integer id) {
		return systemRoleRepository.findById(id).get();

	}

	@Override
	public Collection<SystemRole> findAllSystemRoles() {
		return systemRoleRepository.findAll();

	}

}
