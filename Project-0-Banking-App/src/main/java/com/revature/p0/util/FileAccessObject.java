package com.revature.p0.util;

import java.io.ObjectInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileWriter;

public class FileAccessObject {	
	public static void main(String... args) {
		/*Local Variables*/
		File testFile = new File("test.txt");
		List<Object> list = new ArrayList<>();
		FileAccessObject fao = new FileAccessObject();
		
		/*Function*/
		fao.addObject(testFile,"a String");
		list.add(fao.readFile(testFile));
		System.out.println(list);
	}
	
	public Object readFile(File file) {
		/*Local Variables*/
		Object obj = null;
		FileInputStream inStream = null;
		
		/*Function*/
		try {
			inStream = new FileInputStream(file); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (ObjectInputStream in = 
				new ObjectInputStream(inStream)) {
			obj = (Object) in.readObject();
			return obj;
		}
		catch(EOFException e) {
			e.printStackTrace();
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return "Failed to read file";
		}
	}
	
	public void addObject(File file, Object obj)
	{
		/*Local Variables*/
		FileOutputStream outStream = null;
		Serializable srlObj = null;
		
		/*Function*/
		try {
			outStream = new FileOutputStream(file);
			srlObj = (Serializable) obj; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (ObjectOutputStream out = new ObjectOutputStream(outStream);) {
			out.writeObject(srlObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
