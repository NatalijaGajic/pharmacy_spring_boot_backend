package com.example.user_alergies_app.service;

import java.util.Collection;

import com.example.user_alergies_app.dto.UserAlergiesDto;
import com.example.user_alergies_app.model.UserAlergies;

public interface UserAlergiesService {
	
	public void create(Collection<UserAlergies> userAlergies) throws Exception;
	public Collection<UserAlergiesDto> findMedicinesByUserId(Integer id) throws Exception;
	public void delete(UserAlergies userAlergies);

}
