package com.junitIntro.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.junitIntro.model.Person;

@SpringBootTest
class PersonRepoTest {
	
	
	@Autowired
	private PersonRepo personRepo;
	@Test
	void isPersonExitsById()
	{
		Person person = new Person(1234,"Anu9","Pune");
		personRepo.save(person);
		
		Boolean actualResult = personRepo.isPersonExistsById(123);
		assertThat(actualResult).isTrue();
	}
	
	@AfterEach
	void afterEachTest()
	{
		System.out.println("@AfterEach this is will work after each test");
		personRepo.deleteAll();
	}
	
	@BeforeEach
	void beforeEachTest()
	{
		System.out.println("@BeforeEach this will work before each test");
	}

}
