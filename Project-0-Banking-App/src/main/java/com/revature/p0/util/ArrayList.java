package com.revature.p0.util;

public class ArrayList<T> implements List<T> {
	/*Class Variables*/
	T[] arrList = (T[]) new Object[10];
	T[] arrListTemp;

	/*Add a new object to the list*/
	@Override
	public void add(T obj) {
		/*Local Variables*/
		Boolean emptyFound = false;
		int index = 0;
		
		/*Function*/
		do {
			if (index <= 9) {//Index is within bounds of starting size
				if (arrList[index] == null) {//Current index is empty
					emptyFound = true;
					arrList[index] = obj;
				}
				else {//Current index is not empty
					index++;
				}
			}
			else if (arrList[arrList.length - 1] == null){//Final index of the array is empty
				emptyFound = true;
				arrList[arrList.length - 1] = obj;
			}
			else {
				arrListTemp = (T[]) new Object[arrList.length + 1];
				index = 0;
				for(T o : arrList)
				{
					arrListTemp[index] = o;
					index++;
				}
				arrList = (T[]) new Object[arrListTemp.length];
				index = 0;
				for(T o : arrListTemp)
				{
					arrList[index] = o;
					index++;
				}
				arrList[arrList.length - 1] = obj;
				emptyFound = true;
			}
		}while(emptyFound == false);
		System.out.println("Your object has been added to the list");
	}

	@Override
	public Object get(int index) {
		/*Local Variables*/
		T result;
		String error = "The index you requested does not exist in the array";
		
		/*Function*/
		if (index >= arrList.length || index < 0) {
			result = (T) error;
		}
		else {
			result = arrList[index];
		}
		return result;
	}

	@Override
	public Object delete(int omitIndex) {
		/*Local Variables*/
		Object result;
		int arrIndex = 0;
		String error = "The index you requested does not exist in the array";
		
		/*Function*/
		if (omitIndex >= arrList.length || omitIndex < 0) {
			result = error;
		}
		else {
			arrListTemp = (T[]) new Object[arrList.length];
			for (Object o: arrList){
				if (arrIndex >= omitIndex) {
					arrListTemp[arrIndex] = arrList[arrIndex + 1];
				}
				else {
					arrListTemp[arrIndex] = arrList[arrIndex];
				}
				arrIndex++;
			}
			arrList = arrListTemp;
			result = arrList;
		}			
		return result;
	}

	@Override
	public int indexOf(Object obj) {
		/*Local Variables*/
		int index = 0;
		Boolean exitArr = false;
		
		/*Function*/
		do {
			if (index >= arrList.length) {
				if(arrList[index] == obj) {
					exitArr = true;
				}
				else {
					index++;
				}
			}
			else { 
				System.out.println("Object not found");
				index = -1;
			}
		}while (exitArr == false);
		return index;
	}
	
	@Override
	public int length() {
		return arrList.length;
	}

}
