package com.example.user_alergies_app.jdbc_repository;

import org.springframework.stereotype.Repository;

import com.example.user_alergies_app.model.UserAlergies;

@Repository
public class UserAlergiesJdbcRepositoryImpl implements UserAlergiesJdbcRepository{

	@Override
	public void save(UserAlergies userAlergies) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserAlergies findMedicineByUserId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UserAlergies userAlergies) {
		// TODO Auto-generated method stub
		
	}


}
