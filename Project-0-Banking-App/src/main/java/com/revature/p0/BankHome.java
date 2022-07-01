package com.revature.p0;
public class BankHome  extends P0Main{
	private String command = "", name = "user";
	private Account a;
	
	/*Allow the user to choose the account to access or create a new one*/
	protected Boolean home(int id, String email, String fName) {
		a = new Account(id);
		if (fName != null) {
			name = fName;
		}
		System.out.println("Hello " + name + ",");
		/*Local Variables*/
		Boolean logout = false;
		
		/*Function*/
		do {//Bank home loop
			System.out.println("Enter 'S' to select an account to view, 'C' to create a new account, or 'L' to logout\n");
			command = getInput();
			switch(command.toUpperCase()) {
				case "S":
					a.accountSelect();break;
				case "C":
					a.accountCreate();break;
				case "L":
					System.out.println("You will now be logged out\n");
					logout = true;break;
				default:
					System.out.println("The command you entered is invalid\n");break;
			}
		}while (logout == false);
		return logout;
	}
}
