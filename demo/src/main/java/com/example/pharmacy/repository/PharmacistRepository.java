package com.example.pharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.jpa.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer>, PharmacistCustomRepository{

	Collection<Pharmacist> findByUsernameContainingIgnoreCase(String username);
}
