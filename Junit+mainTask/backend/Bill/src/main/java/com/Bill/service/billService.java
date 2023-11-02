package com.Bill.service;

import java.util.List;

import com.Bill.model.userBill;

public interface billService {
	
	userBill saveBill(userBill uBill);
	
	List<userBill> getBillByEmail(String emailId);

}
