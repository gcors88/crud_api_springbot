package com.crud.crm.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crm.application.ClientApplication;
import com.crud.crm.interfaces.DefaultSucessResponse;
import com.crud.crm.infrastructure.databases.entities.ClientEntity;


@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	private ClientApplication clientApplication;
	
	@GetMapping("/")
	public List<ClientEntity> findAll() {
		return this.clientApplication.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<ClientEntity> findById(@PathVariable("id") Long id) {
		return this.clientApplication.findById(id);
		
	}
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClientEntity create(@RequestBody() ClientEntity body) {
		return this.clientApplication.create(body);
	}
	
	@DeleteMapping("/{id}")
	public DefaultSucessResponse delete(@PathVariable("id") Long id) {
		return this.clientApplication.delete(id);
	}
	
	@PutMapping("/{id}")
	public DefaultSucessResponse put(@PathVariable("id") Long id, @RequestBody() ClientEntity body) {
		return this.clientApplication.updateClient(id, body);
	}
}
