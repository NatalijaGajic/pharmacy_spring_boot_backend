package com.example.medicine_app.controller;

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

import com.example.medicine_app.dto.MedicineCreationUpdateDto;
import com.example.medicine_app.dto.MedicineDto;
import com.example.medicine_app.dto.MedicineIdsDto;
import com.example.medicine_app.jpa.Medicine;
import com.example.medicine_app.service.MedicineService;

@RestController
public class MedicineController {
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("medicines")
	private ResponseEntity getMedicines(){
		/*
		 * if(name!= null && !name.isEmpty()) { return
		 * medicineRepository.findByNameContainingIgnoreCase(name); }else { return
		 * medicineRepository.findAll(); }
		 */
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Collection<MedicineDto> medicineDtoList = new ArrayList<MedicineDto>();
		for(Medicine medicineEntity: medicineService.findAllMedicines()) {
			medicineDtoList.add(mapper.map(medicineEntity, MedicineDto.class));
		}
		return new ResponseEntity<Collection<MedicineDto>>(medicineDtoList, HttpStatus.OK);
	}
	
	@GetMapping("medicines/{id}")
	private ResponseEntity getMedicineById(@PathVariable Integer id){
		MedicineDto medicineDto = new MedicineDto();
		try {
			Medicine medicine = medicineService.findMedicineById(id);
			medicineDto = mapper.map(medicine, MedicineDto.class);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if(medicine != null) {
//			return new ResponseEntity<>(MedicineService.findBymedicine(medicine), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
		return new ResponseEntity<MedicineDto>(medicineDto, HttpStatus.OK);
	}
	
	@GetMapping("medicines-from-ids")
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Collection<MedicineDto>>(medicineDtoList, HttpStatus.OK);

//		if(medicine != null) {
//			return new ResponseEntity<>(MedicineService.findBymedicine(medicine), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
	}
	
	@PostMapping("medicines")
	private ResponseEntity createMedicines(@RequestBody MedicineCreationUpdateDto medicineCreation){
		
		try {
				Medicine Medicine = mapper.map(medicineCreation, Medicine.class);
				medicineService.createMedicine(Medicine);
				return new ResponseEntity(HttpStatus.CREATED);
			}
		catch(Exception e){
			//TODO: check exception type 
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	@PutMapping("medicines/{id}")
	private ResponseEntity updateMedicine(@PathVariable Integer id, @RequestBody MedicineDto medicineDto) {
		try {
			Medicine medicine = mapper.map(medicineDto, Medicine.class);
			medicine.setId(id);
			medicineService.updateMedicine(medicine);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("medicines/{id}")
	private ResponseEntity deleteMedicine(@PathVariable Integer id) {
		try {
			medicineService.deleteMedicine(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
