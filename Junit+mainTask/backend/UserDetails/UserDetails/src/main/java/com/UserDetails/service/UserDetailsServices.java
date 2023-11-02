package com.UserDetails.service;

import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;


import com.UserDetails.model.UserDetails;

public interface UserDetailsServices {

	// to save data
	UserDetails saveData(UserDetails userDetails);

	// get all
	List<UserDetails> getAll();

	// get by userId
	UserDetails getByUserId(String userId);

	
	// delete by id

	String deleteByEmailId(@PathVariable String emailId);

	// get by email id
	UserDetails fetchUserDetailsByEmailId(String emailId);
	
	//update by email id
	UserDetails updateByEmailId(String emailId, UserDetails userDetails);

}
