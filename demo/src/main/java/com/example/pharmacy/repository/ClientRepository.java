package com.example.pharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pharmacy.jpa.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Collection<Client> findByUsernameContainingIgnoreCase(String username);
}
