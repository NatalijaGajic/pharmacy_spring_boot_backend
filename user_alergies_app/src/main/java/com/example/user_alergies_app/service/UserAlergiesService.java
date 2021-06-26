package com.example.user_alergies_app.service;

import com.example.user_alergies_app.model.UserAlergies;

public interface UserAlergiesService {
	
	public void save(UserAlergies userAlergies);
	public UserAlergies findMedicineByUserId(Integer id);
	public void delete(UserAlergies userAlergies);

}
