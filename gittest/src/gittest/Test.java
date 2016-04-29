package gittest;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		try {
			
			String[] array = {"Str","str","a","abc","cba"};
			Arrays.sort(array);
			System.out.println(Arrays.toString(array));
			
			System.out.println((int)'S');
			System.out.println((int)'s');
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
