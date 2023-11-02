package com.testDouble.dummyTestDouble;

import java.util.Collection;

public interface bookRepo {
	
	void save(Book book);
	Collection<Book> findAll(); 

}
