package com.PersonalDetails.repo;


import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.PersonalDetails.model.PersonalDetails;

public interface PersonalDetailsRepo extends MongoRepository<PersonalDetails, String> {
	
	PersonalDetails findBypersonalId(String personalId);
	List<PersonalDetails> findByemailId(String emailId);

	String deleteByemailId(String emailId);

}
