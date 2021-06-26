package com.example.user_alergies_app.util;

import java.util.Collection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user_alergies_app.dto.MedicineDto;

@Service
public class MedicineServiceImpl implements MedicineService{
	
	@Override
	public Collection<MedicineDto> getMedicinesByIds(Collection<Integer> ids) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8030/medicines-from-ids/";
		HttpEntity entity = new HttpEntity<>(ids);

		ResponseEntity responseEntity = restTemplate.exchange(uri,
		        HttpMethod.GET,
		        entity,
		        MedicineDto[].class
		      );
 		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			Collection<MedicineDto> medicines = (Collection<MedicineDto>) responseEntity.getBody();
			return medicines;
		}else {
			return null;
		}
	}
	
	

}
