package com.lyn.javacore;

import java.util.Date;

public class SuperClassDemo extends Date{
	private static final long serialVersionUID = 1110083246317579733L;

	public String getClassName(){
		System.out.println(super.getClass().getName());
		return super.getClass().getName();
	}
	
	public String getSuperClassName(){
		System.out.println(this.getClass().getSuperclass().getName());
		return this.getClass().getSuperclass().getName();
	}
	
	public boolean compareSuperAndThisClassName(){
		System.out.println(this.getClass());
		System.out.println(super.getClass());
		return this.getClass() == super.getClass();
	}
	
	public static void main(String[] args) {
		SuperClassDemo demo = new SuperClassDemo();
		boolean b = demo.compareSuperAndThisClassName();
		System.out.println(b);
	}
	
}
