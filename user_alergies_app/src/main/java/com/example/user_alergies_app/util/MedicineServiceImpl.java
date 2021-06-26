package com.example.user_alergies_app.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user_alergies_app.dto.MedicineDto;
import com.example.user_alergies_app.dto.MedicineIdsDto;

@Service
public class MedicineServiceImpl implements MedicineService{
	
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
