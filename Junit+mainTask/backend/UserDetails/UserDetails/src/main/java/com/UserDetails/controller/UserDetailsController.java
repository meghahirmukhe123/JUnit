package com.UserDetails.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.UserDetails.model.UserDetails;
import com.UserDetails.repo.UserDetailsRepo;
import com.UserDetails.service.UserDetailsServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserDetailsController {

	@Autowired
	private UserDetailsServices userDetailsServices;

	@Autowired
	private UserDetailsRepo repo;

	// save data
	@PostMapping("/save")
	public ResponseEntity<UserDetails> saveUserData(@RequestBody UserDetails userDetails) throws Exception {
		String tempEmailId = userDetails.getEmailId();
		if (tempEmailId != null && !"".equals(tempEmailId)) {

			UserDetails pro = userDetailsServices.fetchUserDetailsByEmailId(tempEmailId);
			if (pro != null) {
				throw new Exception("user with this email id is already exits");

			}

		}
		return ResponseEntity.ok().body(userDetailsServices.saveData(userDetails));
	}

	// get all
	@GetMapping("/getall")

	public ResponseEntity<List<UserDetails>> getAllUsers() {
		return ResponseEntity.ok().body(userDetailsServices.getAll());
	}

	// get by user id
	@GetMapping("/getbyid/{userId}")
	public ResponseEntity<UserDetails> getByUserId(@PathVariable String userId) {
		return ResponseEntity.ok().body(userDetailsServices.getByUserId(userId));
	}

	// get by user id
//	@GetMapping("/getbyemailid/{emailId}")
//	public ResponseEntity<UserDetails> getbyemailid(@PathVariable String emailId) {
//		return ResponseEntity.ok().body(userDetailsServices.fetchUserDetailsByEmailId(emailId));
//	}

	@GetMapping("/getbyemailid/{emailId}")
	public ResponseEntity<UserDetails> getbyemailid(@PathVariable String emailId) {
		UserDetails userDetails = userDetailsServices.fetchUserDetailsByEmailId(emailId);
		if (userDetails == null || userDetails.getUserId() == null) {
			// Handle the case where userDetails is empty (null or userId is null)
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(userDetails);
	}

	// update by email id
	@PutMapping("/update/{emailId}")
	public ResponseEntity<UserDetails> updateByEmailId(@PathVariable String emailId,
			@RequestBody UserDetails userDetails) {
		UserDetails existingUser = userDetailsServices.fetchUserDetailsByEmailId(emailId);

		if (existingUser == null) {
			return ResponseEntity.notFound().build();
		}

		existingUser.setFirstName(userDetails.getFirstName());
		existingUser.setLastName(userDetails.getLastName());
		existingUser.setCity(userDetails.getCity());
		existingUser.setPinCode(userDetails.getPinCode());

		UserDetails updatedUser = userDetailsServices.saveData(existingUser);
		return ResponseEntity.ok(updatedUser);
	}

	// delete by email ID
	@DeleteMapping("/delete/{emailId}")
	public ResponseEntity<Map<String, String>> deleteByEmailID(@PathVariable String emailId) {
		userDetailsServices.deleteByEmailId(emailId);

		Map<String, String> response = new HashMap<>();
		response.put("message", "User details deleted successfully");

		return ResponseEntity.ok(response);
	}

	// delete by user id
	@DeleteMapping("/deleted/{userId}")
	public String delete(@PathVariable String userId)
	{
		repo.deleteById(userId);
		return "delete by user id";
		
	}

}
