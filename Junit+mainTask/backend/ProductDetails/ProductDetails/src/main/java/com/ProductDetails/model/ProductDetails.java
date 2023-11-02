package com.ProductDetails.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "productDetails")
public class ProductDetails {
	
	@Id
	private String productId;
	
	private String emailId;
	private String productName;
	private long price;
	private String date;

}
