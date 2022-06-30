package com.revature.p0;

import com.revature.p0.data.RegisterDAO;

public class Register extends P0Main{
	/* Class Variables */
	private String email = "", password = "", command = "", fName = "", mInit = "", lName = "";
	private RegisterDAO sql = new RegisterDAO();

	/* Registering a new user for the app */
	public Boolean register() {
		/* Local Variables */
		int emailLength;
		Boolean toHome = false;

		/* Function */
		do {// Email Loop
			System.out.println("Please enter your email or 'H' to go back\n");
			email = getInput();
			emailLength = email.length();

			try {// Testing email validity
				if (email.contains("@") && email.substring(emailLength - 5).contains(".")) {// email has '@' and '.' towards the end
					switch (sql.findByEmail(email)) {// Testing for existing email
					case 0:// Email does not have an account
						registerNewUser();
						toHome = true;
						break;
					default:// Email already has an account || SQL connection failed
						System.out.println(
								"A user account has already been linked to this email. Please use a different email or log in\n");
						break;
					}
				} else {// Invalid email
					switch (email.toUpperCase()) {
					case "H":
						toHome = true;
						break;
					default:
						System.out.println("The email you entered is invalid\n");
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("The email you entered is invalid\n");
			}
		} while (toHome == false);
		return toHome;
	}
	
	protected Boolean registerNewUser() {
		Boolean accountCreated = false;
		registerNameEntry();
		try {
			if(registerPasswordEntry()) {
				sql.insert(this);
			}else {
				accountCreated = false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return accountCreated;
	}

	/* Prompts user for a name and verifies validity */
	protected Boolean registerNameEntry() {
		/* Local Variables */
		Boolean nameConfirm = false, lNameValid = false, fNameValid = false, mInitValid = false;
		/* Function */
		do {// Name Entry Loop
			while (fNameValid == false) {//fNameLoop
				System.out.println("Please enter your first name\n");
				fName = getInput();
				if (fName.length() > 0) {
					if (fName.length() <= 25) {
						if ((fName.contains(";") || fName.contains("?") || fName.contains("!")) == false) {// No SQL Injection plz
							fNameValid = true;
						} else {
							System.out.println("The entry contains an invalid character: ;, ?, !");
						}
					} else {
						System.out.println("Please limit the entry to 25 characters");
					}
				} else {
					System.out.println("The entry cannot be null");
				}
			}
			
			while (mInitValid == false) {//mInit Loop
				System.out.println("Please enter your middle initial\n");
				mInit = getInput();
				if (mInit.length() <= 1) {
					if ((fName.contains(";") || fName.contains("?") || fName.contains("!")) == false) {
						mInitValid = true;
					}else {
						System.out.println("The entry contains an invalid character: ;, ?, !");
					}
				}else {
					System.out.println("Please limit the entry to 1 character");
				}
			}
			
			while (lNameValid == false) {//lName Loop
				System.out.println("Please enter your last name\n");
				lName = getInput();
				if (lName.length() > 0) {
					if (lName.length() <= 25) {
						if ((lName.contains(";") || lName.contains("?") || lName.contains("!")) == false) {// No SQL Injection plz
							lNameValid = true;
						} else {
							System.out.println("The entry contains an invalid character: ;, ?, !");
						}
					} else {
						System.out.println("Please limit the entry to 25 characters");
					}
				} else {
					System.out.println("The entry cannot be null");
				}
			}
			System.out.println("Your name is" + fName + " " + mInit + ". " + lName + "\n");
			System.out.println("Enter 'Y' to confirm. All other commands will reset the name entry\n");
			command = getInput();
			switch (command.toUpperCase()) {
			case "Y":
				nameConfirm = true;
				break;
			default:
				nameConfirm = false;
				break;
			}
		} while (nameConfirm == false);
		return nameConfirm;
	}

	/* Prompt for a new password and then confirm it */
	protected Boolean registerPasswordEntry() {
		/* Local Variables */
		String password1, password2;
		Boolean toHome = false, passwordMatched = false;

		/* Function */
		do {
			System.out.println("Please enter a password for the new user or 'C' to cancel the account creation\n");
			password1 = getInput();
			if (password1.length() < 8) {// Invalid password entry
				switch (password1.toUpperCase()) {
				case "C":
					System.out.println("User creation canceled, you will be returned to the home screen");
					toHome = true;
					break;
				default:
					System.out.println("The password must be at least 8 characters long\n");
					break;
				}
			} else { // Reenter password to confirm
				System.out.println("Please reenter the password to confirm\n");
				password2 = getInput();
				if (password2.equals(password1)) {// Matched
					System.out.println("Your user account has been created. You can now log in\n");
					toHome = true;
					password = password1;
					passwordMatched = true;
				} else {// Didn't match
					System.out.println("The passwords you entered did not match. Please try again\n");
				}
			}
		} while (toHome == false);
		return passwordMatched;
	}

	/* Getters and Setters */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getMInit() {
		return mInit;
	}

	public void setMInit(String mInit) {
		this.mInit = mInit;
	}

	public String getLName() {
		return lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}
}
