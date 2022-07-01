package com.revature.p0.util;

public interface List<T> {
	public void add(T obj);
	public Object get(int index);
	public Object delete(int index);
	public int indexOf(T obj);	
	public int length();
}
