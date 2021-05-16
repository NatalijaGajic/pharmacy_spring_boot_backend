package com.example.pharmacy.contoller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pharmacy.jpa.*;
import com.example.pharmacy.repository.ClientRepository;
import com.example.pharmacy.repository.PharmacistRepository;
import com.example.pharmacy.repository.SystemUserRepository;

@RestController
public class SystemUserController {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@GetMapping("pharmacists")
	public ResponseEntity<?> getPharmacists(@RequestParam(required = false) String username, @RequestParam(required = false) String active){
		if(username == null) {
			if(active!= null && !active.isEmpty()) {
				if(active.equals("true")){
					return new ResponseEntity<> (pharmacistRepository.findAllActivePharmacists(), HttpStatus.OK);
				}else if(active.equals("false")){
					return new ResponseEntity<> (pharmacistRepository.findAllInactivePharmacists(), HttpStatus.OK);
				}else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}else {
				return new ResponseEntity<>(pharmacistRepository.findAll(), HttpStatus.OK);
			}
			
		}else {
			return new ResponseEntity<> (pharmacistRepository.findByUsernameContainingIgnoreCase(username), HttpStatus.OK);
			
		}
	}
	
	@GetMapping("clients")
	public Collection<Client> getClients(@RequestParam(required = false) String username){
		if(username == null) {
			return clientRepository.findAll();
		}else {
			return clientRepository.findByUsernameContainingIgnoreCase(username);
			
		}
	}
	
	@PostMapping("pharmacists")
	public ResponseEntity<Pharmacist> createPharmacist(@RequestBody Pharmacist pharmacist){
		Pharmacist p = pharmacistRepository.save(pharmacist);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping("clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client){
		Client c = clientRepository.save(client);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
}
