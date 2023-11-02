package dummyTestDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.testDouble.dummyTestDouble.Book;
import com.testDouble.dummyTestDouble.BookService;
import com.testDouble.dummyTestDouble.EmailService;
import com.testDouble.dummyTestDouble.bookRepo;

public class dummyTest {

	@Test
	public void demoDummy() {

		// lets create instance of fake bookrepository
		bookRepo bookRepository = new FakeBookRepository();

		EmailService emailService = new DummyEmailService();
		// to test bookservice lets create instance of book service class
		BookService bookService = new BookService(bookRepository, emailService);

		// lets add few book data using above book service
		bookService.addBook(new Book("1", "Mockito in action", 120, LocalDate.now()));
		bookService.addBook(new Book("2", "Junit5 in action", 190, LocalDate.now()));

		// lets test findNumberOfBooks method from bookservice
		assertEquals(2, bookService.findNumberOfBooks());
	}

	
	
	// lets test above things with the help of mockito
	// Note: If we are using below mockito method we dont need to write above stuff
	@Test
	public void demoDummyWithMockito() {
		// to create mock of bookRepository class
		bookRepo bookRepository = Mockito.mock(bookRepo.class);

		//to create mock of email services
		EmailService emailService = Mockito.mock(EmailService.class);
		
		BookService bookService = new BookService(bookRepository,emailService);

		Book book1 = new Book("1", "Mockito in action", 120, LocalDate.now());
		Book book2 = new Book("2", "Junit5 in action", 190, LocalDate.now());

		// add above books in array list
		Collection<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);

		// have to tell to mockito on when and which method should called;
		Mockito.when(bookRepository.findAll()).thenReturn(books);

		assertEquals(2, bookService.findNumberOfBooks());

	}

}
