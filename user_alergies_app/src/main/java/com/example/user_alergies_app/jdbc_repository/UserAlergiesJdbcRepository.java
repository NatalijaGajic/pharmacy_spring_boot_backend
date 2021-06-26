package com.example.user_alergies_app.jdbc_repository;

import java.util.Collection;

import com.example.user_alergies_app.model.UserAlergies;

public interface UserAlergiesJdbcRepository {

	public void save(UserAlergies userAlergies);
	public UserAlergies findMedicineByUserId(Integer id);
	public void delete(UserAlergies userAlergies);
}
