package learningJUnit5.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import learningJUnit5.DemoUtils;


//to set order of displaying test results
@TestMethodOrder(MethodOrderer.Random.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)


//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(ReplaceUnderScore.Simple.class)
//@DisplayNameGeneration(IndicativeSentences.Simple.class)
public class DemoUtilTest {

	DemoUtils demoUtils;
	
	@BeforeEach
	public void setUp()
	{
		demoUtils = new DemoUtils();
		System.out.println("make new instance of demoutils before each test");
	}

	@Test
	@DisplayName("test adding method")
	public void testAdd()
	{
		int expected= 6;
		int actual = demoUtils.add(4, 2);
		
		assertEquals(expected, actual,"addition is wrong");
	}
	
	
	@Test
	//we can use following to display test name else at top of class use @DisplayNameGenerattion annotation
	@DisplayName("test null method")
	@Order(1)
	//we have used @TestMethodOrder to set order of test.another way to set order of test is to use @order annotation
	
	public void testNullMethod()
	{
		String str1= "hello";
		String str2= null;
		//assertEquals(null, demoUtils.checkNull(str1),"String is not null");  //test will fail
		assertEquals(null, demoUtils.checkNull(str2),"string is null");      //test will pass
	}
	
	
	@AfterEach
	public void setDown()
	{
		System.out.println("all test done successfully");
	}
	
	
	@BeforeAll
	static void beforeallMethod()
	{
		System.out.println("this method will execute once before all remaining methods.the method annotated with @BeforeAll should be static");
	}
	
	@AfterAll
	static void afterallmethod() {
		System.out.println("this method will execute once after all ramaining methods.the method annotated with @AfterAll should be static");
	}
	
	//lets learn assertSame and assertNotSame
	
	@Test
	@DisplayName("same or not")
	@Order(2)
	public void checkSameOrNot()
	{
		//assertSame(demoUtils.getStr(), demoUtils.getStr2(),"both same");
		assertNotSame(demoUtils.getStr(), demoUtils.getStr2(),"both are not same");
	}
	
	//lets check boolean method
	@Test
	@DisplayName("checking boolean method")
	public void checkBoolean()
	{
		assertTrue(demoUtils.checkNum());
	}
	
	
	//lets test list of character is same as following list or not
	List<Character> testList= List.of('a','b','c');
	@Test
	@DisplayName("check give collection is same or not")
	public void testAssertIterableEquals()
	{
		assertIterableEquals(testList, demoUtils.getListOfCharacter(),"testList should be same as list from demoutils class");
	}
	
	

}
