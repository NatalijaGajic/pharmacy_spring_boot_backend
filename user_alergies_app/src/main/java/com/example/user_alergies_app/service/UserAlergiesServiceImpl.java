package com.example.user_alergies_app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.dto.MedicineDto;
import com.example.pharmacy.dto.MedicineIdsDto;
import com.example.user_alergies_app.dto.UserAlergiesDto;
import com.example.user_alergies_app.jdbc_repository.UserAlergiesJdbcRepository;
import com.example.user_alergies_app.model.UserAlergies;
import com.example.user_alergies_app.util.MedicineService;

@Service
public class UserAlergiesServiceImpl implements UserAlergiesService{
	
	@Autowired
	private UserAlergiesJdbcRepository userAlergiesRepository;

	@Autowired
	private MedicineService medicineService;
	
	@Override
	public void create(Collection<UserAlergies> userAlergies) throws Exception {
		for(UserAlergies userAlergie: userAlergies) {
			userAlergiesRepository.save(userAlergie);
		}	
	}

	@Override
	public Collection<UserAlergiesDto> findMedicinesByUserId(Integer id) throws Exception {
		List<UserAlergies> userAlergies = (List<UserAlergies>) userAlergiesRepository.findMedicineByUserId(id);
		Collection<Integer> ids = new ArrayList<Integer>();
		
		for (UserAlergies userAlergie:userAlergies) {
			  ids.add(userAlergie.getMedicineId());
		}
		MedicineIdsDto medicineIdsDto = new MedicineIdsDto();
		medicineIdsDto.setMedicineIds(ids);
		Collection<MedicineDto> medicines = medicineService.getMedicinesByIds(medicineIdsDto);
		Collection<UserAlergiesDto> userAlergiesDtos = new ArrayList<UserAlergiesDto>();
		Integer index = 0;
		for (MedicineDto med: medicines) {
			UserAlergiesDto userAlergiesDto = new UserAlergiesDto(med);
			userAlergiesDtos.add(userAlergiesDto);
			index ++;
		}
		return userAlergiesDtos;
	}

	@Override
	public void delete(UserAlergies userAlergies) {
		userAlergiesRepository.delete(userAlergies);
		
	}


}
