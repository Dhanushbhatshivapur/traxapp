package com.traxcrm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traxcrm.entities.Contact;
import com.traxcrm.repositories.ContactRepository;

@Service
public class ContactSeriveImpl implements ContactService {
	
	@Autowired 
	ContactRepository contactRepo; // to save the record

	@Override
	public void saveContact(Contact contact) {
		contactRepo.save(contact); // save the record
	}

	@Override
	public List<Contact> listAll() {
		List<Contact> contacts = contactRepo.findAll();
		return contacts;
	}

	@Override
	public Contact findConactById(long id) {
		Optional<Contact> contactBy = contactRepo.findById(id);
		//or //Contact contact = contactBy.get();
		return contactBy.get(); // we are converting ContactBy to contact object in one line
	}

}
