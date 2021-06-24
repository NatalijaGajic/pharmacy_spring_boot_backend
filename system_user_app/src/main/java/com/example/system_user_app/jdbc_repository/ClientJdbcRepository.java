package com.example.system_user_app.jdbc_repository;

import java.util.Collection;

import com.example.system_user_app.model.Client;
import com.example.system_user_app.model.Pharmacist;

public interface ClientJdbcRepository {

	public void save(Client user);
	public Client findById(Integer id);
	public Collection<Client> findAll();
	public Collection<Client> findByUsernameContainingIgnoreCase(String username);
	public void delete(Client client);
}
