package com.Bill.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Bill.model.userBill;

public interface billRepo extends MongoRepository<userBill, String>{
	
	List<userBill> findByemailId(String emailId);

}
