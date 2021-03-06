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

import com.example.pharmacy.dto.PharmacistDto;

@Service
public class UserServiceImpl implements UserService{
	@Override
	public PharmacistDto getPharmacistsById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8020/medicines-from-ids";
		ResponseEntity<PharmacistDto> responseEntity = restTemplate.exchange(uri,
		        HttpMethod.GET,
		        null,
		        PharmacistDto.class
		      );
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			PharmacistDto pharmacist = responseEntity.getBody();
			return pharmacist;
		}else {
			return null;
		}
	}
}