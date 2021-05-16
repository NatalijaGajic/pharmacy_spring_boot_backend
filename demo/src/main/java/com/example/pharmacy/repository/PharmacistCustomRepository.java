package com.example.pharmacy.repository;

import java.util.Collection;

import com.example.pharmacy.jpa.Pharmacist;

public interface PharmacistCustomRepository {

	Collection<Pharmacist> findAllActivePharmacists();
	Collection<Pharmacist> findAllInactivePharmacists();
}
