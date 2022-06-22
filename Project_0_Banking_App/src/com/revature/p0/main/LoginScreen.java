package com.revature.p0.main;
import java.util.Scanner;
public class LoginScreen {

	//Declare Variables
	private String email, password, entry;
	private Boolean appAccess = false;
	private static Scanner key_inp = new Scanner(System.in);
	
	//Constructor
	public LoginScreen() {
		while (appAccess == false)
		{
			System.out.println("Enter 'L' to login or 'R' to register\n");
			entry = key_inp.nextLine();
			switch (entry.toUpperCase()) {
			case "L": 
				login(); break;
			case "R": 
				register(); break;
			default:
				System.out.println("The command you entered is invalid\n");break;
			}
		}
		BankHome bank = new BankHome(email);
		key_inp.close();
	}
	
	//Logging in to the app
	private void login() {
		Boolean loginSuccess = false, toHome = false;
		do {
			System.out.println("Please enter your email or 'H' to go back\n");
			email = key_inp.nextLine();
			switch (email.toUpperCase()) {
			case "H": 
				toHome = true; break;
			default:
				switch(email) {
				case("It exists"):
					loginPasswordEntry();
					toHome = true;break;
				default:
					System.out.println("The email you entered does not have an account\n");
					loginSuccess = false;break;
				}break;	
			}
		}while(loginSuccess == false && toHome == false);
	}
	
	//Prompt for password and test validity
	private void loginPasswordEntry() {
		Boolean toHome = false;
		do {
			System.out.println("Please enter your password or 'H' to return to the home screen\n");
			password = key_inp.nextLine();
			switch(password.toUpperCase()) {
			case("H"):
				System.out.println("You will be returned to the home screen\n");
				toHome = true; break;
			default:
				switch(password) {
				case "Is correct":
					System.out.println("You have gained access\n");
					appAccess = true; 
					toHome = true; break;
				default:
					System.out.println("The password you have entered is invalid\n");
				}break;
			}
		}while (toHome == false);
	}
	
	//Registering a new account for the app
	private void register() {
		int emailLength;
		Boolean toHome = false;
		do {
			System.out.println("Please enter your email or 'H' to go back\n");
			email = key_inp.nextLine();
			emailLength = email.length();
			//Testing email validity
			try {
				if (email.contains("@") && email.substring(emailLength - 5).contains(".")) {//email has '@' and '.' towards the end
					switch(email) {
					case "Already@exists.":
						System.out.println("An account has already been linked to this email. Please use a different email or log in\n");break;
					default:
						registerPasswordEntry();
						toHome = true; break;
					}
				}
				else {
					switch(email.toUpperCase()) {
					case "H":
						toHome = true; break;
					default:
						System.out.println("The email you entered is invalid\n");break;
					}
				}
			}
			catch (Exception e){
				System.out.println("The email you entered is invalid\n");
				register();
			}
		}while (toHome == false);
	}
	
	//Prompt for a new password and then confirm it
	public void registerPasswordEntry() {
		String password1, password2;
		Boolean toHome = false;
		do {
		System.out.println("Please enter a password for the new account or 'C' to cancel the account creation\n");
		password1 = key_inp.nextLine();
		if (password1.length() < 8) {
			switch(password1.toUpperCase()) {
			case "C":
				System.out.println("Account creation canceled, you will be returned to the home screen");
				toHome = true; break;
			default:
				System.out.println("The password must be at least 8 characters long\n");break;
			}
		}
		else { //Reenter password to confirm
			System.out.println("Please reenter the password to confirm\n");
			password2 = key_inp.nextLine();			
			if (password2.equals(password1)) {
				System.out.println("Your account has been created. You can now log in\n");
				toHome = true;
			}
			else {
				System.out.println("The passwords you entered did not match. Please try again\n");
			}
		}
		}while(toHome == false);
	}
}
