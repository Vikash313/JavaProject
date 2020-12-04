package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.SwagRepo;
import com.example.demo.model.Contact;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class AddressBookResource
{
	
	@Autowired
	SwagRepo repo;

	ConcurrentMap<String, Contact> contacts = new ConcurrentHashMap<>();
    
	@GetMapping("/{id}")
	@ApiOperation(value = "Finds Contacts by id",
	notes = "Provide an id to look up specific contact from the address book",
	response = Contact.class)
	
	public Contact getContact(@ApiParam(value = "ID value for the contact you need to retrieve", required = true)@PathVariable String id)
	{
		return contacts.get(id);
	}
	
	@GetMapping("/")
	public List<Contact> getAllContacts() 
	{
		return new ArrayList<Contact>(contacts.values());
	}
	
	@PostMapping("/")
	public Contact addContact(@RequestBody Contact contact)
	{
		contacts.put(contact.getId(), contact);
		return contact;
	}
	
	@PutMapping("/")
	public Contact saveOrUpdateContact(@RequestBody Contact contact)
	{
		repo.save(contact);
		return contact;
	}
	
	@DeleteMapping("/{id}")
	public String  deleteContact(@PathVariable int id)
	{
		Contact c = repo.getOne(id);
		repo.delete(c);
		return "deleted";
		
	}
	
}
