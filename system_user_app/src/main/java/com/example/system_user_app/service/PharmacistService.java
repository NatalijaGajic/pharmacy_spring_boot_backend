package com.example.system_user_app.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.system_user_app.model.Pharmacist;

@Service
public interface PharmacistService {

	public Pharmacist getPharmacistById(Integer id) throws Exception;
	public Collection<Pharmacist> getPharmacists(String username) throws Exception;
}
