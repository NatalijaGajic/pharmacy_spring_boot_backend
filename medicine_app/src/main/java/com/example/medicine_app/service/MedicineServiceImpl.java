package com.example.medicine_app.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Collection<Medicine> findAllMedicines() {
		return medicineRepository.findAll();
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) throws Exception {
		return medicineRepository.save(medicine);
	}



	@Override
	public Collection<Medicine> findMedicinesByIds(Collection<Integer> ids) {
		Collection<Medicine> medicines = new ArrayList<Medicine>();
		for(Integer id: ids) {
			medicines.add(medicineRepository.getOne(id));
		}
		return medicines;
	}

	@Override
	public Medicine findMedicineById(Integer id) throws Exception {
		return medicineRepository.findById(id).get();
	}

}
