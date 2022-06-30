package com.revature.p0;
import java.util.Scanner;
public class P0Main {	
	public static Scanner key_inp = new Scanner(System.in);
	public static void main(String Args[]) {
		LoginScreen login = new LoginScreen();
		login.home();
		key_inp.close();
	}
	
	public static String getInput() {
		String input = "";
		input = key_inp.nextLine().toString();
		return input;
	}
}
