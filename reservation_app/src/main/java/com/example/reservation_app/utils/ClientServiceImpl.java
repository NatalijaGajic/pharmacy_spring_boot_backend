package com.example.reservation_app.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.pharmacy.dto.ClientDto;

@Service
public class ClientServiceImpl implements ClientService{

	@Override
	public ClientDto getClientById(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8020/clients/"+id.toString();
		ResponseEntity<ClientDto> responseEntity = restTemplate.exchange(uri,
		        HttpMethod.GET,
		        null,
		        ClientDto.class
		      );
 		if(responseEntity.getStatusCode() == HttpStatus.OK) {
 			ClientDto client = responseEntity.getBody();
			return client;
		}else {
			return null;
		}
	}

	@Override
	public void updateClientPenalty() {
		// TODO Auto-generated method stub
		
	}

}
