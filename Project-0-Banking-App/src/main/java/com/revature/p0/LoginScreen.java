package com.revature.p0;
import java.util.Scanner;
public class LoginScreen {

	/*Class Variables*/
	private String email, password, command;
	private Boolean appAccess = false;
	private static Scanner key_inp = new Scanner(System.in);
	
	/*Constructor*/
	public LoginScreen() {
		while (appAccess == false)
		{
			System.out.println("Enter 'L' to login or 'R' to register\n");
			command = key_inp.nextLine();
			switch (command.toUpperCase()) {
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
	
	/*Logging in to the app*/
	private void login() {
		/*Local Variables*/
		Boolean toHome = false;
		
		/*Function*/
		do {//Login Email Loop
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
							System.out.println("The email you entered does not have an account\n");break;
				}break;	
			}
		}while(toHome == false);
	}
	
	/*Prompt for password and test validity*/
	private void loginPasswordEntry() {
		/*Local Variables*/
		Boolean toHome = false;
		
		/*Function*/
		do {//Login Password Loop
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
	
	/*Registering a new user for the app*/
	private void register() {
		/*Local Variables*/
		int emailLength;
		Boolean toHome = false, nameConfirm = false;
		String fName = "", mInit = "", lName = "";
		
		/*Function*/
		do {//Name Loop
			System.out.println("Please enter your first name\n");
			fName = key_inp.nextLine();
			System.out.println("Please enter your middle initial\n");
			mInit = key_inp.nextLine();
			System.out.println("Please enter your last name\n");
			lName = key_inp.nextLine();
			System.out.println("Your is name is" + fName + " " + mInit + ". " + lName + "\n");
			System.out.println("Enter 'Y' to confirm. All other commands will reset the name entry\n");
			command = key_inp.nextLine();
			switch(command.toUpperCase()) {
				case "Y":
					nameConfirm = true;break;
				default:
					nameConfirm = false;break;
			}
		}while(nameConfirm == false);		
		
		do {//Email Loop
			System.out.println("Please enter your email or 'H' to go back\n");
			email = key_inp.nextLine();
			emailLength = email.length();
			
			try {//Testing email validity
				if (email.contains("@") && email.substring(emailLength - 5).contains(".")) {//email has '@' and '.' towards the end
					switch(email) {
						case "Already@exists.":
							System.out.println("A user account has already been linked to this email. Please use a different email or log in\n");break;
						default://Password
							registerPasswordEntry();
							toHome = true; break;
					}
				}
				else {//Invalid email
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
	
	/*Prompt for a new password and then confirm it*/
	public void registerPasswordEntry() {
		/*Local Variables*/
		String password1, password2;
		Boolean toHome = false;
		
		/*Function*/
		do {
			System.out.println("Please enter a password for the new user or 'C' to cancel the account creation\n");
			password1 = key_inp.nextLine();
			
			if (password1.length() < 8) {//Invalid password entry
				switch(password1.toUpperCase()) {
					case "C":
						System.out.println("User creation canceled, you will be returned to the home screen");
						toHome = true; break;
					default:
						System.out.println("The password must be at least 8 characters long\n");break;
				}
			}
			else { //Reenter password to confirm
				System.out.println("Please reenter the password to confirm\n");
				password2 = key_inp.nextLine();	
				
				if (password2.equals(password1)) {//Matched
					System.out.println("Your user account has been created. You can now log in\n");
					toHome = true;
					//TODO INSERT email, password, TO users
				}
				else {//Didn't match
					System.out.println("The passwords you entered did not match. Please try again\n");
				}
			}
		}while(toHome == false);
	}
}
