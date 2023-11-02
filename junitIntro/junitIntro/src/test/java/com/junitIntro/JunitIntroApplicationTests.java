package com.junitIntro;



import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JunitIntroApplicationTests {
	
	private Calculator c = new Calculator();

	@Test
	void contextLoads() {
	}
	
	@Test
	void testSumFromCalculator() {
		
		//expected result
		int expect = 12;
		
		
		//actual result
		int actual=c.doSum(3, 4, 5);
		
		assertThat(actual).isEqualTo(expect);
	}
	
	
	@Test
	void testProductFromCalculator() {
		
		//expected result
		int expect = 8;
		
		
		//actual result
		int actual=c.doMult(2, 4);
		
		assertThat(actual).isEqualTo(expect);
	}

}
