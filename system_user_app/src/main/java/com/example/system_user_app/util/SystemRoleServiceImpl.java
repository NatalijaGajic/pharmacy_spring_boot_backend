package com.example.system_user_app.util;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.system_user_app.dto.SystemRoleDTO;
import com.example.system_user_app.model.SystemRole;

@Service
public class SystemRoleServiceImpl implements SystemRoleService{

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SystemRole getRoleById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8010/system-roles/"+Integer.toString(id);
		ResponseEntity<SystemRoleDTO> responseEntity = restTemplate.getForEntity(uri, SystemRoleDTO.class);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			SystemRoleDTO dto = responseEntity.getBody();
			return mapper.map(dto, SystemRole.class);
			
		}else {
			return null;
		}
	}

	@Override
	public Collection<SystemRole> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
