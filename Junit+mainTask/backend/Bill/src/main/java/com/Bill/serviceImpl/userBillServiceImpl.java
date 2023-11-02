package com.Bill.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bill.model.userBill;
import com.Bill.repo.billRepo;
import com.Bill.service.billService;

@Service
public class userBillServiceImpl implements billService {
	
	@Autowired
	private billRepo repo;

	@Override
	public userBill saveBill(userBill bill) {
		
		return repo.save(bill);
	}

	@Override
	public List<userBill> getBillByEmail(String emailId) {
		
		return repo.findByemailId(emailId);
	}

}
