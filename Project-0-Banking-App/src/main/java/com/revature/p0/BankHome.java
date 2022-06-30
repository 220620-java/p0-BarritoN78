package com.revature.p0;
import java.util.Random;
public class BankHome  extends P0Main{
	/*Class Variables*/
	private double balance = 420.69;
	private double newBalance = 0;
	private String name = "user", command;
	
	/*Allow the user to choose the account to access or create a new one*/
	protected Boolean accountSelect(String email) {
		//TODO name = SELECT name FROM users WHERE PK == email
		System.out.println("Hello " + name + ",");
		/*Local Variables*/
		Boolean logout = false;
		String accID = "";
		
		/*Function*/
		do {//Bank home loop
			System.out.println("Enter 'S' to select an account to view, 'C' to create a new account, or 'L' to logout\n");
			command = getInput();
			switch(command.toUpperCase()) {
				case "S":
					transBegin(accID);break;
				case "C":
					accountCreate();break;
				case "L":
					System.out.println("You will now be logged out\n");
					logout = true;break;
				default:
					System.out.println("The command you entered is invalid\n");break;
			}
		}while (logout == false);
		return logout;
	}
	
	/*Creates a new finance account for the user*/
	private void accountCreate() {
		/*Local Variables*/
		String type, accName, accID;
		Boolean cancel = false, typeGiven = false, nameGiven = false;
		
		/*Function*/
		do {//Account Type Loop
		System.out.println("Select the account type: 'C': Checking, 'S': Savings, 'J': Joint. 'X' to cancel\n");
		command = getInput();
		switch(command.toUpperCase()) {
			case "C","S","J":
				type = command.toUpperCase();
				typeGiven = true;break;
			case "X":
				cancel = true;break;
			default:
				System.out.println("The command you entered is invalid\n");break;
			}
		}while(cancel == false && typeGiven == false);
		if (typeGiven) {//Account was given a type/Was not canceled
			do {//Account Name Loop
				System.out.println("Write a name to help you identify the account later. 'X' to cancel\n");
				accName = getInput();
				switch(accName.toUpperCase()) {
					case "X":
						cancel = true;break;
					default://Name Validation
						if (accName.length() < 25) {//Name is less than 25 characters
							if ((accName.contains("!") || accName.contains(";")) == false) {//Name does not contain illegal symbol. No SQL injection plz
								accID = accountIDCreate();
								//TODO INSERT accID, accName, type, 0 TO bank
								nameGiven = true;
							}
							else {
								System.out.println("Your name contain an illegal symbol. '!' and ';' cannot be used\n");
							}
						}
						else {
							System.out.println("Please limit the name to 25 characters\n");
						}
				}
			
			}while(nameGiven == false && cancel == false);
			}
	}
	
	/*Creates a new account ID thru RNG and references the DB*/
	private String accountIDCreate() {
		/*Local Variables*/
		String newID;
		Random rng = new Random();
		Boolean idExists = true;
		int sqlResult = 0;
		
		/*Function*/
		do {//RNG for New ID loop
			newID = Integer.toString(rng.nextInt(0, 100000000));
			if(newID.length() < 8) {//Adds leading zeros if the account ID is less than 8 long
				for (int c = 8 - newID.length(); c < 8; c++ ) {
					newID = "0" + newID;
				}
				//TODO SELECT count(*) FROM accounts WHERE accID = newID
				if(sqlResult > 0) {
					idExists = false;
				}
			}
		}while(idExists);
		return newID;
	}
	
	/*Tell the user their balance and ask what transaction they want to perform*/
	private void transBegin(String accID) {
		/*Local Variables*/
		Boolean exit = false;
		
		/*Function*/
		do {//Transaction Loop
			//TODO balance = SELECT balance FROM bank WHERE PK == email && AccountID == accID
			System.out.println("Your current balance is $" + balance + "\n");
			System.out.println("Enter 'W' to withdraw, 'D' to deposit, or 'H' to view this account's history\n");
			command = getInput().toUpperCase();
			switch (command) {
				case "W", "D":
					transaction();break;
				case "H":
					transHistory(accID);break;
				default:
					System.out.println("The command you entered is invalid\n");break;
			}
			System.out.println("Would you like to perform another transaction? 'Y' or 'N'\n");
			command = getInput().toUpperCase();
			switch(command) {
				case "Y":
					exit = false;break;
				case "N":
					System.out.println("You will now be logged out\n");
					exit = true;break;
				default:
					System.out.println("The command you entered is invalid\n");break;
			}
		
		}
		while(exit == false);
	}
		
	
	
	/*Perform deposits or withdraws based on the passed command*/
	private void transaction() {
		/*Local Variables*/
		double amount = 0.0;
		
		/*Function*/
		switch(command) {
			case "W":
				System.out.println("Please enter the amount you would like to withdraw\n");break;
			case "D":
				System.out.println("Please enter the amount you would like to deposit\n");break;
		}
		try {
			amount = Double.parseDouble(getInput());
			if (amount <= 0)
			{
				System.out.println("The amount you have entered is invalid\n");
			}
			else {
				switch(command) {
					case "W": //Withdraw functions
						if(balance < amount) {
							System.out.println("We are unable to process the request due to a lack of funds\n");
							newBalance = balance;
						}
						else {
							newBalance = balance - amount;
						}break;
					case "D": //Deposit functions
						newBalance = balance + amount;break;
				}
				System.out.println("Your new balance is $" + Double.toString(newBalance) + "\n");
				balance = newBalance;
			}
		}
		catch (Exception e) {
			System.out.println("The amount you have entered is invalid\n");
		}
	}
	
	/*Retrieves a list of an account's transaction history*/
	private void transHistory(String accID) {
		/*Local Variables*/
		String header = "Date:\t Balance:\t Comments:";
		String transaction = "";
		int records = 0, index = 0;
		
		/*Function*/		
		System.out.println(header);
		//TODO records = SELECT count(*) FROM history WHERE PK == email && AccountID == accID;
		while(index < records){
			//TODO transaction = SELECT * FROM history WHERE PK == email && AccountID == accID;
			System.out.println(transaction);
		}
	}
}
