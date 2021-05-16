package com.example.system_user_app.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.example.system_user_app.model.Pharmacist;

@Service
public interface PharmacistService {

	public Pharmacist getPharmacistById(Integer id);
	public Collection<Pharmacist> getPharmacists();
}
