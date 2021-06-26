package com.example.system_user_app.service;
import java.util.Collection;
import org.springframework.stereotype.Service;
import com.example.system_user_app.model.Client;

@Service
public interface ClientService {

	public Client getClientById(Integer id) throws Exception;
	public Collection<Client> getClients(String username) throws Exception;
}
