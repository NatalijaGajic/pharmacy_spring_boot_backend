package com.example.reservation_app.utils;

import org.springframework.stereotype.Service;

import com.example.reservation_app.dto.ClientDTO;

@Service
public interface ClientService {
	public ClientDTO getClientById(Integer id);
	public void updateClientPenalty();
	
}
