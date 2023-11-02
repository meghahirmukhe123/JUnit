package com.ProductDetails.repo;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.ProductDetails.model.ProductDetails;

public interface ProductRepo extends MongoRepository<ProductDetails, String> {
	
	ProductDetails findByemailId(String emailId);

	String deleteByemailId(String emailId);
}
