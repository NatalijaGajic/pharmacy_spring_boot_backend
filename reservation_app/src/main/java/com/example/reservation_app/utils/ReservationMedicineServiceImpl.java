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

import com.example.pharmacy.dto.ReservationMedicineDto;
import com.example.reservation_app.dto.ReservationMedicineCreationDto;

@Service
public class ReservationMedicineServiceImpl implements ReservationMedicineService{

	@Override
	public Collection<ReservationMedicineDto> getReservationMedicine(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8050/reservation-medicines/reservation/"+id.toString();
		ResponseEntity<ReservationMedicineDto[]> responseEntity = restTemplate.exchange(uri,
		        HttpMethod.GET,
		        null,
		        ReservationMedicineDto[].class
		      );
 		if(responseEntity.getStatusCode() == HttpStatus.OK) {
 			ReservationMedicineDto[] medicineArray = responseEntity.getBody();
 			Collection<ReservationMedicineDto> medicines =  new ArrayList<ReservationMedicineDto>(Arrays.asList(medicineArray));
			return medicines;
		}else {
			return null;
		}
	}

	@Override
	public boolean createReservationMedicine(ReservationMedicineCreationDto body) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity entity = new HttpEntity<>(body);
		String uri = "http://localhost:8050/reservation-medicines";
		ResponseEntity responseEntity = restTemplate.exchange(uri,
		        HttpMethod.POST,
		        entity,
		        Void.class
		      );
 		if(responseEntity.getStatusCode() == HttpStatus.OK) {
 			return true;
 		}
 		return false;
	}

}
