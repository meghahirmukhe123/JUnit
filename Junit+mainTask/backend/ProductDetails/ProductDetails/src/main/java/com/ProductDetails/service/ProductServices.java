package com.ProductDetails.service;

import java.util.List;
import java.util.Optional;

import com.ProductDetails.model.ProductDetails;

import lombok.ToString;

public interface ProductServices {
	
	
	//save data
	ProductDetails saveProductData(ProductDetails productDetails);
	
	//get all
	List<ProductDetails> getAllProduct();
	
	//get by product id
	Optional<ProductDetails> getByProductId(String productId);
	
	
	//delete by email id
	String deleteByEmailID(String emailId);
	
	
	//get by email id
	ProductDetails fetchProductDetailsByEmailId(String emailId);	
	
	//update by email id
	ProductDetails updateProductDataByEmailId(String emailId, ProductDetails updatedProductDetails);
	

}
