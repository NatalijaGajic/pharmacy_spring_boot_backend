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
import org.springframework.web.bind.annotation.RestController;

import com.example.system_user_app.dto.PharmacistCreationDTO;
import com.example.system_user_app.dto.PharmacistDTO;
import com.example.system_user_app.dto.PharmacistUpdateDTO;
import com.example.system_user_app.jdbc_repository.PharmacistJdbcRepository;
import com.example.system_user_app.model.Pharmacist;
import com.example.system_user_app.model.SystemRole;
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
	private Collection<PharmacistDTO> getAllPharmacists(){
		//return this.pharmacistRepository.findAll();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Collection<Pharmacist> pharmacists = this.pharmacistService.getPharmacists();
		Collection<PharmacistDTO> collection = new ArrayList<PharmacistDTO>();
		for(Pharmacist pharmacist: pharmacists) {
			collection.add(mapper.map(pharmacist, PharmacistDTO.class));
		}
		return collection;
	}
	
	@GetMapping("pharmacists/{id}")
	private ResponseEntity<PharmacistDTO> getPharmacistById(@PathVariable Integer id){
		//return this.pharmacistRepository.findById(id);
		//mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Pharmacist pharmacist = this.pharmacistService.getPharmacistById(id);
		PharmacistDTO dto = mapper.map(pharmacist, PharmacistDTO.class);
		return new ResponseEntity<PharmacistDTO>(dto, HttpStatus.OK);
	}
	
	@PutMapping("pharmacists/{id}")
	private ResponseEntity<?> updatePharmacist(@PathVariable Integer id, @RequestBody PharmacistUpdateDTO body){
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Pharmacist pharmacist = mapper.map(body, Pharmacist.class);
		pharmacist.setId(id);
		this.pharmacistRepository.save(pharmacist);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@PostMapping("pharmacists")
	private ResponseEntity<?> updatePharmacist(@RequestBody PharmacistCreationDTO body){
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Pharmacist pharmacist = mapper.map(body, Pharmacist.class);
		this.pharmacistRepository.save(pharmacist);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}
	
	@DeleteMapping("pharmacists/{id}")
	private ResponseEntity<?> deletePharmacist(@PathVariable Integer id){
		Pharmacist pharmacist = this.pharmacistRepository.findById(id);
		this.pharmacistRepository.delete(pharmacist);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
