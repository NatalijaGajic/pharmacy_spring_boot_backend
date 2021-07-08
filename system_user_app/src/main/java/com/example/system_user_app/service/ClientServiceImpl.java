package com.example.system_user_app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.SystemRoleDto;
import com.example.system_user_app.exceptions.InvalidIdException;
import com.example.system_user_app.jdbc_repository.ClientJdbcRepository;
import com.example.system_user_app.model.Client;
import com.example.system_user_app.util.SystemRoleService;

@Service
public class ClientServiceImpl implements ClientService{

	
	@Autowired
	private ClientJdbcRepository clientRepository;

	@Autowired
	private SystemRoleService systemRoleService;
	
	@Override
	public Client getClientById(Integer id) throws Exception {
		Client client = this.clientRepository.findById(id);
		SystemRoleDto role = systemRoleService.getRoleById(client.getSystemRole().getId());
		if(role != null) {
			client.setSystemRole(role);
		}
		else {
			throw new InvalidIdException("Invalid role id");
		}
		return client;
	}

	@Override
	public Collection<Client> getClients(String username) throws Exception {
		Collection<Client> clients;
		if(username!= null && !username.isEmpty()) {
			clients = this.clientRepository.findByUsernameContainingIgnoreCase(username);
		}else {
			clients = this.clientRepository.findAll();
		}
		for(Client client: clients) {
			SystemRoleDto role = systemRoleService.getRoleById(client.getSystemRole().getId());
			if(role != null) {
				client.setSystemRole(role);
			}
			else {
				throw new InvalidIdException("Invalid role id");
			}
		}
		return clients;
	}

}
