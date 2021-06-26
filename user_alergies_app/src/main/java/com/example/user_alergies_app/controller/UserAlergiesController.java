package com.example.user_alergies_app.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_alergies_app.dto.UserAlergiesCreationDTO;
import com.example.user_alergies_app.dto.UserAlergiesDeleteDto;
import com.example.user_alergies_app.dto.UserAlergiesDto;
import com.example.user_alergies_app.model.UserAlergies;
import com.example.user_alergies_app.service.UserAlergiesService;

@RestController
public class UserAlergiesController {
	
	@Autowired
	private UserAlergiesService userAlergiesService;
	
	@GetMapping("user-alergies/user/{id}")
	private ResponseEntity<?> getUserAlergies(@PathVariable Integer id){
		try {
			Collection<UserAlergiesDto> userAlergies = this.userAlergiesService.findMedicinesByUserId(id);
			return new ResponseEntity<Collection<UserAlergiesDto>>(userAlergies, HttpStatus.OK);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("user-alergies")
	private ResponseEntity<?> createUserAlergies(@RequestBody UserAlergiesCreationDTO body){
		try {
			Collection<UserAlergies> userAlergies = new ArrayList<UserAlergies>();
			for(Integer medicineId: body.getMedicineIds()) {
				UserAlergies userAlergie = new UserAlergies (body.getUserId(), medicineId);
				userAlergies.add(userAlergie);
			}
			userAlergiesService.create(userAlergies);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
	
	@DeleteMapping("user-alergies/user/{id}")
	private ResponseEntity<?> deleteAlergies(@PathVariable Integer id, @RequestBody UserAlergiesDeleteDto body){
		try {
			for (Integer i: body.getMedicinesId()) {
				UserAlergies ua = new UserAlergies(id, i);
				userAlergiesService.delete(ua);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
 
	}
}
