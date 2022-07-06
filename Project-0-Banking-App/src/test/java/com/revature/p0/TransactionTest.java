package com.revature.p0;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.p0.data.AccountDAO;
public class TransactionTest {
	/*Class Variables*/
	Account acc;
	AccountDAO sql = new AccountDAO();
	InputStream replace = new ByteArrayInputStream("".getBytes());
	
	@InjectMocks
	Transaction trans;
	
	@Mock
	 static P0Main main;
	
	
	
	@BeforeEach
	/*User #208 is the test user, Account #162 is the main test account.
	 * Creating a new instance with current info before every test*/
	public void beforeEach() {
		acc = sql.findByAccIDAndUserID(162, 208);
		trans = new Transaction(acc.getAccID(), acc.getAccBal());
	}
	
	@Test
	@Disabled
	/*Withdraws remove the entered amount from the balance.
	 * The mock input is 1, so the balance should be 1 less after the withdraw
	 * The test will pass assuming the balance is above or equal to 1*/
	public void transDepositPassTest() {
		/*Local Variables*/
		double expected = (trans.getAccountBal() + 1),
				actual = 0.0;
		
		/*Mocks*/
		replace = new ByteArrayInputStream("1".getBytes());
		
		/*Function*/
		P0Main.key_inp = new Scanner(replace);
		trans.transaction("D");
		actual = trans.getAccountBal();
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Disabled
	/*Deposits only occur with numerical inputs
	 * The mock input is not a number, so the balance shouldn't change*/
	public void transDepositNonNumTest() {
		/*Local Variables*/
		double expected = trans.getAccountBal(),
				actual = 0.0;
		
		/*Mocks*/
		replace = new ByteArrayInputStream("Invalid Input".getBytes());
		
		/*Function*/
		P0Main.key_inp = new Scanner(replace);
		trans.transaction("D");
		actual = trans.getAccountBal();
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Disabled
	/*Deposits only occur with positive numbers
	 * The mock input is a negative number, so the balance shouldn't change*/
	public void transDepositNegativeNumTest() {
		/*Local Variables*/
		double expected = trans.getAccountBal(),
				actual = 0.0;
		
		/*Mocks*/
		replace = new ByteArrayInputStream("-1".getBytes());
		
		/*Function*/
		P0Main.key_inp = new Scanner(replace);
		trans.transaction("D");
		actual = trans.getAccountBal();
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Disabled
	/*Withdraws should only occur if the account has enough balance to cover it.
	 * The mock input is returning an amount that is 1 higher than the balance in the account*/
	public void transWithdrawOverDraftTest() {
		/*Local Variables*/
		double expected = trans.getAccountBal(),
				actual = 0.0;
		
		/*Mocks*/
		replace = new ByteArrayInputStream((Double.toString(expected + 1).getBytes()));
		
		/*Function*/
		P0Main.key_inp = new Scanner(replace);
		trans.transaction("W");
		actual = trans.getAccountBal();
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Disabled
	/*Withdraws remove the entered amount from the balance.
	 * The mock input is 1, so the balance should be 1 less after the withdraw
	 * The test will pass assuming the balance is above or equal to 1*/
	public void transWithdrawPassTest() {
		/*Local Variables*/
		double expected = (trans.getAccountBal() - 1),
				actual = 0.0;
		
		/*Mocks*/
		replace = new ByteArrayInputStream("1".getBytes());
		
		/*Function*/
		P0Main.key_inp = new Scanner(replace);
		trans.transaction("W");
		actual = trans.getAccountBal();
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Disabled
	/*Withdraws only occur with numerical inputs
	 * The mock input is not a number, so the balance shouldn't change*/
	public void transWithdrawNonNumTest() {
		/*Local Variables*/
		double expected = trans.getAccountBal(),
				actual = 0.0;
		
		/*Mocks*/
		replace = new ByteArrayInputStream("Invalid Input".getBytes());
		
		/*Function*/
		P0Main.key_inp = new Scanner(replace);
		trans.transaction("W");
		actual = trans.getAccountBal();
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	@Disabled
	/*Withdraws only occur with positive numbers
	 * The mock input is a negative number, so the balance shouldn't change*/
	public void transWithdrawNegativeNumTest() {
		/*Local Variables*/
		double expected = trans.getAccountBal(),
				actual = 0.0;
		
		/*Mocks*/
		replace = new ByteArrayInputStream("-1".getBytes());
		
		/*Function*/
		P0Main.key_inp = new Scanner(replace);
		trans.transaction("W");
		actual = trans.getAccountBal();
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	
	@Test
	/*Account #162 has multiple transactions as it was used in testing. Should return true*/
	public void transHistoryTest(){
		/*Local Variables*/
		Boolean expected = true, actual = false;
		
		/*Function*/
		actual = trans.transHistory(162);
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@AfterEach
	public void afterEach() {
		P0Main.key_inp = new Scanner(System.in);
	}
}
