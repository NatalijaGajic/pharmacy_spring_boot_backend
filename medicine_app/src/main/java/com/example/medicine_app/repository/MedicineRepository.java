package com.example.medicine_app.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicine_app.jpa.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>{

	Collection<Medicine> findByNameContainingIgnoreCase(String name);
}

