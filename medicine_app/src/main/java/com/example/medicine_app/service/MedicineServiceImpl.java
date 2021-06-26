package com.example.medicine_app.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicine_app.exceptions.InvalidIdException;
import com.example.medicine_app.jpa.Medicine;
import com.example.medicine_app.repository.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService {
	@Autowired
	private MedicineRepository medicineRepository;
	
	@Override
	public void createMedicine(Medicine medicine) throws Exception {
		medicineRepository.save(medicine);
	}

	@Override
	public void deleteMedicine(Integer id) {
		medicineRepository.deleteById(id);
	}

	@Override
	public Collection<Medicine> findAllMedicines(String name) {
		if(name!= null && !name.isEmpty()) 
		{ 
			return medicineRepository.findByNameContainingIgnoreCase(name); 
		}
		return medicineRepository.findAll();
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) throws Exception {
		return medicineRepository.save(medicine);
	}

	@Override
	public Collection<Medicine> findMedicinesByIds(Collection<Integer> ids) throws Exception {
		Collection<Medicine> medicines = new ArrayList<Medicine>();
		for(Integer id: ids) {
			Medicine medicine = medicineRepository.findById(id).get();
			medicines.add(medicine);
		}
		return medicines;
	}

	@Override
	public Medicine findMedicineById(Integer id) {
		return medicineRepository.findById(id).get();
	}

}
