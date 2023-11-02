package com.ProductDetails.controller;

import java.util.List;
import java.util.Optional;

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

import com.ProductDetails.model.ProductDetails;
import com.ProductDetails.service.ProductServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductDetailsController {
	
	@Autowired
	private ProductServices productServices;
	
	//save data
	@PostMapping("/saveProductDetails")
	public ResponseEntity<ProductDetails> saveData(@RequestBody ProductDetails productDetails) throws Exception
	{
		String tempEmailId= productDetails.getEmailId();
		if(tempEmailId !=null && !"".equals(tempEmailId))
		{
			
			ProductDetails pro=productServices.fetchProductDetailsByEmailId(tempEmailId);
			if(pro != null)
			{
				throw new Exception("user with this email id is already exits");
			}
			
		}
		return ResponseEntity.ok().body(productServices.saveProductData(productDetails));
	}
	
	//get all
	@GetMapping("/getallproducts")
	public ResponseEntity<List<ProductDetails>> getall()
	{
		return ResponseEntity.ok().body(productServices.getAllProduct());
	}
	
	//get by product id
	@GetMapping("/getbyproductid/{productId}")
	public ResponseEntity<Optional<ProductDetails>> getByproductId(@PathVariable String productId)
	{
		return ResponseEntity.ok().body(productServices.getByProductId(productId));
		
	}

	//get by email id
	@GetMapping("/getproductbyemailid/{emailId}")
	public ResponseEntity<ProductDetails> getByUserId(@PathVariable String emailId)
	{
		return ResponseEntity.ok().body(productServices.fetchProductDetailsByEmailId(emailId));
	}
	
	//delete by email id
	@DeleteMapping("/deleteProductdetails/{emailId}")
	public String deleteByEmailId(@PathVariable String emailId)
	{
		productServices.deleteByEmailID(emailId);
		return "Product details deleted successfully";
	}
	
	
	//update by emailid
	@PutMapping("/updatebyemailid/{emailId}")
    public ResponseEntity<ProductDetails> updateByEmailId(
        @PathVariable String emailId,
        @RequestBody ProductDetails updatedProductDetails
    ) {
        ProductDetails updatedProduct = productServices.updateProductDataByEmailId(emailId, updatedProductDetails);
        
        if (updatedProduct != null) {
            return ResponseEntity.ok().body(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
