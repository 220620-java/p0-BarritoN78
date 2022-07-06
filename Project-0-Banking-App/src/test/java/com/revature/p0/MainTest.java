package com.revature.p0;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class MainTest {
	/*ClassVariables*/
	private P0Main main;
	
	@BeforeAll
	public void BeforeAll() {
		main = new P0Main();
	}
	
	@Test
	public void stringPadderTest() {
		/*Local Variables*/
		String test = "test";
		int actLength = 0, expLength = 10;
		
		/*Function*/
		test = main.stringPadder(test, 10);
		actLength = test.length();
		
		/*Test*/
		Assertions.assertEquals(expLength, actLength);
	}
}