package com.PersonalDetails.ServiceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalDetails.model.PersonalDetails;
import com.PersonalDetails.repo.PersonalDetailsRepo;
import com.PersonalDetails.service.PersonalDetailsServices;


@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsServices {
	@Autowired
	private PersonalDetailsRepo personalDetailsRepo;

	@Override
	public PersonalDetails saveData(PersonalDetails personalDetails) {
		// TODO Auto-generated method stub
		return personalDetailsRepo.save(personalDetails);
	}

	@Override
	public List<PersonalDetails> getAllPersonalDetails() {
		// TODO Auto-generated method stub
		return personalDetailsRepo.findAll();
	}

	@Override
	public PersonalDetails getByPersonalId(String personalId) {
		// TODO Auto-generated method stub
		return personalDetailsRepo.findBypersonalId(personalId);
	}

	@Override
	public String deletebyemialid(String emailId) {
		// TODO Auto-generated method stub
		personalDetailsRepo.deleteByemailId(emailId);
		return "Deleted successfully";
	}

	@Override
	public List<PersonalDetails> fetchPersonalsByEmailId(String emailId) {
		// TODO Auto-generated method stub
		return (List<PersonalDetails>) personalDetailsRepo.findByemailId(emailId);
	}
	
	

	
	@Override
	public List<PersonalDetails> updatePersonalDetailsByEmailId(String emailId, PersonalDetails personalDetails) {
	    List<PersonalDetails> existingProducts = personalDetailsRepo.findByemailId(emailId);

	    if (!existingProducts.isEmpty()) {
	        for (PersonalDetails existingProduct : existingProducts) {
	            // Update the fields of existingProduct with the fields from updatedProductDetails
	        	existingProduct.setEmailId(personalDetails.getEmailId());
	            existingProduct.setGender(personalDetails.getGender());
	            existingProduct.setAge(personalDetails.getAge());
	            // ... and so on for other fields
	            
	            // Save the updated product details
	            personalDetailsRepo.save(existingProduct);
	        }

	        return existingProducts;
	    }
	    
	    return null; // Return null or throw an exception if the product is not found
	}
	
	
	
	

	    
	
	
	
	


}
