package com.crud.crm.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.crud.crm.infrastructure.databases.entities.ClientEntity;
import com.crud.crm.infrastructure.databases.repositories.ClientRepository;
import com.crud.crm.interfaces.DefaultSucessResponse;

import ch.qos.logback.classic.Logger;

@Component()
public class ClientApplication {
	@Autowired
	private ClientRepository clientRepository;
	Logger logger = (Logger) LoggerFactory.getLogger(ClientApplication.class);
	
	public List<ClientEntity> findAll() {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.ASC, "id"));
		orders.add(new Order(Sort.Direction.ASC, "name"));
		return this.clientRepository.findAll(Sort.by(orders));
	}
	
	public Optional<ClientEntity> findById(Long id) {
		Optional<ClientEntity> client = this.clientRepository.findById(id);
		
		if(client.isEmpty()) {
			logger.warn(String.format("Client with id %d not found.", id));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Client with id %d not found.", id));
		}
		
		return client;
	}
	
	public ClientEntity create(ClientEntity data) {
		return this.clientRepository.save(data);
	}
	
	public DefaultSucessResponse delete(Long id) {
		this.findById(id);
		
		this.clientRepository.deleteById(id);
		
		return new DefaultSucessResponse("Cliente deleted with successfully", HttpStatus.OK); 
	}
	
	public DefaultSucessResponse updateClient(Long id,ClientEntity client) {
		this.findById(id);
		client.setId(id);
		this.clientRepository.save(client);
		
		return new DefaultSucessResponse("Cliente updated with successfully", HttpStatus.OK); 
	}
}
