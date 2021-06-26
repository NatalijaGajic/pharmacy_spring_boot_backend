package com.example.reservation_medicine_app.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.medicine_app.dto.MedicineDto;
import com.example.reservation_medicine_app.dto.MedicineIdsDto;

@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Collection<MedicineDto> getMedicinesByIds(MedicineIdsDto medicineIdsDto) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8030/medicines-from-ids";
		MedicineIdsDto md = new MedicineIdsDto();
		  HttpEntity entity = new HttpEntity<>(medicineIdsDto);
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
}