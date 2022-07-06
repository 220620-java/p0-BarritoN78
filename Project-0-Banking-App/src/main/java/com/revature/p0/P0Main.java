package com.revature.p0;
import java.io.File;
import java.util.Scanner;

import com.revature.p0.util.ArrayList;
import com.revature.p0.util.FileAccessObject;
import com.revature.p0.util.List;
public class P0Main {	
	public static Scanner key_inp = new Scanner(System.in);
	private static FileAccessObject fao = new FileAccessObject();
	private static File file = new File("exceptionLogger.txt");
	
	public static void main(String Args[]) {
		LoginScreen login = new LoginScreen();
		login.home();
		key_inp.close();
		exceptionLogger();
		exceptionLogReader();
	}
	
	public static String getInput() {
		String input = "";
		input = key_inp.nextLine().toString();
		return input;
	}
	
	/*Adds spaces to a string to make it a certain length. Useful for displaying data in command line*/
	public static String stringPadder(String input, int desiredLength) {
		int length = input.length(), loop = 0;
		while (loop < (desiredLength - length)) {
			input += " ";
			loop++;
		}
		return input;
	}
	
	public static void exceptionLogger(Exception e) {
		fao.addObject(file, e.getStackTrace());
	}
	
	public static void exceptionLogger() {
		fao.addObject(file, "End of Test");
	}
	
	public static void exceptionLogReader() {
		List<Object> eList = new ArrayList<>();
		eList.add(fao.readFile(file));
		System.out.println(eList);
	}
}
