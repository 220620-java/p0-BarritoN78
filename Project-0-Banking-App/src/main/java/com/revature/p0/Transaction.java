package com.revature.p0;

public class Transaction extends P0Main{
	/*Class Variables*/
	private double accountBal = 0, newBal = 0;
	private int accID = -1;
	private String command = "", notes = "", type = "", date = "";
	
	public Transaction() {
		
	}
	
	/*Tell the user their accountBal and ask what transaction they want to perform*/
	protected void transBegin() {
		/*Local Variables*/
		Boolean exit = false;
		
		/*Function*/
		do {//Transaction Loop
			//TODO accountBal = SELECT accountBal FROM bank WHERE PK == email && AccountID == accID
			System.out.println("Your current accountBal is $" + accountBal + "\n");
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
						if(accountBal < amount) {
							System.out.println("We are unable to process the request due to a lack of funds\n");
							newBal = accountBal;
						}
						else {
							newBal = accountBal - amount;
						}break;
					case "D": //Deposit functions
						newBal = accountBal + amount;break;
				}
				System.out.println("Your new accountBal is $" + Double.toString(newBal) + "\n");
				accountBal = newBal;
			}
		}
		catch (Exception e) {
			System.out.println("The amount you have entered is invalid\n");
		}
	}
	
	/*Retrieves a list of an account's transaction history*/
	private void transHistory(int accID) {
		/*Local Variables*/
		String header = "Date:\t Type:\t Balance Before:\t Balance After:\t Comments:";
		String transaction = "";
		int records = 0, index = 0;
		
		/*Function*/		
		System.out.println(header);
	}
	
	/*Getters and Setters*/
	public double getAccountBal() {
		return accountBal;
	}



	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}



	public double getNewBal() {
		return newBal;
	}



	public void setNewBal(double newBal) {
		this.newBal = newBal;
	}



	public int getAccID() {
		return accID;
	}



	public void setAccID(int accID) {
		this.accID = accID;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



}
