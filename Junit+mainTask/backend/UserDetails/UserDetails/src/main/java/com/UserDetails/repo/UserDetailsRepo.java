package com.UserDetails.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.UserDetails.model.UserDetails;

public interface UserDetailsRepo extends MongoRepository<UserDetails, String>{
	
	List<UserDetails> findByfirstName(String firstname);
	UserDetails findByemailId(String emailId);
	
	String deleteByemailId(String emailId);
	
	

}
