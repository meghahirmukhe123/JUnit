package com.ProductDetails.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProductDetails.model.ProductDetails;
import com.ProductDetails.repo.ProductRepo;
import com.ProductDetails.service.ProductServices;

@Service
public class ProductDetailsServiceImpl implements ProductServices{
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public ProductDetails saveProductData(ProductDetails productDetails) {
		// TODO Auto-generated method stub
		return productRepo.save(productDetails);
	}

	@Override
	public List<ProductDetails> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepo.findAll();
	}

	@Override
	public Optional<ProductDetails> getByProductId(String productId) {
		// TODO Auto-generated method stub
		return productRepo.findById(productId);
	}

	

	@Override
	public String deleteByEmailID(String emailId) {
		// TODO Auto-generated method stub
		productRepo.deleteByemailId(emailId);
		return "deleted succeessfully";
	}



	@Override
	public ProductDetails fetchProductDetailsByEmailId(String emailId) {
		
		return productRepo.findByemailId(emailId);
	}


	
	
	@Override
	public ProductDetails updateProductDataByEmailId(String emailId, ProductDetails updatedProductDetails) {
	    ProductDetails existingProduct = productRepo.findByemailId(emailId);
	    
	    if (existingProduct != null) {
	        // Update the fields of existingProduct with the fields from updatedProductDetails
	        existingProduct.setEmailId(updatedProductDetails.getEmailId());
	        existingProduct.setProductName(updatedProductDetails.getProductName());
	        existingProduct.setPrice(updatedProductDetails.getPrice());
	        existingProduct.setDate(updatedProductDetails.getDate());
	        
	        // Save the updated product details
	        return productRepo.save(existingProduct);
	    }
	    return null; // Return null if the product with the given emailId is not found
	}



}
