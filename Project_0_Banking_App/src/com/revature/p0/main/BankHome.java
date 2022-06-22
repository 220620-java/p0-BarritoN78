package com.revature.p0.main;
import java.util.Scanner;
public class BankHome {
	//Declare variables
	private double balance = 420.69;
	private double newBalance = 0;
	private static Scanner key_inp = new Scanner(System.in);
	private String name = "user";
	
	//Constructor
	public BankHome(String email) {
		//name = SELECT name FROM bank WHERE PK == email
		//balance = SELECT balance FROM bank WHERE PK == email
		System.out.println("Hello " + name + ",");
		transBegin();
		key_inp.close();
	}
	
	//Tell the user their balance and ask what transaction they want to perform
	private void transBegin() {
		String command;
		Boolean exit = false;
		do {
			System.out.println("Your current balance is $" + balance + "\n");
			System.out.println("Enter 'W' to withdraw or 'D' to deposit\n");
			command = key_inp.nextLine().toUpperCase();
			switch (command) {
			case "W", "D":
				transaction(command);break;
			default:
				System.out.println("The command you entered is invalid\n");break;
			}
			System.out.println("Would you like to perform another transaction? 'Y' or 'N'\n");
			command = key_inp.nextLine().toUpperCase();
			switch(command) {
			case "Y":
				exit = false;break;
			case "N":
				System.out.println("You will now be logged out");
				exit = true;break;
			default:
				System.out.println("The command you entered is invalid");break;
			}
		
		}
		while(exit == false);
	}
		
	
	
	//Perform deposits or withdraws based on the passed command
	private void transaction(String command) {
		double amount = 0.0;
		switch(command) {
		case "W":
			System.out.println("Please enter the amount you would like to withdraw\n");break;
		case "D":
			System.out.println("Please enter the amount you would like to deposit\n");break;
		}
		try {
			amount = Double.parseDouble(key_inp.nextLine());
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
}
