package com.example.system_user_app.util;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pharmacy.dto.SystemRoleDto;


@Service
public class SystemRoleServiceImpl implements SystemRoleService{

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public SystemRoleDto getRoleById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8010/system-roles/"+Integer.toString(id);
		ResponseEntity<SystemRoleDto> responseEntity = restTemplate.getForEntity(uri, SystemRoleDto.class);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			SystemRoleDto dto = responseEntity.getBody();
			return mapper.map(dto, SystemRoleDto.class);
			
		}else {
			return null;
		}
	}

	@Override
	public Collection<SystemRoleDto> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
