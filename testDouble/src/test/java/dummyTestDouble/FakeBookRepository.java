package dummyTestDouble;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

import com.testDouble.dummyTestDouble.Book;
import com.testDouble.dummyTestDouble.bookRepo;




//as our book service is depend on bookRepo so here we have created fake class of bookRepo
public class FakeBookRepository implements bookRepo {

	//instead of using actual db we can use In-memory db or HashMap or List
	Map<String, Book> bookStore= new HashMap<>();
	
	
	@Override
	public void save(Book book) {
		//to store book in hashmap
		bookStore.put(book.getBookId(), book);
	}

	@Override
	public Collection<Book> findAll() {
		//to get number of books store in bookStore hashMap
		return bookStore.values();
	}

}
