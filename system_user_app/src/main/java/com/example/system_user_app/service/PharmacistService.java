package com.example.system_user_app.service;

import java.util.Collection;

import com.example.system_user_app.model.Pharmacist;

public interface PharmacistService {

	public Pharmacist getPharmacistById(Integer id);
	public Collection<Pharmacist> getPharmacists();
}
