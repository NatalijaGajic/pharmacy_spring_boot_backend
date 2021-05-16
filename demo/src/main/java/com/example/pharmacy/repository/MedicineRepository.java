package com.example.pharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.jpa.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>{

	Collection<Medicine> findByNameContainingIgnoreCase(String name);

}
