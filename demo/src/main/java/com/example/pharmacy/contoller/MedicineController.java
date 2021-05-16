package com.example.pharmacy.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pharmacy.jpa.Medicine;
import java.util.Collection;



import com.example.pharmacy.repository.MedicineRepository;

@RestController
public class MedicineController {

	@Autowired
	private MedicineRepository medicineRepository;
	
	//TODO filtering by quantity and price :)
	@GetMapping("medicines")
	private Collection<Medicine> getMedicines(@RequestParam(required = false) String name){
		if(name!= null && !name.isEmpty()) {
			return medicineRepository.findByNameContainingIgnoreCase(name);
		}else {
			return medicineRepository.findAll();
		}
	}
	
	@PostMapping("medicines")
	private ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine){
		medicineRepository.save(medicine);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
