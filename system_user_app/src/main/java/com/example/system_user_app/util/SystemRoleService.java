package com.example.system_user_app.util;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.system_user_app.model.SystemRole;

@Service
public interface SystemRoleService {

	public SystemRole getRoleById(Integer id);
	public Collection<SystemRole> getRoles();
}
