package com.example.system_user_app.jdbc_repository;

import java.util.Collection;

import com.example.system_user_app.model.Pharmacist;

public interface PharmacistJdbcRepository {

	public void save(Pharmacist pharmacist);
	public Pharmacist findById(Integer id);
	public Collection<Pharmacist> findAll();
	public Collection<Pharmacist> findAllActivePharmacists();
	public Collection<Pharmacist> findAllInactivePharmacists();
	public Collection<Pharmacist> findByUsernameContainingIgnoreCase(String username);
	public void delete(Pharmacist pharmacist);

}
