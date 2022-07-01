package com.revature.p0;

import com.revature.p0.data.AccountDAO;
import com.revature.p0.util.ArrayList;
import com.revature.p0.util.List;

public class Account extends P0Main {
	/*Class Variables*/
	private String command = "", type = "", accNotes = "";
	private int userID = -1, accID = -1;
	private double accBal = 0.0;
	private AccountDAO sql = new AccountDAO();
	private Transaction trans;
	
	/*Constructor*/
	public Account(int id) {
		setUserID(id);
	}

	/* Creates a new finance account for the user */
	protected Boolean accountCreate() {
		/*Local Variables*/
		Boolean accCreated = false;
		
		/*Function*/
		if (accountTypeInput()) {//Account was given a type/Was not canceled
			if (accountNameInput()) {
				if(sql.insert(this) != null) {
					accCreated = true;
				}
				else {
					accCreated = false;
				}
			}
			else {
				accCreated = false;
			}
		}
		else {
			accCreated = false;
		}
		
		/*Output*/
		return accCreated;
	}

	/*Prompt user for a type for the new account*/
	protected Boolean accountTypeInput() {
		/*Local Variables*/
		Boolean typeGiven = false, cancel = false;
		do {//Account Type Loop
			System.out.println("Select the account type: 'C': Checking, 'S': Savings. 'X' to cancel\n");
			command = getInput();
			switch(command.toUpperCase()) {
				case "C","S":
					type = command.toUpperCase();
					typeGiven = true;break;
				case "X":
					cancel = true;break;
				default:
					System.out.println("The command you entered is invalid\n");break;
				}
			}while(cancel == false && typeGiven == false);
		return typeGiven;
	}
	
	/*Prompt user for a name for the new account*/
	protected Boolean accountNameInput() {
		/*Local Variables*/
		Boolean nameGiven = false, cancel = false;
		
		/*Function*/
		do {//Account Name Loop
			System.out.println("Write a name to help you identify the account later. 'X' to cancel\n");
			accNotes = getInput();
			switch(accNotes.toUpperCase()) {
				case "X":
					cancel = true;break;
				default://Name Validation
					if (accNotes.length() < 25) {//Name is less than 25 characters
						if ((accNotes.contains("!") || accNotes.contains(";")) == false) {//Name does not contain illegal symbol. No SQL injection plz
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
		return nameGiven;
	}
	
	protected Boolean accountSelect() {
		/*Local Variables*/
		Boolean accSelected = false;
		List<Account> list = new ArrayList<Account>();
		Account current;
		int size = 0, records = 0, index = 0, inAccID = -1;
		String result = "";
		
		/*Function*/
		list = sql.findByUserID(userID);
		size = list.length();
		while (index < size) {
			if (list.get(index) != null) {
				current = (Account) list.get(index);
				result += "\n" + current.getAccID();
				result += "\t" + current.getType();
				result += "\t" + current.getAccNotes();
				result += "\t" + current.getAccBal();
			}
			index++;
		}
		if (result == "") {
			result = "No accounts were found";
			System.out.println(result);
			accSelected = false;
		}
		else {
			result = "AccountID\tType\tNotes\tBalance"
					+ "\n---------\t----\t-----\t-------"
					+ result + "\n";
			while (accSelected == false) {
				System.out.println(result);
				System.out.println("Please enter the id of the account you wish to access");
				command = getInput();
				try {
					inAccID = Integer.parseInt(command);
					current = sql.findByAccIDAndUserID(inAccID, userID);
					if (current != null){
						trans = new Transaction();
					}
					else {
						System.out.println("You do not have access to an account with an id of " + command);
						accSelected = false;
					}
				}
				catch (NumberFormatException e) {
					e.printStackTrace();
					System.out.println("The account id you entered is invalid");
					accSelected = false;
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		/*Output*/
		return accSelected;
	}

	/*Getters and Setters*/
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccNotes() {
		return accNotes;
	}

	public void setAccNotes(String accNotes) {
		this.accNotes = accNotes;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
	}

	public double getAccBal() {
		return accBal;
	}

	public void setAccBal(double accBal) {
		this.accBal = accBal;
	}
}
