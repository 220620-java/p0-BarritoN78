package com.revature.p0;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterAll;
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

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class LoginTest{
	private InputStream sysInbackup = System.in;
	private ByteArrayInputStream input;
	@InjectMocks
	@Spy
	private LoginScreen lScreen;
	
	@Mock
	private BankHome bHome;
	
	@BeforeAll
	public void beforeAll() {
		lScreen = new LoginScreen();
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeEach
	public void beforeEach() {
		lScreen.setAppAccess(false);
	}
	
	@Test
	/*Should equal true if the password is correct*/
	public void testLoginPasswordEntryPass() {
		/*Variables*/
		Boolean result;
		
		/*Setup*/
		input = new ByteArrayInputStream("Is correct".getBytes());
		System.setIn(input);
		
		/*Test*/
		//result = lScreen.loginPasswordEntry();
		
		/*Assertion*/
		//Assertions.assertEquals(true, result);
	}
	
	@Test
	@Disabled
	/*Should equal false for an incorrect password*/
	//Activate test line in LoginScreen to break out of loop
	public void testLoginPasswordEntryFail() {
		/*Variables*/
		Boolean result;
		
		/*Setup*/
		input = new ByteArrayInputStream(("Random String").getBytes());
		System.setIn(input);
		
		/*Test*/
		//result = lScreen.loginPasswordEntry();
		
		/*Assertion*/
		//Assertions.assertEquals(false, result);		
	}
	
	@Test
	/*Should equal false if the user decides to return to the home screen*/
	public void testLoginPasswordEntryHome() {
		/*Variables*/
		Boolean result;
		
		/*Setup*/
		input = new ByteArrayInputStream("h".getBytes());
		System.setIn(input);
		
		/*Test*/
		//result = lScreen.loginPasswordEntry();
		
		/*Assertion*/
		//Assertions.assertEquals(false, result);
	}
	
	@Test
	/*Should equal true if the user enters the same password twice*/
	public void testRegisterPasswordEntryPass() {
		/*Variables*/
		Boolean result;
		
		/*Mocks*/
		Mockito.when(lScreen.getInput()).thenReturn("MyPassword");
		
		/*Test*/
		//result = lScreen.registerPasswordEntry();
		
		/*Assertion*/
		//Assertions.assertEquals(true, result);
	}
	
	@AfterAll
	public void afterAll() {
		System.setIn(sysInbackup);
	}
}
