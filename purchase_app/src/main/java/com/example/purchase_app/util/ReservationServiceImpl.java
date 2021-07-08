package com.example.purchase_app.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pharmacy.dto.ReservationDto;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Override
	public ReservationDto getReservationById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8040/reservation/" + id.toString();
		ResponseEntity<ReservationDto> responseEntity = restTemplate.exchange(uri,
		        HttpMethod.GET,
		        null,
		        ReservationDto.class
		      );
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ReservationDto reservation = responseEntity.getBody();
			return reservation;
		}else {
			return null;
		}
	}
}