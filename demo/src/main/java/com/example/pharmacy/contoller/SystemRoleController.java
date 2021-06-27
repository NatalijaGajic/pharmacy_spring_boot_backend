package com.example.pharmacy.contoller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

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
import com.example.pharmacy.dto.SystemRoleCreationDto;
import com.example.pharmacy.dto.SystemRoleDto;
import com.example.pharmacy.dto.SystemRoleUpdateDto;
import com.example.pharmacy.jpa.SystemRole;
import com.example.pharmacy.service.SystemRoleService;

@RestController
public class SystemRoleController {

	@Autowired
	private SystemRoleService systemRoleService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("system-roles")
	private ResponseEntity getSystemRoles(){

		try {
			Collection<SystemRoleDto> systemRoleDtoList = new ArrayList<SystemRoleDto>();
			for(SystemRole medicineEntity: systemRoleService.findAllSystemRoles()) {
				systemRoleDtoList.add(mapper.map(medicineEntity, SystemRoleDto.class));
			}
			return new ResponseEntity<Collection<SystemRoleDto>>(systemRoleDtoList, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@GetMapping("system-roles/{id}")
	private ResponseEntity getSystemRoleById(@PathVariable Integer id){
		SystemRoleDto systemRoleDto = new SystemRoleDto();
		try {
			SystemRole role = systemRoleService.findSystemRoleById(id);
			systemRoleDto = mapper.map(role, SystemRoleDto.class);
			return new ResponseEntity<SystemRoleDto>(systemRoleDto, HttpStatus.OK);
		} 
		catch (Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("system-roles")
	private ResponseEntity createMedicines(@RequestBody SystemRoleCreationDto body){
		
		try {
				SystemRole role = mapper.map(body, SystemRole.class);
				systemRoleService.createSystemRole(role);;
				return new ResponseEntity(HttpStatus.CREATED);
			}
		catch(Exception e){

			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@PutMapping("system-roles/{id}")
	private ResponseEntity updateSystemRole(@PathVariable Integer id, @RequestBody SystemRoleUpdateDto body) {
		try {
			SystemRole role = systemRoleService.findSystemRoleById(id); //throws exc
			role = mapper.map(body, SystemRole.class); 
			role.setId(id);
			systemRoleService.updateSystemRole(role);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("system-roles/{id}")
	private ResponseEntity deleteSystemRole(@PathVariable Integer id) {
		try {
			SystemRole medicine = systemRoleService.findSystemRoleById(id);
			systemRoleService.deleteSystemRole(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
