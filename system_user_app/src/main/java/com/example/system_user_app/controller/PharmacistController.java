package com.example.system_user_app.controller;

import java.util.ArrayList;
import java.util.Collection;

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

import com.example.system_user_app.dto.PharmacistCreationDTO;
import com.example.system_user_app.dto.PharmacistDTO;
import com.example.system_user_app.dto.PharmacistUpdateDTO;
import com.example.system_user_app.exceptions.InvalidIdException;
import com.example.system_user_app.jdbc_repository.PharmacistJdbcRepository;
import com.example.system_user_app.model.Pharmacist;
import com.example.system_user_app.service.PharmacistService;

@RestController
public class PharmacistController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PharmacistJdbcRepository pharmacistRepository;
	
	@Autowired
	private PharmacistService pharmacistService;
	
	@GetMapping("pharmacists")
	private ResponseEntity<?> getAllPharmacists(@RequestParam(required = false) String username){
		try {
			Collection<Pharmacist> pharmacists = this.pharmacistService.getPharmacists(username);
			Collection<PharmacistDTO> collection = new ArrayList<PharmacistDTO>();
			for(Pharmacist pharmacist: pharmacists) {
				collection.add(mapper.map(pharmacist, PharmacistDTO.class));
			}
			return new ResponseEntity<Collection<PharmacistDTO>>(collection, HttpStatus.OK);
		} catch (Exception e) {
			if(e.getClass().equals(InvalidIdException.class)) {
				return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("pharmacists/{id}")
	private ResponseEntity<?> getPharmacistById(@PathVariable Integer id){
		try {
			Pharmacist pharmacist = this.pharmacistService.getPharmacistById(id);
			PharmacistDTO dto = mapper.map(pharmacist, PharmacistDTO.class);
			return new ResponseEntity<PharmacistDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			if(e.getClass().equals(InvalidIdException.class)) {
				return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//TODO: wrong id for PUT, DELETE, POST
	@PutMapping("pharmacists/{id}")
	private ResponseEntity<?> updatePharmacist(@PathVariable Integer id, @RequestBody PharmacistUpdateDTO body){
		try {
			Pharmacist pharmacist = mapper.map(body, Pharmacist.class);
			pharmacist.setId(id);
			this.pharmacistRepository.save(pharmacist);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
	
	@PostMapping("pharmacists")
	private ResponseEntity<?> createPharmacist(@RequestBody PharmacistCreationDTO body){
		try {
			Pharmacist pharmacist = mapper.map(body, Pharmacist.class);
			pharmacist.getSystemRole().setId(body.getSystemRoleId());;
			this.pharmacistRepository.save(pharmacist);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
	
	@DeleteMapping("pharmacists/{id}")
	private ResponseEntity<?> deletePharmacist(@PathVariable Integer id){
		try {
			Pharmacist pharmacist = this.pharmacistRepository.findById(id);
			this.pharmacistRepository.delete(pharmacist);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}

	}

}
