package com.revature.p0;
public class LoginScreen  extends P0Main{

	/*Class Variables*/
	private String command = "";
	private Boolean appAccess = false, close = false;
	private BankHome bank;
	private Login l = new Login();
	private Register r = new Register();;
	
	/*Constructor*/
	public LoginScreen() {
		bank = new BankHome();
	}
	/*Select to login, register or close*/
	public  void home() {
		while (getAppAccess() == false && close == false)
		{
			System.out.println("Enter 'L' to login, 'R' to register, 'C' to close\n");
			command = getInput();
			switch (command.toUpperCase()) {
				case "L": 
					setAppAccess(l.login()); break;
				case "R": 
					r.register(); break;
				case "C":
					close = true;
					System.out.println("The program will now close");break;
				default:
					System.out.println("The command you entered is invalid\n");break;
			}
		}
		if (getAppAccess() == true) {
			bank.accountSelect(l.getEmail());;
		}
	}

	public Boolean getAppAccess() {
		return appAccess;
	}

	public void setAppAccess(Boolean appAccess) {
		this.appAccess = appAccess;
	}
}
