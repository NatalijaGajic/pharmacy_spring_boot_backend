package com.example.system_user_app.util;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.system_user_app.dto.SystemRoleDTO;

@Service
public interface SystemRoleService {

	public SystemRoleDTO getRoleById(Integer id);
	public Collection<SystemRoleDTO> getRoles();
}
