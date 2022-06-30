package com.revature.p0;

import com.revature.p0.data.RegisterDAO;

public class Register extends LoginScreen {
	/* Class Variables */
	private String email = "", password = "", command = "", fName = "", mInit = "", lName = "";
	private RegisterDAO sql = new RegisterDAO();

	/* Registering a new user for the app */
	public void register() {
		/* Local Variables */
		int emailLength;
		Boolean toHome = false, nameConfirm = false;

		/* Function */
		do {// Name Loop
			System.out.println("Please enter your first name\n");
			fName = getInput();
			System.out.println("Please enter your middle initial\n");
			mInit = getInput();
			System.out.println("Please enter your last name\n");
			lName = getInput();
			System.out.println("Your is name is" + fName + " " + mInit + ". " + lName + "\n");
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

		do {// Email Loop
			System.out.println("Please enter your email or 'H' to go back\n");
			email = getInput();
			emailLength = email.length();

			try {// Testing email validity
				if (email.contains("@") && email.substring(emailLength - 5).contains(".")) {// email has '@' and '.' towards the end
					switch (sql.findByEmail(email)) {
					case 0:// Password
						registerPasswordEntry();
						toHome = true;
						break;
					default:
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
	}

	/* Prompt for a new password and then confirm it */
	protected Boolean registerPasswordEntry() {
		/* Local Variables */
		String password1, password2;
		Boolean toHome = false, accountCreated = false;

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
					// TODO INSERT email, password, TO users
					try {
						accountCreated = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {// Didn't match
					System.out.println("The passwords you entered did not match. Please try again\n");
				}
			}
		} while (toHome == false);
		return accountCreated;
	}

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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmInit() {
		return mInit;
	}

	public void setmInit(String mInit) {
		this.mInit = mInit;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
}
