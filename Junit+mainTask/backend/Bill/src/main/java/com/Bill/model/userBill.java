package com.Bill.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "userBill")
public class userBill {

	@Id
	private String billId;
	private String emailId;
	private String productName;
	private double price;
	private String date;
	private double gst;
	private double priceWithGST;
	private String totalBillRs;

}
