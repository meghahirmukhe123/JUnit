package com.junitIntro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junitIntro.model.Person;
import com.junitIntro.repo.PersonRepo;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepo repo;
	
	public List<Person> getAllPerson()
	{
		return this.repo.findAll();
	}

}
