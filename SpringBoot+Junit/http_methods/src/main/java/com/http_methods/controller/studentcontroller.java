package com.http_methods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.http_methods.entity.student;
import com.http_methods.repo.studentrepo;

@RestController
public class studentcontroller {
	
	@Autowired
	private studentrepo Studentrepo;
	
	//add data 
	@PostMapping("/http")
	public student adddata(@RequestBody student Student)
	{
		return Studentrepo.save(Student);
	}
	
	//to get data
	@GetMapping("/http")
	public Object getdata()
	{
		return Studentrepo.findAll();
	}
	
	//to update data
	@PutMapping("/http/{stuid}")
	public String updatedata(@RequestBody student stu,@PathVariable String stuid)
	{
		Studentrepo.save(stu);
		return "Updated successfully!!";
	}
	
	//to delete data
	@DeleteMapping("/http/{stuid}")
	public String deletedata(@PathVariable String stuid)
	
	{
		Studentrepo.deleteById(stuid);
		return "Record deleted";
	}
	
	
}
