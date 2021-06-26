package com.example.system_user_app.controller;
import java.util.ArrayList;
import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.system_user_app.dto.ClientCreationDTO;
import com.example.system_user_app.dto.ClientDTO;
import com.example.system_user_app.dto.ClientUpdateDTO;
import com.example.system_user_app.dto.PharmacistCreationDTO;
import com.example.system_user_app.dto.PharmacistDTO;
import com.example.system_user_app.dto.PharmacistUpdateDTO;
import com.example.system_user_app.exceptions.InvalidIdException;
import com.example.system_user_app.jdbc_repository.ClientJdbcRepository;
import com.example.system_user_app.jdbc_repository.PharmacistJdbcRepository;
import com.example.system_user_app.model.Client;
import com.example.system_user_app.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ClientJdbcRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("clients")
	private ResponseEntity<?> getAllClients(@RequestParam(required = false) String username){
		try {
			Collection<Client> clients = this.clientService.getClients(username);
			Collection<ClientDTO> collection = new ArrayList<ClientDTO>();
			for(Client client: clients) {
				collection.add(mapper.map(client, ClientDTO.class));
			}
			return new ResponseEntity<Collection<ClientDTO>>(collection, HttpStatus.OK);
		} catch (Exception e) {
			if(e.getClass().equals(InvalidIdException.class)) {
				return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("clients/{id}")
	private ResponseEntity<?> getClientById(@PathVariable Integer id){
		try {
			Client client = this.clientService.getClientById(id);
			ClientDTO dto = mapper.map(client, ClientDTO.class);
			return new ResponseEntity<ClientDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			if(e.getClass().equals(InvalidIdException.class)) {
				return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//TODO: wrong id for PUT, DELETE, POST
	@PutMapping("clients/{id}")
	private ResponseEntity<?> updateClient(@PathVariable Integer id, @RequestBody ClientUpdateDTO body){
		try {
			Client client = mapper.map(body, Client.class);
			client.setId(id);
			this.clientRepository.save(client);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
	
	@PostMapping("clients")
	private ResponseEntity<?> createClient(@RequestBody ClientCreationDTO body){
		try {
			Client client = mapper.map(body, Client.class);
			client.getSystemRole().setId(body.getSystemRoleId());;
			this.clientRepository.save(client);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}
	
	@DeleteMapping("clients/{id}")
	private ResponseEntity<?> deleteClient(@PathVariable Integer id){
		try {
			Client client = this.clientRepository.findById(id);
			this.clientRepository.delete(client);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}

	}
}
