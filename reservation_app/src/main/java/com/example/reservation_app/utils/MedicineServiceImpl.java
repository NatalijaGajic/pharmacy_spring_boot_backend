package com.example.reservation_app.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pharmacy.dto.MedicineDto;
import com.example.pharmacy.dto.MedicineIdsDto;
import com.example.pharmacy.dto.MedicineUpdateDto;


@Service
public class MedicineServiceImpl implements MedicineService{

	@Override
	public Collection<MedicineDto> getMedicinesFromIds(MedicineIdsDto body) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8030/medicines-from-ids";
		MedicineIdsDto md = new MedicineIdsDto();
		HttpEntity entity = new HttpEntity<>(body);
		ResponseEntity<MedicineDto[]> responseEntity = restTemplate.exchange(uri,
		        HttpMethod.POST,
		        entity,
		        MedicineDto[].class
		      );
 		if(responseEntity.getStatusCode() == HttpStatus.OK) {
 			MedicineDto[] medicineArray = responseEntity.getBody();
			Collection<MedicineDto> medicines =  new ArrayList<MedicineDto>(Arrays.asList(medicineArray));
			return medicines;
		}else {
			return null;
		}
	}

	@Override
	public boolean updateMedicines(MedicineUpdateDto body, Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8030/medicines/"+id.toString(); 
		HttpEntity entity = new HttpEntity<>(body);
		ResponseEntity responseEntity = restTemplate.exchange(uri,
		        HttpMethod.PUT,
		        entity,
		        Void.class
		      );
 		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
	}

}
