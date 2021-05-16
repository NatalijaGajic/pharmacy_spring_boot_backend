package com.example.system_role_app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.system_role_app.dto.SystemRoleCreationDTO;
import com.example.system_role_app.dto.SystemRoleDTO;
import com.example.system_role_app.dto.SystemRoleUpdateDTO;
import com.example.system_role_app.jpa.SystemRole;
import com.example.system_role_app.repository.SystemRoleRepository;

@RestController
public class SystemRoleController {

	@Autowired
	private SystemRoleRepository systemRoleRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("system-roles")
	private Collection<SystemRoleDTO> getSystemRoles(@RequestParam(required = false) String name){
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Collection<SystemRoleDTO> collection = new ArrayList<SystemRoleDTO>();
		if(name!= null && !name.isEmpty()) {
			for(SystemRole role: systemRoleRepository.findByNameContainingIgnoreCase(name)) {
				collection.add(mapper.map(role, SystemRoleDTO.class));
			}
		}else {
			for(SystemRole role: systemRoleRepository.findAll()) {
				collection.add(mapper.map(role, SystemRoleDTO.class));
			}
		}
		return collection;
	}
	
	@GetMapping("system-roles/{id}")
	private SystemRoleDTO getSystemRoleById(@PathVariable Integer id){
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(systemRoleRepository.findById(id).get(), SystemRoleDTO.class);
	}
	
	@PostMapping("system-roles")
	private ResponseEntity<?> createSystemRole(@RequestBody SystemRoleCreationDTO role){
		SystemRole systemRole = mapper.map(role, SystemRole.class); 
		systemRoleRepository.save(systemRole);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("system-roles/{id}")
	private ResponseEntity<?> updateSystemRole(@RequestBody SystemRoleUpdateDTO role, @PathVariable Integer id){
		SystemRole systemRole = mapper.map(role, SystemRole.class); 
		systemRole.setId(id);
		systemRoleRepository.save(systemRole);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("system-roles/{id}")
	private ResponseEntity<?> deleteSystemRole(@PathVariable Integer id){
		SystemRole role = systemRoleRepository.findById(id).get();
		systemRoleRepository.delete(role);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
