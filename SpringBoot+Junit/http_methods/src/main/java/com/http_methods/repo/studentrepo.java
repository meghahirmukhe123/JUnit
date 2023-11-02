package com.http_methods.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.http_methods.entity.student;

public interface studentrepo extends MongoRepository<student, String> {

}
