package com.PersonalDetails.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalDetails.model.PersonalDetails;
import com.PersonalDetails.service.PersonalDetailsServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonalDetailsController {

	@Autowired
	private PersonalDetailsServices personalDetailsServices;

	@PostMapping("/savePersonalDetails")
	public ResponseEntity<PersonalDetails> saveData(@RequestBody PersonalDetails personalDetails) throws Exception {
		String tempEmailId = personalDetails.getEmailId();
		if (tempEmailId != null && !tempEmailId.isEmpty()) {
			List<PersonalDetails> existingUsers = personalDetailsServices.fetchPersonalsByEmailId(tempEmailId);
			if (!existingUsers.isEmpty()) {
				throw new Exception("User with this email id already exists");
			}
		}
		PersonalDetails savedPersonalDetails = personalDetailsServices.saveData(personalDetails);
		return ResponseEntity.ok(savedPersonalDetails);
	}
	
	


	// get all
	@GetMapping("/getallpersonaldetails")
	public ResponseEntity<List<PersonalDetails>> getalldata() {
		return ResponseEntity.ok().body(personalDetailsServices.getAllPersonalDetails());
	}

	// get by personal id
	@GetMapping("/getbypersonalid/{personalId}")
	public ResponseEntity<PersonalDetails> getbypersonalid(@PathVariable String personalId) {
		return ResponseEntity.ok().body(personalDetailsServices.getByPersonalId(personalId));
	}

	// get by email id
	@GetMapping("/getpersonaldetailsbyemialId/{emailId}")
	public ResponseEntity<List<PersonalDetails>> getByUserId(@PathVariable String emailId) {
		return ResponseEntity.ok().body(personalDetailsServices.fetchPersonalsByEmailId(emailId));
	}
	
	
	
	// delete by email id
	@DeleteMapping("/deletePersonalDetails/{emailId}")
	public String deleteByEmailID(@PathVariable String emailId) {
		personalDetailsServices.deletebyemialid(emailId);
		return "Personal details deleted successfully";
	}

	// update by emailid
	@PutMapping("/updatebyEmailid/{emailId}")
	public ResponseEntity<List<PersonalDetails>> updateByEmailId(@PathVariable String emailId,
			@RequestBody PersonalDetails personalDetails) {
		List<PersonalDetails> updatePersonalDetails = personalDetailsServices.updatePersonalDetailsByEmailId(emailId,
				personalDetails);

		if (updatePersonalDetails != null) {
			return ResponseEntity.ok().body(updatePersonalDetails);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}


	
	





