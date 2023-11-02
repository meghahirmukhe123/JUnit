package com.http_methods.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
	private studentrepo repo;

	// add data
	@PostMapping("/http")
	public student adddata(@RequestBody student Student) {
		return repo.save(Student);
	}

	// to get data
	@GetMapping("/http")
	public List<student> getdata() {
		return repo.findAll();
	}
	
	//get by student id
	@GetMapping("/http/{stuid}")
	public student getByStudentID(@PathVariable String stuid)
	{
		return repo.findBystuid(stuid);
	}

	// to update data
//	@PutMapping("/http/{stuid}")
//	public String updatedata(@RequestBody student stu, @PathVariable String stuid) {
//		repo.save(stu);
//		return "Updated successfully!!";
//	}
	
	@PutMapping("/http/{stuid}")
	public student updateStudentDataById(@RequestBody student stu) throws Exception
	{
		if(stu ==null || stu.getStuid() ==null)
		{
			throw new Exception("student or student id must not be null");
		}
		
		Optional<student> optionalStudent = repo.findById(stu.getStuid());
		
		if(!optionalStudent.isPresent())
		{
			throw new Exception("student with id: "+stu.getStuid()+" does not exists");  
			
		}
		
		student existingStudentRecord = optionalStudent.get();
		existingStudentRecord.setStuid(stu.getStuid());
		existingStudentRecord.setStuname(stu.getStuname());
		existingStudentRecord.setStufees(stu.getStufees());
		
		return repo.save(existingStudentRecord);
	}

	// to delete data
	@DeleteMapping("/http/{stuid}")
	public String deletedata(@PathVariable String stuid)

	{
		repo.deleteById(stuid);
		return "Record deleted";
	}

}
