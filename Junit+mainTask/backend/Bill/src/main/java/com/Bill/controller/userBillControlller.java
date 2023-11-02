package com.Bill.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Bill.model.userBill;
import com.Bill.service.billService;

@RestController
public class userBillControlller {

    @Autowired
    private billService service;

    @PostMapping("/save")
    public ResponseEntity<userBill> saveUserBill(@RequestBody userBill requestBill) {
        // Create a new userBill object with the desired fields
        userBill bill = new userBill();
        bill.setEmailId(requestBill.getEmailId());
        bill.setProductName(requestBill.getProductName());
        bill.setPrice(requestBill.getPrice());
        bill.setDate(requestBill.getDate());
        bill.setGst(requestBill.getGst());

        // Calculate priceWithGST and totalBill
        double priceWithGST = bill.getPrice() * (bill.getGst() / 100);
        double totalBill = bill.getPrice() + priceWithGST;
        bill.setPriceWithGST(priceWithGST);

        // Format totalBill with 'Rs' and set it to totalBillRs
        String totalBillRs = totalBill + " Rs";
        bill.setTotalBillRs(totalBillRs);

        // Now, you can save the modified bill object
        return ResponseEntity.ok().body(service.saveBill(bill));
    }

    
    //get by email id
    @GetMapping("/getbyemailid/{emailId}")
    public ResponseEntity<List<userBill>> getByUserEmailId(@PathVariable String emailId) {
        return ResponseEntity.ok().body(service.getBillByEmail(emailId));
    }
}
