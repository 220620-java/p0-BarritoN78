package com.revature.p0;
import java.util.Scanner;
public class P0Main {	
	public static void main(String Args[]) {
		LoginScreen login = new LoginScreen();
		login.home();
	}
	
	public static String getInput() {
		Scanner key_inp = new Scanner(System.in);
		String input = "";
		input = key_inp.nextLine().toString();
		return input;
	}
}
