package com.example.reservation_app.utils;

import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.ClientDto;

@Service
public interface ClientService {
	public ClientDto getClientById(Integer id);
	public void updateClientPenalty();
	
}
