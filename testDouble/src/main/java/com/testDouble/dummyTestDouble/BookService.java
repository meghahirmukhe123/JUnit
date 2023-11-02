package com.testDouble.dummyTestDouble;

public class BookService {

	private bookRepo repo;
	
	//for demonstration purpose lets create emial service
	private EmailService emailService;
	
	
//we need to pass both repo and emailService dependancy so we have made change in below constructor
	public BookService(bookRepo repo, EmailService emailService) {
		super();
		this.repo = repo;
		this.emailService = emailService;
	}

	// save book data
	public void addBook(Book book) {
		repo.save(book);
	}

	// get count of books stored in database
	public int findNumberOfBooks() {
		return repo.findAll().size();
	}
	
	//others methods which uses emial service
	
	

}
