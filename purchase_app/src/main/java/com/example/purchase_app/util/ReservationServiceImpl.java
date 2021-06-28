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
import com.example.purchase_app.dto.PharmacistDTO;
import com.example.purchase_app.dto.ReservationDTO;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Override
	public ReservationDTO getReservationById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8040/reservation/" + id.toString();
		ResponseEntity<ReservationDTO> responseEntity = restTemplate.exchange(uri,
		        HttpMethod.GET,
		        null,
		        ReservationDTO.class
		      );
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ReservationDTO reservation = responseEntity.getBody();
			return reservation;
		}else {
			return null;
		}
	}
}