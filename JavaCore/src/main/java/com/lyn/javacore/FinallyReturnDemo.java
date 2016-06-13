package com.lyn.javacore;

public class FinallyReturnDemo {

	public int returnMethod(){
		return 1;
	}
	
	public int returnMethodWithException(){
		String str = "";
		str.charAt(0);
		return 2;
	}
	
	public int returnMethodWithTryCatch(){
		try {
			String str = "";
			str.charAt(0);
			return 3;
		} catch (Exception e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	@SuppressWarnings("finally")
	public int returnMethodWithTryCatchFinally(){
		try {
//			String str = "";
//			str.charAt(0);
			return 4;
		} catch (Exception e) {
			e.printStackTrace();
			return -4;
		} finally{
			return 44;
		}
	}
	
	public static void main(String[] args) {
		FinallyReturnDemo demo = new FinallyReturnDemo();
		System.out.println(demo.returnMethod());
//		System.out.println(demo.returnMethodWithException());
		System.out.println(demo.returnMethodWithTryCatch());
		System.out.println(demo.returnMethodWithTryCatchFinally());
	}
	
}
