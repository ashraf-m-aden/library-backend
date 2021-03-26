package com.ashraf.library.services;

import java.util.List;

import com.ashraf.library.entity.Client;


public interface ClientService {

	
	public List<Client> findAll();
	public Client save(Client client);
	public Client findClient(int id);
	public String deleteClient(int id);
}
