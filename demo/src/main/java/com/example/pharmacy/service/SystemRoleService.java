package com.example.pharmacy.service;

import java.util.Collection;

import com.example.pharmacy.jpa.SystemRole;


public interface SystemRoleService {
	void createSystemRole(SystemRole systemRole) throws Exception;
	void deleteSystemRole(Integer id);
	SystemRole updateSystemRole(SystemRole  systemRole) throws Exception;
	SystemRole findSystemRoleById(Integer id);
	Collection<SystemRole> findAllSystemRoles();
}
