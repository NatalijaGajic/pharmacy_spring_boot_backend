package com.example.medicine_app.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.medicine_app.jpa.Medicine;

@Service
public interface MedicineService {
	void createMedicine(Medicine medicine) throws Exception;
	void deleteMedicine(Integer id);
	Medicine updateMedicine(Medicine  medicine) throws Exception;
	Medicine findMedicineById(Integer id) throws Exception;
	Collection<Medicine> findMedicinesByIds(Collection<Integer> ids);
	Collection<Medicine> findAllMedicines();
}
