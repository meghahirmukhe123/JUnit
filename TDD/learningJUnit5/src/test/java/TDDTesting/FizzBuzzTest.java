package TDDTesting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import TDD.FizzBuzz;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FizzBuzzTest {
	
	//if number is divisible by 3 ,print Fizz
	//if number is divisible by 5 , print Buzz
	//if number is divisible by 3 and 5 , print FizzBuzz
	//if number is NOT divisible by 3 or 5 , then print that number itself

	
	@Test
	@DisplayName("Divisible by 3")
	@Order(1)
	void testForDivisibleByThree()
	{
		String expected = "Fizz";
		assertEquals(expected, FizzBuzz.compute(3),"should return Fizz");
	}
	
	

	
	@Test
	@DisplayName("Divisible by 5")
	@Order(2)
	void testForDivisibleByFive()
	{
		String expected = "Buzz";
		assertEquals(expected, FizzBuzz.compute(5),"should return Buzz");
	}
	
	@Test
	@DisplayName("Divisible by 3 and 5")
	@Order(3)
	void testForDivisibleByFiveAndThree()
	{
		String expected = "FizzBuzz";
		assertEquals(expected, FizzBuzz.compute(15),"should return FizzBuzz");
	}
	
	
	@Test
	@DisplayName("Not Divisible by 3 or 5")
	@Order(4)
	void testForNotDivisibleByFiveOrThree()
	{
		String expected = "1";
		assertEquals(expected, FizzBuzz.compute(1),"should return 1");
	}
	
	
	
	@DisplayName("testing with small data file")
	@ParameterizedTest(name="value={0}, expected={1}")
	@CsvFileSource(resources ="/small-data-test.csv")
	@Order(5)
	void testSmallDataTest(int value,String expected)
	{
		assertEquals(expected, FizzBuzz.compute(value));
	}
}
