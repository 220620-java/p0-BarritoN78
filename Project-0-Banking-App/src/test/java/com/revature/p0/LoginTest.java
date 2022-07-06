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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.p0.data.LoginDAO;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class LoginTest{
	private ByteArrayInputStream replace;
	@InjectMocks
	private Login lScreen;
	
	@Mock
	private LoginDAO sql;
	
	@BeforeAll
	public void beforeAll() {
		MockitoAnnotations.openMocks(this);
	}
	
	@BeforeEach
	public void beforeEach() {
		lScreen = new Login();
	}
	
	@Test
	/*Should equal true if user input matches the given string
	 *The mock input and the given string are "password"*/
	public void testLoginPasswordEntryPass() {
		/*Variables*/
		Boolean result;
		
		/*Mocks*/
		replace = new ByteArrayInputStream("password".getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = lScreen.loginPasswordEntry("password");
		
		/*Test*/
		Assertions.assertEquals(true, result);
	}
	
	@Test
	/*Should equal false if user input does not match the given string
	 *The mock input is "PassWord" and the given string is "password". The line separator and "H" are to break the loop
	 *The test also shows that there is case sensitivity
	 *Also, if the loop does break as intended, then that function is proved as well*/
	public void testLoginPasswordEntryFail() {
		/*Variables*/
		Boolean result;
		
		/*Mocks*/
		replace = new ByteArrayInputStream(("PassWord"+ System.lineSeparator() +"H").getBytes());
		P0Main.key_inp = new Scanner(replace);
		
		/*Function*/
		result = lScreen.loginPasswordEntry("password");
		
		/*Test*/
		Assertions.assertEquals(false, result);
	}
	
	@AfterEach
	public void afterEach() {
		P0Main.key_inp = new Scanner(System.in);
	}
}
