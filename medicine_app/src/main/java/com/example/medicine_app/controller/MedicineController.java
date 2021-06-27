package com.example.medicine_app.controller;

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

import com.example.medicine_app.dto.MedicineCreationDto;
import com.example.medicine_app.dto.MedicineDto;
import com.example.medicine_app.dto.MedicineIdsDto;
import com.example.medicine_app.dto.MedicineUpdateDto;
import com.example.medicine_app.exceptions.InvalidIdException;
import com.example.medicine_app.jpa.Medicine;
import com.example.medicine_app.service.MedicineService;

@RestController
public class MedicineController {
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("medicines")
	private ResponseEntity getMedicines(@RequestParam(required = false) String name){

		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Collection<MedicineDto> medicineDtoList = new ArrayList<MedicineDto>();
			for(Medicine medicineEntity: medicineService.findAllMedicines(name)) {
				medicineDtoList.add(mapper.map(medicineEntity, MedicineDto.class));
			}
			return new ResponseEntity<Collection<MedicineDto>>(medicineDtoList, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@GetMapping("medicines/{id}")
	private ResponseEntity getMedicineById(@PathVariable Integer id){
		MedicineDto medicineDto = new MedicineDto();
		try {
			Medicine medicine = medicineService.findMedicineById(id);
			medicineDto = mapper.map(medicine, MedicineDto.class);
			return new ResponseEntity<MedicineDto>(medicineDto, HttpStatus.OK);
		} 
		catch (Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("medicines-from-ids")
	private ResponseEntity getMedicinesFromIds(@RequestBody MedicineIdsDto medicineIdsDto){
		Collection<MedicineDto> medicineDtoList = new ArrayList<MedicineDto>();
		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			for(Medicine medicineEntity: medicineService.findMedicinesByIds(medicineIdsDto.getMedicineIds())) {
				medicineDtoList.add(mapper.map(medicineEntity, MedicineDto.class));
			}
			return new ResponseEntity<Collection<MedicineDto>>(medicineDtoList, HttpStatus.OK);
		} 
		catch (Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				//TODO: response body
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage().toString());

			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@PostMapping("medicines")
	private ResponseEntity createMedicines(@RequestBody MedicineCreationDto medicineCreation){
		
		try {
				Medicine Medicine = mapper.map(medicineCreation, Medicine.class);
				medicineService.createMedicine(Medicine);
				return new ResponseEntity(HttpStatus.CREATED);
			}
		catch(Exception e){

			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@PutMapping("medicines/{id}")
	private ResponseEntity updateMedicine(@PathVariable Integer id, @RequestBody MedicineUpdateDto medicineDto) {
		try {
			Medicine medicine = medicineService.findMedicineById(id);
			medicine = mapper.map(medicineDto, Medicine.class);
			medicine.setId(id);
			medicineService.updateMedicine(medicine);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);

			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@DeleteMapping("medicines/{id}")
	private ResponseEntity deleteMedicine(@PathVariable Integer id) {
		try {
			Medicine medicine = medicineService.findMedicineById(id);
			medicineService.deleteMedicine(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			if(e.getClass().equals(NoSuchElementException.class)) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);

			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
}

