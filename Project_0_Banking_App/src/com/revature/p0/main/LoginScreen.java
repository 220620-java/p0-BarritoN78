package com.revature.p0.main;
import java.util.Scanner;
public class LoginScreen {

	//Declare Variables
	private String email, password, entry;
	private Boolean appAccess = false;
	private Scanner key_inp = new Scanner(System.in);
	
	public LoginScreen() {
		while (appAccess == false)
		{
			System.out.println("Enter 'L' to login or 'R' to register\n");
			entry = key_inp.nextLine();
			System.out.println(entry);
			switch (entry.toUpperCase()) {
			case "L": 
				login(); break;
			case "R": 
				register(); break;
			default:
				System.out.println("The command you entered is invalid\n");break;
			}
		}
	}
	
	//Logging in to the app
	private void login() {
		System.out.println("Please enter your email or 'R' to register\n");
		email = key_inp.nextLine();
		switch (email.toUpperCase()) {
		case "R": 
			register(); break;
		default:
			switch(email) {
			case("It exists"):
				loginPasswordEntry();break;
			default:
				System.out.println("The email you entered does not have an account\n");
				login();break;
			}			
		}
	}
	
	//Prompt for password and test validity
	private void loginPasswordEntry() {
		System.out.println("Please enter your password or 'H' to return to the home screen\n");
		password = key_inp.nextLine();
		switch(password.toUpperCase()) {
		case("H"):
			System.out.println("You will be returned to the home screen\n");
		default:
			switch(password) {
			case "Is correct":
				System.out.println("You have gained access\n");
				appAccess = true;
			default:
				System.out.println("The password you have entered is invalid\n");
				loginPasswordEntry();
			}
		}
	}
	
	//Registering a new account for the app
	private void register() {
		int emailLength;
		System.out.println("Please enter your email or 'L' to log in instead\n");
		email = key_inp.nextLine();
		emailLength = email.length();
		//Testing email validity
		try {
			if (email.contains("@") && email.substring(emailLength - 5).contains(".")) {//email has '@' and '.' towards the end
				switch(email) {
				case "Already exists":
					System.out.println("An account has already been linked to this email. Please use a different email or log in\n");
					register();break;
				default:
					registerPasswordEntry();
				}
			}
			else {
				switch(email.toUpperCase()) {
				case "L":
					login();break;
				default:
					System.out.println("The email you entered is invalid\n");
					register();
				}
			}
		}
		catch (Exception e){
			System.out.println("The email you entered is invalid\n");
			register();
		}
	}
	
	public void registerPasswordEntry() {
		String password1, password2;
		System.out.println("Please enter a password for the new account or 'C' to cancel the account creation\n");
		password1 = key_inp.nextLine();
		/* */
		if (password1.length() < 8) {
			switch(password.toUpperCase()) {
			case "C":
				System.out.println("Account creation canceled, you will be returned to the home screen");break;
			default:
				System.out.println("The password must be at least 8 characters long\n");
				registerPasswordEntry();break;
			}
		}
		else { //Reenter password to confirm
			System.out.println("Please reenter the password to confirm\n");
			password2 = key_inp.nextLine();			
			if (password2.equals(password1)) {
				System.out.println("The passwords you entered did not match. Please try again\n");
				registerPasswordEntry();
			}
			else {
				System.out.println("Your account has been created. You can now log in\n");
			}
		}
	}
}
