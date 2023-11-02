package com.UserDetails.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UserDetails.model.PersonalDetails;

@FeignClient(name = "PERSONALDETAILS-SERVICE")
public interface PersonalDetailsService {
	
	@GetMapping("/getpersonaldetailsbyemialId/{emailId}")
//	List<PersonalDetails> getPersonalDetails(@PathVariable String emailId);
	
	List<PersonalDetails> getPersonalDetails(@PathVariable String emailId);

}
