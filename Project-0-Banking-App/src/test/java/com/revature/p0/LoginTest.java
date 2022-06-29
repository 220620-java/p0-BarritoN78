package com.revature.p0;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginTest {
	
	@InjectMocks
	@Spy
	private LoginScreen lScreen;
	
	@Mock
	private BankHome bHome;

	@Test
	//appAccess should be true if the login succeeds
	public void testLScreenToLogin() {
		/*Local Variables*/
		InputStream sysInBackup = System.in;
		ByteArrayInputStream replaceStream = new ByteArrayInputStream("L".getBytes());
		
		/*Mocks*/
		Mockito.doReturn(true).when(lScreen).login();
		Mockito.doNothing().when(bHome);
		
		/*Begin Test*/
		System.setIn(replaceStream);
		lScreen = new LoginScreen();
		
		/*Assertion*/
		Assertions.assertEquals(true, lScreen.getAppAccess());
	}
	
	@Test
	//appAccess should remain false regardless of register()
	public void testLScreentoRegister() {
		/*Local Variables*/
		InputStream sysInBackup = System.in;
		ByteArrayInputStream replaceStream = new ByteArrayInputStream("R".getBytes());
		
		/*Mocks*/
		Mockito.doReturn(false).when(lScreen).register();
		
		/*Begin Test*/
		System.setIn(replaceStream);
		lScreen = new LoginScreen();
		
		/*Assertion*/
		Assertions.assertEquals(false, lScreen.getAppAccess());
	}
	
	@Test
	//appAccess should remain false when an invalid command is given
	public void testLScreenInvalid() {
		/*Local Variables*/
		InputStream sysInBackup = System.in;
		ByteArrayInputStream replaceStream = new ByteArrayInputStream("Invalid command".getBytes());
		
		/*Begin Test*/
		System.setIn(replaceStream);
		lScreen = new LoginScreen();
		
		/*Assertion*/
		Assertions.assertEquals(false, lScreen.getAppAccess());
	}

	@Test
	//appAccess should return true if email exists and password is correct
	public void testLoginToLoginPasswordEntry() {
		/*Local Variables*/
		InputStream sysInBackup = System.in;
		ByteArrayInputStream replaceStream = new ByteArrayInputStream("L\nIt exists".getBytes());
		
		/*Mocks*/
		Mockito.doReturn(true).when(lScreen).loginPasswordEntry();
		Mockito.doNothing().when(bHome);
		
		/*Begin Test*/
		System.setIn(replaceStream);
		lScreen = new LoginScreen();
		
		/*Assertion*/
		Assertions.assertEquals(true, lScreen.getAppAccess());
	}
	
	@Test
	//appAccess should remain false if email the user returns to the home screen using the 'H' command
	public void testLoginToHome() {
		/*Local Variables*/
		InputStream sysInBackup = System.in;
		ByteArrayInputStream replaceStream = new ByteArrayInputStream("L\nH".getBytes());
		
		/*Begin Test*/
		System.setIn(replaceStream);
		lScreen = new LoginScreen();
		
		/*Assertion*/
		Assertions.assertEquals(false, lScreen.getAppAccess());
	}
	
	@Test
	//appAccess should return true if the password is correct
	public void testLoginPasswordEntrySuccess() {
		/*Local Variables*/
		InputStream sysInBackup = System.in;
		ByteArrayInputStream replaceStream = new ByteArrayInputStream("L\nIs correct".getBytes());
		
		/*Mocks*/
		Mockito.doReturn(true).when(lScreen).loginPasswordEntry();
		Mockito.doNothing().when(bHome);
		
		/*Begin Test*/
		System.setIn(replaceStream);
		lScreen = new LoginScreen();
		
		/*Assertion*/
		Assertions.assertEquals(true, lScreen.getAppAccess());
	}
}
