package com.example.system_user_app.util;

import java.util.Collection;
import org.springframework.stereotype.Service;
import com.example.pharmacy.dto.SystemRoleDto;

@Service
public interface SystemRoleService {

	public SystemRoleDto getRoleById(Integer id);
	public Collection<SystemRoleDto> getRoles();
}
