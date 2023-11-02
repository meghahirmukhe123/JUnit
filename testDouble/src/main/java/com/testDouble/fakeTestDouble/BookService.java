package com.testDouble.fakeTestDouble;

public class BookService {

	private bookRepo repo;

	public BookService(bookRepo repo) {

		this.repo = repo;
	}

	// save book data
	public void addBook(Book book) {
		repo.save(book);
	}

	// get count of books stored in database
	public int findNumberOfBooks() {
		return repo.findAll().size();
	}

}
