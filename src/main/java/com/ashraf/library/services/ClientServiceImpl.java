package com.ashraf.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashraf.library.dao.ClientRepository;
import com.ashraf.library.entity.Book;
import com.ashraf.library.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepo;

	// inject with the constructor
	@Autowired
	public ClientServiceImpl(ClientRepository clientRepo) {
		this.clientRepo = clientRepo;
	}

	@Override
	@Transactional
	public List<Client> findAll() {

		return this.clientRepo.findAll();

	}

	@Override
	@Transactional
	public Client save(Client client) {

		return this.clientRepo.save(client);
	}

	@Override
	@Transactional
	public Client findClient(int id) {

		Optional<Client> result = this.clientRepo.findById(id);
		Client client = null;
		if (result.isPresent()) {
			client = result.get();
		} else {
			throw new RuntimeException("Did not find book id " + id);
		}
		return client;
	}

	@Override
	@Transactional
	public String deleteClient(int id) {

		clientRepo.deleteById(id);
		return "Deleted book id " + id;
	}

}
