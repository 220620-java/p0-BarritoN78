package com.revature.p0;

import com.revature.p0.data.LoginDAO;

public class Login extends P0Main{

	private String email = "", password = "";
	private LoginDAO sql = new LoginDAO();

	/* Logging in to the app */
	protected Boolean login() {
		/* Local Variables */
		Boolean toHome = false, verdict = false;
		Login test;

		/* Function */
		do {// Login Email Loop
			System.out.println("Please enter your email or 'H' to go back\n");
			email = getInput();
			switch (email.toUpperCase()) {
			case "H":
				verdict = false;
				toHome = true;
				break;
			default:
				test = sql.findByEmail(email);
				switch (test.getPassword()) {
				case (""):
					verdict = false;
					System.out.println("The email you entered does not have an account\n");
					break;
				default:
					verdict = loginPasswordEntry(test.getPassword());
					toHome = true;
					break;
				}
				break;
			}
		} while (toHome == false);
		return verdict;
	}

	/* Prompt for password and test validity */
	protected Boolean loginPasswordEntry(String test) {
		/* Local Variables */
		Boolean toHome = false, verdict = false;

		/* Function */
		do {// Login Password Loop
			System.out.println("Please enter your password or 'H' to return to the home screen\n");
			password = getInput();
			switch (password.toUpperCase()) {
			case ("H"):
				System.out.println("You will be returned to the home screen\n");
				verdict = false;
				toHome = true;
				break;
			default:
				if (test.equals(password)) {
					System.out.println("You have gained access\n");
					verdict = true;
					toHome = true;
				}else {
					// toHome = true;//Test Only
					verdict = false;
					System.out.println("The password you have entered is invalid\n");
				}
			}
		} while (toHome == false);
		return verdict;
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
}
