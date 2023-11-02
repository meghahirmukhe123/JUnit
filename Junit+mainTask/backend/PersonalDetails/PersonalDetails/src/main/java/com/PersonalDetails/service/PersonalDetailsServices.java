package com.PersonalDetails.service;

import java.util.List;


import com.PersonalDetails.model.PersonalDetails;



public interface PersonalDetailsServices {

	// save data
	PersonalDetails saveData(PersonalDetails personalDetails);

	// get all
	List<PersonalDetails> getAllPersonalDetails();

	// get by personal id
	PersonalDetails getByPersonalId(String personalId);

	// get by email id
	List<PersonalDetails> fetchPersonalsByEmailId(String emailId);
	

	

	// delete by personal id
	String deletebyemialid(String emailId);

	// update by email id
	List<PersonalDetails> updatePersonalDetailsByEmailId(String emailId, PersonalDetails personalDetails);
	


	


}
