package com.revature.p0;

public class Login extends P0Main{

	private String email = "", password = "";

	/* Logging in to the app */
	protected Boolean login() {
		/* Local Variables */
		Boolean toHome = false, verdict = false;

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
				switch (email) {
				case ("It exists"):
					verdict = loginPasswordEntry();
					toHome = true;
					break;
				default:
					verdict = false;
					System.out.println("The email you entered does not have an account\n");
					break;
				}
				break;
			}
		} while (toHome == false);
		return verdict;
	}

	/* Prompt for password and test validity */
	protected Boolean loginPasswordEntry() {
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
				switch (password) {
				case "Is correct":
					System.out.println("You have gained access\n");
					verdict = true;
					toHome = true;
					break;
				default:
					// toHome = true;//Test Only
					verdict = false;
					System.out.println("The password you have entered is invalid\n");
				}
				break;
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
