package com.revature.p0;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.p0.data.AccountDAO;
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AccountTest {
	private ByteArrayInputStream replace;
	@InjectMocks
	private Account acc = new Account(208);
	
	@Mock
	private AccountDAO sql;
	
	@Mock
	private Transaction trans;
	
	@BeforeAll
	public void beforeAll() {
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeEach
	/*User #208 is the test user
	 * Creating new instance with current info before every test*/
	public void beforeEach() {
		acc = new Account(208);
	}
	
	@Test
	/*Should return true and the type should be "Checkings"
	 *The mock input is "C" for the checkings type*/
	public void accountTypeChecking() {
		/*Local Variables*/
		Boolean result;
		String expected = "Checkings", actual = "";
		
		/*Mocks*/
		replace = new ByteArrayInputStream("C".getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = acc.accountTypeInput();
		actual = acc.getType();
		
		/*Tests*/
		Assertions.assertEquals(true, result);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	/*Should return true and the type should be "Savings"
	 *The mock input is "S" for the savings type*/
	public void accountTypeSavings() {
		/*Local Variables*/
		Boolean result;
		String expected = "Savings", actual = "";
		
		/*Mocks*/
		replace = new ByteArrayInputStream("S".getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = acc.accountTypeInput();
		actual = acc.getType();
		
		/*Tests*/
		Assertions.assertEquals(true, result);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	/*Should return false and the type should be "", the default
	 *The mock input is invalid followed by "X" to cancel creation
	 *Both the invalid command and cancel functions are tested*/
	public void accountTypeInvalidAndCancel() {
		/*Local Variables*/
		Boolean result;
		String expected = "", actual = "";
		
		/*Mocks*/
		replace = new ByteArrayInputStream(("Invalid Input" + System.lineSeparator() + "X").getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = acc.accountTypeInput();
		actual = acc.getType();
		
		/*Tests*/
		Assertions.assertEquals(false, result);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	/*Should return true and AccNotes should be the input
	 *The mock input would pass validation
	 *Note: AccNotes is the account name, just didnt want to refactor in the DB*/
	public void accountNameValid() {
		/*Local Variables*/
		Boolean result;
		String expected = "Testing", actual = "";
		
		/*Mocks*/
		replace = new ByteArrayInputStream(("Testing").getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = acc.accountNameInput();
		actual = acc.getAccNotes();
		
		/*Tests*/
		Assertions.assertEquals(true, result);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	/*Should return false and AccNotes should be "". the default
	 *The mock input is 26 characters long
	 *Validation limits to 25*/
	public void accountNameTooLongAndCancel() {
		/*Local Variables*/
		Boolean result;
		String expected = "", actual = "";
		
		/*Mocks*/
		replace = new ByteArrayInputStream(("12345678901234567890123456" + System.lineSeparator() + "X").getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = acc.accountNameInput();
		actual = acc.getAccNotes();
		
		/*Tests*/
		Assertions.assertEquals(false, result);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	/*Should return false and AccNotes should be "". the default
	 *The mock input is 24 characters long but contains an illegal symbol
	 *Any potential injection would likely be too long to fit without the use of '*'*/
	public void accountNameIllegalAndCancel() {
		/*Local Variables*/
		Boolean result;
		String expected = "", actual = "";
		
		/*Mocks*/
		replace = new ByteArrayInputStream(("select * from tbl_users;" + System.lineSeparator() + "X").getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = acc.accountNameInput();
		actual = acc.getAccNotes();
		
		/*Tests*/
		Assertions.assertEquals(false, result);
		Assertions.assertEquals(expected, actual);
	}
	
	@AfterEach
	public void afterEach(){
		P0Main.key_inp = new Scanner(System.in);
	}
}
