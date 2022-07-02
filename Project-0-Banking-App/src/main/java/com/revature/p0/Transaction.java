package com.revature.p0;

import com.revature.p0.data.AccountDAO;
import com.revature.p0.data.TransactionDAO;
import com.revature.p0.util.List;
import com.revature.p0.util.ArrayList;

import java.sql.Date;
import java.time.LocalDate;

public class Transaction extends P0Main{
	/*Class Variables*/
	private TransactionDAO sql = new TransactionDAO();
	private AccountDAO accSQL = new AccountDAO();
	private double accountBal = 0, newBal = 0;
	private int accID = -1, transID = -1;
	private String command = "", notes = "", type = "";
	LocalDate date = LocalDate.now();
	
	/*Constructor: For data collection*/
	public Transaction(int accIDLoc) {
		setAccID(accIDLoc);
	}
	
	/*Constructor: For user functions*/
	public Transaction(int accIDLoc, double accBal) {
		setAccountBal(accBal);
		setAccID(accIDLoc);
	}

	/*Tell the user their accountBal and ask what transaction they want to perform*/
	protected void transBegin() {
		/*Local Variables*/
		Boolean exit = false;
		
		/*Function*/
		do {//Transaction Loop
			System.out.println("Your current balance is $" + accountBal + "\n");
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
			System.out.println("Would you like to perform another transaction with this account? 'Y' or 'N'\n");
			command = getInput().toUpperCase();
			switch(command) {
				case "Y":
					exit = false;break;
				case "N":
					System.out.println("You have been returned to the account selection screen\n");
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
		Transaction test;
		
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
						setType("Withdraw");
						if(accountBal < amount) {
							System.out.println("We are unable to process the request due to a lack of funds\n");
							setNotes("A withdraw was attempted, but failed due to a lack of funds");
							newBal = accountBal;
						}
						else {
							setNotes("Normal Withdraw of $" + amount);
							newBal = accountBal - amount;
						}break;
					case "D": //Deposit functions
						setType("Deposit");
						setNotes("Normal Deposit of $" + amount);
						newBal = accountBal + amount;break;
				}
				test = sql.insert(this);
				if (test != null) {
					if(accSQL.updateBalance(newBal, accID)) {
						System.out.println("Transaction Successful");
						System.out.println("Your new balance is $" + Double.toString(newBal) + "\n");
						accountBal = newBal;
					}
					else {
						System.out.println("Your account failed to update");
					}
				}
				else {
					System.out.println("The transaction failed to process");
				}
				System.out.println("Your new balance is $" + Double.toString(newBal) + "\n");
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
		List<Transaction> list = new ArrayList<Transaction>();
		Transaction current;
		int size = 0, index = 0;
		String result = "";
		
		/*Function*/
		list = sql.findByID(accID);
		size = list.length();
		while (index < size) {
			if (list.get(index) != null) {
				current = (Transaction) list.get(index);
				result += "\n" + current.getDate().toString();
				result += "  " + stringPadder(Integer.toString(current.getTransID()), 13);
				result += "\t" + stringPadder(current.getType(), 8);
				result += "\t" + stringPadder(Double.toString(current.getAccountBal()), 11);
				result += "\t" + stringPadder(Double.toString(current.getNewBal()), 11);
				result += "\t" + current.getNotes();
			}
			index++;
		}
		if (result == "") {
			result = "No accounts were found";
		}
		else {
			result = "Date\t    TransactionID\tType\t\tPre-Balance\tPost-Balance\tNotes"
					+ "\n----\t    -------------\t----\t\t-----------\t------------\t----"
					+ result + "\n";

			System.out.println(result);
		}
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



	public Date getDate() {
		return Date.valueOf(date);
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

}
